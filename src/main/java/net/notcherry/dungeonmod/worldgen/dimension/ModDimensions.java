package net.notcherry.dungeonmod.worldgen.dimension;

import com.ibm.icu.impl.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {

    public static final ResourceKey<LevelStem> DUNGEONWORLD_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld"));
    public static final ResourceKey<Level> DUNGEONWORLD_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld"));
    public static final ResourceKey<DimensionType> DUNGEONWORLD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
//        context.register(DUNGEONWORLD_DIM_TYPE, new DimensionType(
//                OptionalLong.of(12000), // fixedTime
//                false, // hasSkylight
//                false, // hasCeiling
//                false, // ultraWarm
//                false, // natural
//                1.0, // coordinateScale
//                false, // bedWorks
//                false, // respawnAnchorWorks
//                0, // minY
//                256, // height
//                256, // logicalHeight
//                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
//                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
//                0f, // ambientLight 1.0
//                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
        context.register(DUNGEONWORLD_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                false,
                1.0,
                false,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.DUNGEON_BIOME)),
//                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.CHERRY_GROVE)),
                noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY)
                );

        // Register your void biome
        Holder<Biome> voidBiome = biomeRegistry.getOrThrow(ModBiomes.DUNGEON_BIOME);

        // Register your custom or default noise settings
        Holder<NoiseGeneratorSettings> voidNoiseSettings = noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY);



//        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
//                MultiNoiseBiomeSource.createFromList(
//                        new Climate.ParameterList<>(List.of(
//                                Pair.of(
//                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.CHERRY_GROVE)),
//                                Pair.of(
//                                        Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)),
//                                Pair.of(
//                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.OCEAN)),
//                                Pair.of(
//                                        Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.DARK_FOREST))
//
//                        ))),
//                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

//        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEONWORLD_DIM_TYPE), noiseBasedChunkGenerator);
//        NoiseBasedChunkGenerator chunkGenerator = new NoiseBasedChunkGenerator(
//                new FixedBiomeSource(voidBiome),
//                voidNoiseSettings
//        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEONWORLD_DIM_TYPE), wrappedChunkGenerator);
//        LevelStem stem = new LevelStem(
//                (Holder<DimensionType>) ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld_type")),
//                chunkGenerator
//        );

        context.register(DUNGEONWORLD_KEY, stem);
    }
}
