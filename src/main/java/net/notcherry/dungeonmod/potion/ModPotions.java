package net.notcherry.dungeonmod.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, DungeonMod.MOD_ID);

    public static final RegistryObject<Potion> STUN_POTION =
            POTIONS.register("stun_potion",
                    () -> new Potion(new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 400))); // 5 seconds

    public static final RegistryObject<Potion> MANA_SICKNESS_POTION =
            POTIONS.register("mana_sickness_potion",
                    () -> new Potion(new MobEffectInstance(ModEffects.MANA_SICKNESS_EFFECT.get(), 400))); // 5 seconds

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
