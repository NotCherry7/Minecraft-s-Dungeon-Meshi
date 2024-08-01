package net.notcherry.dungeonmod.entity.client.golem;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.notcherry.dungeonmod.entity.animations.ModAnimationDefinitions;
import net.notcherry.dungeonmod.entity.custom.HugeScorpionEntity;

public class GolemModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart golem;
	private final ModelPart head;

	public GolemModel(ModelPart root) {
		this.golem = root.getChild("golem");
		this.head = golem.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition golem = partdefinition.addOrReplaceChild("golem", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = golem.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(72, 24).addBox(-6.0F, 1.0F, -5.0F, 12.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -48.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -2.0F, -0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 1).addBox(2.0F, -2.0F, -0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -43.0F, -5.0F));

		PartDefinition chest = golem.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -41.0F, -7.0F, 18.0F, 15.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = chest.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -26.0F, -6.0F, 16.0F, 13.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_bulk = golem.addOrReplaceChild("left_bulk", CubeListBuilder.create().texOffs(42, 40).addBox(9.0F, -40.0F, -7.0F, 8.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_middle = left_bulk.addOrReplaceChild("left_middle", CubeListBuilder.create().texOffs(64, 0).addBox(9.0F, -26.0F, -6.0F, 8.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_hand = left_middle.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(86, 41).addBox(10.0F, -14.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_bulk = golem.addOrReplaceChild("right_bulk", CubeListBuilder.create().texOffs(0, 54).addBox(-17.0F, -40.0F, -7.0F, 8.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_middle = right_bulk.addOrReplaceChild("right_middle", CubeListBuilder.create().texOffs(44, 68).addBox(-17.0F, -26.0F, -6.0F, 8.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_hand = right_middle.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(98, 75).addBox(-16.0F, -14.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = golem.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition cube_r2 = left_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(72, 80).mirror().addBox(0.0F, -2.0F, -6.0F, 7.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -13.25F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition left_foot = left_leg.addOrReplaceChild("left_foot", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r3 = left_foot.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 92).mirror().addBox(0.0F, 6.0F, -6.0F, 7.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition right_leg = golem.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition cube_r4 = right_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(72, 80).addBox(-7.0F, -2.0F, -6.0F, 7.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -13.25F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition right_foot = right_leg.addOrReplaceChild("right_foot", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -7.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r5 = right_foot.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 92).addBox(-7.0F, 6.0F, -6.0F, 7.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

//		this.animateWalk(ModAnimationDefinitions.HUGE_SCORPION_WALK, limbSwing, limbSwingAmount, 6f, 4f);
//		this.animate(((HugeScorpionEntity) entity).idleAnimationState, ModAnimationDefinitions.HUGE_SCORPION_IDLE, ageInTicks, 1f);
//		this.animate(((HugeScorpionEntity) entity).attackAnimationState, ModAnimationDefinitions.HUGE_SCORPION_ATTACK_CLAW, ageInTicks, 1f);
//		this.animate(((HugeScorpionEntity) entity).retaliateAnimationState, ModAnimationDefinitions.HUGE_SCORPION_ATTACK_BARB, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -15.0F, 15.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -5.0F, 5.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		golem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return golem;
	}
}