package net.notcherry.dungeonmod.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.notcherry.dungeonmod.effect.custom.StunPotionEffect;

public class MouseScrollEventHandler {

    @SubscribeEvent
    public void onMouseScrollInput(InputEvent.MouseScrollingEvent event) {
        // Your logic here to handle mouse events
        // Example: Cancel the event if player is stunned
        if (StunPotionEffect.isStunned(Minecraft.getInstance().player)) {
            event.setCanceled(true); // Cancel the event
        }
    }
}
