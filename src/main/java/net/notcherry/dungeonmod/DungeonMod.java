package net.notcherry.dungeonmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.notcherry.dungeonmod.block.ModBlocks;
import net.notcherry.dungeonmod.block.entity.ModBlockEntities;
import net.notcherry.dungeonmod.client.RadialMenuScreen;
import net.notcherry.dungeonmod.client.overlays.SpellWheelOverlay;
import net.notcherry.dungeonmod.effect.ModEffects;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.entity.client.golem.GolemRenderer;
import net.notcherry.dungeonmod.entity.client.huge_scorpion.HugeScorpionRenderer;
import net.notcherry.dungeonmod.entity.client.mandrake.MandrakeRenderer;
import net.notcherry.dungeonmod.entity.client.walking_mushroom.WalkingMushroomRenderer;
import net.notcherry.dungeonmod.item.ModCreativeModTabs;
import net.notcherry.dungeonmod.item.ModItems;
import net.notcherry.dungeonmod.loot.ModLootModifiers;
import net.notcherry.dungeonmod.networking.ModMessages;
import net.notcherry.dungeonmod.potion.ModPotions;
import net.notcherry.dungeonmod.recipe.ModRecipes;
import net.notcherry.dungeonmod.screen.CookingPotScreen;
import net.notcherry.dungeonmod.screen.ModMenuTypes;
import net.notcherry.dungeonmod.sounds.ModSounds;
import net.notcherry.dungeonmod.worldgen.biome.ModTerrablender;
import net.notcherry.dungeonmod.worldgen.biome.surface.ModSurfaceRules;
import org.slf4j.Logger;
//import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DungeonMod.MOD_ID)
public class DungeonMod {

    public static final String MOD_ID = "dungeonmod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

//        modEventBus.register(new KeyboardEventHandler());
//        modEventBus.register(new MouseEventHandler());
//        modEventBus.register(new MouseScrollEventHandler());

        ModCreativeModTabs.register(modEventBus);
        ModMessages.register();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);
//        ModTrunkPlacerTypes.register(modEventBus);

//        ModFoliagePlacers.register(modEventBus);
//        ModTerrablender.registerBiomes();

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
//            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.FIRE_SHARD);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.MANDRAKE.get(), MandrakeRenderer::new);
            EntityRenderers.register(ModEntities.WALKING_MUSHROOM.get(), WalkingMushroomRenderer::new);
            EntityRenderers.register(ModEntities.HUGE_SCORPION.get(), HugeScorpionRenderer::new);
            EntityRenderers.register(ModEntities.GOLEM.get(), GolemRenderer::new);
            MinecraftForge.EVENT_BUS.register(SpellWheelOverlay.instance);


            MenuScreens.register(ModMenuTypes.COOKING_POT_MENU.get(), CookingPotScreen::new);
        }
    }
}
