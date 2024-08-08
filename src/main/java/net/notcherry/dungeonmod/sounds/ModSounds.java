package net.notcherry.dungeonmod.sounds;

import net.minecraft.sounds.Music;
import net.notcherry.dungeonmod.DungeonMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DungeonMod.MOD_ID);

    public static final RegistryObject<SoundEvent> MANDRAKE_AMBIENT = registerSoundEvents("mandrake_ambient");
    public static final RegistryObject<SoundEvent> MANDRAKE_SCREAM = registerSoundEvents("mandrake_scream");
    public static final RegistryObject<SoundEvent> MANDRAKE_DEATH = registerSoundEvents("mandrake_death");

    public static final RegistryObject<SoundEvent> WALKING_MUSHROOM_SQUISH = registerSoundEvents("walking_mushroom_squish");
    public static final RegistryObject<SoundEvent> HEAVENLY_SOUNDS = registerSoundEvents("heavenly_sounds");

//    public static final RegistryObject<SoundEvent> HAPPINESS_DOES_NOT_WAIT = registerSoundEvents("happiness_does_not_wait");
//
//    public static final RegistryObject<SoundEvent> ETERNAL_DREAM = registerSoundEvents("eternal_dream");
//    public static final RegistryObject<SoundEvent> FLY_OCTO_FLY = registerSoundEvents("fly_octo_fly");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
