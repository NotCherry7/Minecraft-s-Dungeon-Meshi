package net.notcherry.dungeonmod.worldgen.dimension;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.notcherry.dungeonmod.DungeonMod;

import java.util.Arrays;
import java.util.List;

public class ModNoiseSettings {
    public static final ResourceKey<NoiseGeneratorSettings> VOID_NOISE_SETTINGS_KEY = ResourceKey.create(Registries.NOISE_SETTINGS,
            new ResourceLocation(DungeonMod.MOD_ID, "void_noise_settings"));

    public static void bootstrapNoiseSettings(BootstapContext<NoiseGeneratorSettings> context) {
        context.register(VOID_NOISE_SETTINGS_KEY, noiseSettings(context));
    } // NoiseGeneratorSettings.class

    public static NoiseGeneratorSettings noiseSettings(BootstapContext<NoiseGeneratorSettings> context) {
        return new NoiseGeneratorSettings(
                NoiseSettings.create(0, 256, 2, 1),
                Blocks.AIR.defaultBlockState(),
                Blocks.AIR.defaultBlockState(),
//                NoiseRouterData.end(context.lookup(Registries.DENSITY_FUNCTION)),
//                new NoiseSettings(-64, 256, 1, 1),
                createVoidNoiseRouter(context.lookup(Registries.DENSITY_FUNCTION)),
                SurfaceRuleData.end(),
                List.of(),
                0,
                true,
                false,
                false,
                true
        );
    }

    private static NoiseRouter createVoidNoiseRouter(HolderGetter<DensityFunction> densityFunctionRegistry) {
        DensityFunction constantFunction = DensityFunctions.constant(0);

        return new NoiseRouter(
                constantFunction, // The density function for the first noise router parameter
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction,
                constantFunction
        );
    }
}

