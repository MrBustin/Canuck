package net.bustin.canuckmod.item;

import net.bustin.canuckmod.CanuckMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CanuckMod.MOD_ID);

    public static  final DeferredItem<Item> SAP_BUCKET = ITEMS.register("sap_bucket",
            ()-> new Item(new Item.Properties().stacksTo(1)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
