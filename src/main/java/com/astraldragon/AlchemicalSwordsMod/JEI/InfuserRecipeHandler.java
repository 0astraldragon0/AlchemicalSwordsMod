package com.astraldragon.AlchemicalSwordsMod.JEI;

import javax.annotation.Nonnull;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.astraldragon.AlchemicalSwordsMod.crafting.InfusingRecipe;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.item.ItemStack;

public class InfuserRecipeHandler implements IRecipeHandler<InfusingRecipe>
{

	@Override
	public Class<InfusingRecipe> getRecipeClass() {
		return InfusingRecipe.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return AlchemicalSwords.INFUSING_UID;
	}

	@Override
	public String getRecipeCategoryUid(InfusingRecipe recipe) {
		return AlchemicalSwords.INFUSING_UID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(InfusingRecipe recipe) {
		return new InfuserRecipeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(InfusingRecipe recipe) {
		if (recipe.getRecipeOutput() == null) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
			Log.error("Recipe has no outputs. {}", recipeInfo);
			return false;
		}
		int inputCount = 0;
		for (ItemStack input : recipe.recipeItems) {
			if (input != null) {
				inputCount++;
			}
		}
		if (inputCount > 9) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
			Log.error("Recipe has too many inputs. {}", recipeInfo);
			return false;
		}
		if (inputCount == 0) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
			Log.error("Recipe has no inputs. {}", recipeInfo);
			return false;
		}
		return true;
	}
}
