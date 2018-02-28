package com.astraldragon.AlchemicalSwordsMod.world;

import java.util.Random;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorMod implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()){
		case 0:
			generateOverworld(random,chunkX*16,chunkZ*16,world);
			break;
		case -1:
			generateNether(random,chunkX*16,chunkZ*16,world);
			break;
		case 1:
			generateEnd(random,chunkX*16,chunkZ*16,world);
			break;
		}
		
	}

	private void generateEnd(Random random, int i, int j, World world) {
		// TODO Auto-generated method stub
		
	}

	private void generateNether(Random random, int chunkX,int chunkZ,World world){
		for(int i = 0;i<150;i++){
			int posX = chunkX + random.nextInt(16);
			int posZ = chunkZ + random.nextInt(16);
			
			int posY = 20 + random.nextInt(240-20);
			
			new WorldGenMinable(AlchemicalSwords.alchemical_soul_sand.getDefaultState(),random.nextInt(8),BlockMatcher.forBlock(Blocks.SOUL_SAND)).generate(world,random, new BlockPos(posX,posY,posZ));
		}
		
	}

	private void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		for(int i = 0;i<50;i++){
			BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX + random.nextInt(16), 1, chunkZ + random.nextInt(16)));
			
			new WorldStructureGenerator().generate(world,random, pos);
		}
		
	}

}
