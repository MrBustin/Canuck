package net.bustin.canuckmod.event;


import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.ModEntities;
import net.bustin.canuckmod.entity.custom.BeaverEntity;
import net.bustin.canuckmod.entity.custom.MooseEntity;
import net.bustin.canuckmod.entity.model.BeaverModel;
import net.bustin.canuckmod.entity.model.MooseModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = CanuckMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BeaverModel.LAYER_LOCATION, BeaverModel::createBodyLayer);
        event.registerLayerDefinition(MooseModel.LAYER_LOCATION, MooseModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BEAVER.get(), BeaverEntity.createAttributes().build());
        event.put(ModEntities.MOOSE.get(), MooseEntity.createAttributes().build());
    }
}
