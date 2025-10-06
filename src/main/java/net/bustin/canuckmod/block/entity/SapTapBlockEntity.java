package net.bustin.canuckmod.block.entity;

import net.bustin.canuckmod.block.custom.SapTapBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.bustin.canuckmod.block.custom.SapTapBlock.FULL_BUCKET;
import static net.bustin.canuckmod.block.custom.SapTapBlock.HAS_BUCKET;


public class SapTapBlockEntity extends BlockEntity {
    private int sapTimer = 0;
    public static final int MAX_TIMER = 200; // 10 seconds at 20 ticks/sec

    public SapTapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SAP_TAP_BE.get(), pos, state);
    }

    // === SERVER SIDE LOGIC ===
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(SapTapBlock.HAS_BUCKET)) {
            sapTimer++;
            if (sapTimer >= MAX_TIMER && !state.getValue(FULL_BUCKET)) {
                level.setBlock(pos, state.setValue(FULL_BUCKET, true), 3);
            }
        } else {
            sapTimer = 0;
            if (state.getValue(FULL_BUCKET)) {
                level.setBlock(pos, state.setValue(FULL_BUCKET, false), 3);
            }
        }
    }

    // === CLIENT SIDE PARTICLES ===
    public void clientTick(Level level, BlockPos pos, BlockState state) {

        if (state.getValue(HAS_BUCKET)) {
            if (level.random.nextFloat() < 0.01f) {
                double x = pos.getX() + 0.5;
                double y = pos.getY() + 0.45;
                double z = pos.getZ() + 0.5;

                double motionY = -0.05;
                level.addParticle(ParticleTypes.FALLING_HONEY, x, y, z, 0, motionY, 0);
            }
        }
    }

    public int getSapTimer() {
        return sapTimer;
    }

    public void resetSapTimer() {
        sapTimer = 0;
    }

    // === SAVE/LOAD ===
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("SapTimer", sapTimer);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        sapTimer = tag.getInt("SapTimer");
    }
}
