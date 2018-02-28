package com.astraldragon.AlchemicalSwordsMod.JEI;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class InfuserRecipeCategory extends BlankRecipeCategory<InfuserRecipeWrapper> implements IRecipeCategory<InfuserRecipeWrapper>
{

	private static final int craftOutputSlot = 0;
	private static final int craftInputSlot1 = 1;

	public static final int width = 129;
	public static final int height = 81;

	private final IDrawable background;
	private final String localizedName;
	private final ICraftingGridHelper craftingGridHelper;

	public InfuserRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("aswords", "textures/gui/infuserJEI.png");
		background = guiHelper.createDrawable(location, 0, 0, width, height);
		localizedName = Translator.translateToLocal("gui.jei.category.infuser");
		craftingGridHelper = guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);
	}

	@Override
	public String getUid() {
		return AlchemicalSwords.INFUSING_UID;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, InfuserRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(craftOutputSlot, false, 106, 34);

		guiItemStacks.init(1, true, 8, 12);
		guiItemStacks.init(2, true, 36, 0);
		guiItemStacks.init(3, true, 64, 12);
		guiItemStacks.init(4, true, 0, 33);
		guiItemStacks.init(5, true, 36, 33);
		guiItemStacks.init(6, true, 72, 33);
		guiItemStacks.init(7, true, 9, 56);
		guiItemStacks.init(8, true, 36, 63);
		guiItemStacks.init(9, true, 63, 56);

		if (recipeWrapper instanceof InfuserRecipeWrapper) {
			InfuserRecipeWrapper wrapper = (InfuserRecipeWrapper) recipeWrapper;
			craftingGridHelper.setInput(guiItemStacks, wrapper.getInputs(), wrapper.getWidth(), wrapper.getHeight());
			craftingGridHelper.setOutput(guiItemStacks, wrapper.getOutputs());
		}
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, InfuserRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(craftOutputSlot, false, 106, 34);

		guiItemStacks.init(1, true, 8, 12);
		guiItemStacks.init(2, true, 36, 0);
		guiItemStacks.init(3, true, 64, 12);
		guiItemStacks.init(4, true, 0, 33);
		guiItemStacks.init(5, true, 36, 33);
		guiItemStacks.init(6, true, 72, 33);
		guiItemStacks.init(7, true, 9, 56);
		guiItemStacks.init(8, true, 36, 63);
		guiItemStacks.init(9, true, 63, 56);


		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class);

		if (recipeWrapper instanceof IShapedCraftingRecipeWrapper) {
			IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) recipeWrapper;
			craftingGridHelper.setInputStacks(guiItemStacks, inputs, wrapper.getWidth(), wrapper.getHeight());
			craftingGridHelper.setOutput(guiItemStacks, outputs);
		} else {
			craftingGridHelper.setInputStacks(guiItemStacks, inputs);
			craftingGridHelper.setOutput(guiItemStacks, outputs);
		}
	}

}
