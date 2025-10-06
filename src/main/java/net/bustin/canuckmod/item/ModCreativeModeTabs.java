package net.bustin.canuckmod.item;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CanuckMod.MOD_ID);

    public static final Supplier<CreativeModeTab> CANUCKMOD_ITEMS = CREATIVE_MODE_TABS.register("canuck_items_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(Items.ALLIUM))
                    .title(Component.literal("Canuck Items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.MAPLE_LEAVES);
                        output.accept(ModBlocks.MAPLE_LOG);
                        output.accept(ModBlocks.STRIPPED_MAPLE_LOG);
                        output.accept(ModBlocks.STRIPPED_MAPLE_WOOD);
                        output.accept(ModBlocks.MAPLE_WOOD);
                        output.accept(ModBlocks.MAPLE_PLANKS);
                        output.accept(ModBlocks.MAPLE_SAPLING);

                        output.accept(ModBlocks.SAP_TAP);
                        output.accept(ModItems.SAP_BUCKET);




                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
