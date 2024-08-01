package net.notcherry.dungeonmod.datagen;


import net.notcherry.dungeonmod.block.ModBlocks;
import net.notcherry.dungeonmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.DungeonMod;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DungeonMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.FIRE_SHARD);
        // MANDRAKE
        simpleItem(ModItems.MANDRAKE);
        simpleItem(ModItems.MANDRAKE_HEAD);
        withExistingParent(ModItems.MANDRAKE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        // WALKING MUSHROOM
        simpleItem(ModItems.WALKING_MUSHROOM_FOOT);
        simpleItem(ModItems.WALKING_MUSHROOM_CHUNK);
        withExistingParent(ModItems.WALKING_MUSHROOM_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        // HUGE SCORPION
        simpleItem(ModItems.HUGE_SCORPION_BODY);
        simpleItem(ModItems.HUGE_SCORPION_HEAD);
        handheldItem(ModItems.HUGE_SCORPION_BARB);
        handheldItem(ModItems.HUGE_SCORPION_CLAW);
        handheldItem(ModItems.HUGE_SCORPION_LEG);
        handheldItem(ModItems.HUGE_SCORPION_TAIL);
        withExistingParent(ModItems.HUGE_SCORPION_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        // GOLEM
        withExistingParent(ModItems.GOLEM_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        //withExistingParent(ModItems.MANDRAKE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        wallItem(ModBlocks.COBBLED_DUNGEON_STONE_WALL, ModBlocks.COBBLED_DUNGEON_STONE);
        wallItem(ModBlocks.DUNGEON_STONE_TILE_WALL, ModBlocks.DUNGEON_STONE_TILES);
        wallItem(ModBlocks.DUNGEON_STONE_BRICK_WALL, ModBlocks.DUNGEON_STONE_BRICKS);
        wallItem(ModBlocks.POLISHED_DUNGEON_STONE_WALL, ModBlocks.POLISHED_DUNGEON_STONE);

        evenSimplerBlockItem(ModBlocks.COBBLED_DUNGEON_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.DUNGEON_STONE_TILE_STAIRS);
        evenSimplerBlockItem(ModBlocks.DUNGEON_STONE_BRICK_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_DUNGEON_STONE_STAIRS);

        evenSimplerBlockItem(ModBlocks.COBBLED_DUNGEON_STONE_SLAB);
        evenSimplerBlockItem(ModBlocks.DUNGEON_STONE_TILE_SLAB);
        evenSimplerBlockItem(ModBlocks.DUNGEON_STONE_BRICK_SLAB);
        evenSimplerBlockItem(ModBlocks.POLISHED_DUNGEON_STONE_SLAB);

    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(DungeonMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    private void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(DungeonMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(DungeonMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(DungeonMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(DungeonMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DungeonMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DungeonMod.MOD_ID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DungeonMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = DungeonMod.MOD_ID; // Change this to your mod id

        if (itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}