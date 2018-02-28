package com.astraldragon.AlchemicalSwordsMod.JEI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.astraldragon.AlchemicalSwordsMod.crafting.InfusingRecipe;

import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

public class InfuserRecipeWrapper extends BlankRecipeWrapper implements IRecipeWrapper {

	private final InfusingRecipe recipe;

	public InfuserRecipeWrapper(InfusingRecipe recipe) {
		this.recipe = recipe;
		for (ItemStack itemStack : this.recipe.recipeItems) {
			if (itemStack != null && itemStack.stackSize != 1) {
				itemStack.stackSize = 1;
			}
		}
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		List<ItemStack> recipeItems = Arrays.asList(recipe.recipeItems);
		ItemStack recipeOutput = recipe.getRecipeOutput();
		try {
			ingredients.setInputs(ItemStack.class, recipeItems);
			if (recipeOutput != null) {
				ingredients.setOutput(ItemStack.class, recipeOutput);
			}
		} catch (RuntimeException e) {
			String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipeItems, recipeOutput);
			throw new BrokenCraftingRecipeException(info, e);
		}
	}

	@Override
	public List getInputs() {
		return Arrays.asList(recipe.recipeItems);
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getRecipeOutput());
	}

	public int getWidth() {
		return recipe.recipeWidth;
	}

	public int getHeight() {
		return recipe.recipeHeight;
	}

}
