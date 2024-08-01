package net.notcherry.dungeonmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.notcherry.dungeonmod.DungeonMod;
import net.notcherry.dungeonmod.recipe.CookingPotRecipe;
import net.notcherry.dungeonmod.screen.CookingPotScreen;

import java.util.List;

@JeiPlugin
public class JEIDungeonModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(DungeonMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CookingPotCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<CookingPotRecipe> cookingPotRecipes = recipeManager.getAllRecipesFor(CookingPotRecipe.Type.INSTANCE);
        registration.addRecipes(CookingPotCategory.COOKING_POT_TYPE, cookingPotRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CookingPotScreen.class, 80,30,20,30,
                CookingPotCategory.COOKING_POT_TYPE);
    }
}
