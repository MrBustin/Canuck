package net.bustin.canuckmod.worldgen;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.ModEntities;
import net.bustin.canuckmod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {
    public static ResourceKey<BiomeModifier> ADD_TREE_MAPLE = registerKey("add_tree_maple");
    public static final ResourceKey<BiomeModifier> SPAWN_MOOSE = registerKey("spawn_moose");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        var mapleHeights = biomes.getOrThrow(ModBiomes.MAPLE_HEIGHTS);

        context.register(SPAWN_MOOSE, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(mapleHeights),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MOOSE.get(), 20, 2, 4))
        ));


    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID, name));
    }
}
