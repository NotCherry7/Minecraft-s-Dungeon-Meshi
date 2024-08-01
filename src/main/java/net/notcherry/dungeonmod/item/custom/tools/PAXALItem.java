package net.notcherry.dungeonmod.item.custom.tools;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class PAXALItem extends DiggerItem {

    private final boolean damageable;

    public PAXALItem(Tier tier, int attackDamage, float attackSpeed, boolean damageable, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
        this.damageable = damageable;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_SHOVEL) || state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.MINEABLE_WITH_PICKAXE)) ? this.getTier().getSpeed() : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_SHOVEL) || state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.MINEABLE_WITH_PICKAXE)) || super.isCorrectToolForDrops(state);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context); // Add custom behavior if necessary
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return damageable;
    }
}
