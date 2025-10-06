package net.bustin.canuckmod.worldgen.tree;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower MAPLE = new TreeGrower(CanuckMod.MOD_ID + ":maple",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MAPLE_KEY), Optional.empty());
}
