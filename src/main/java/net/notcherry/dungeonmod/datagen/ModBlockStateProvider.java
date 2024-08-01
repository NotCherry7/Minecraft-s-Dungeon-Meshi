package net.notcherry.dungeonmod.datagen;

import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;
import net.notcherry.dungeonmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.DungeonMod;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DungeonMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.DUNGEON_STONE_TILES);
        blockWithItem(ModBlocks.COBBLED_DUNGEON_STONE);
        blockWithItem(ModBlocks.CRACKED_DUNGEON_STONE_TILES);
        blockWithItem(ModBlocks.DUNGEON_STONE_BRICKS);
        blockWithItem(ModBlocks.CRACKED_DUNGEON_STONE_BRICKS);
        blockWithItem(ModBlocks.POLISHED_DUNGEON_STONE);
        blockWithItem(ModBlocks.CHISELED_DUNGEON_STONE);

        stairsBlock(((StairBlock) ModBlocks.COBBLED_DUNGEON_STONE_STAIRS.get()), blockTexture(ModBlocks.COBBLED_DUNGEON_STONE.get()));
        stairsBlock(((StairBlock) ModBlocks.DUNGEON_STONE_TILE_STAIRS.get()), blockTexture(ModBlocks.DUNGEON_STONE_TILES.get()));
        stairsBlock(((StairBlock) ModBlocks.DUNGEON_STONE_BRICK_STAIRS.get()), blockTexture(ModBlocks.DUNGEON_STONE_BRICKS.get()));
        stairsBlock(((StairBlock) ModBlocks.POLISHED_DUNGEON_STONE_STAIRS.get()), blockTexture(ModBlocks.POLISHED_DUNGEON_STONE.get()));

        slabBlock(((SlabBlock) ModBlocks.COBBLED_DUNGEON_STONE_SLAB.get()), blockTexture(ModBlocks.COBBLED_DUNGEON_STONE.get()), blockTexture(ModBlocks.COBBLED_DUNGEON_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.DUNGEON_STONE_TILE_SLAB.get()), blockTexture(ModBlocks.DUNGEON_STONE_TILES.get()), blockTexture(ModBlocks.DUNGEON_STONE_TILES.get()));
        slabBlock(((SlabBlock) ModBlocks.DUNGEON_STONE_BRICK_SLAB.get()), blockTexture(ModBlocks.DUNGEON_STONE_BRICKS.get()), blockTexture(ModBlocks.DUNGEON_STONE_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_DUNGEON_STONE_SLAB.get()), blockTexture(ModBlocks.POLISHED_DUNGEON_STONE.get()), blockTexture(ModBlocks.POLISHED_DUNGEON_STONE.get()));

        wallBlock(((WallBlock) ModBlocks.COBBLED_DUNGEON_STONE_WALL.get()), blockTexture(ModBlocks.COBBLED_DUNGEON_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.DUNGEON_STONE_TILE_WALL.get()), blockTexture(ModBlocks.DUNGEON_STONE_TILES.get()));
        wallBlock(((WallBlock) ModBlocks.DUNGEON_STONE_BRICK_WALL.get()), blockTexture(ModBlocks.DUNGEON_STONE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_DUNGEON_STONE_WALL.get()), blockTexture(ModBlocks.POLISHED_DUNGEON_STONE.get()));

        logBlock((RotatedPillarBlock) ModBlocks.DUNGEON_STONE.get());

        blockItem(ModBlocks.DUNGEON_STONE);
        blockWithItem(ModBlocks.MOD_PORTAL);

//        buttonBlock(((ButtonBlock) ModBlocks.MOONPEARL_BUTTON.get()), blockTexture(ModBlocks.MOONPEARL_BLOCK.get()));
//        pressurePlateBlock(((PressurePlateBlock) ModBlocks.MOONPEARL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.MOONPEARL_BLOCK.get()));

//        doorBlockWithRenderType(((DoorBlock) ModBlocks.MOONPEARL_DOOR.get()), modLoc("block/moonpearl_door_bottom"), modLoc("block/moonpearl_door_top"), "cutout");
//        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.MOONPEARL_TRAPDOOR.get()), modLoc("block/moonpearl_trapdoor"), true, "cutout");

//        simpleBlockWithItem(ModBlocks.BUTTERCUP.get(), models().cross(blockTexture(ModBlocks.BUTTERCUP.get()).getPath(),
//                blockTexture(ModBlocks.BUTTERCUP.get())).renderType("cutout"));
//        simpleBlockWithItem(ModBlocks.POTTED_BUTTERCUP.get(), models().singleTexture("potted_buttercup", new ResourceLocation("flower_pot_cross"), "plant",
//                blockTexture(ModBlocks.BUTTERCUP.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.COOKING_POT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/cooking_pot")));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(DungeonMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
