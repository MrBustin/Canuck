package net.bustin.canuckmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.bustin.canuckmod.block.entity.ModBlockEntities;
import net.bustin.canuckmod.block.entity.SapTapBlockEntity;
import net.bustin.canuckmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SapTapBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final MapCodec<SapTapBlock> CODEC = simpleCodec(SapTapBlock::new);
    private static final VoxelShape SHAPE_EAST = Block.box(6.0, 9.0, 9.0, 10.0, 13.0, 16.0);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0.0, 9.0, 6.0, 7.0, 13.0, 10.0);
    private static final VoxelShape SHAPE_WEST = Block.box(6.0, 9.0, 0.0, 10.0, 13.0, 7.0);
    private static final VoxelShape SHAPE_NORTH = Block.box(9.0, 9.0, 6.0, 16.0, 13.0, 10.0);

    private static final VoxelShape BUCKET_SHAPE = Block.box(6.5, 3.0, 6.5, 9.5, 4, 9.5);

    public static final BooleanProperty HAS_BUCKET = BooleanProperty.create("has_bucket");

    public static final BooleanProperty FULL_BUCKET = BooleanProperty.create("full_bucket");



    public SapTapBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(HAS_BUCKET, false)
                        .setValue(FULL_BUCKET, false)
        );
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape baseShape = switch(state.getValue(FACING)) {
            case EAST  -> SHAPE_EAST;
            case WEST  -> SHAPE_WEST;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case DOWN, UP -> Shapes.empty();
        };

        // If bucket is attached, combine base shape with bucket shape
        if (state.getValue(HAS_BUCKET)) {
            return Shapes.or(baseShape, BUCKET_SHAPE);
        } else {
            return baseShape;
        }
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        // only allow attaching to a horizontal face
        if (!clickedFace.getAxis().isHorizontal()) return null;

        BlockPos placePos = context.getClickedPos();
        BlockPos attachedPos = placePos.relative(clickedFace.getOpposite());
        BlockState attachedState = context.getLevel().getBlockState(attachedPos);

        // allow placement only if the block we're attaching to is a log
        if (attachedState.is(BlockTags.LOGS)) {
            Direction rotated = clickedFace.getClockWise();
            return this.defaultBlockState().setValue(FACING, rotated).setValue(HAS_BUCKET, false);
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HAS_BUCKET, FULL_BUCKET);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                           BlockPos pos, Player player, InteractionHand hand,
                                           BlockHitResult hit) {
        if (stack.is(Items.BUCKET) && !state.getValue(HAS_BUCKET)) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(HAS_BUCKET, true), 3);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1); // consume bucket
                }
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        // remove the bucket on empty hand right-click
        if (stack.isEmpty() && state.getValue(HAS_BUCKET)) {
            if (!level.isClientSide) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof SapTapBlockEntity sapTap) {
                    level.setBlock(pos, state.setValue(HAS_BUCKET, false), 3);

                    ItemStack result;
                    if (sapTap.getSapTimer() >= SapTapBlockEntity.MAX_TIMER) {
                        result = new ItemStack(ModItems.SAP_BUCKET.get()); // give sap bucket
                        sapTap.resetSapTimer();
                        level.setBlock(pos, state.setValue(FULL_BUCKET, false).setValue(HAS_BUCKET, false), 3);
                    } else {
                        result = new ItemStack(Items.BUCKET); // give empty bucket
                    }

                    if (!player.getAbilities().instabuild) {
                        player.addItem(result);
                    }
                }
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SapTapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ModBlockEntities.SAP_TAP_BE.get()
                ? (lvl, pos, blockState, be) -> {
            if (lvl.isClientSide) {
                ((SapTapBlockEntity) be).clientTick(lvl, pos, blockState);
            } else {
                ((SapTapBlockEntity) be).serverTick(lvl, pos, blockState);
            }
        }
                : null;
    }
}
