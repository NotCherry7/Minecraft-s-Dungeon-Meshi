package net.notcherry.dungeonmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.notcherry.dungeonmod.effect.ModEffects;

public class ModFoods {
    public static final FoodProperties MANDRAKE = new FoodProperties.Builder().nutrition(5).fast()
            .saturationMod(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 0.10f)
            .build();

    public static final FoodProperties MANDRAKE_HEAD = new FoodProperties.Builder().nutrition(3).fast()
            .saturationMod(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.75f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 0.50f)
            .build();

    public static final FoodProperties WALKING_MUSHROOM_FOOT = new FoodProperties.Builder().nutrition(5).fast()
            .saturationMod(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.05f)
            .build();

    public static final FoodProperties WALKING_MUSHROOM_CHUNK = new FoodProperties.Builder().nutrition(7).fast()
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.05f)
            .build();

    public static final FoodProperties HUGE_SCORPION_HEAD = new FoodProperties.Builder().nutrition(7).fast()
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 200), 1.0f)
            .build();

    public static final FoodProperties HUGE_SCORPION_LEG = new FoodProperties.Builder().nutrition(7).fast()
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 200), 1.0f)
            .build();

    public static final FoodProperties HUGE_SCORPION_BODY = new FoodProperties.Builder().nutrition(7).fast()
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 200), 1.0f)
            .build();

    public static final FoodProperties HUGE_SCORPION_TAIL = new FoodProperties.Builder().nutrition(7).fast()
            .saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 200), 1.0f)
            .build();
}
