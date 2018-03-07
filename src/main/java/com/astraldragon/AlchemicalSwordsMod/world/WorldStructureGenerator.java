package com.astraldragon.AlchemicalSwordsMod.world;

import java.util.List;
import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;
import com.google.common.collect.Lists;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldStructureGenerator extends WorldGenerator implements IWorldGenerator
{
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)   { }

    private void removeWood(World world, int x, int z)
    {
        int y = world.getTopSolidOrLiquidBlock(new BlockPos(x,0,z)).getY();
        BlockPos pos = new BlockPos(x, y, z);
        Block posBlock = world.getBlockState(pos).getBlock();
        while(posBlock == Blocks.LOG || posBlock == Blocks.LOG2 || posBlock == Blocks.LEAVES || posBlock == Blocks.LEAVES2)
        {
            if(posBlock == Blocks.LOG || posBlock == Blocks.LOG2)
            {
                world.setBlockToAir(pos);
            }
            --y;
            posBlock = world.getBlockState(pos.add(0, -1, 0)).getBlock();
        }
    }

    /**
     * Clear Trees
     * Clears out all trees in the specified radius.  This creates a
     * clearing which we can then place the structure.
     */
    private void clearTrees(World world, BlockPos pos, int radius)
    {
        int radiusSquared = radius * radius;
        for(int i = radius * -1; i <= radius; ++i)
        {
            for(int j = radius * -1; j <= radius; ++j)
            {
                if(((i * i) + (j * j)) <= radiusSquared)
                {
                    removeWood(world, pos.getX() + i, pos.getZ() + j);
                }
            }
        }
    }


    private boolean isValidSpawn(World world, BlockPos pos)
    {

    	//chek if the structure isn't underwater
    	for(int x=0;x<12 ;x++){
        	for(int y=0;y<4 ;y++){
            	for(int z=0;z<12 ;z++){
            		if(world.getBlockState(pos.add(x, y, z)).getBlock() == Blocks.WATER){
            			return false;
            		}
            	}
        	}
    	}
    	
    	
    	
        BlockPos[] positions = new BlockPos[]{pos, pos.add(12,0,0), pos.add(0,0,12), pos.add(12,0,12)};
        int minY = Integer.MAX_VALUE;

        //Checks each coord to make sure the ground doesn't vary by more than 3 blocks in height.
        for(BlockPos p : positions)
        {
            int i = 0;
            IBlockState state = world.getBlockState(p);
            Block block = state.getBlock();
            while(block.isAir(state, world, pos) || block.isLeaves(state, world, pos) || block.isFoliage(world, pos) || block == Blocks.LOG || block == Blocks.LOG2)
            {
                i++;
                p = p.down();
                block = world.getBlockState(p).getBlock();
                if(i > 3)
                    return false;
                if(p.getY() < minY)
                    minY = p.getY();
            }
        }

        return true;
    }
    
	public void setBlockState(World world, int x, int y, int z, net.minecraft.block.Block STONEBRICK, int metadata)
	{
		BlockPos pos = new BlockPos(x,y,z);
		
		world.setBlockState(new BlockPos(x, y, z), STONEBRICK.getStateFromMeta(metadata), 2);
		
	}

	public boolean generateStructure(World world,BlockPos pos) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		this.setBlockState(world, i + 0, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 0, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 0, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 0, j + 1, k + 6, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 0, j + 2, k + 6, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 0, j + 3, k + 6, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 0, j + 4, k + 6, Blocks.GLASS, 0);
		this.setBlockState(world, i + 1, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 1, j + 0, k + 4, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 1, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 1, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 1, j + 0, k + 7, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 1, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 1, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 2, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 4, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 6, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 2, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 2, j + 1, k + 2, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 2, j + 1, k + 10, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 2, j + 2, k + 2, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 2, j + 2, k + 10, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 2, j + 3, k + 2, Blocks.GLASS, 0);
		this.setBlockState(world, i + 2, j + 3, k + 10, Blocks.GLASS, 0);
		this.setBlockState(world, i + 3, j + 0, k + 1, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 3, j + 0, k + 2, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 3, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 3, j + 0, k + 4, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 3, j + 0, k + 5, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 3, j + 0, k + 6, AlchemicalSwords.runic_bricks, 3);
		this.setBlockState(world, i + 3, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 3, j + 0, k + 8, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 3, j + 0, k + 9, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 3, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 3, j + 0, k + 11, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 1, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 2, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 4, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 4, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 7, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 4, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 9, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 4, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 4, j + 0, k + 11, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 4, j + 1, k + 5, Blocks.STONE_BRICK_STAIRS, 2);
		this.setBlockState(world, i + 4, j + 1, k + 6, Blocks.STONE_BRICK_STAIRS, 0);
		this.setBlockState(world, i + 4, j + 1, k + 7, Blocks.STONE_BRICK_STAIRS, 3);
		this.setBlockState(world, i + 5, j + 0, k + 0, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 1, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 2, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 5, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 4, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 5, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 7, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 5, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 11, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 0, k + 12, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 1, k + 4, Blocks.STONE_BRICK_STAIRS, 0);
		this.setBlockState(world, i + 5, j + 1, k + 5, Blocks.STONE_BRICK_STAIRS, 2);
		this.setBlockState(world, i + 5, j + 1, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 5, j + 1, k + 7, Blocks.STONE_BRICK_STAIRS, 0);
		this.setBlockState(world, i + 5, j + 1, k + 8, Blocks.STONE_BRICK_STAIRS, 0);
		this.setBlockState(world, i + 6, j + 0, k + 0, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 0, k + 1, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 0, k + 2, AlchemicalSwords.runic_bricks, 3);
		this.setBlockState(world, i + 6, j + 0, k + 3, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 6, j + 0, k + 4, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 0, k + 5, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 6, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 0, k + 9, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 6, j + 0, k + 10, AlchemicalSwords.runic_bricks, 3);
		this.setBlockState(world, i + 6, j + 0, k + 11, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 6, j + 0, k + 12, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 1, k + 0, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 6, j + 1, k + 4, Blocks.STONE_BRICK_STAIRS, 2);
		this.setBlockState(world, i + 6, j + 1, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 1, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 1, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 6, j + 1, k + 8, Blocks.STONE_BRICK_STAIRS, 3);
		this.setBlockState(world, i + 6, j + 1, k + 12, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 6, j + 2, k + 0, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 6, j + 2, k + 6, AlchemicalSwords.infuser, 0);
		this.setBlockState(world, i + 6, j + 2, k + 12, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 6, j + 3, k + 0, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 6, j + 3, k + 12, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 6, j + 4, k + 0, Blocks.GLASS, 0);
		this.setBlockState(world, i + 6, j + 4, k + 12, Blocks.GLASS, 0);
		this.setBlockState(world, i + 7, j + 0, k + 0, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 1, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 7, j + 0, k + 2, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 4, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 7, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 6, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 7, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 11, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 0, k + 12, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 1, k + 4, Blocks.STONE_BRICK_STAIRS, 1);
		this.setBlockState(world, i + 7, j + 1, k + 5, Blocks.STONE_BRICK_STAIRS, 1);
		this.setBlockState(world, i + 7, j + 1, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 7, j + 1, k + 7, Blocks.STONE_BRICK_STAIRS, 3);
		this.setBlockState(world, i + 7, j + 1, k + 8, Blocks.STONE_BRICK_STAIRS, 1);
		this.setBlockState(world, i + 8, j + 0, k + 1, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 8, j + 0, k + 2, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 8, j + 0, k + 3, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 8, j + 0, k + 4, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 8, j + 0, k + 5, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 8, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 8, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 8, j + 0, k + 8, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 8, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 8, j + 0, k + 10, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 8, j + 0, k + 11, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 8, j + 1, k + 5, Blocks.STONE_BRICK_STAIRS, 2);
		this.setBlockState(world, i + 8, j + 1, k + 6, Blocks.STONE_BRICK_STAIRS, 1);
		this.setBlockState(world, i + 8, j + 1, k + 7, Blocks.STONE_BRICK_STAIRS, 3);
		this.setBlockState(world, i + 9, j + 0, k + 1, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 2, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 4, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 6, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 9, j + 0, k + 7, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 9, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 9, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 9, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 9, j + 0, k + 11, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 10, j + 0, k + 2, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 10, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 10, j + 0, k + 4, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 10, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 10, j + 0, k + 6, AlchemicalSwords.runic_bricks, 3);
		this.setBlockState(world, i + 10, j + 0, k + 7, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 10, j + 0, k + 8, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 10, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 10, j + 0, k + 10, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 10, j + 1, k + 2, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 10, j + 1, k + 10, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 10, j + 2, k + 2, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 10, j + 2, k + 10, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 10, j + 3, k + 2, Blocks.GLASS, 0);
		this.setBlockState(world, i + 10, j + 3, k + 10, Blocks.GLASS, 0);
		this.setBlockState(world, i + 11, j + 0, k + 3, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 11, j + 0, k + 4, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 11, j + 0, k + 5, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 11, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 11, j + 0, k + 7, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 11, j + 0, k + 8, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 11, j + 0, k + 9, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 12, j + 0, k + 5, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 12, j + 0, k + 6, AlchemicalSwords.runic_bricks, 0);
		this.setBlockState(world, i + 12, j + 0, k + 7, AlchemicalSwords.runic_bricks, 1);
		this.setBlockState(world, i + 12, j + 1, k + 6, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 12, j + 2, k + 6, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 12, j + 3, k + 6, Blocks.OBSIDIAN, 0);
		this.setBlockState(world, i + 12, j + 4, k + 6, Blocks.GLASS, 0);
		this.setBlockState(world, i + 0, j + 1, k + 5, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 0, j + 1, k + 7, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 1, j + 1, k + 5, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 1, j + 1, k + 6, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 1, j + 1, k + 7, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 2, j + 1, k + 3, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 2, j + 1, k + 9, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 3, j + 1, k + 2, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 3, j + 1, k + 3, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 3, j + 1, k + 9, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 3, j + 1, k + 10, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 5, j + 1, k + 0, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 5, j + 1, k + 1, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 5, j + 1, k + 11, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 5, j + 1, k + 12, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 6, j + 1, k + 1, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 6, j + 1, k + 11, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 7, j + 1, k + 0, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 7, j + 1, k + 1, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 7, j + 1, k + 11, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 7, j + 1, k + 12, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 9, j + 1, k + 2, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 9, j + 1, k + 3, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 9, j + 1, k + 9, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 9, j + 1, k + 10, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 10, j + 1, k + 3, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 10, j + 1, k + 9, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 11, j + 1, k + 5, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 11, j + 1, k + 6, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 11, j + 1, k + 7, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 12, j + 1, k + 5, Blocks.REDSTONE_WIRE, 0);
		this.setBlockState(world, i + 12, j + 1, k + 7, Blocks.REDSTONE_WIRE, 0);

		return true;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if(this.isValidSpawn(worldIn, position)== true && rand.nextInt(100) == 0)
        {
            //Clear the trees around the area if there are any
            clearTrees(worldIn, position.add(6, 0, 6), 10);

            //Generate the structure
            generateStructure(worldIn, position);
            return true;
        }
		return false;
	}

	
}