package net.bustin.canuckmod.worldgen.biome;

import net.bustin.canuckmod.CanuckMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID, "overworld"), 1));
    }
}
