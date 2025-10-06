package net.bustin.canuckmod.datagen;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.block.ModBlocks;
import net.bustin.canuckmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CanuckMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        saplingItem(ModBlocks.MAPLE_SAPLING);
        basicItem(ModItems.SAP_BUCKET.get());



    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID,"block/" + item.getId().getPath()));
    }

}
