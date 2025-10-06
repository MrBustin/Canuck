package net.bustin.canuckmod.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.custom.MooseEntity;
import net.bustin.canuckmod.entity.model.MooseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MooseRenderer extends MobRenderer<MooseEntity, MooseModel<MooseEntity>> {
    public MooseRenderer(EntityRendererProvider.Context context) {
        super(context, new MooseModel<>(context.bakeLayer(MooseModel.LAYER_LOCATION)),0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MooseEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID, "textures/entity/moose/moose.png");
    }

    @Override
    public void render(MooseEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
