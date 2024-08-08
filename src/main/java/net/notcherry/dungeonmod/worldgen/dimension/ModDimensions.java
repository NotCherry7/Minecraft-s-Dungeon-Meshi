package net.notcherry.dungeonmod.worldgen.dimension;

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

import java.util.OptionalLong;

public class ModDimensions {

    public static final ResourceKey<LevelStem> DUNGEONWORLD_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld"));
    public static final ResourceKey<Level> DUNGEONWORLD_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld"));
    public static final ResourceKey<DimensionType> DUNGEONWORLD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeonworld_type"));


    public static final ResourceKey<LevelStem> DUNGEON1_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon1"));
    public static final ResourceKey<Level> DUNGEON1_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon1"));
    public static final ResourceKey<DimensionType> DUNGEON1_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon1_type"));

    public static final ResourceKey<LevelStem> DUNGEON2_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon2"));
    public static final ResourceKey<Level> DUNGEON2_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon2"));
    public static final ResourceKey<DimensionType> DUNGEON2_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon2_type"));

    public static final ResourceKey<LevelStem> DUNGEON3_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon3"));
    public static final ResourceKey<Level> DUNGEON3_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon3"));
    public static final ResourceKey<DimensionType> DUNGEON3_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon3_type"));

    public static final ResourceKey<LevelStem> DUNGEON4_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon4"));
    public static final ResourceKey<Level> DUNGEON4_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon4"));
    public static final ResourceKey<DimensionType> DUNGEON4_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon4_type"));

    public static final ResourceKey<LevelStem> DUNGEON5_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon5"));
    public static final ResourceKey<Level> DUNGEON5_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon5"));
    public static final ResourceKey<DimensionType> DUNGEON5_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon5_type"));

    public static final ResourceKey<LevelStem> DUNGEON6_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon6"));
    public static final ResourceKey<Level> DUNGEON6_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon6"));
    public static final ResourceKey<DimensionType> DUNGEON6_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon6_type"));

    public static final ResourceKey<LevelStem> DUNGEON7_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon7"));
    public static final ResourceKey<Level> DUNGEON7_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon7"));
    public static final ResourceKey<DimensionType> DUNGEON7_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon7_type"));



    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(DUNGEONWORLD_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
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
        context.register(DUNGEON1_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
                true,
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
        context.register(DUNGEON2_DIM_TYPE, new DimensionType(
                OptionalLong.of(0),
                true,
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
        context.register(DUNGEON3_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
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
        context.register(DUNGEON4_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
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
        context.register(DUNGEON5_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
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
        context.register(DUNGEON6_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
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
        context.register(DUNGEON7_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000),
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

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.DUNGEON_BIOME)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON1_DIM_TYPE), wrappedChunkGenerator);
        context.register(DUNGEONWORLD_KEY, stem);

        NoiseBasedChunkGenerator wrappedChunkGenerator1 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR1)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem1 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON1_DIM_TYPE), wrappedChunkGenerator1);
        context.register(DUNGEON1_KEY, stem1);

        NoiseBasedChunkGenerator wrappedChunkGenerator2 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR2)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem2 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON2_DIM_TYPE), wrappedChunkGenerator2);
        context.register(DUNGEON2_KEY, stem2);

        NoiseBasedChunkGenerator wrappedChunkGenerator3 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR3)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem3 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON3_DIM_TYPE), wrappedChunkGenerator3);
        context.register(DUNGEON3_KEY, stem3);

        NoiseBasedChunkGenerator wrappedChunkGenerator4 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR4)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem4 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON4_DIM_TYPE), wrappedChunkGenerator4);
        context.register(DUNGEON4_KEY, stem4);

        NoiseBasedChunkGenerator wrappedChunkGenerator5 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR5)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem5 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON5_DIM_TYPE), wrappedChunkGenerator5);
        context.register(DUNGEON5_KEY, stem5);

        NoiseBasedChunkGenerator wrappedChunkGenerator6 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR6)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem6 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON6_DIM_TYPE), wrappedChunkGenerator6);
        context.register(DUNGEON6_KEY, stem6);

        NoiseBasedChunkGenerator wrappedChunkGenerator7 = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.BFLOOR7)), noiseGenSettings.getOrThrow(ModNoiseSettings.VOID_NOISE_SETTINGS_KEY));
        LevelStem stem7 = new LevelStem(dimTypes.getOrThrow(ModDimensions.DUNGEON7_DIM_TYPE), wrappedChunkGenerator7);
        context.register(DUNGEON7_KEY, stem7);
    }
}
