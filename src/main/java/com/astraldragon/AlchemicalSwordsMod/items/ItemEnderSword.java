package com.astraldragon.AlchemicalSwordsMod.items;

import java.util.List;
import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.astraldragon.AlchemicalSwordsMod.entities.EntityEnderPearlAS;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemEnderSword extends ItemSword {

	public ItemEnderSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("ender_sword");
        
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		super.hitEntity(stack, target, attacker);
       
       BlockPos pos = target.getPosition();
       Random r = new Random();
       
       attacker.getEntityWorld().spawnParticle(EnumParticleTypes.PORTAL, pos.getX(), pos.getY() + r.nextDouble() * 2.0D, pos.getZ(), r.nextGaussian(), 0.0D, r.nextGaussian(), new int[0]);
       
       target.setPosition((double)pos.getX() + r.nextInt(4), (double)pos.getY() + 4 + r.nextInt(4), (double)pos.getZ() + r.nextInt(4));
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
        	EntityEnderPearlAS entityenderpearl =  new EntityEnderPearlAS(worldIn, playerIn,itemStackIn.copy());
            entityenderpearl.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entityenderpearl);        }

        --itemStackIn.stackSize;
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add(I18n.format("lore.EnderSword1.name"));
		tooltip.add(I18n.format("lore.EnderSword2.name"));
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() == AlchemicalSwords.ender_ingot) return true;
        return false;
    }

}
