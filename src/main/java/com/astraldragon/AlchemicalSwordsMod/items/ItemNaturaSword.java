package com.astraldragon.AlchemicalSwordsMod.items;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemNaturaSword extends ItemSword {


	public ItemNaturaSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("natura_sword");
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		super.hitEntity(stack, target, attacker);
       
       BlockPos pos = target.getPosition();
       Random r = new Random();
       if(r.nextBoolean()){
    	   target.addPotionEffect(new PotionEffect(Potion.getPotionById(19),100,0));
       }
       return true;
    }
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		BlockPos pos = entity.getPosition();
		
		if(entity instanceof EntityPlayer){
			if(!(((EntityPlayer)entity).getHeldItem(EnumHand.MAIN_HAND) == stack || (((EntityPlayer)entity).getHeldItem(EnumHand.OFF_HAND) == stack))){
				return;
			}
		}
		
		if(world.getBlockState(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ())).getBlock() == Blocks.GRASS){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionById(1),10,0));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionById(5),10,0));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add("has a 50% chance to poison the enemy");
		tooltip.add("make you stronger and faster on grass");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() == AlchemicalSwords.natura_ingot) return true;
        return false;
    }
}
