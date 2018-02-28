package com.astraldragon.AlchemicalSwordsMod.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class InfusingRecipe implements IRecipe
{
	 /** How many horizontal slots this recipe is wide. */
    public int recipeWidth;
    /** How many vertical slots this recipe uses. */
    public int recipeHeight;
    /** Is a array of ItemStack that composes the recipe. */
    public final ItemStack[] recipeItems;
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;
    private boolean field_92101_f;
    private static final String __OBFID = "CL_00000093";

    public InfusingRecipe(ItemStack result, Object... recipe)
    {
    	recipeOutput = result.copy();

        String shape = "";
        int idx = 0;

        if (recipe[idx] instanceof String[])
        {
            String[] parts = ((String[])recipe[idx++]);

            for (String s : parts)
            {
                recipeWidth = s.length();
                shape += s;
            }

            recipeHeight = parts.length;
        }
        else
        {
            while (recipe[idx] instanceof String)
            {
                String s = (String)recipe[idx++];
                shape += s;
                recipeWidth = s.length();
                recipeHeight++;
            }
        }

        if (recipeWidth * recipeHeight != shape.length())
        {
            String ret = "Invalid shaped ore recipe: ";
            for (Object tmp :  recipe)
            {
                ret += tmp + ", ";
            }
            ret += recipeOutput;
            throw new RuntimeException(ret);
        }

        HashMap<Character, Object> itemMap = new HashMap<Character, Object>();

        for (; idx < recipe.length; idx += 2)
        {
            Character chr = (Character)recipe[idx];
            Object in = recipe[idx + 1];

            if (in instanceof ItemStack)
            {
                itemMap.put(chr, ((ItemStack)in).copy());
            }
            else if (in instanceof Item)
            {
                itemMap.put(chr, new ItemStack((Item)in));
            }
            else if (in instanceof Block)
            {
                itemMap.put(chr, new ItemStack((Block)in, 1, OreDictionary.WILDCARD_VALUE));
            }
            else if (in instanceof String)
            {
                itemMap.put(chr, OreDictionary.getOres((String)in));
            }
            else
            {
                String ret = "Invalid shaped ore recipe: ";
                for (Object tmp :  recipe)
                {
                    ret += tmp + ", ";
                }
                ret += recipeOutput;
                throw new RuntimeException(ret);
            }
        }

        recipeItems = new ItemStack[recipeWidth * recipeHeight];
        int x = 0;
        for (char chr : shape.toCharArray())
        {
            recipeItems[x++] = (ItemStack) itemMap.get(chr);
        }
    }
    
    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     * @return The recipes input vales.
     */
    public Object[] getInput()
    {
        return recipeItems;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput.copy();
    }

    public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_)
    {
        ItemStack[] aitemstack = new ItemStack[p_179532_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            ItemStack itemstack = p_179532_1_.getStackInSlot(i);
            aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
        }

        return aitemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv_, World worldIn)
    {
        for (int i = 0; i <= 3 - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= 3 - this.recipeHeight; ++j)
            {
                if (this.checkMatch(inv_, i, j, true))
                {
                    return true;
                }

                if (this.checkMatch(inv_, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }
    
    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting inv, int p_77573_2_, int p_77573_3_, boolean p_77573_4_)
    {
        if (checkMatch(inv))
        {
            //LogHelper.info("Recipe Checked. We have a match!");
            return true;
        }

        /*
        if (mirrored && checkMatch(inv, true))
        {
            return true;
        }
        */

        //LogHelper.info("Recipe Checked. No match.");
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean checkMatch(InventoryCrafting inv) //, boolean mirror)
    {
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                Object target = recipeItems[x + (y * 3)];

                ItemStack slot = inv.getStackInRowAndColumn(x, y);
                /*
                if(slot == null)
                    LogHelper.info("Crafting slot " + x + "," + y + ": Null");
                else
                    LogHelper.info("Crafting slot " + x + "," + y + ": " + slot.getDisplayName());
                */

                if (target instanceof ItemStack)
                {
                    //LogHelper.info("Comparing slot to: " + ((ItemStack)target).getDisplayName());

                    if (!OreDictionary.itemMatches((ItemStack)target, slot, false))
                    {
                        //LogHelper.info("Crafting slot " + x + "," + y + " doesn't match ore dictionary.");
                        return false;
                    }
                }
                else if (target instanceof List)
                {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>)target).iterator();
                    while (itr.hasNext() && !matched)
                    {
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
                    }

                    if (!matched)
                    {
                        return false;
                    }
                }
                else if (target == null && slot != null)
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
    {
        ItemStack itemstack = this.getRecipeOutput().copy();

        if (this.field_92101_f)
        {
            for (int i = 0; i < p_77572_1_.getSizeInventory(); ++i)
            {
                ItemStack itemstack1 = p_77572_1_.getStackInSlot(i);

                if (itemstack1 != null && itemstack1.hasTagCompound())
                {
                    itemstack.setTagCompound((NBTTagCompound)itemstack1.getTagCompound().copy());
                }
            }
        }

        return itemstack;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeWidth * this.recipeHeight;
    }

    public InfusingRecipe func_92100_c()
    {
        this.field_92101_f = true;
        return this;
    }
    
    @Override
    public String toString()
    {
        String str = "Output: " + recipeOutput.getUnlocalizedName() + ", Inputs: ";
        for(Object o : recipeItems)
        {
            if(o instanceof ItemStack)
                str += ((ItemStack)o).getUnlocalizedName();
            else if(o instanceof Item)
                str += ((Item)o).getUnlocalizedName();
            else if(o instanceof Block)
                str += ((Block)o).getUnlocalizedName();
            else if(o instanceof String)
                str += (String)o;
            else if(o instanceof List)
                str += "List of " + ((List)o).size();
            else
                str += "<UNKNOWN>";
            str += ", ";
        }
        str = str.substring(0, str.length()-2);
        return str;
    }
}