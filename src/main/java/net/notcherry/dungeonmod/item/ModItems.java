package net.notcherry.dungeonmod.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.item.custom.ModFoods;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.item.custom.WoodenWandItem;
import net.notcherry.dungeonmod.item.custom.tools.EffectSwordItem;
import net.notcherry.dungeonmod.item.custom.tools.PAXALItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DungeonMod.MOD_ID);

    public static final RegistryObject<Item> FIRE_SHARD = ITEMS.register("fire_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MANDRAKE = ITEMS.register("mandrake",
            () -> new Item(new Item.Properties().food(ModFoods.MANDRAKE)));

    public static final RegistryObject<Item> MANDRAKE_HEAD = ITEMS.register("mandrake_head",
            () -> new Item(new Item.Properties().food(ModFoods.MANDRAKE_HEAD)));

    public static final RegistryObject<Item> WALKING_MUSHROOM_FOOT = ITEMS.register("walking_mushroom_foot",
            () -> new Item(new Item.Properties().food(ModFoods.WALKING_MUSHROOM_FOOT)));

    public static final RegistryObject<Item> WALKING_MUSHROOM_CHUNK = ITEMS.register("walking_mushroom_chunk",
            () -> new Item(new Item.Properties().food(ModFoods.WALKING_MUSHROOM_CHUNK)));

    public static final RegistryObject<Item> HUGE_SCORPION_HEAD = ITEMS.register("huge_scorpion_head",
            () -> new Item(new Item.Properties().food(ModFoods.HUGE_SCORPION_HEAD).stacksTo(8)));

    public static final RegistryObject<Item> HUGE_SCORPION_LEG = ITEMS.register("huge_scorpion_leg",
            () -> new Item(new Item.Properties().food(ModFoods.HUGE_SCORPION_LEG).stacksTo(16)));

    public static final RegistryObject<Item> HUGE_SCORPION_BARB = ITEMS.register("huge_scorpion_barb",
            () -> new EffectSwordItem(
                    ModToolTiers.HUGE_SCORPION, 1, 2.5f, new Item.Properties()
                    .stacksTo(1), MobEffects.POISON, 200, 1, 0.3, false
            ));

    public static final RegistryObject<Item> HUGE_SCORPION_BODY = ITEMS.register("huge_scorpion_body",
            () -> new Item(new Item.Properties().food(ModFoods.HUGE_SCORPION_BODY).stacksTo(16)));

    public static final RegistryObject<Item> HUGE_SCORPION_CLAW = ITEMS.register("huge_scorpion_claw",
            () -> new PAXALItem(ModToolTiers.HUGE_SCORPION, 5, 1.5f, true, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> HUGE_SCORPION_TAIL = ITEMS.register("huge_scorpion_tail",
            () -> new Item(new Item.Properties().food(ModFoods.HUGE_SCORPION_TAIL).stacksTo(16)));

    public static final RegistryObject<Item> MANDRAKE_SPAWN_EGG = ITEMS.register("mandrake_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MANDRAKE, 0x7A3425, 0x456B26,
                    new Item.Properties()));

    public static final RegistryObject<Item> WALKING_MUSHROOM_SPAWN_EGG = ITEMS.register("walking_mushroom_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.WALKING_MUSHROOM, 0xe0a8a4, 0xe62719,
                    new Item.Properties()));

    public static final RegistryObject<Item> HUGE_SCORPION_SPAWN_EGG = ITEMS.register("huge_scorpion_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HUGE_SCORPION, 0x8a0b31, 0x590b22,
                    new Item.Properties()));

    public static final RegistryObject<Item> GOLEM_SPAWN_EGG = ITEMS.register("golem_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GOLEM, 0x3C1414, 0xA52A2A,
                    new Item.Properties()));


    public static final RegistryObject<Item> WOODEN_WAND = ITEMS.register("wooden_wand",
            () -> new WoodenWandItem(new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
