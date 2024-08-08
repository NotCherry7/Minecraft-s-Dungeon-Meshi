package net.notcherry.dungeonmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.item.custom.tools.WoodenWandItem;

public class RadialMenuScreen extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/gui/radial_menu/radial_menu.png");
    private static final ResourceLocation SPELL_ICON = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/gui/radial_menu/spell_icon.png");

    private int numberOfSpells = 8; // Example number of spells
    private float angleStep = 360.0f / numberOfSpells;
    private float radius = 110.0f;

    public RadialMenuScreen() {
        super(Component.literal("Radial Menu"));
    }

    @Override
    protected void init() {
        // Initialize buttons or interactive elements if needed
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(pGuiGraphics);
        Minecraft mc = Minecraft.getInstance();
//        mc.getTextureManager().bindForSetup(TEXTURE);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Render the radial menu background
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

//        RenderSystem.setShaderTexture(0, TEXTURE);
        mc.getTextureManager().bindForSetup(TEXTURE);
        RenderSystem.enableBlend();
        pGuiGraphics.blit(TEXTURE, centerX - 128, centerY - 128, 0, 0, 256, 256, 256, 256);

        // Render spell icons in a circular pattern
//        int numberOfSpells = 8; // Example number of spells
//        float angleStep = 360.0f / numberOfSpells;
//        float radius = 110.0f;



        // Render spell icons in a circular pattern
        for (int i = 0; i < numberOfSpells; i++) {
            float angle = i * angleStep;
            double radian = Math.toRadians(angle);
            int iconX = centerX + (int) (radius * Math.cos(radian));
            int iconY = centerY + (int) (radius * Math.sin(radian));

            // Bind and render the spell icon
            mc.getTextureManager().bindForSetup(SPELL_ICON);
            RenderSystem.enableBlend();
            pGuiGraphics.blit(SPELL_ICON, iconX - 8, iconY - 8, 0, 0, 16, 16, 16, 16);
        }

        // Calculate the mouse position relative to the center
        double dx = mouseX - centerX;
        double dy = mouseY - centerY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Highlight the spell if the mouse is within the radial menu radius
        if (distance <= radius) {
            double angle = Math.atan2(dy, dx) * (180 / Math.PI);
            if (angle < 0) angle += 360;

            int selectedSpellIndex = (int) (angle / angleStep);

            // Draw the highlight triangle
            drawHighlightTriangle(pGuiGraphics, centerX, centerY, selectedSpellIndex);
        }

        super.render(pGuiGraphics, mouseX, mouseY, partialTicks);
    }

    private void drawHighlightTriangle(GuiGraphics pGuiGraphics, int centerX, int centerY, int selectedSpellIndex) {
        float angle = selectedSpellIndex * angleStep;
        double radian = Math.toRadians(angle);

        int triangleBase = 20; // Base width of the triangle
        int triangleHeight = 40; // Height of the triangle

        int x1 = centerX + (int) (radius * Math.cos(radian));
        int y1 = centerY + (int) (radius * Math.sin(radian));

        double leftRadian = Math.toRadians(angle - angleStep / 2);
        int x2 = centerX + (int) ((radius - triangleHeight) * Math.cos(leftRadian));
        int y2 = centerY + (int) ((radius - triangleHeight) * Math.sin(leftRadian));

        double rightRadian = Math.toRadians(angle + angleStep / 2);
        int x3 = centerX + (int) ((radius - triangleHeight) * Math.cos(rightRadian));
        int y3 = centerY + (int) ((radius - triangleHeight) * Math.sin(rightRadian));

        // Draw the triangle
        fillTriangle(pGuiGraphics, x1, y1, x2, y2, x3, y3);
    }

    private void fillTriangle(GuiGraphics pGuiGraphics, int x1, int y1, int x2, int y2, int x3, int y3) {
        PoseStack poseStack = pGuiGraphics.pose();
        poseStack.pushPose();

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();

        bufferBuilder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(poseStack.last().pose(), x1, y1, 0).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(poseStack.last().pose(), x2, y2, 0).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(poseStack.last().pose(), x3, y3, 0).color(255, 255, 255, 255).endVertex();
        tesselator.end();

        poseStack.popPose();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        double dx = mouseX - centerX;
        double dy = mouseY - centerY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= radius) {
            double angle = Math.atan2(dy, dx) * (180 / Math.PI);
            if (angle < 0) angle += 360;

            int selectedSpellIndex = (int) (angle / angleStep);
            String selectedSpell = "spell_" + selectedSpellIndex;

            Minecraft.getInstance().setScreen(null);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
