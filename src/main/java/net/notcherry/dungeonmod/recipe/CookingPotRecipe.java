package net.notcherry.dungeonmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.notcherry.dungeonmod.DungeonMod;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CookingPotRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public CookingPotRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        // Count the occurrences of each ingredient in the container
        Map<Ingredient, Integer> containerCounts = new HashMap<>();
        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            ItemStack itemStack = pContainer.getItem(i);
            if (!itemStack.isEmpty()) {
                for (Ingredient ingredient : inputItems) {
                    if (ingredient.test(itemStack)) {
                        containerCounts.put(ingredient, containerCounts.getOrDefault(ingredient, 0) + 1);
                    }
                }
            }
        }

        // Count the required occurrences of each ingredient
        Map<Ingredient, Integer> requiredCounts = new HashMap<>();
        for (Ingredient ingredient : inputItems) {
            requiredCounts.put(ingredient, requiredCounts.getOrDefault(ingredient, 0) + 1);
        }

        // Check if the counts match
        for (Map.Entry<Ingredient, Integer> entry : requiredCounts.entrySet()) {
            if (!containerCounts.containsKey(entry.getKey()) || containerCounts.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CookingPotRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "cooking_pot";
    }

    public static class Serializer implements RecipeSerializer<CookingPotRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(DungeonMod.MOD_ID, "cooking_pot");

        @Override
        public CookingPotRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();

            for (int i = 0; i < ingredients.size(); i++) {
                JsonObject ingredientObject = ingredients.get(i).getAsJsonObject();
                Ingredient ingredient = Ingredient.fromJson(ingredientObject);
                int count = GsonHelper.getAsInt(ingredientObject, "count", 1);

                for (int j = 0; j < count; j++) {
                    inputs.add(ingredient);
                }
            }

            return new CookingPotRecipe(inputs, output, pRecipeId);
        }

        @Override
        public @Nullable CookingPotRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new CookingPotRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, CookingPotRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient: pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}