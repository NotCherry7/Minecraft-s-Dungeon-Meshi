package net.notcherry.dungeonmod.entity.client.mandrake;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.notcherry.dungeonmod.entity.animations.ModAnimationDefinitions;
import net.notcherry.dungeonmod.entity.custom.MandrakeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.ModelPart;


public class MandrakeModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart mandrake;

	public MandrakeModel(ModelPart root) {
		this.mandrake = root.getChild("mandrake");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition mandrake = partdefinition.addOrReplaceChild("mandrake", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = mandrake.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -16.25F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(7, 8).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.9F, 0.975F, -0.7854F, 0.0F, -1.5708F));

		PartDefinition top_c_leaves = mandrake.addOrReplaceChild("top_c_leaves", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r2 = top_c_leaves.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.25F, 0.5F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r3 = top_c_leaves.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.25F, -0.5F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r4 = top_c_leaves.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(6, 4).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.25F, -0.5F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r5 = top_c_leaves.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(4, 8).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -8.25F, -0.5F, 0.0F, 0.0F, 0.6109F));

		PartDefinition middle_body = mandrake.addOrReplaceChild("middle_body", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, -14.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bottom_body = mandrake.addOrReplaceChild("bottom_body", CubeListBuilder.create().texOffs(8, 5).addBox(-0.5F, -12.25F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eyes = mandrake.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition cube_r6 = eyes.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -8.25F, 0.5F, -0.1745F, -0.3491F, 0.0F));

		PartDefinition cube_r7 = eyes.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -8.25F, 0.5F, -0.1745F, 0.3491F, 0.0F));

		PartDefinition top_leaves = mandrake.addOrReplaceChild("top_leaves", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r8 = top_leaves.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(6, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.25F, 0.5F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r9 = top_leaves.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(8, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.25F, -0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r10 = top_leaves.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.25F, -0.5F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r11 = top_leaves.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -8.25F, -0.5F, 0.0F, 0.0F, 0.9599F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
//		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

//		this.animateWalk(ModAnimationDefinitions.MANDRAKE, limbSwing, limbSwingAmount, 2f, 2.5f);
//		this.animate(((MandrakeEntity) entity).idleAnimationState, ModAnimationDefinitions.MANDRAKE_IDLE, ageInTicks, 2f);
//		this.animate(((MandrakeEntity) entity).attackAnimationState, ModAnimationDefinitions.MANDRAKE_SCREAM, ageInTicks, 1f);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mandrake.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return mandrake;
	}
}