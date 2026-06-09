package net.mofusya.mochi_craft.data;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.ModBlocks;
import net.mofusya.mochi_craft.items.ModItems;
import net.mofusya.ornatelib.registries.toolset.ToolSet;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        foodSmelting(writer, ModItems.RICE.get(), ModItems.COOKED_RICE.get());
        foodSmelting(writer, ModItems.CUT_MOCHI.get(), ModItems.COOKED_CUT_MOCHI.get());
        foodSmelting(writer, ModItems.COOKED_CUT_MOCHI.get(), ModItems.WELL_COOKED_CUT_MOCHI.get());
        foodSmelting(writer, ModItems.WELL_COOKED_CUT_MOCHI.get(), ModItems.OVERCOOKED_CUT_MOCHI.get());
        foodSmelting(writer, ModItems.OVERCOOKED_CUT_MOCHI.get(), ModItems.CARBON.get());
        foodSmelting(writer, ModItems.MOLTEN_MOCHI.get(), ModItems.MOCHI_GAS.get());

        compressing(writer, ModItems.BIT_OF_MOCHI.get(), ModItems.MOCHI.get(), true);
        compressing(writer, ModItems.MOCHI.get(), ModBlocks.MOCHI_BLOCK.get(), false);
        compressing(writer, ModBlocks.MOCHI_BLOCK.get(), ModBlocks.MOCHI_BLOCK_X9.get(), false);
        compressing(writer, ModBlocks.MOCHI_BLOCK_X9.get(), ModBlocks.MOCHI_BLOCK_X81.get(), false);
        compressing(writer, ModBlocks.MOCHI_BLOCK_X81.get(), ModBlocks.MOCHI_BLOCK_X729.get(), false);

        tools(writer, ModBlocks.MOCHI_BLOCK.get(), ModItems.MOCHI_TOOLS);
    }

    private static void tools(Consumer<FinishedRecipe> writer, ItemLike base, ToolSet toolSet) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, toolSet.getItem(ToolSet.Type.SWORD).get())
                .pattern("#")
                .pattern("#")
                .pattern("%")
                .define('#', Ingredient.of(base))
                .define('%', Ingredient.of(Items.STICK))
                .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                        .of(base).build()))
                .save(writer, MochiCraft.MOD_ID + ":" + getItemName(toolSet.getItem(ToolSet.Type.SWORD).get()) + "_from_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, toolSet.getItem(ToolSet.Type.AXE).get())
                .pattern("##")
                .pattern("#%")
                .pattern(" %")
                .define('#', Ingredient.of(base))
                .define('%', Ingredient.of(Items.STICK))
                .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                        .of(base).build()))
                .save(writer, MochiCraft.MOD_ID + ":" + getItemName(toolSet.getItem(ToolSet.Type.AXE).get()) + "_from_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, toolSet.getItem(ToolSet.Type.PICKAXE).get())
                .pattern("###")
                .pattern(" % ")
                .pattern(" % ")
                .define('#', Ingredient.of(base))
                .define('%', Ingredient.of(Items.STICK))
                .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                        .of(base).build()))
                .save(writer, MochiCraft.MOD_ID + ":" + getItemName(toolSet.getItem(ToolSet.Type.PICKAXE).get()) + "_from_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, toolSet.getItem(ToolSet.Type.SHOVEL).get())
                .pattern("#")
                .pattern("%")
                .pattern("%")
                .define('#', Ingredient.of(base))
                .define('%', Ingredient.of(Items.STICK))
                .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                        .of(base).build()))
                .save(writer, MochiCraft.MOD_ID + ":" + getItemName(toolSet.getItem(ToolSet.Type.SHOVEL).get()) + "_from_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, toolSet.getItem(ToolSet.Type.HOE).get())
                .pattern("##")
                .pattern(" %")
                .pattern(" %")
                .define('#', Ingredient.of(base))
                .define('%', Ingredient.of(Items.STICK))
                .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                        .of(base).build()))
                .save(writer, MochiCraft.MOD_ID + ":" + getItemName(toolSet.getItem(ToolSet.Type.HOE).get()) + "_from_crafting");
    }

    private static void compressing(Consumer<FinishedRecipe> writer, ItemLike base, ItemLike compressed, boolean small) {
        if (small) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, compressed)
                    .pattern("##")
                    .pattern("##")
                    .define('#', Ingredient.of(base))
                    .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                            .of(base).build()))
                    .save(writer, MochiCraft.MOD_ID + ":" + getItemName(compressed) + "_from_compressing_" + getItemName(base));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, base, 4)
                    .requires(compressed)
                    .unlockedBy(getHasName(compressed), inventoryTrigger(ItemPredicate.Builder.item()
                            .of(compressed).build()))
                    .save(writer, MochiCraft.MOD_ID + ":" + getItemName(base) + "_from_separating_" + getItemName(compressed));
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, compressed)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', Ingredient.of(base))
                    .unlockedBy(getHasName(base), inventoryTrigger(ItemPredicate.Builder.item()
                            .of(base).build()))
                    .save(writer, MochiCraft.MOD_ID + ":" + getItemName(compressed) + "_from_compressing_" + getItemName(base));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, base, 9)
                    .requires(compressed)
                    .unlockedBy(getHasName(compressed), inventoryTrigger(ItemPredicate.Builder.item()
                            .of(compressed).build()))
                    .save(writer, MochiCraft.MOD_ID + ":" + getItemName(base) + "_from_separating_" + getItemName(compressed));
        }
    }

    private static void foodSmelting(Consumer<FinishedRecipe> writer, ItemLike ingredient, ItemLike result) {
        smelting(writer, List.of(ingredient), RecipeCategory.MISC, result, 0.25f, getHasName(result));
        smoking(writer, List.of(ingredient), RecipeCategory.MISC, result, 0.25f, getHasName(result));
        campFireCooking(writer, List.of(ingredient), RecipeCategory.MISC, result, 0.25f, getHasName(result));
    }

    private static void smelting(Consumer<FinishedRecipe> writer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, String pGroup) {
        oreCooking(writer, RecipeSerializer.SMELTING_RECIPE, ingredients, category, result, experience, 200, pGroup, "_from_smelting");
    }

    private static void smoking(Consumer<FinishedRecipe> writer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, String pGroup) {
        oreCooking(writer, RecipeSerializer.SMOKING_RECIPE, ingredients, category, result, experience, 200, pGroup, "_from_smoking");
    }

    private static void campFireCooking(Consumer<FinishedRecipe> writer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, String pGroup) {
        oreCooking(writer, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, ingredients, category, result, experience, 200, pGroup, "_from_campfire_cooking");
    }
}
