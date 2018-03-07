package com.astraldragon.AlchemicalSwordsMod.items;

import java.util.List;
import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSoulSword extends ItemSword {

	public ItemSoulSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("soul_sword");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() instanceof ItemGlassIngot) return true;
        return false;
    }
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		super.hitEntity(stack, target, attacker);
		
		Random r = new Random();
		
		if(target.getHealth() == 0 && r.nextInt(20) == 0){
			target.dropItem(AlchemicalSwords.soul_crystal, 1);
		}
		
		return true;
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add(I18n.format("lore.SoulSword1.name"));
	}
	
}
