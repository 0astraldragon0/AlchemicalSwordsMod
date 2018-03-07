package com.astraldragon.AlchemicalSwordsMod.items;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAlchemicalIngot extends Item{
	
	private final int IngotType;
	
	public static final int EnderIngotID = 0;
	public static final int IceIngotID = 1;
	public static final int FireIngotID = 2;
	public static final int NaturaIngotID = 3;
	
	public ItemAlchemicalIngot(int IngotId){
		super();
		this.IngotType=IngotId;
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		
		switch(((ItemAlchemicalIngot)stack.getItem()).getIngotType()){
		case EnderIngotID:
			playerIn.addStat(AlchemicalSwords.AchievementMakeEnderIngot);
			break;
		case FireIngotID:
			playerIn.addStat(AlchemicalSwords.AchievementMakeFireIngot);
			break;
		case IceIngotID:
			playerIn.addStat(AlchemicalSwords.AchievementMakeIceIngot);
			break;
		case NaturaIngotID:
			playerIn.addStat(AlchemicalSwords.AchievementMakeNaturaIngot);
			break;
		}
		
	}
	
	public int getIngotType(){
		return IngotType;
	}
}
