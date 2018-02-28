package com.astraldragon.AlchemicalSwordsMod.proxy;

import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropEvent {
	
	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event)
	{/*
		if(event.getEntityLiving() instanceof EntityWither){
			Random r = new Random();
			event.getEntityLiving().dropItem(AlchemicalSwords.philosopher_stone, r.nextInt(2));
		}
		
		if(event.getSource().getDamageType().equals("player"))
		{
			if(event.getEntityLiving() instanceof EntitySkeleton && ((EntitySkeleton) event.getEntityLiving()).getSkeletonType() == SkeletonType.WITHER){
				Random r = new Random();
				event.getEntityLiving().dropItem(AlchemicalSwords.wither_bone, r.nextInt(3));
			}
		}else{
			if(event.getEntityLiving() instanceof EntitySkeleton && ((EntitySkeleton) event.getEntityLiving()).getSkeletonType() == SkeletonType.WITHER){
				Random r = new Random();
				if(r.nextInt(8)== 3){
				event.getEntityLiving().dropItem(AlchemicalSwords.wither_bone, r.nextInt(3));
				}
			}
		}
		*/
	}

}
