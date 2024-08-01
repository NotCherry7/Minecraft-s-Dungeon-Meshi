package net.notcherry.dungeonmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.entity.ModEntities;
import net.notcherry.dungeonmod.entity.custom.GolemEntity;
import net.notcherry.dungeonmod.entity.custom.HugeScorpionEntity;
import net.notcherry.dungeonmod.entity.custom.MandrakeEntity;
import net.notcherry.dungeonmod.entity.custom.WalkingMushroomEntity;

@Mod.EventBusSubscriber(modid = DungeonMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventBusForgeEvents {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof GolemEntity) {
            GolemEntity dirtEntity = (GolemEntity) event.getEntity();
            generateDirtBlocks(dirtEntity.level(), dirtEntity.blockPosition());
        }
    }

    private static void generateDirtBlocks(Level world, BlockPos pos) {
        BlockState dirtBlock = Blocks.FARMLAND.defaultBlockState();

        // Define the "H" shape coordinates relative to the entity's death location
        int[][] hShape = {
                {0, 0}, {0, 1}, {0, 2}, // Vertical left
                {2, 0}, {2, 1}, {2, 2}, // Vertical right
                {1, 1}                  // Horizontal middle
        };

        for (int[] offset : hShape) {
            BlockPos targetPos = pos.offset(offset[0], 0, offset[1]);
            if (world.getBlockState(targetPos).isAir()) {
                BlockPos fallPos = findGround(world, targetPos);
                world.setBlock(fallPos, dirtBlock, 3);
            }
        }
    }

    private static BlockPos findGround(Level world, BlockPos pos) {
        while (world.getBlockState(pos.below()).isAir()) {
            pos = pos.below();
        }
        return pos;
    }
}
