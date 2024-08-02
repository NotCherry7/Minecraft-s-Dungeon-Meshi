package net.notcherry.dungeonmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.block.ModBlocks;
import net.notcherry.dungeonmod.block.entity.portal.*;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DungeonMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<CookingPotBlockEntity>> COOKING_POT_BE =
            BLOCK_ENTITIES.register("cooking_pot_be", () ->
                    BlockEntityType.Builder.of(CookingPotBlockEntity::new, ModBlocks.COOKING_POT.get()).build(null));

    public static final RegistryObject<BlockEntityType<Portal1BlockEntity>> PORTAL_1_BE = BLOCK_ENTITIES.register("portal_1_be", () -> BlockEntityType.Builder.of(Portal1BlockEntity::new, ModBlocks.PORTAL_1.get()).build(null));
    public static final RegistryObject<BlockEntityType<Portal2BlockEntity>> PORTAL_2_BE = BLOCK_ENTITIES.register("portal_2_be", () -> BlockEntityType.Builder.of(Portal2BlockEntity::new, ModBlocks.PORTAL_2.get()).build(null));
    public static final RegistryObject<BlockEntityType<Portal3BlockEntity>> PORTAL_3_BE = BLOCK_ENTITIES.register("portal_3_be", () -> BlockEntityType.Builder.of(Portal3BlockEntity::new, ModBlocks.PORTAL_3.get()).build(null));
    public static final RegistryObject<BlockEntityType<Portal4BlockEntity>> PORTAL_4_BE = BLOCK_ENTITIES.register("portal_4_be", () -> BlockEntityType.Builder.of(Portal4BlockEntity::new, ModBlocks.PORTAL_4.get()).build(null));
    public static final RegistryObject<BlockEntityType<Portal5BlockEntity>> PORTAL_5_BE = BLOCK_ENTITIES.register("portal_5_be", () -> BlockEntityType.Builder.of(Portal5BlockEntity::new, ModBlocks.PORTAL_5.get()).build(null));
    public static final RegistryObject<BlockEntityType<Portal6BlockEntity>> PORTAL_6_BE = BLOCK_ENTITIES.register("portal_6_be", () -> BlockEntityType.Builder.of(Portal6BlockEntity::new, ModBlocks.PORTAL_6.get()).build(null));
    public static final RegistryObject<BlockEntityType<Portal7BlockEntity>> PORTAL_7_BE = BLOCK_ENTITIES.register("portal_7_be", () -> BlockEntityType.Builder.of(Portal7BlockEntity::new, ModBlocks.PORTAL_7.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
