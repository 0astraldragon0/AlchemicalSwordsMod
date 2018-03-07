package com.astraldragon.AlchemicalSwordsMod.items;

import java.util.List;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPhilosopherStone extends Item {
	public ItemPhilosopherStone(){
		super();
		this.setUnlocalizedName("philosopher_stone");
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.maxStackSize = 1;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add(I18n.format("lore.PhilosopherStone1.name"));
		tooltip.add(I18n.format("lore.PhilosopherStone2.name"));
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!playerIn.canPlayerEdit(pos, facing, stack) || !playerIn.isSneaking()){
			return EnumActionResult.FAIL;
		}
		
		worldIn.setBlockState(pos, AlchemicalSwords.infuser.getDefaultState());
		
		return EnumActionResult.SUCCESS;
    }
}
