package net.bustin.canuckmod.datagen;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CanuckMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE);


        tag(BlockTags.NEEDS_IRON_TOOL);


        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MAPLE_LOG.get())
                .add(ModBlocks.MAPLE_WOOD.get())
                .add(ModBlocks.STRIPPED_MAPLE_LOG.get())
                .add(ModBlocks.STRIPPED_MAPLE_WOOD.get());
    }
}
