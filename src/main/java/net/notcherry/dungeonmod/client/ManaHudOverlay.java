package net.notcherry.dungeonmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.effect.ModEffects;

public class ManaHudOverlay {
    private static final ResourceLocation MANA_BAR = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/mana/mana_bar.png");
    private static final ResourceLocation MANA_BAR_BACKGROUND = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/mana/mana_bar_background.png");
    private static final ResourceLocation FILLED_MANA = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/mana/filled_mana_line.png");
    private static final ResourceLocation EMPTY_MANA = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/mana/empty_mana_line.png");
    private static final ResourceLocation BACKGROUND_MANA = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/mana/background_mana_line.png");

    private static final int FRAME_COUNT = 45; // Total number of frames
    private static final int FRAME_TIME = 2; // Ticks per frame (adjust as needed)
    private static final ResourceLocation[] FRAMES = new ResourceLocation[FRAME_COUNT];

    static {
        for (int i = 0; i < FRAME_COUNT; i++) {
            String frameNumber = String.format("%02d", i);
            FRAMES[i] = new ResourceLocation(DungeonMod.MOD_ID, "textures/mana/overlay_spark_frames/frame_" + frameNumber + ".png");
        }
    }

    private static int tickCounter = 0;

    public static final IGuiOverlay HUD_MANA = (gui, guiGraphics, partialTick, width, height) -> {
        int x = - 35;
        int y = height - 20;

        int yoffset = 0; // 82
        int xoffset = 0; // 90

        Player pPlayer = Minecraft.getInstance().player;

//         Determine current mana amount (for example, 56)
        int currentMana = ClientManaData.getPlayerMana();

//        if(currentMana < 10) {
////            pPlayer.addEffect(new MobEffectInstance(ModEffects.MANA_SICKNESS_EFFECT.get(), 1));
//            pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1, 2));
//            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 5));
//        }

        // Calculate frame width based on current mana amount
//        int frameWidth = 42 + currentMana;

        // Calculate the current frame based on the tick counter
        int frameIndex = (tickCounter / FRAME_TIME) % FRAME_COUNT;
        ResourceLocation currentFrame = FRAMES[frameIndex];

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, EMPTY_MANA);
        for (int i = 0; i < 100; i++) {
            guiGraphics.blit(EMPTY_MANA, x - xoffset + (i), y - yoffset - 1, 0, 0, 42, 19, 42, 19);
        }

        RenderSystem.setShaderTexture(0, MANA_BAR_BACKGROUND);
        guiGraphics.blit(MANA_BAR_BACKGROUND, x - xoffset, y - yoffset, 0, 0, 182, 19, 182, 19);

        RenderSystem.setShaderTexture(0, FILLED_MANA);
        for (int i = 0; i < 100; i++) {
            if (ClientManaData.getPlayerMana() > i) {
                guiGraphics.blit(FILLED_MANA, x - xoffset + (i), y - yoffset - 1, 0, 0, 42, 19, 42, 19);
            } else {
                break;
            }
        }

        RenderSystem.setShaderTexture(0, currentFrame);
        guiGraphics.blit(currentFrame, x - xoffset, y - yoffset + 2, 0, 0, 182, 19, 182, 19);

        RenderSystem.setShaderTexture(0, BACKGROUND_MANA);
        for (int i = 0; i > -100; i--) {
            if (ClientManaData.getPlayerMana() - 100 < i) {
                guiGraphics.blit(BACKGROUND_MANA, x - xoffset + (i), y - yoffset - 1, 0, 0,  182, 19, 182, 19);
            } else {
                break;
            }
        }

        // Render the animation frame
//        RenderSystem.setShaderTexture(0, currentFrame);
//        guiGraphics.blit(currentFrame, x - 90, y - yoffset + 3, 0, 0, 182, 19, frameWidth, 19);

        RenderSystem.setShaderTexture(0, MANA_BAR);
        guiGraphics.blit(MANA_BAR, x - xoffset, y - yoffset, 0, 0, 182, 19, 182, 19);

        tickCounter++;
    };
}