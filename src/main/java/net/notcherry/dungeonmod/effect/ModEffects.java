package net.notcherry.dungeonmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.effect.custom.ManaSicknessPotionEffect;
import net.notcherry.dungeonmod.effect.custom.StunPotionEffect;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DungeonMod.MOD_ID);

    public static final RegistryObject<MobEffect> STUN_EFFECT = MOB_EFFECTS.register("stun_effect", StunPotionEffect::new);
    public static final RegistryObject<MobEffect> MANA_SICKNESS_EFFECT = MOB_EFFECTS.register("mana_sickness_effect", ManaSicknessPotionEffect::new);


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
