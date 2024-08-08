package net.notcherry.dungeonmod.datagen.loot;

import net.notcherry.dungeonmod.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

//        this.dropSelf(ModBlocks.BUTTERCUP.get());
//        this.add(ModBlocks.POTTED_BUTTERCUP.get(), createPotFlowerItemTable(ModBlocks.BUTTERCUP.get()));

        this.dropSelf(ModBlocks.DUNGEON_LADDER.get());
        this.dropSelf(ModBlocks.COOKING_POT.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE.get());
        this.dropSelf(ModBlocks.COBBLED_DUNGEON_STONE.get());
        this.dropSelf(ModBlocks.COBBLED_DUNGEON_STONE_SLAB.get());
        this.dropSelf(ModBlocks.COBBLED_DUNGEON_STONE_STAIRS.get());
        this.dropSelf(ModBlocks.COBBLED_DUNGEON_STONE_WALL.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_TILES.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_TILE_SLAB.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_TILE_STAIRS.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_TILE_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_DUNGEON_STONE_TILES.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DUNGEON_STONE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_DUNGEON_STONE_BRICKS.get());
        this.dropSelf(ModBlocks.POLISHED_DUNGEON_STONE.get());
        this.dropSelf(ModBlocks.POLISHED_DUNGEON_STONE_SLAB.get());
        this.dropSelf(ModBlocks.POLISHED_DUNGEON_STONE_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_DUNGEON_STONE_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_DUNGEON_STONE.get());

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
