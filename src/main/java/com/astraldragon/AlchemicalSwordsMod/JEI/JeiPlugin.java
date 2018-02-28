package com.astraldragon.AlchemicalSwordsMod.JEI;

import javax.annotation.Nonnull;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.astraldragon.AlchemicalSwordsMod.crafting.InfusingManager;
import com.astraldragon.AlchemicalSwordsMod.gui.GuiInfuser;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin extends BlankModPlugin implements IModPlugin
{
    public static IJeiHelpers jeiHelper;

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        jeiHelper = registry.getJeiHelpers();

        registry.addRecipeCategories(new InfuserRecipeCategory(jeiHelper.getGuiHelper()));
        registry.addRecipeHandlers(new InfuserRecipeHandler());

        registry.addRecipes(InfusingManager.getInstance().getRecipeList());
        
        registry.addRecipeClickArea(GuiInfuser.class, 88, 32, 28, 23, AlchemicalSwords.INFUSING_UID);
        registry.addRecipeCategoryCraftingItem(new ItemStack(AlchemicalSwords.infuser), AlchemicalSwords.INFUSING_UID);
    }
}