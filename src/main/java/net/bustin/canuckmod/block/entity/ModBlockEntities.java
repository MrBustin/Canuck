package net.bustin.canuckmod.block.entity;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CanuckMod.MOD_ID);

    public static final Supplier<BlockEntityType<SapTapBlockEntity>> SAP_TAP_BE =
            BLOCK_ENTITIES.register("sap_tap_be", () -> BlockEntityType.Builder.of(
                    SapTapBlockEntity::new, ModBlocks.SAP_TAP.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
