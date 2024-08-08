package net.notcherry.dungeonmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.notcherry.dungeonmod.block.entity.CookingPotBlockEntity;

public class CookingPotBlockEntityRenderer implements BlockEntityRenderer<CookingPotBlockEntity> {
    private float rotation;

    public CookingPotBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(CookingPotBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack0 = pBlockEntity.getRenderStack0();
        ItemStack itemStack1 = pBlockEntity.getRenderStack1();
        ItemStack itemStack2 = pBlockEntity.getRenderStack2();
        ItemStack itemStack3 = pBlockEntity.getRenderStack3();
        ItemStack itemStack4 = pBlockEntity.getRenderStack4();

        ItemStack itemStackOut = pBlockEntity.getRenderStackOutput();

        // Common scale factor
        float scale = 0.35f;
        float scaleOut = scale * 1.75f;
        float bottomLeftx = 0.4f;
        float bottomLeftz = 0.6f;

        float bottomRightx = 0.6f;
        float bottomRightz = 0.6f;

        float centerx = 0.5f;
        float centerz = 0.5f;

        float topLeftx = 0.4f;
        float topLeftz = 0.4f;

        float topRightx = 0.6f;
        float topRightz = 0.4f;

        // Update rotation
        rotation += 2.0f; // Adjust rotation speed as needed
        if (rotation >= 360.0f) {
            rotation -= 360.0f; // Keep rotation within 0-359 degrees
        }

        // Render item in top-left corner
        renderItemDisplayAt(pBlockEntity, pPoseStack, itemRenderer, itemStack0, scale, topLeftx, 0.40f, topLeftz, 90, pBuffer, pPackedLight, pPackedOverlay);

        // Render item in top-right corner
        renderItemDisplayAt(pBlockEntity, pPoseStack, itemRenderer, itemStack1, scale, topRightx, 0.42f, topRightz, 90, pBuffer, pPackedLight, pPackedOverlay);

        // Render item in center
        renderItemDisplayAt(pBlockEntity, pPoseStack, itemRenderer, itemStack2, scale, centerx, 0.48f, centerz, 90, pBuffer, pPackedLight, pPackedOverlay);

        // Render item above center
        pPoseStack.pushPose();
        pPoseStack.scale(scaleOut, scaleOut, scaleOut);
        pPoseStack.translate(centerx / scaleOut, 1.0, centerz / scaleOut); // Above center
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        itemRenderer.renderStatic(itemStackOut, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();

        // Render item in bottom-left corner
        renderItemDisplayAt(pBlockEntity, pPoseStack, itemRenderer, itemStack3, scale, bottomLeftx, 0.44f, bottomLeftz, 90, pBuffer, pPackedLight, pPackedOverlay);

        // Render item in bottom-right corner
        renderItemDisplayAt(pBlockEntity, pPoseStack, itemRenderer, itemStack4, scale, bottomRightx, 0.46f, bottomRightz, 90, pBuffer, pPackedLight, pPackedOverlay);
    }

    private void renderItemDisplayAt(CookingPotBlockEntity pBlockEntity, PoseStack pPoseStack, ItemRenderer itemRenderer, ItemStack itemStack,
                                     float scale, float x, float y, float z, float rotation, MultiBufferSource pBuffer,
                                     int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.scale(scale, scale, scale);
        pPoseStack.translate(x / scale, y, z / scale); // Bottom-right corner
        pPoseStack.mulPose(Axis.XP.rotationDegrees(rotation));
        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
    }


    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
