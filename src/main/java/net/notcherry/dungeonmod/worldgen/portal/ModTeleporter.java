package net.notcherry.dungeonmod.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.block.ModBlocks;
import net.notcherry.dungeonmod.block.custom.ModPortalBlock;

import java.util.Optional;
import java.util.function.Function;


public class ModTeleporter implements ITeleporter {

    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public ModTeleporter(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);

        BlockPos destinationPos = thisPos;

        if (insideDimension) {
            BlockPos barrierPos = findBarrierLocation(destinationWorld, new BlockPos(0, 0, 0));
            if (barrierPos != null) {
                destinationPos = barrierPos.below(2); // Move 5 blocks below the detected barrier

                // Log the barrier position and the teleport position
                System.out.println("Barrier found at: " + barrierPos);
                System.out.println("Teleporting to: " + destinationPos);
            } else {
                System.out.println("No barrier found.");
            }
        }

//        entity.teleportTo(destinationPos.getX() + 0.5, destinationPos.getY() + 0.5, destinationPos.getZ() + 0.5);
        entity.teleportTo(-8, 112, 8);

        return entity;
    }

    private BlockPos findBarrierLocation(ServerLevel world, BlockPos center) {

        int radius = 10; // Search radius around (0, 0)
        int yRadius = 100; // Search radius around (0, 0)

        for (int x = -radius; x <= radius; x++) {
            for (int y = -yRadius; y <= yRadius; y++) { // Include y-level
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = center.offset(x, y, z);

                    // Debugging: Print the current position being checked
//                    System.out.println("Checking position: " + pos);

                    if (isBarrier(world, pos)) {
                        return pos;
                    }

//                    if (!isAir(world, pos)) {
//                        System.out.println(world.getBlockState(pos).getBlock());
//                    }
                }
            }
        }

        return null; // Return null if no barrier found
    }

    private boolean isBarrier(ServerLevel world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == Blocks.BARRIER;
    }

    private boolean isAir(ServerLevel world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == Blocks.VOID_AIR;
    }
}

//public class ModTeleporter implements ITeleporter {
//
//    public static BlockPos thisPos = BlockPos.ZERO;
//    public static boolean insideDimension = true;
//
//    public ModTeleporter(BlockPos pos, boolean insideDim) {
//        thisPos = pos;
//        insideDimension = insideDim;
//    }
//
//    @Override
//    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
//                              float yaw, Function<Boolean, Entity> repositionEntity) {
//        entity = repositionEntity.apply(false);
//        int y = 61;
//
//        if (!insideDimension) {
//            y = thisPos.getY();
//        }
//
//        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());
//
////        int tries = 0;
////        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
////                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
////                (destinationWorld.getBlockState(destinationPos.above()).getBlock()  != Blocks.AIR) &&
////                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25)) {
////            destinationPos = destinationPos.above(2);
////            tries++;
//
////        }
//
////        BlockPos spawnPos = findStructureLocation(currentWorld, destinationPos);
////        entity.teleportTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
//
//
////        if (insideDimension) {
////            boolean doSetBlock = true;
////            for (BlockPos checkPos : BlockPos.betweenClosed(destinationPos.below(10).west(10),
////                    destinationPos.above(10).east(10))) {
////                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof ModPortalBlock) {
////                    doSetBlock = false;
////                    break;
////                }
////            }
////            if (doSetBlock) {
////                destinationWorld.setBlock(destinationPos, ModBlocks.MOD_PORTAL.get().defaultBlockState(), 3);
////            }
////        }
//
//        if (insideDimension) {
//            BlockPos structurePos = findStructureLocation(destinationWorld, destinationPos);
//            if (structurePos != null) {
//                destinationPos = structurePos.below(5); // Move 5 blocks below the detected structure
//            }
//        }
//
//        entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());
//
//        return entity;
//    }
//
//    private BlockPos findStructureLocation(ServerLevel world, BlockPos center) {
//        // Define your search radius
//        int radius = 100;
//
//        // Iterate over a cubic region around the center
//        for (int x = -radius; x <= radius; x++) {
//            for (int z = -radius; z <= radius; z++) {
//                BlockPos pos = center.offset(x, 0, z);
//
//                // Check for the structure at this position
//                if (structureAt(world, pos)) {
//                    return pos;
//                }
//            }
//        }
//
//        // Return default position if no structure found
//        return center;
//    }
//
//    private boolean structureAt(ServerLevel world, BlockPos pos) {
//        StructureTemplateManager structureManager = world.getStructureManager();
//
//        // Checking for a specific block
//        for (BlockPos checkPos : BlockPos.betweenClosed(pos.below(10).west(10), pos.above(10).east(10))) {
//            if (world.getBlockState(checkPos).getBlock() == Blocks.BARRIER) {
//                return true;
//            }
//        }
//        return false;
//    }
//}

