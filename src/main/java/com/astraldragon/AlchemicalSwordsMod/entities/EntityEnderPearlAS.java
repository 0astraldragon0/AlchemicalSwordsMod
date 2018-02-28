package com.astraldragon.AlchemicalSwordsMod.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEnderPearlAS extends EntityEnderPearl{
	
	private ItemStack item;
	private EntityPlayer playerIn;

	public EntityEnderPearlAS(World worldIn, EntityLivingBase p_i1783_2_,ItemStack _item) {
		super(worldIn, p_i1783_2_);
		this.item =_item;
		if (p_i1783_2_ instanceof EntityPlayer){
			playerIn = (EntityPlayer) p_i1783_2_;
		}
	}
	
	@Override
	protected void onImpact(RayTraceResult p_70184_1_){
		super.onImpact(p_70184_1_);
		playerIn.inventory.addItemStackToInventory(item);
		
	}
	
	@Override
	public float getGravityVelocity(){
		return 0.01F;
		
	}

}
