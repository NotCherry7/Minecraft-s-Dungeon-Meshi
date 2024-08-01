package net.notcherry.dungeonmod.datagen;

import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.block.ModBlocks;
import net.notcherry.dungeonmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DungeonMod.MOD_ID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COOKING_POT.get());
//                .add(ModBlocks.MOONPEARL_BLOCK.get();

//        this.tag(BlockTags.NEEDS_IRON_TOOL)
//                .add(ModBlocks.MOONPEARL_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
//                .add(ModBlocks.MOONPEARL_ORE.get();

        this.tag(ModTags.Blocks.NEEDS_HUGE_SCORPION_CLAW)
                .add(ModBlocks.DUNGEON_STONE.get())
                .add(ModBlocks.COBBLED_DUNGEON_STONE.get())
                .add(ModBlocks.COBBLED_DUNGEON_STONE_SLAB.get())
                .add(ModBlocks.COBBLED_DUNGEON_STONE_STAIRS.get())
                .add(ModBlocks.COBBLED_DUNGEON_STONE_WALL.get())
                .add(ModBlocks.DUNGEON_STONE_TILES.get())
                .add(ModBlocks.DUNGEON_STONE_TILE_SLAB.get())
                .add(ModBlocks.DUNGEON_STONE_TILE_STAIRS.get())
                .add(ModBlocks.DUNGEON_STONE_TILE_WALL.get())
                .add(ModBlocks.CRACKED_DUNGEON_STONE_TILES.get())
                .add(ModBlocks.DUNGEON_STONE_BRICKS.get())
                .add(ModBlocks.DUNGEON_STONE_BRICK_SLAB.get())
                .add(ModBlocks.DUNGEON_STONE_BRICK_STAIRS.get())
                .add(ModBlocks.DUNGEON_STONE_BRICK_WALL.get())
                .add(ModBlocks.CRACKED_DUNGEON_STONE_BRICKS.get())
                .add(ModBlocks.POLISHED_DUNGEON_STONE.get())
                .add(ModBlocks.POLISHED_DUNGEON_STONE_SLAB.get())
                .add(ModBlocks.POLISHED_DUNGEON_STONE_STAIRS.get())
                .add(ModBlocks.POLISHED_DUNGEON_STONE_WALL.get())
                .add(ModBlocks.CHISELED_DUNGEON_STONE.get());


//        this.tag(BlockTags.NEEDS_STONE_TOOL)
//                .add(ModBlocks.NETHER_SAPPHIRE_ORE.get());

//        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
//                .add(ModBlocks.END_STONE_SAPPHIRE_ORE.get());

//        this.tag(Tags.Blocks.NEEDS_MOONPEARL_TOOL)
//                .add(ModBlocks.END_STONE_SAPPHIRE_ORE.get());
//
//        this.tag(BlockTags.FENCES)
//                .add(ModBlocks.MOONPEARL_FENCE.get());
//        this.tag(BlockTags.FENCE_GATES)
//                .add(ModBlocks.MOONPEARL_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.COBBLED_DUNGEON_STONE_WALL.get())
                .add(ModBlocks.POLISHED_DUNGEON_STONE_WALL.get())
                .add(ModBlocks.DUNGEON_STONE_TILE_WALL.get())
                .add(ModBlocks.DUNGEON_STONE_BRICK_WALL.get());
    }
}
