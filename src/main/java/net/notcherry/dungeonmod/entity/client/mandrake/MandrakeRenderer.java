package net.notcherry.dungeonmod.entity.client.mandrake;

import com.mojang.blaze3d.vertex.PoseStack;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.client.ModModelLayers;
import net.notcherry.dungeonmod.entity.custom.MandrakeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MandrakeRenderer extends MobRenderer<MandrakeEntity, MandrakeModel<MandrakeEntity>> {
    public MandrakeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MandrakeModel<>(pContext.bakeLayer(ModModelLayers.MANDRAKE_LAYER)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(MandrakeEntity pEntity) {
        return new ResourceLocation(DungeonMod.MOD_ID, "textures/entity/mandrake.png");
    }

    @Override
    public void render(MandrakeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack poseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        poseStack.pushPose();
        poseStack.translate(0, 0.1, 0);
        super.render(pEntity, pEntityYaw, pPartialTicks, poseStack, pBuffer, pPackedLight);
        poseStack.popPose();
    }
}
