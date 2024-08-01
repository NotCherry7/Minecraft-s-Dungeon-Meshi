package net.notcherry.dungeonmod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WoodenWandItem extends Item {
    private String selectedSpell;

    public WoodenWandItem(Properties properties) {
        super(properties);
        this.selectedSpell = "default_spell";
    }

    public String getSelectedSpell() {
        return selectedSpell;
    }

    public void setSelectedSpell(String selectedSpell) {
        this.selectedSpell = selectedSpell;
    }

    // Save to NBT
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
        if (!world.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putString("SelectedSpell", selectedSpell);
            stack.setTag(tag);
        }
    }

    // Read from NBT when the item is loaded
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag tag = super.getShareTag(stack);
        if (tag != null && tag.contains("SelectedSpell")) {
            this.selectedSpell = tag.getString("SelectedSpell");
        }
        return tag;
    }

    // Write to NBT when the item is saved
    @Override
    public void readShareTag(ItemStack stack, CompoundTag nbt) {
        super.readShareTag(stack, nbt);
        if (nbt != null) {
            this.selectedSpell = nbt.getString("SelectedSpell");
        }
    }
}
