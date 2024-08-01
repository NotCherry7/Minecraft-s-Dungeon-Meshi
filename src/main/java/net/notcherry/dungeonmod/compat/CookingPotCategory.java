package net.notcherry.dungeonmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.block.ModBlocks;
import net.notcherry.dungeonmod.recipe.CookingPotRecipe;

public class CookingPotCategory implements IRecipeCategory<CookingPotRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(DungeonMod.MOD_ID, "cooking_pot");
    public static final ResourceLocation TEXTURE = new ResourceLocation(DungeonMod.MOD_ID,
            "textures/gui/cooking_pot_gui.png");

    public static final RecipeType<CookingPotRecipe> COOKING_POT_TYPE =
            new RecipeType<>(UID, CookingPotRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CookingPotCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.COOKING_POT.get()));
    }

    @Override
    public RecipeType<CookingPotRecipe> getRecipeType() {
        return COOKING_POT_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.dungeonmod.cooking_pot");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CookingPotRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 11).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 62, 11).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 98, 11).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 116, 11).addIngredients(recipe.getIngredients().get(4));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }
}
