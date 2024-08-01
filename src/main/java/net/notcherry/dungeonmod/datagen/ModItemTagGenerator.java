package net.notcherry.dungeonmod.datagen;

import net.notcherry.dungeonmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.notcherry.dungeonmod.DungeonMod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, DungeonMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
//        this.tag(ItemTags.TRIMMABLE_ARMOR)
//                .add(ModItems.EMERALD_HELMET.get(),
//                        ModItems.EMERALD_CHESTPLATE.get(),
//                        ModItems.EMERALD_LEGGINGS.get(),
//                        ModItems.EMERALD_BOOTS.get());
//
//        this.tag(ItemTags.MUSIC_DISCS)
//                .add(ModItems.HAPPINESS_DOES_NOT_WAIT_MUSIC_DISC.get());
//
//        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
//                .add(ModItems.HAPPINESS_DOES_NOT_WAIT_MUSIC_DISC.get(),
//                    ModItems.ETERNAL_DREAM_MUSIC_DISC.get(),
//                    ModItems.FLY_OCTO_FLY_MUSIC_DISC.get());
    }
}