package net.notcherry.dungeonmod.util;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.item.ModItems;
import net.notcherry.dungeonmod.item.custom.SpellType;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeWand(ModItems.WOODEN_WAND.get());
    }

    public static void makeWand(Item item) {
        ItemProperties.register(item, new ResourceLocation("spell"), (stack, level, living, seed) -> {
            // Retrieve the spell state from the item's NBT data
            int spellState = stack.getOrCreateTag().getInt("SpellState");
            if (spellState < 0) {
                spellState = 0;
            } else if (spellState > 4) {
                spellState = 4;
            }
            return spellState;
        });
    }
}
