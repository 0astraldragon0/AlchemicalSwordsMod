package com.astraldragon.AlchemicalSwordsMod.gui;

import com.astraldragon.AlchemicalSwordsMod.crafting.InfusingManager;
import com.astraldragon.AlchemicalSwordsMod.crafting.SlotInfusing;
import com.astraldragon.AlchemicalSwordsMod.tileentities.TileEntityInfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/*
 * SLOTS:
 * 
 * Tile Entity 0-5 ........ 0  - 5
 * Player Inventory 6-32 .. 6  - 32
 * Player Inventory 0-8 ... 33 - 41
 */
public class ContainerInfuser extends Container {
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
	private TileEntityInfuser te;
	
	public ContainerInfuser(InventoryPlayer playerInv, TileEntityInfuser te) {
        this.te = te;
        
     
        this.addSlotToContainer(new SlotInfusing(playerInv.player, this.craftMatrix, this.craftResult, 0, 118, 36));

         // Tile Entity, Slot 0-9, Slot IDs 0-9
        this.addSlotToContainer(new Slot(this.craftMatrix,0, 21, 15));
        this.addSlotToContainer(new Slot(this.craftMatrix,1, 49, 3));
        this.addSlotToContainer(new Slot(this.craftMatrix,2, 77, 15));
        this.addSlotToContainer(new Slot(this.craftMatrix,3, 13, 36));
        this.addSlotToContainer(new Slot(this.craftMatrix,4, 49, 36));
        this.addSlotToContainer(new Slot(this.craftMatrix,5, 85, 36));
        this.addSlotToContainer(new Slot(this.craftMatrix,6, 22, 59));
        this.addSlotToContainer(new Slot(this.craftMatrix,7, 49, 66));
        this.addSlotToContainer(new Slot(this.craftMatrix,8, 76, 59));
        
        //loads item from tile entity inventory
        for(int i = 0; i < 9; ++i){
        	this.craftMatrix.setInventorySlotContents(i, te.getStackInSlot(i));
        }
            

        // Player Inventory, Slot 10-36, Slot IDs 10-36
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 10 , 8 + x * 18, 84 + y * 18));
            }
            
            this.onCraftMatrixChanged(this.craftMatrix);
        }

        // Player Inventory, Slot 0-8, Slot IDs 37-45
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x , 8 + x * 18, 142));
        }
    }
	
	@Override
	 public void onCraftMatrixChanged(IInventory inventoryIn)
	    {
	        this.craftResult.setInventorySlotContents(0, InfusingManager.getInstance().findMatchingRecipe(this.craftMatrix,te.getWorld()));
	    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
    	return this.te.isUseableByPlayer(playerIn); 
    }
    
    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!te.getWorld().isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftMatrix.getStackInSlot(i);

                te.setInventorySlotContents(i, itemstack);
            }
        }
    }
    
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        
        /*if(slot instanceof SlotInfusing){
        	return null;
        }*/
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
    
    @Override
    public boolean canMergeSlot(ItemStack p_94530_1_, Slot p_94530_2_)
    {
        return p_94530_2_.inventory != this.craftResult && super.canMergeSlot(p_94530_1_, p_94530_2_);
    }

}
