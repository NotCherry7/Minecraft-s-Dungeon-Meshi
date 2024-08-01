package net.notcherry.dungeonmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.notcherry.dungeonmod.DungeonMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_HUGE_SCORPION_CLAW = tag("needs_huge_scorpion_claw");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(DungeonMod.MOD_ID, name));
        }

    }

    public static class Items {


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(DungeonMod.MOD_ID, name));
        }
    }

}
