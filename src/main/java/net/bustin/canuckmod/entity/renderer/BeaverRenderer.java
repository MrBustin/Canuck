package net.bustin.canuckmod.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.custom.BeaverEntity;
import net.bustin.canuckmod.entity.model.BeaverModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BeaverRenderer extends MobRenderer<BeaverEntity, BeaverModel<BeaverEntity>> {
    public BeaverRenderer(EntityRendererProvider.Context context) {
        super(context, new BeaverModel<>(context.bakeLayer(BeaverModel.LAYER_LOCATION)),0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(BeaverEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID, "textures/entity/beaver/beaver.png");
    }

    @Override
    public void render(BeaverEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
