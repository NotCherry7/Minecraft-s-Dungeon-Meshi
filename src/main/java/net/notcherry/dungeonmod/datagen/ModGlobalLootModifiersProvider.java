package net.notcherry.dungeonmod.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.notcherry.dungeonmod.DungeonMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.notcherry.dungeonmod.item.ModItems;
import net.notcherry.dungeonmod.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, DungeonMod.MOD_ID);
    }

    @Override
    protected void start() {
//        add("sorcerer_branch_from_leaves", new AddItemModifier(new LootItemCondition[]{
//                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.OAK_LEAVES).build(),
//                LootItemRandomChanceCondition.randomChance(0.6f).build()}, ModItems.FIRE_SHARD.get()));

//        add("x_from_y", new AddItemModifier(new LootItemCondition[] {
//                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.MAGMA_BLOCK).build(),
//                LootItemRandomChanceCondition.randomChance(0.35f).build()},
//                ModItems.FIRE_SHARD.get()
//        ));

//        add("x_from_structure_chest", new AddItemModifier(new LootItemCondition[] {
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build()},
//                ModItems.WALKING_MUSHROOM_FOOT.get()
//        ));

//        add("spawn_egg_from_walking_mushroom", new AddItemModifier(new LootItemCondition[] {
//                new LootTableIdCondition.Builder(new ResourceLocation("dungeonmod", "entities/walking_mushroom")).build()},
//                ModItems.WALKING_MUSHROOM_SPAWN_EGG.get()
//        ));
//                LootItemRandomChanceCondition.randomChance(1f).build()}, ModItems.WALKING_MUSHROOM_FOOT.get()));

    }
}
