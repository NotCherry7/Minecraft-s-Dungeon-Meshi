package net.notcherry.dungeonmod.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.notcherry.dungeonmod.DungeonMod;

public class ModModelLayers {
    public static final ModelLayerLocation MANDRAKE_LAYER = new ModelLayerLocation(
            new ResourceLocation(DungeonMod.MOD_ID, "mandrake_layer"), "main");

    public static final ModelLayerLocation WALKINGMUSHROOM_LAYER = new ModelLayerLocation(
            new ResourceLocation(DungeonMod.MOD_ID, "walking_mushroom_layer"), "main");

    public static final ModelLayerLocation HUGESCORPION_LAYER = new ModelLayerLocation(
            new ResourceLocation(DungeonMod.MOD_ID, "huge_scorpion_layer"), "main");

    public static final ModelLayerLocation GOLEM_LAYER = new ModelLayerLocation(
            new ResourceLocation(DungeonMod.MOD_ID, "golem_layer"), "main");
}
