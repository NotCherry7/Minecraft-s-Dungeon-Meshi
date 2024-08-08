package net.notcherry.dungeonmod.block.entity.renderer.portal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.notcherry.dungeonmod.client.LightBeamRenderer;
import net.notcherry.dungeonmod.block.entity.portal.Portal5BlockEntity;

import java.awt.*;

public class Portal5BlockEntityRenderer implements BlockEntityRenderer<Portal5BlockEntity> {
    public float rotation;

    public Portal5BlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(Portal5BlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        Color color = Color.ORANGE;

        rotation += 2.0f;
        if (rotation >= 360.0f) { rotation -= 360.0f; }

        LightBeamRenderer.renderLightBeam(pPoseStack, pBuffer, rotation, color);
    }
}
