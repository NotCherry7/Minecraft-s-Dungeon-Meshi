package net.notcherry.dungeonmod.entity.client.huge_scorpion;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.notcherry.dungeonmod.entity.animations.ModAnimationDefinitions;
import net.notcherry.dungeonmod.entity.custom.HugeScorpionEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.ModelPart;

public class HugeScorpionModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart huge_scorpion;
	private final ModelPart head;

	public HugeScorpionModel(ModelPart root) {
		this.huge_scorpion = root.getChild("huge_scorpion");
		this.head = huge_scorpion.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition huge_scorpion = partdefinition.addOrReplaceChild("huge_scorpion", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = huge_scorpion.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -3.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -4.0F, -2.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition left_bulk = head.addOrReplaceChild("left_bulk", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1314F, 0.0865F, 0.0114F));

		PartDefinition cube_r2 = left_bulk.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 9).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.25F, 2.75F, -0.25F, 0.1034F, 0.5648F, 0.0555F));

		PartDefinition cube_r3 = left_bulk.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 7).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 39).addBox(-1.0F, -2.0F, -7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.5F, -3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition left_claw = left_bulk.addOrReplaceChild("left_claw", CubeListBuilder.create(), PartPose.offset(5.0F, -1.0F, -7.0F));

		PartDefinition cube_r4 = left_claw.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 12).addBox(-1.0F, 0.3334F, -3.3638F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition right_bulk = head.addOrReplaceChild("right_bulk", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1314F, -0.0865F, -0.0114F));

		PartDefinition cube_r5 = right_bulk.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 9).mirror().addBox(-2.0F, -4.0F, -4.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.25F, 2.75F, -0.25F, 0.1034F, -0.5648F, -0.0555F));

		PartDefinition cube_r6 = right_bulk.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F, -2.0F, -7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 7).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.5F, -3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition right_claw = right_bulk.addOrReplaceChild("right_claw", CubeListBuilder.create(), PartPose.offset(-5.0F, -1.0F, -7.0F));

		PartDefinition cube_r7 = right_claw.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(16, 12).addBox(-1.0F, 0.3334F, -3.3638F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition body = huge_scorpion.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_legs = body.addOrReplaceChild("left_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition l1 = left_legs.addOrReplaceChild("l1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r8 = l1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(30, 39).addBox(-5.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.75F, 0.5F, -2.75F, 0.0F, 0.4363F, 0.0F));

		PartDefinition l2 = left_legs.addOrReplaceChild("l2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r9 = l2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(30, 39).addBox(-5.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 0.5F, 0.25F, 0.0F, 0.2182F, 0.0F));

		PartDefinition l3 = left_legs.addOrReplaceChild("l3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = l3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 39).addBox(-5.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, 0.5F, 3.5F, 0.0F, -0.0873F, 0.0F));

		PartDefinition l4 = left_legs.addOrReplaceChild("l4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r11 = l4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(30, 39).addBox(-5.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.25F, 0.5F, 6.5F, 0.0F, -0.3054F, 0.0F));

		PartDefinition right_legs = body.addOrReplaceChild("right_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition r1 = right_legs.addOrReplaceChild("r1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r12 = r1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 43).addBox(-2.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.75F, 0.5F, -2.75F, 0.0F, -0.4363F, 0.0F));

		PartDefinition r2 = right_legs.addOrReplaceChild("r2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r13 = r2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(30, 43).addBox(-2.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.5F, 0.25F, 0.0F, -0.2182F, 0.0F));

		PartDefinition r3 = right_legs.addOrReplaceChild("r3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r14 = r3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(30, 43).addBox(-2.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 0.5F, 3.5F, 0.0F, 0.0873F, 0.0F));

		PartDefinition r4 = right_legs.addOrReplaceChild("r4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r15 = r4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(30, 43).addBox(-2.0F, -3.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.25F, 0.5F, 6.5F, 0.0F, 0.3054F, 0.0F));

		PartDefinition tail = huge_scorpion.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 17).addBox(-3.0F, -1.8755F, 0.2867F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition tailmiddle = tail.addOrReplaceChild("tailmiddle", CubeListBuilder.create().texOffs(22, 26).addBox(-2.0F, -7.5F, 0.0F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.6255F, 4.2867F));

		PartDefinition tailtop = tailmiddle.addOrReplaceChild("tailtop", CubeListBuilder.create().texOffs(0, 11).addBox(-2.0F, -1.5F, -7.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 1.0F));

		PartDefinition barb = tailtop.addOrReplaceChild("barb", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -0.5F, -4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 31).addBox(-1.0F, 1.5F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, -2).addBox(0.0F, 3.5F, -4.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(0.0F, 4.5F, -5.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.HUGE_SCORPION_WALK, limbSwing, limbSwingAmount, 6f, 4f);
		this.animate(((HugeScorpionEntity) entity).idleAnimationState, ModAnimationDefinitions.HUGE_SCORPION_IDLE, ageInTicks, 1f);
		this.animate(((HugeScorpionEntity) entity).attackAnimationState, ModAnimationDefinitions.HUGE_SCORPION_ATTACK_CLAW, ageInTicks, 1f);
		this.animate(((HugeScorpionEntity) entity).retaliateAnimationState, ModAnimationDefinitions.HUGE_SCORPION_ATTACK_BARB, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -15.0F, 15.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -5.0F, 5.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		huge_scorpion.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return huge_scorpion;
	}
}