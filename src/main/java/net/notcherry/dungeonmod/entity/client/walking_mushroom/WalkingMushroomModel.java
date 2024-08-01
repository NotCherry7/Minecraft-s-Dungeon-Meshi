package net.notcherry.dungeonmod.entity.client.walking_mushroom;


import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.AnimationState;
import net.notcherry.dungeonmod.entity.animations.ModAnimationDefinitions;
import net.notcherry.dungeonmod.entity.custom.MandrakeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.ModelPart;
import net.notcherry.dungeonmod.entity.custom.WalkingMushroomEntity;

public class WalkingMushroomModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart walking_mushroom;
    private final ModelPart cap;

    public WalkingMushroomModel(ModelPart root) {
        this.walking_mushroom = root.getChild("walking_mushroom");
        this.cap = walking_mushroom.getChild("cap");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition walking_mushroom = partdefinition.addOrReplaceChild("walking_mushroom", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition left_leg = walking_mushroom.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(1.5F, 3.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-2.5F, 3.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 35).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(15, 39).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(37, 37).addBox(-1.5F, 3.0F, 1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 13.0F, 0.5F));

        PartDefinition right_leg = walking_mushroom.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 11).addBox(-0.5F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 27).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(1.5F, 3.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-2.5F, 3.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, 3.0F, -2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 36).addBox(-1.5F, 3.0F, 1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 13.0F, -0.5F));

        PartDefinition cap = walking_mushroom.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 18).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -3.0F, -5.0F, 12.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(23, 34).addBox(-5.0F, -3.0F, 5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 7).addBox(-5.0F, -3.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 27).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 11).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 36).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

        PartDefinition body = walking_mushroom.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(34, 0).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.WALKING_MUSHROOM_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((WalkingMushroomEntity) entity).idleAnimationState, ModAnimationDefinitions.WALKING_MUSHROOM_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.cap.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.cap.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        walking_mushroom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return walking_mushroom;
    }
}