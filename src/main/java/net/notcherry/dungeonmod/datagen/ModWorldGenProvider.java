package net.notcherry.dungeonmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.worldgen.ModBiomeModifiers;
import net.notcherry.dungeonmod.worldgen.ModConfiguredFeatures;
import net.notcherry.dungeonmod.worldgen.ModPlacedFeatures;
import net.notcherry.dungeonmod.worldgen.biome.ModBiomes;
import net.notcherry.dungeonmod.worldgen.dimension.ModDimensions;
import net.notcherry.dungeonmod.worldgen.dimension.ModNoiseSettings;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
            .add(Registries.NOISE_SETTINGS, ModNoiseSettings::bootstrapNoiseSettings);
//            .add(Registries.CONFIGURED_STRUCTURE_FEATURE, ModStructures::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(DungeonMod.MOD_ID));
    }
}
