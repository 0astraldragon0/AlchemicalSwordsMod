package com.astraldragon.AlchemicalSwordsMod.items;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGlassIngot extends Item {
	public ItemGlassIngot(){
		super();
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("glass_ingot");
	}
	
	 public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
    	 playerIn.addStat(AlchemicalSwords.AchievementMakeGlassIngot, 1);
    	 
     }
}
