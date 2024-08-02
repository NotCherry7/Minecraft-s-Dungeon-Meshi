package net.notcherry.dungeonmod.block;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.block.custom.CookingPotBlock;
import net.notcherry.dungeonmod.block.custom.CustomRedstoneLightBlock;
import net.notcherry.dungeonmod.block.custom.ModPortalBlock;
import net.notcherry.dungeonmod.block.custom.PortalBlock;
import net.notcherry.dungeonmod.block.custom.portal.*;
import net.notcherry.dungeonmod.item.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.item.ModItems;

import java.awt.*;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonMod.MOD_ID);

//    public static final RegistryObject<Block> BUTTERCUP = registerBlock("moonbloom",
//            () -> new FlowerBlock(() -> MobEffects.LUCK, 5,
//                    BlockBehaviour.Properties.copy(Blocks.DANDELION).noOcclusion().noCollission()));
//    public static final RegistryObject<Block> POTTED_BUTTERCUP = BLOCKS.register("potted_moonbloom",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.BUTTERCUP,
//                    BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));

    public static final RegistryObject<Block> COOKING_POT = registerBlock("cooking_pot",
            () -> new CookingPotBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.METAL).noOcclusion()));

    public static final RegistryObject<Block> PORTAL_1 = registerBlock("portal_1",
            () -> new Portal1Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noCollission().noOcclusion()));

    public static final RegistryObject<Block> PORTAL_2 = registerBlock("portal_2",
            () -> new Portal2Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noCollission().noOcclusion()));

    public static final RegistryObject<Block> PORTAL_3 = registerBlock("portal_3",
            () -> new Portal3Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noOcclusion()));

    public static final RegistryObject<Block> PORTAL_4 = registerBlock("portal_4",
            () -> new Portal4Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noOcclusion()));

    public static final RegistryObject<Block> PORTAL_5 = registerBlock("portal_5",
            () -> new Portal5Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noOcclusion()));

    public static final RegistryObject<Block> PORTAL_6 = registerBlock("portal_6",
            () -> new Portal6Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noOcclusion()));

    public static final RegistryObject<Block> PORTAL_7 = registerBlock("portal_7",
            () -> new Portal7Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.SCULK_SENSOR).noLootTable().noOcclusion()));

    public static final RegistryObject<Block> DUNGEON_STONE = registerBlock("dungeon_stone",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> COBBLED_DUNGEON_STONE = registerBlock("cobbled_dungeon_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DUNGEON_STONE_TILES = registerBlock("dungeon_stone_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> COBBLED_DUNGEON_STONE_STAIRS = registerBlock("cobbled_dungeon_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.COBBLED_DUNGEON_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DUNGEON_STONE_TILE_SLAB = registerBlock("dungeon_stone_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> COBBLED_DUNGEON_STONE_SLAB = registerBlock("cobbled_dungeon_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> COBBLED_DUNGEON_STONE_WALL = registerBlock("cobbled_dungeon_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DUNGEON_STONE_TILE_STAIRS = registerBlock("dungeon_stone_tile_stairs",
            () -> new StairBlock(() -> ModBlocks.DUNGEON_STONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> DUNGEON_STONE_TILE_WALL = registerBlock("dungeon_stone_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> CRACKED_DUNGEON_STONE_TILES = registerBlock("cracked_dungeon_stone_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> DUNGEON_STONE_BRICKS = registerBlock("dungeon_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));

    public static final RegistryObject<Block> DUNGEON_STONE_BRICK_SLAB = registerBlock("dungeon_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));

    public static final RegistryObject<Block> DUNGEON_STONE_BRICK_STAIRS = registerBlock("dungeon_stone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.DUNGEON_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));

    public static final RegistryObject<Block> DUNGEON_STONE_BRICK_WALL = registerBlock("dungeon_stone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));

    public static final RegistryObject<Block> CRACKED_DUNGEON_STONE_BRICKS = registerBlock("cracked_dungeon_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));

    public static final RegistryObject<Block> POLISHED_DUNGEON_STONE = registerBlock("polished_dungeon_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_DUNGEON_STONE_SLAB = registerBlock("polished_dungeon_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_DUNGEON_STONE_STAIRS = registerBlock("polished_dungeon_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.POLISHED_DUNGEON_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_DUNGEON_STONE_WALL = registerBlock("polished_dungeon_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));

    public static final RegistryObject<Block> CHISELED_DUNGEON_STONE = registerBlock("chiseled_dungeon_stone",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));
//            () -> new RedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));
            () -> new CustomRedstoneLightBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).sound(SoundType.POLISHED_DEEPSLATE)));

    public static final RegistryObject<Block> MOD_PORTAL = registerBlock("mod_portal",
            () -> new ModPortalBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noLootTable().noOcclusion().noCollission()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItems(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItems(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}