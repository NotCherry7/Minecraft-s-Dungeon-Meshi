package net.notcherry.dungeonmod.worldgen.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

public class StructureFeature extends Structure {
    protected StructureFeature(StructureSettings pSettings) {
        super(pSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext generationContext) {
        // Example implementation: Find a suitable generation point in the chunk
        // This implementation places the structure at intervals of 40 blocks

        BlockPos start = generationContext.chunkPos().getWorldPosition();
        WorldgenRandom random = generationContext.random();
        ChunkGenerator generator = generationContext.chunkGenerator();

        // Offset the structure placement based on the chunk coordinates
        int x = start.getX() + random.nextInt(40);
        int z = start.getZ() + random.nextInt(40);

        // Adjust the Y-coordinate to be on the surface or at a specific height
        int y = generationContext.chunkGenerator().getSeaLevel() + 1;

        BlockPos pos = new BlockPos(x, y, z);

        // Create a GenerationStub to initiate structure generation
        return Optional.of(new GenerationStub(pos, (Consumer<StructurePiecesBuilder>) generator));
    }

    @Override
    public StructureType<?> type() {
        return null;
    }
}


//    @Override
//    public boolean place(StructurePlace,e context, ChunkGenerator generator, Random random, BlockPos pos, StructurePlacementData data) {
//        // Define the distance at which the structure should be placed
//        int distance = 40;
//
//        // Calculate the coordinates for placement
//        BlockPos structurePos = pos;
//
//        // Place the structure if the position is valid
//        if ((structurePos.getX() % distance == 0) && (structurePos.getZ() % distance == 0)) {
//            // Your code to place the structure
//            return super.place(context, generator, random, structurePos, data);
//        }
//        return false;
//    }
//}
