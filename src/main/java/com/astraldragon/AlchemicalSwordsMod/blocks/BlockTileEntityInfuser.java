package com.astraldragon.AlchemicalSwordsMod.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.astraldragon.AlchemicalSwordsMod.tileentities.TileEntityInfuser;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockTileEntityInfuser extends BlockContainer {
	
	public AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
	public TileEntityInfuser te;
	
    public BlockTileEntityInfuser() {
        super(Material.ROCK);
        this.setUnlocalizedName("infuser");
        this.setHardness(5.0f);
        this.setResistance(20.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setCreativeTab(AlchemicalSwords.ASwordsTab);
        this.setLightOpacity(0);
        this.isBlockContainer = true;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB;
    }

	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		 if (!world.isRemote) {
		        player.openGui(AlchemicalSwords.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
		    }
		    return true;

	    
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityInfuser();
	}
    
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
	    TileEntityInfuser te = (TileEntityInfuser) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}


	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((TileEntityInfuser) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}

}
