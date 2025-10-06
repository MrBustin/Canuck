package net.bustin.canuckmod.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.animations.BeaverAnimations;
import net.bustin.canuckmod.entity.custom.BeaverEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BeaverModel<T extends BeaverEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID, "beaver"), "main");
    private final ModelPart root;
    private final ModelPart Body;
    private final ModelPart Tail;
    private final ModelPart Head;
    private final ModelPart Snout;
    private final ModelPart Ears;
    private final ModelPart Legs;
    private final ModelPart FR;
    private final ModelPart FL;
    private final ModelPart BR;
    private final ModelPart BL;

    public BeaverModel(ModelPart root) {
        this.root = root.getChild("bone");
        this.Body = this.root.getChild("Body");
        this.Tail = this.root.getChild("Tail");
        this.Head = this.root.getChild("Head");
        this.Snout = this.Head.getChild("Snout");
        this.Ears = this.Head.getChild("Ears");
        this.Legs = this.root.getChild("Legs");
        this.FR = this.Legs.getChild("FR");
        this.FL = this.Legs.getChild("FL");
        this.BR = this.Legs.getChild("BR");
        this.BL = this.Legs.getChild("BL");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, (float)Math.PI, 0.0F));

        PartDefinition Body = bone.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.5F, -6.0F, 8.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Tail = bone.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, -4.2996F, -5.8123F));

        PartDefinition cube_r1 = Tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 36).addBox(-1.0F, -0.5F, -8.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-2.0F, -0.5F, -7.5F, 5.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.2004F, 0.3123F, 0.3927F, 0.0F, 0.0F));

        PartDefinition Head = bone.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(26, 20).addBox(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 7.0F));

        PartDefinition Snout = Head.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(24, 36).addBox(-1.5F, -5.5F, 12.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 37).addBox(-1.0F, -3.5F, 12.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, -7.0F));

        PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = Ears.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 37).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, 2.0F, 0.0F, -0.4363F, 0.0F));

        PartDefinition cube_r3 = Ears.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 37).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 2.0F, 0.0F, 0.4363F, 0.0F));

        PartDefinition Legs = bone.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition FR = Legs.addOrReplaceChild("FR", CubeListBuilder.create().texOffs(34, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(4.0F, -3.0F, 5.0F));

        PartDefinition FL = Legs.addOrReplaceChild("FL", CubeListBuilder.create().texOffs(24, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(-4.0F, -3.0F, 5.0F));

        PartDefinition BR = Legs.addOrReplaceChild("BR", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.001F))
                .texOffs(6, 37).addBox(-1.0F, 3.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, -4.0F));

        PartDefinition BL = Legs.addOrReplaceChild("BL", CubeListBuilder.create().texOffs(12, 29).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.001F))
                .texOffs(0, 37).addBox(-1.0F, 3.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(BeaverEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(BeaverAnimations.BEAVER_WALKING, limbSwing, limbSwingAmount, 10f, 2.5f);
        this.animate(entity.idleAnimationState, BeaverAnimations.BEAVER_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.Head.yRot = headYaw * ((float)Math.PI / 180f);
        this.Head.xRot = -headPitch *  ((float)Math.PI / 180f);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, buffer, packedLight, packedOverlay, color);
    }

    public ModelPart root() {
        return root;
    }
}
