package net.notcherry.dungeonmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.block.entity.ModBlockEntities;
import net.notcherry.dungeonmod.block.entity.renderer.CookingPotBlockEntityRenderer;
import net.notcherry.dungeonmod.client.ManaHudOverlay;
import net.notcherry.dungeonmod.client.overlays.SpellWheelOverlay;
import net.notcherry.dungeonmod.entity.client.*;
import net.notcherry.dungeonmod.entity.client.golem.GolemModel;
import net.notcherry.dungeonmod.entity.client.huge_scorpion.HugeScorpionModel;
import net.notcherry.dungeonmod.entity.client.mandrake.MandrakeModel;
import net.notcherry.dungeonmod.entity.client.walking_mushroom.WalkingMushroomModel;
import net.notcherry.dungeonmod.networking.ModMessages;
import net.notcherry.dungeonmod.networking.packet.ConsumeManaC2SPacket;
import net.notcherry.dungeonmod.util.KeyBinding;

@Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
//    private static final KeyState SPELLBOOK_CAST_STATE = register(SPELLBOOK_CAST_ACTIVE_KEYMAP);
//    private static final List<KeyState> QUICK_CAST_STATES = registerQuickCast(KeyMappings.QUICK_CAST_MAPPINGS);

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MANDRAKE_LAYER, MandrakeModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WALKINGMUSHROOM_LAYER, WalkingMushroomModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HUGESCORPION_LAYER, HugeScorpionModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GOLEM_LAYER, GolemModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.COOKING_POT_BE.get(), CookingPotBlockEntityRenderer::new);
    }

    @Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new ConsumeManaC2SPacket());
            }
            if (KeyBinding.SPELL_WHEEL_KEY.isDown()) {
                SpellWheelOverlay.instance.open();
            }
//            handleInputEvent(event.getKey(), event.getAction());
        }
    }

    private static void handleInputEvent(int button, int action) {
        var minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player == null) {
            return;
        }
//        if (SPELL_WHEEL_KEY.wasPressed())
    }

    @Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
            event.register(KeyBinding.SPELL_WHEEL_KEY);
        }
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("mana", ManaHudOverlay.HUD_MANA);
    }
}
