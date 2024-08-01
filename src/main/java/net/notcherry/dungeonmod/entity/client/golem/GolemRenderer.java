package net.notcherry.dungeonmod.entity.client.golem;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.client.ModModelLayers;
import net.notcherry.dungeonmod.entity.client.huge_scorpion.HugeScorpionModel;
import net.notcherry.dungeonmod.entity.custom.GolemEntity;
import net.notcherry.dungeonmod.entity.custom.HugeScorpionEntity;

public class GolemRenderer extends MobRenderer<GolemEntity, GolemModel<GolemEntity>> {
    public GolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GolemModel<>(pContext.bakeLayer(ModModelLayers.GOLEM_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(GolemEntity pEntity) {
        return new ResourceLocation(DungeonMod.MOD_ID, "textures/entity/golem.png");
    }

    @Override
    public void render(GolemEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack poseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        poseStack.pushPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, poseStack, pBuffer, pPackedLight);
        poseStack.popPose();
    }
}

