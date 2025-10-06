package net.bustin.canuckmod.entity.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bustin.canuckmod.CanuckMod;
import net.bustin.canuckmod.entity.animations.BeaverAnimations;
import net.bustin.canuckmod.entity.animations.MooseAnimations;
import net.bustin.canuckmod.entity.custom.MooseEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class MooseModel<T extends MooseEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CanuckMod.MOD_ID, "moose"), "main");
	private final ModelPart root;
	private final ModelPart Upper;
	private final ModelPart Upperbody;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart Ears;
	private final ModelPart Beard;
	private final ModelPart Rightantler;
	private final ModelPart Leftantler;
	private final ModelPart Lowerbody;
	private final ModelPart Legs;
	private final ModelPart FR;
	private final ModelPart FL;
	private final ModelPart BR;
	private final ModelPart BL;

	public MooseModel(ModelPart root) {
		this.root = root.getChild("root");
		this.Upper = this.root.getChild("Upper");
		this.Upperbody = this.Upper.getChild("Upperbody");
		this.Neck = this.Upper.getChild("Neck");
		this.Head = this.Upper.getChild("Head");
		this.Ears = this.Head.getChild("Ears");
		this.Beard = this.Head.getChild("Beard");
		this.Rightantler = this.Head.getChild("Rightantler");
		this.Leftantler = this.Head.getChild("Leftantler");
		this.Lowerbody = this.root.getChild("Lowerbody");
		this.Legs = this.root.getChild("Legs");
		this.FR = this.Legs.getChild("FR");
		this.FL = this.Legs.getChild("FL");
		this.BR = this.Legs.getChild("BR");
		this.BL = this.Legs.getChild("BL");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, (float) Math.toRadians(-90.0F), 0.0F));

		PartDefinition Upper = root.addOrReplaceChild("Upper", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Upperbody = Upper.addOrReplaceChild("Upperbody", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -6.0F, -7.0F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -18.0F, 0.0F));

		PartDefinition Neck = Upper.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(36, 49).addBox(-4.0F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.002F)), PartPose.offset(-16.0F, -18.0F, 0.0F));

		PartDefinition cube_r1 = Neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 0).addBox(-2.6F, -2.0F, -4.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.5F, -3.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition Head = Upper.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(-19.0F, -18.0F, 0.0F));

		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 49).addBox(-12.0F, -2.5F, -3.0F, 12.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create(), PartPose.offset(19.0F, 18.0F, 0.0F));

		PartDefinition cube_r3 = Ears.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(76, 35).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.5F, -21.0F, 2.5F, 0.1238F, 0.3272F, 0.3695F));

		PartDefinition cube_r4 = Ears.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(74, 71).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.5F, -21.0F, -2.5F, -0.1238F, -0.3272F, 0.3695F));

		PartDefinition Beard = Head.addOrReplaceChild("Beard", CubeListBuilder.create(), PartPose.offset(1.0F, 15.0F, 0.0F));

		PartDefinition cube_r5 = Beard.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(54, 65).addBox(0.5F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(56, 47).addBox(0.5F, -1.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -8.5F, 1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r6 = Beard.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(72, 58).addBox(1.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(58, 65).addBox(1.5F, -1.0F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -9.5F, 1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r7 = Beard.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(68, 10).addBox(-2.5F, 0.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -11.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition Rightantler = Head.addOrReplaceChild("Rightantler", CubeListBuilder.create().texOffs(12, 61).addBox(-24.0F, -21.0F, 2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(19.0F, 18.0F, 0.0F));

		PartDefinition cube_r8 = Rightantler.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 72).addBox(-0.5767F, -0.1515F, -0.7813F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.809F, -26.1383F, 9.2026F, 1.2654F, 0.0259F, -0.3945F));

		PartDefinition cube_r9 = Rightantler.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(58, 77).addBox(-0.5767F, -1.5913F, 2.2757F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.809F, -26.1383F, 9.2026F, 0.8291F, 0.0259F, -0.3945F));

		PartDefinition cube_r10 = Rightantler.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(20, 77).addBox(-0.8787F, -0.7997F, -1.0015F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0879F, -25.7701F, 9.6157F, 1.2125F, 0.2245F, -0.8491F));

		PartDefinition cube_r11 = Rightantler.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(72, 53).addBox(-0.5768F, -0.1515F, -0.7813F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1387F, -27.2019F, 8.4216F, 1.3092F, 0.6405F, -0.2698F));

		PartDefinition cube_r12 = Rightantler.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(54, 77).addBox(-0.5768F, -1.5913F, 2.2757F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1387F, -27.2019F, 8.4216F, 0.8729F, 0.6405F, -0.2698F));

		PartDefinition cube_r13 = Rightantler.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(40, 76).addBox(-1.3533F, -0.7887F, 0.9563F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.1416F, -26.4336F, 5.1238F, -2.8063F, 1.1024F, 1.9986F));

		PartDefinition cube_r14 = Rightantler.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 71).addBox(-1.3533F, -2.4805F, 2.6749F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.1416F, -26.4336F, 5.1238F, 3.0406F, 1.1024F, 1.9986F));

		PartDefinition cube_r15 = Rightantler.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(74, 63).addBox(-0.4579F, -0.9794F, 0.5541F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.506F, -27.9729F, 6.3632F, 0.281F, 1.2961F, -1.4911F));

		PartDefinition cube_r16 = Rightantler.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(76, 41).addBox(-0.4579F, -2.4834F, 2.2298F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.506F, -27.9729F, 6.3632F, -0.1553F, 1.2961F, -1.4911F));

		PartDefinition cube_r17 = Rightantler.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(60, 72).addBox(-0.82F, -6.6631F, -1.1019F, 1.0F, 2.1227F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(68, 35).addBox(-0.82F, -4.5405F, -1.1019F, 1.0F, 4.8773F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.7194F, -19.8773F, 7.805F, -0.6745F, -1.169F, 0.7351F));

		PartDefinition cube_r18 = Rightantler.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(68, 13).addBox(0.0F, -9.0F, 0.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, -20.0F, 6.0F, -0.1426F, -0.6423F, 0.4777F));

		PartDefinition cube_r19 = Rightantler.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(68, 67).addBox(0.0F, -8.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, -21.0F, 6.0F, 0.0F, 0.0F, 0.3142F));

		PartDefinition cube_r20 = Rightantler.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(74, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -20.0F, 6.0F, 0.0F, 1.2217F, 0.0F));

		PartDefinition cube_r21 = Rightantler.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(26, 61).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -20.0F, 6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r22 = Rightantler.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(26, 65).addBox(-0.3533F, -1.2887F, -0.0437F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.13F)), PartPose.offsetAndRotation(-20.3409F, -20.2113F, 6.2222F, -2.3127F, 1.3242F, 2.5858F));

		PartDefinition cube_r23 = Rightantler.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(50, 72).addBox(-1.3533F, -0.7887F, -0.0437F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.3409F, -20.2113F, 5.2222F, 0.0F, -0.7418F, 0.0F));

		PartDefinition cube_r24 = Rightantler.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(22, 75).addBox(-1.3533F, -2.4805F, 2.6749F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.3409F, -20.2113F, 5.2222F, -0.4363F, -0.7418F, 0.0F));

		PartDefinition cube_r25 = Rightantler.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(56, 44).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.3409F, -20.2113F, 8.2222F, -0.4363F, -0.3491F, 0.0F));

		PartDefinition cube_r26 = Rightantler.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(40, 71).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -20.0F, 6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r27 = Rightantler.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(12, 67).addBox(-2.0F, -0.5F, -1.2F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-22.0F, -21.5F, 5.5F, 0.2791F, -0.4307F, -0.6295F));

		PartDefinition Leftantler = Head.addOrReplaceChild("Leftantler", CubeListBuilder.create().texOffs(60, 61).addBox(-24.0F, -21.0F, -7.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(19.0F, 18.0F, 0.0F));

		PartDefinition cube_r28 = Leftantler.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(72, 48).addBox(-0.5767F, -0.1515F, -3.2187F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.809F, -26.1383F, -9.2026F, -1.2654F, -0.0259F, -0.3945F));

		PartDefinition cube_r29 = Leftantler.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(74, 77).addBox(-0.5767F, -1.5913F, -3.2757F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.809F, -26.1383F, -9.2026F, -0.8291F, -0.0259F, -0.3945F));

		PartDefinition cube_r30 = Leftantler.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(48, 77).addBox(-0.8787F, -0.7997F, -0.9985F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0879F, -25.7701F, -9.6157F, -1.2125F, -0.2245F, -0.8491F));

		PartDefinition cube_r31 = Leftantler.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(74, 18).addBox(-0.5768F, -0.1515F, -3.2187F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1387F, -27.2019F, -8.4216F, -1.3092F, -0.6405F, -0.2698F));

		PartDefinition cube_r32 = Leftantler.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(70, 77).addBox(-0.5768F, -1.5913F, -3.2757F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1387F, -27.2019F, -8.4216F, -0.8729F, -0.6405F, -0.2698F));

		PartDefinition cube_r33 = Leftantler.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(12, 77).addBox(-1.3533F, -0.7887F, -3.9563F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.1416F, -26.4336F, -5.1238F, 2.8063F, -1.1024F, 1.9986F));

		PartDefinition cube_r34 = Leftantler.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(4, 71).addBox(-1.3533F, -2.4805F, -3.6749F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.1416F, -26.4336F, -5.1238F, -3.0406F, -1.1024F, 1.9986F));

		PartDefinition cube_r35 = Leftantler.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(74, 67).addBox(-0.4579F, -0.9794F, -3.5541F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.506F, -27.9729F, -6.3632F, -0.281F, -1.2961F, -1.4911F));

		PartDefinition cube_r36 = Leftantler.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(66, 77).addBox(-0.4579F, -2.4834F, -3.2298F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.506F, -27.9729F, -6.3632F, 0.1553F, -1.2961F, -1.4911F));

		PartDefinition cube_r37 = Leftantler.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(74, 58).addBox(-0.82F, -6.6631F, -1.8981F, 1.0F, 2.1227F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(32, 71).addBox(-0.82F, -4.5405F, -1.8981F, 1.0F, 4.8773F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.7194F, -19.8773F, -7.805F, 0.6745F, 1.169F, 0.7351F));

		PartDefinition cube_r38 = Leftantler.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(68, 24).addBox(0.0F, -9.0F, -2.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, -20.0F, -6.0F, 0.1426F, 0.6423F, 0.4777F));

		PartDefinition cube_r39 = Leftantler.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(26, 71).addBox(0.0F, -8.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, -21.0F, -6.0F, 0.0F, 0.0F, 0.3142F));

		PartDefinition cube_r40 = Leftantler.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(74, 31).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -20.0F, -6.0F, 0.0F, -1.2217F, 0.0F));

		PartDefinition cube_r41 = Leftantler.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(74, 27).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -20.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r42 = Leftantler.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(40, 65).addBox(-0.3533F, -1.2887F, -4.9563F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.13F)), PartPose.offsetAndRotation(-20.3409F, -20.2113F, -6.2222F, 2.3127F, -1.3242F, 2.5858F));

		PartDefinition cube_r43 = Leftantler.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(74, 13).addBox(-1.3533F, -0.7887F, -3.9563F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.3409F, -20.2113F, -5.2222F, 0.0F, 0.7418F, 0.0F));

		PartDefinition cube_r44 = Leftantler.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(62, 77).addBox(-1.3533F, -2.4805F, -3.6749F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.3409F, -20.2113F, -5.2222F, 0.4363F, 0.7418F, 0.0F));

		PartDefinition cube_r45 = Leftantler.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(22, 72).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.3409F, -20.2113F, -8.2222F, 0.4363F, 0.3491F, 0.0F));

		PartDefinition cube_r46 = Leftantler.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(72, 43).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -20.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r47 = Leftantler.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(54, 67).addBox(-2.0F, -0.5F, -2.8F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-22.0F, -21.5F, -5.5F, -0.2791F, 0.4307F, -0.6295F));

		PartDefinition Lowerbody = root.addOrReplaceChild("Lowerbody", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -5.0F, -6.5F, 15.0F, 10.0F, 13.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.5F, -17.5F, 0.0F));

		PartDefinition Legs = root.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition FR = Legs.addOrReplaceChild("FR", CubeListBuilder.create().texOffs(56, 20).addBox(-1.5F, 6.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(44, 27).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.49F)), PartPose.offset(-13.5F, -13.0F, 5.0F));

		PartDefinition FL = Legs.addOrReplaceChild("FL", CubeListBuilder.create().texOffs(56, 10).addBox(-1.5F, 6.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(43, 0).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.49F)), PartPose.offset(-13.5F, -13.0F, -5.0F));

		PartDefinition BR = Legs.addOrReplaceChild("BR", CubeListBuilder.create().texOffs(60, 43).addBox(-1.5F, 6.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(56, 31).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.49F)), PartPose.offset(8.5F, -13.0F, 5.0F));

		PartDefinition BL = Legs.addOrReplaceChild("BL", CubeListBuilder.create().texOffs(0, 73).addBox(-1.5F, 6.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 61).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.49F)), PartPose.offset(8.5F, -13.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(MooseAnimations.MOOSE_WALKING, limbSwing, limbSwingAmount, 10f, 2.5f);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -15f, 35);

		this.Head.yRot = headYaw * ((float)Math.PI / 180f);
		this.Head.xRot = -headPitch *  ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		root.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}