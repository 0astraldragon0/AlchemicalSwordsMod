package com.astraldragon.AlchemicalSwordsMod.items;

import java.util.List;
import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.astraldragon.AlchemicalSwordsMod.entities.EntitySnowballAS;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemIceSword extends ItemSword {

	public ItemIceSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("ice_sword");	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		super.hitEntity(stack, target, attacker);
		Random r = new Random();
		
		if(r.nextBoolean()){
			target.addPotionEffect(new PotionEffect(Potion.getPotionById(2),100,0));
		}
		
       return true;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (!playerIn.capabilities.isCreativeMode)
        {
            itemStackIn.damageItem(10, playerIn);
        }

        if (!worldIn.isRemote)
        {
        	EntitySnowballAS entitysnowball =  new EntitySnowballAS(worldIn, playerIn);
            entitysnowball.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entitysnowball);         }

        return super.onItemRightClick(itemStackIn, worldIn, playerIn,hand);
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add((I18n.format("lore.IceSword1.name")));
		
		tooltip.add(I18n.format("lore.IceSword2.name"));
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() == AlchemicalSwords.ice_ingot) return true;
        return false;
    }

}
