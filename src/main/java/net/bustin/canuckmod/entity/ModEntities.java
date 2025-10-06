package net.bustin.canuckmod.entity;

import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.animations.BeaverAnimations;
import net.bustin.canuckmod.entity.custom.BeaverEntity;
import net.bustin.canuckmod.entity.custom.MooseEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CanuckMod.MOD_ID);


    public static final Supplier<EntityType<BeaverEntity>> BEAVER =
            ENTITY_TYPES.register("beaver", ()-> EntityType.Builder.of(BeaverEntity::new, MobCategory.CREATURE)
                    .sized(1.0f,0.5f).build("beaver"));

    public static final Supplier<EntityType<MooseEntity>> MOOSE =
            ENTITY_TYPES.register("moose", ()-> EntityType.Builder.of(MooseEntity::new, MobCategory.CREATURE)
                    .sized(1.25f, 1.5f).build("moose"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
