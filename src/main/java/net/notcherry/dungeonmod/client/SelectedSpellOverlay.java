package net.notcherry.dungeonmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.item.custom.WoodenWandItem;

@Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, value = Dist.CLIENT)
public class SelectedSpellOverlay {
    public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player != null) {
            ItemStack heldItem = player.getMainHandItem();

            if (heldItem.getItem() instanceof WoodenWandItem) {
                WoodenWandItem staff = (WoodenWandItem) heldItem.getItem();
                String spell = staff.getSelectedSpell();

                // Render spell name in the bottom right
                int width = mc.getWindow().getGuiScaledWidth();
                int height = mc.getWindow().getGuiScaledHeight();
                Font font = mc.font;
                GuiGraphics guiGraphics = event.getGuiGraphics();

                PoseStack poseStack = guiGraphics.pose();
                poseStack.pushPose();

                RenderSystem.enableBlend();
                guiGraphics.drawString(font, spell, width - font.width(spell) - 10, height - 20, 0xFFFFFF);
                RenderSystem.disableBlend();

                poseStack.popPose();
            }
        }
    }
}
