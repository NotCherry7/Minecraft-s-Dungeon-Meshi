package net.notcherry.dungeonmod.entity.client.spells;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.client.ModModelLayers;
import net.notcherry.dungeonmod.entity.client.walking_mushroom.WalkingMushroomModel;
import net.notcherry.dungeonmod.entity.custom.LightOrbSpell;
import net.notcherry.dungeonmod.entity.custom.WalkingMushroomEntity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class LightOrbRenderer extends EntityRenderer<LightOrbSpell> {
    private static final ResourceLocation LIGHT_ORB_LOCATION = new ResourceLocation(DungeonMod.MOD_ID, "textures/spell/light_orb.png");
    private static final RenderType RENDER_TYPE;

    public LightOrbRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }


    @Override
    public ResourceLocation getTextureLocation(LightOrbSpell pEntity) {
        return LIGHT_ORB_LOCATION;
    }

//    @Override
//    public void render(LightOrbSpell pEntity, float pEntityYaw, float pPartialTicks, PoseStack poseStack,
//                       MultiBufferSource pBuffer, int pPackedLight) {
//        if(pEntity.isBaby()) {
//            poseStack.scale(0.6f, 0.6f, 0.6f);
//        }
//        poseStack.pushPose();
//
//        poseStack.translate(0.0F, 0.1F, 0.0F);
//        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
//        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
//
//        // Render the entity (assuming you have a model or texture to render)
//        super.render(pEntity, pEntityYaw, pPartialTicks, poseStack, pBuffer, pPackedLight);
//
//        poseStack.popPose();
//    }

    protected int getBlockLightLevel(LightOrbSpell pEntity, BlockPos pPos) {
        return Mth.clamp(super.getBlockLightLevel(pEntity, pPos) + 7, 0, 15);
    }

//    @Override
//    public void render(LightOrbSpell pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        pPoseStack.pushPose();
//        int $$6 = pEntity.getIcon();
//        float $$7 = (float)($$6 % 4 * 16 + 0) / 64.0F;
//        float $$8 = (float)($$6 % 4 * 16 + 16) / 64.0F;
//        float $$9 = (float)($$6 / 4 * 16 + 0) / 64.0F;
//        float $$10 = (float)($$6 / 4 * 16 + 16) / 64.0F;
//        float $$11 = 1.0F;
//        float $$12 = 0.5F;
//        float $$13 = 0.25F;
//        float $$14 = 255.0F;
//        float $$15 = ((float)pEntity.tickCount + pPartialTicks) / 2.0F;
//        int $$16 = (int)((Mth.sin($$15 + 0.0F) + 1.0F) * 0.5F * 255.0F);
//        boolean $$17 = true;
//        int $$18 = (int)((Mth.sin($$15 + 4.1887903F) + 1.0F) * 0.1F * 255.0F);
//        pPoseStack.translate(0.0F, 0.1F, 0.0F);
//        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
//        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
//        float $$19 = 0.3F;
//        pPoseStack.scale(0.3F, 0.3F, 0.3F);
//        VertexConsumer $$20 = pBuffer.getBuffer(RENDER_TYPE);
//        PoseStack.Pose $$21 = pPoseStack.last();
//        Matrix4f $$22 = $$21.pose();
//        Matrix3f $$23 = $$21.normal();
//        vertex($$20, $$22, $$23, -0.5F, -0.25F, $$16, 255, $$18, $$7, $$10, pPackedLight);
//        vertex($$20, $$22, $$23, 0.5F, -0.25F, $$16, 255, $$18, $$8, $$10, pPackedLight);
//        vertex($$20, $$22, $$23, 0.5F, 0.75F, $$16, 255, $$18, $$8, $$9, pPackedLight);
//        vertex($$20, $$22, $$23, -0.5F, 0.75F, $$16, 255, $$18, $$7, $$9, pPackedLight);
//        pPoseStack.popPose();
//        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
//    }

    @Override
    public void render(LightOrbSpell pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.1F, 0.0F);
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        pPoseStack.scale(1F, 1F, 1F);

        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DungeonMod.MOD_ID, "textures/spell/light_orb.png")));
        PoseStack.Pose pose = pPoseStack.last();
        Matrix4f matrix = pose.pose();
        Matrix3f normal = pose.normal();

        vertex(vertexConsumer, matrix, normal, -0.5F, -0.5F, 255, 255, 255, 0, 1, pPackedLight); // Red
        vertex(vertexConsumer, matrix, normal, 0.5F, -0.5F, 255, 255, 255, 1, 1, pPackedLight); // Green
        vertex(vertexConsumer, matrix, normal, 0.5F, 0.5F, 255, 255, 255, 1, 0, pPackedLight); // Blue
        vertex(vertexConsumer, matrix, normal, -0.5F, 0.5F, 255, 255, 255, 0, 0, pPackedLight); // White

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    private static void vertex(VertexConsumer pConsumer, Matrix4f pMatrix, Matrix3f pMatrixNormal, float pX, float pY, int pRed, int pGreen, int pBlue, float pTexU, float pTexV, int pPackedLight) {
        pConsumer.vertex(pMatrix, pX, pY, 0.0F).color(pRed, pGreen, pBlue, 255).uv(pTexU, pTexV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(pMatrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }

    static {
        RENDER_TYPE = RenderType.entitySolid(LIGHT_ORB_LOCATION);
    }
}
