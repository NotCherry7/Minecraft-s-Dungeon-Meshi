package net.notcherry.dungeonmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier HUGE_SCORPION = TierSortingRegistry.registerTier(
            new ForgeTier(5, 25, 3f, 3, 0,
                    ModTags.Blocks.NEEDS_HUGE_SCORPION_CLAW, () -> Ingredient.of(ModItems.HUGE_SCORPION_CLAW.get())),
            new ResourceLocation(DungeonMod.MOD_ID, "huge_scorpion_claw"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
}
