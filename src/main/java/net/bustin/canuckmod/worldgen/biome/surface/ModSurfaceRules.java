package net.bustin.canuckmod.worldgen.biome.surface;

import net.bustin.canuckmod.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource GRASS = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);

    public static SurfaceRules.RuleSource makeRules() {
        // Only place grass if at or above water
        SurfaceRules.ConditionSource atOrAboveWater = SurfaceRules.waterBlockCheck(-1, 0);

        // Top layer: grass only above water
        SurfaceRules.RuleSource topLayer = SurfaceRules.ifTrue(atOrAboveWater, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS));

        // Filler layer: 1–3 blocks of dirt below grass
        SurfaceRules.RuleSource fillerLayer = SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT);

        // Base layer: stone for everything else
        SurfaceRules.RuleSource baseLayer = STONE;

        // Combine top, filler, and base
        SurfaceRules.RuleSource mapleSurface = SurfaceRules.sequence(topLayer, fillerLayer, baseLayer);

        // Apply only in Maple Heights biome
        SurfaceRules.RuleSource mapleBiomeSurface = SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.MAPLE_HEIGHTS), mapleSurface);

        // Only affect blocks above preliminary surface (avoids messing with caves)
        return SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), mapleBiomeSurface);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
