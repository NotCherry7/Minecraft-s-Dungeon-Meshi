package net.notcherry.dungeonmod.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.sounds.ModSounds;

public class ModBiomes {
    public static final ResourceKey<Biome> DUNGEON_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "dungeon_biome"));
    public static final ResourceKey<Biome> BFLOOR1 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor1"));
    public static final ResourceKey<Biome> BFLOOR2 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor2"));
    public static final ResourceKey<Biome> BFLOOR3 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor3"));
    public static final ResourceKey<Biome> BFLOOR4 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor4"));
    public static final ResourceKey<Biome> BFLOOR5 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor5"));
    public static final ResourceKey<Biome> BFLOOR6 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor6"));
    public static final ResourceKey<Biome> BFLOOR7 = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DungeonMod.MOD_ID, "bfloor7"));

    public static void bootstrap(BootstapContext<Biome> context) {
//        context.register(DUNGEON_BIOME, dungeonBiome(context));
        context.register(DUNGEON_BIOME, voidBiome(context));
        context.register(BFLOOR1, floor1Biome(context));
        context.register(BFLOOR2, floor2Biome(context));
        context.register(BFLOOR3, floor3Biome(context));
        context.register(BFLOOR4, floor4Biome(context));
        context.register(BFLOOR5, floor5Biome(context));
        context.register(BFLOOR6, floor6Biome(context));
        context.register(BFLOOR7, floor7Biome(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome dungeonBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MANDRAKE.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.WALKING_MUSHROOM.get(), 2, 3, 5));

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
//        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PINE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MANDRAKE_SCREAM.getHolder().get())).build()).build();
    }

    public static Biome voidBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MANDRAKE.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.WALKING_MUSHROOM.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor1Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.WALKING_MUSHROOM.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor2Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.MANDRAKE.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor3Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor4Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor5Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor6Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }

    public static Biome floor7Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HUGE_SCORPION.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1878B2)
                        .waterFogColor(0x20BED5)
                        .skyColor(0xD4F1EE)
                        .grassColorOverride(0x1D9D33)
                        .foliageColorOverride(0x33B549)
                        .fogColor(0xBCBCBC)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.HEAVENLY_SOUNDS.getHolder().get()))
                        .build())
                .build();
    }
}
