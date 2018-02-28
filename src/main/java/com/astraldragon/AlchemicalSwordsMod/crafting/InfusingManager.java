package com.astraldragon.AlchemicalSwordsMod.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class InfusingManager {
	private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();
	private static final InfusingManager instance = new InfusingManager();

	public InfusingManager(){
		this.addInfusingRecipe(new ItemStack(AlchemicalSwords.runic_bricks,8),"BBB","BSB","BBB",'B',Blocks.STONEBRICK,'S',AlchemicalSwords.soul_crystal);
		this.addInfusingRecipe(new ItemStack(AlchemicalSwords.ender_ingot),"PPP","EGP","EEE",'P',Items.ENDER_PEARL,'E',Items.ENDER_EYE,'G',AlchemicalSwords.glass_ingot);
		this.addInfusingRecipe(new ItemStack(AlchemicalSwords.natura_ingot),"GGG","LIG","LEE",'G',Blocks.TALLGRASS,'E',Items.EMERALD,'L',Blocks.LEAVES,'I',AlchemicalSwords.glass_ingot);
		this.addInfusingRecipe(new ItemStack(AlchemicalSwords.fire_ingot),"BBB","LGB","FFF",'B',Items.BLAZE_POWDER,'L',Items.LAVA_BUCKET,'F',Items.FIRE_CHARGE,'G',AlchemicalSwords.glass_ingot);
		this.addInfusingRecipe(new ItemStack(AlchemicalSwords.ice_ingot),"III","PGI","PPD",'I',Blocks.ICE,'P',Blocks.PACKED_ICE,'D',Items.DIAMOND,'G',AlchemicalSwords.glass_ingot);
	}
	
	
	public void addInfusingRecipe(ItemStack stack, Object ... recipeComponents)
    {
        recipes.add(new InfusingRecipe(stack,recipeComponents));
    }
	
    public static InfusingManager getInstance(){
		return instance;
	}
    
    public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World worldIn)
    {
        Iterator iterator = this.recipes.iterator();
        InfusingRecipe recipe;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            recipe = (InfusingRecipe)iterator.next();
        }
        while (!recipe.matches(p_82787_1_, worldIn));

        return recipe.getRecipeOutput();
    }
    
    public ItemStack[] func_180303_b(InventoryCrafting p_180303_1_, World worldIn)
    {
        Iterator iterator = this.recipes.iterator();

        while (iterator.hasNext())
        {
            IRecipe irecipe = (IRecipe)iterator.next();

            if (irecipe.matches(p_180303_1_, worldIn))
            {
                return irecipe.getRemainingItems(p_180303_1_);
            }
        }

        ItemStack[] aitemstack = new ItemStack[p_180303_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            aitemstack[i] = p_180303_1_.getStackInSlot(i);
        }

        return aitemstack;
    }


	public List getRecipeList() {
		return recipes;
	}
}
