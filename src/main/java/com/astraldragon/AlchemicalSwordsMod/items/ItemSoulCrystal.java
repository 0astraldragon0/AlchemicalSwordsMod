package com.astraldragon.AlchemicalSwordsMod.items;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSoulCrystal extends Item {
     public ItemSoulCrystal(){
    	 super();
    	 this.setUnlocalizedName("soul_crystal");
    	 this.setCreativeTab(AlchemicalSwords.ASwordsTab);
     }
     
     public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
    	 playerIn.addStat(AlchemicalSwords.AchievmentAlchemicalAge, 1);
    	 
     }
}
