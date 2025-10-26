package net.bustin.canuckmod.util;

import net.bustin.canuckmod.entity.custom.BeaverEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class ChopTreeGoal extends Goal {
    private final BeaverEntity beaver;
    private BlockPos targetTree;
    private int chopTimer;
    private int searchCooldown;

    public ChopTreeGoal(BeaverEntity beaver) {
        this.beaver = beaver;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Only recheck every second (20 ticks)
        if (searchCooldown-- > 0) return false;
        searchCooldown = 20;

        targetTree = findNearbyTree();
        return targetTree != null;
    }

    @Override
    public boolean canContinueToUse() {
        return targetTree != null && beaver.level().getBlockState(targetTree).is(BlockTags.LOGS);
    }

    @Override
    public void start() {
        chopTimer = 0;
    }

    @Override
    public void stop() {
        targetTree = null;
        chopTimer = 0;
    }

    @Override
    public void tick() {
        if (targetTree == null) return;

        beaver.getLookControl().setLookAt(targetTree.getX(), targetTree.getY(), targetTree.getZ());
        beaver.getNavigation().moveTo(targetTree.getX(), targetTree.getY(), targetTree.getZ(), 1.0);

        if (beaver.blockPosition().closerThan(targetTree, 2.5)) {
            chopTimer++;

            // Spawn particles every few ticks to show chewing
            if (!beaver.level().isClientSide && chopTimer % 5 == 0) {
                beaver.level().levelEvent(2001, targetTree, Block.getId(beaver.level().getBlockState(targetTree)));
            }

            // After enough chewing time, break the tree
            if (chopTimer > 150) {
                breakTree(targetTree);
                targetTree = null;
            }
        }
    }

    private BlockPos findNearbyTree() {
        Level level = beaver.level();
        BlockPos beaverPos = beaver.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(beaverPos.offset(-8, -2, -8), beaverPos.offset(8, 6, 8))) {
            BlockState state = level.getBlockState(pos);
            if (!state.is(BlockTags.LOGS)) continue;

            // Skip if log is too high off the ground (branches)
            if (pos.getY() - beaverPos.getY() > 3) continue;

            // Find trunk base (keep walking down)
            BlockPos base = pos;
            while (level.getBlockState(base.below()).is(BlockTags.LOGS)) {
                base = base.below();
            }

            // Must be touching dirt/grass/sand/etc.
            BlockPos belowBase = base.below();
            BlockState belowState = level.getBlockState(belowBase);
            if (!belowState.is(BlockTags.DIRT) && !belowState.is(BlockTags.SAND)) continue;

            // Must have nearby leaves (so it's a tree)
            boolean hasLeaves = false;
            for (BlockPos nearby : BlockPos.betweenClosed(base.offset(-4, -1, -4), base.offset(4, 8, 4))) {
                if (level.getBlockState(nearby).is(BlockTags.LEAVES)) {
                    hasLeaves = true;
                    break;
                }
            }

            if (!hasLeaves) continue;
            return base.immutable();
        }
        return null;
    }

    private void breakLog(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) {
            // Spawn breaking particles while the beaver is working on the log
            level.addParticle(
                    new BlockParticleOption(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    (level.random.nextDouble() - 0.5) * 0.1,
                    (level.random.nextDouble() - 0.5) * 0.1,
                    (level.random.nextDouble() - 0.5) * 0.1
            );
            return;
        }

        // Only break the block on the server side
        level.destroyBlock(pos, true);
    }

    private void breakTree(BlockPos start) {
        Level level = beaver.level();
        Queue<BlockPos> toCheck = new LinkedList<>();
        Set<BlockPos> visited = new HashSet<>();
        toCheck.add(start);

        while (!toCheck.isEmpty()) {
            BlockPos current = toCheck.poll();
            if (!visited.add(current)) continue;
            if (!level.getBlockState(current).is(BlockTags.LOGS)) continue;
            if (visited.size() > 200) break;

            // ✅ Spawn particles + play sound
            if (!level.isClientSide()) {
                level.levelEvent(2001, current, Block.getId(level.getBlockState(current)));
            }

            level.destroyBlock(current, true);

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        if (dx == 0 && dy == 0 && dz == 0) continue;
                        toCheck.add(current.offset(dx, dy, dz));
                    }
                }
            }
        }
    }
}
