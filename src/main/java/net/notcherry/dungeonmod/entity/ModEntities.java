package net.notcherry.dungeonmod.entity;

import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.custom.GolemEntity;
import net.notcherry.dungeonmod.entity.custom.HugeScorpionEntity;
import net.notcherry.dungeonmod.entity.custom.MandrakeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.entity.custom.WalkingMushroomEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DungeonMod.MOD_ID);

    public static final RegistryObject<EntityType<MandrakeEntity>> MANDRAKE =
            ENTITY_TYPES.register("mandrake", () -> EntityType.Builder.of(MandrakeEntity::new, MobCategory.CREATURE)
                    .sized(0.2f, 1.4f).build("mandrake"));

    public static final RegistryObject<EntityType<WalkingMushroomEntity>> WALKING_MUSHROOM =
            ENTITY_TYPES.register("walking_mushroom", () -> EntityType.Builder.of(WalkingMushroomEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 0.9f).build("walking_mushroom"));

    public static final RegistryObject<EntityType<HugeScorpionEntity>> HUGE_SCORPION =
            ENTITY_TYPES.register("huge_scorpion", () -> EntityType.Builder.of(HugeScorpionEntity::new, MobCategory.MONSTER)
                    .sized(1.05f, 0.4f).build("huge_scorpion"));

    public static final RegistryObject<EntityType<GolemEntity>> GOLEM =
            ENTITY_TYPES.register("golem", () -> EntityType.Builder.of(GolemEntity::new, MobCategory.MONSTER)
                    .sized(1.3f, 3f).build("golem"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
