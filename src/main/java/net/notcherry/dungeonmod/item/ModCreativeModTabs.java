package net.notcherry.dungeonmod.item;

import net.minecraft.world.level.block.Blocks;
import net.notcherry.dungeonmod.DungeonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DungeonMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DUNGEON_TAB = CREATIVE_MODE_TABS.register("dungeon_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MANDRAKE.get()))
                    .title(Component.translatable("creativetab.dungeon_tab"))
                    .displayItems((pParameters, pOutput) -> {
// ITEMS
    // MANDRAKE
                        pOutput.accept(ModItems.MANDRAKE.get());
                        pOutput.accept(ModItems.MANDRAKE_HEAD.get());
                        pOutput.accept(ModItems.MANDRAKE_SPAWN_EGG.get());
    // WALKING MUSHROOM
                        pOutput.accept(ModItems.WALKING_MUSHROOM_SPAWN_EGG.get());
                        pOutput.accept(ModItems.WALKING_MUSHROOM_FOOT.get());
                        pOutput.accept(ModItems.WALKING_MUSHROOM_CHUNK.get());
    // HUGE SCORPION
                        pOutput.accept(ModItems.HUGE_SCORPION_SPAWN_EGG.get());
                        pOutput.accept(ModItems.HUGE_SCORPION_HEAD.get());
                        pOutput.accept(ModItems.HUGE_SCORPION_BODY.get());
                        pOutput.accept(ModItems.HUGE_SCORPION_CLAW.get());
                        pOutput.accept(ModItems.HUGE_SCORPION_TAIL.get());
                        pOutput.accept(ModItems.HUGE_SCORPION_BARB.get());
                        pOutput.accept(ModItems.HUGE_SCORPION_LEG.get());
    // GOLEM
                        pOutput.accept(ModItems.GOLEM_SPAWN_EGG.get());

// BLOCKS
                        pOutput.accept(ModBlocks.COOKING_POT.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE.get());
                        pOutput.accept(ModBlocks.COBBLED_DUNGEON_STONE.get());
                        pOutput.accept(ModBlocks.COBBLED_DUNGEON_STONE_SLAB.get());
                        pOutput.accept(ModBlocks.COBBLED_DUNGEON_STONE_STAIRS.get());
                        pOutput.accept(ModBlocks.COBBLED_DUNGEON_STONE_WALL.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_TILES.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_TILE_SLAB.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_TILE_STAIRS.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_TILE_WALL.get());
                        pOutput.accept(ModBlocks.CRACKED_DUNGEON_STONE_TILES.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.DUNGEON_STONE_BRICK_WALL.get());
                        pOutput.accept(ModBlocks.CRACKED_DUNGEON_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.POLISHED_DUNGEON_STONE.get());
                        pOutput.accept(ModBlocks.POLISHED_DUNGEON_STONE_SLAB.get());
                        pOutput.accept(ModBlocks.POLISHED_DUNGEON_STONE_STAIRS.get());
                        pOutput.accept(ModBlocks.POLISHED_DUNGEON_STONE_WALL.get());
                        pOutput.accept(ModBlocks.CHISELED_DUNGEON_STONE.get());

                        pOutput.accept(ModBlocks.MOD_PORTAL.get());
                        pOutput.accept(ModItems.WOODEN_WAND.get());
//                        pOutput.accept(Blocks.DEEPSLATE);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}