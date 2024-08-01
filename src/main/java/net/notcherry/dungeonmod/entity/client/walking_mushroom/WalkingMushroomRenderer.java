package net.notcherry.dungeonmod.entity.client.walking_mushroom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.client.ModModelLayers;
import net.notcherry.dungeonmod.entity.custom.WalkingMushroomEntity;

public class WalkingMushroomRenderer extends MobRenderer<WalkingMushroomEntity, WalkingMushroomModel<WalkingMushroomEntity>> {
    public WalkingMushroomRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WalkingMushroomModel<>(pContext.bakeLayer(ModModelLayers.WALKINGMUSHROOM_LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(WalkingMushroomEntity pEntity) {
        return new ResourceLocation(DungeonMod.MOD_ID, "textures/entity/walking_mushroom.png");
    }

    @Override
    public void render(WalkingMushroomEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack poseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        poseStack.pushPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, poseStack, pBuffer, pPackedLight);
        poseStack.popPose();
    }
}
