package net.notcherry.dungeonmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.entity.custom.GolemEntity;
import net.notcherry.dungeonmod.entity.custom.HugeScorpionEntity;
import net.notcherry.dungeonmod.entity.custom.MandrakeEntity;
import net.notcherry.dungeonmod.entity.custom.WalkingMushroomEntity;

@Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MANDRAKE.get(), MandrakeEntity.createAttributes().build());
        event.put(ModEntities.WALKING_MUSHROOM.get(), WalkingMushroomEntity.createAttributes().build());
        event.put(ModEntities.HUGE_SCORPION.get(), HugeScorpionEntity.createAttributes().build());
        event.put(ModEntities.GOLEM.get(), GolemEntity.createAttributes().build());
    }
}
