package com.astraldragon.AlchemicalSwordsMod.blocks;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSoulCrystal extends Block {

	public BlockSoulCrystal() {
		super(Material.GLASS);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("soul_crystal_block");
		this.setHardness(5.0F);
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj,BlockPos pos,BlockPos beacon) {
		super.isBeaconBase(worldObj, pos, beacon);
	    return true;
	}
}
