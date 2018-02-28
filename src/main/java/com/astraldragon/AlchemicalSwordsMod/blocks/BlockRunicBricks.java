package com.astraldragon.AlchemicalSwordsMod.blocks;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRunicBricks extends Block {

	public BlockRunicBricks() {
		super(Material.ROCK);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("runic_bricks");
		this.setHardness(7.0F);
		this.setHarvestLevel("pickaxe", 1);
	}

}
