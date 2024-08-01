//package net.notcherry.dungeonmod.client.gui;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.mojang.blaze3d.vertex.BufferBuilder;
//import com.mojang.blaze3d.vertex.DefaultVertexFormat;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.Tesselator;
//import com.mojang.blaze3d.vertex.VertexFormat;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.Gui;
//import net.minecraft.client.renderer.GameRenderer;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
//import net.minecraftforge.client.gui.overlay.ForgeGui;
//import net.minecraftforge.client.gui.overlay.IGuiOverlay;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.notcherry.dungeonmod.DungeonMod;
//
//@Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class ModGuiOverlays {
//
//    public static final ResourceLocation BLACK_OVERLAY = new ResourceLocation(DungeonMod.MOD_ID, "black_overlay");
//
//    @SubscribeEvent
//    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
//        IGuiOverlay blackOverlay = new IGuiOverlay() {
//
//            @Override
//            public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
//                RenderSystem.disableDepthTest();
//                RenderSystem.setShader(GameRenderer::getPositionColorShader);
//                RenderSystem.enableBlend();
//                RenderSystem.defaultBlendFunc();
//                RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F); // Set the color to black
//
//                Tesselator tesselator = Tesselator.getInstance();
//                BufferBuilder bufferbuilder = tesselator.getBuilder();
//                bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
//                bufferbuilder.vertex(0, screenHeight, -90).color(0, 0, 0, 255).endVertex();
//                bufferbuilder.vertex(screenWidth, screenHeight, -90).color(0, 0, 0, 255).endVertex();
//                bufferbuilder.vertex(screenWidth, 0, -90).color(0, 0, 0, 255).endVertex();
//                bufferbuilder.vertex(0, 0, -90).color(0, 0, 0, 255).endVertex();
//                tesselator.end();
//
//                RenderSystem.disableBlend();
//                RenderSystem.enableDepthTest();
//            }
//        };
//
//        event.registerAboveAll(BLACK_OVERLAY.toString(), blackOverlay);
//    }
//}
