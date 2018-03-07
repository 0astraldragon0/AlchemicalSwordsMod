package com.astraldragon.AlchemicalSwordsMod;

import java.util.List;

import com.astraldragon.AlchemicalSwordsMod.blocks.BlockAlchemicalSoulSand;
import com.astraldragon.AlchemicalSwordsMod.blocks.BlockRunicBricks;
import com.astraldragon.AlchemicalSwordsMod.blocks.BlockSoulCrystal;
import com.astraldragon.AlchemicalSwordsMod.blocks.BlockTileEntityInfuser;
import com.astraldragon.AlchemicalSwordsMod.crafting.InfusingManager;
import com.astraldragon.AlchemicalSwordsMod.gui.GuiHandler;
import com.astraldragon.AlchemicalSwordsMod.items.ItemAlchemicalIngot;
import com.astraldragon.AlchemicalSwordsMod.items.ItemEnderSword;
import com.astraldragon.AlchemicalSwordsMod.items.ItemFireSword;
import com.astraldragon.AlchemicalSwordsMod.items.ItemGlassIngot;
import com.astraldragon.AlchemicalSwordsMod.items.ItemGlassSword;
import com.astraldragon.AlchemicalSwordsMod.items.ItemIceSword;
import com.astraldragon.AlchemicalSwordsMod.items.ItemNaturaSword;
import com.astraldragon.AlchemicalSwordsMod.items.ItemPhilosopherStone;
import com.astraldragon.AlchemicalSwordsMod.items.ItemSoulCrystal;
import com.astraldragon.AlchemicalSwordsMod.items.ItemSoulSword;
import com.astraldragon.AlchemicalSwordsMod.items.ItemWitherBone;
import com.astraldragon.AlchemicalSwordsMod.proxy.CommonProxy;
import com.astraldragon.AlchemicalSwordsMod.tileentities.TileEntityInfuser;
import com.astraldragon.AlchemicalSwordsMod.world.WorldGeneratorMod;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.Achievement;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = AlchemicalSwords.MODID, version = AlchemicalSwords.VERSION)
public class AlchemicalSwords
{
	@Instance("aswords")
	public static AlchemicalSwords instance;
	@SidedProxy(clientSide="com.astraldragon.AlchemicalSwordsMod.proxy.ClientProxy", serverSide="com.astraldragon.AlchemicalSwordsMod.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "aswords";
	public static final String VERSION = "1.0";
	
	public static final String INFUSING_UID = "AlchemicalSwords:InfuserCrafting";
	
	public static final List<Achievement> achievementsList = Lists.newArrayList();

	public static Block alchemical_soul_sand;
	public static Block infuser;
	public static Block soul_crystal_block;
	public static Block runic_bricks;
	
	public static Item soul_crystal;
	//public static Item wither_bone;
	public static Item glass_ingot;
	public static Item philosopher_stone;
	public static Item ender_ingot;
	public static Item fire_ingot;
	public static Item ice_ingot;
	public static Item natura_ingot;
	
	public static CreativeTabs ASwordsTab;
	
	public static ItemSword ender_sword;
	public static ItemSword glass_sword;
	public static ItemSword soul_sword;
	public static ItemSword natura_sword;
	public static ItemSword fire_sword;
	public static ItemSword ice_sword;
	
	public static ToolMaterial AlchemicIngot; 
	
	public static Achievement AchievmentAlchemicalAge;
	public static Achievement AchievementMakeGlassIngot;
	public static Achievement AchievementMakeFireIngot;
	public static Achievement AchievementMakeIceIngot;
	public static Achievement AchievementMakeNaturaIngot;
	public static Achievement AchievementMakeEnderIngot;
	
	public static InfusingManager infusingManager;
	
	
	public GuiHandler guiHandler = new GuiHandler();
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.addTab();
		this.addMaterials();
		this.addBlocks();
		this.addItems();
		this.addAchievements();
		this.registerBlocks();
		this.registerItems();
		
		proxy.registerObject();
		
		this.RegisterAchievements();
		infusingManager = new InfusingManager();
		}


	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.registerEntities();
		this.registerCraftings();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
		
		proxy.dropEvent();
	}
	
	public void RegisterAchievements(){
		Achievement[] achievementArray = new Achievement[achievementsList.size()];
		
		for(Achievement achievement : achievementsList){
			achievement.registerStat();
			achievementArray[achievementsList.indexOf(achievement)] = achievement;
		}
    	AchievementPage.registerAchievementPage(new AchievementPage("AlchemicalSwords",achievementArray));

		
	}
	
	public void registerEntities(){
		GameRegistry.registerTileEntity(TileEntityInfuser.class, "tile_entity_infuser");
	}
	
	public void addAchievements(){
		AchievmentAlchemicalAge = new Achievement("achievement.alchemicalage", "alchemicalage", 3, 0, soul_crystal,(Achievement)null);
		achievementsList.add(AchievmentAlchemicalAge);
		AchievementMakeGlassIngot = new Achievement("achievement.makeglassingot", "makeglassingot", 3, 2, glass_ingot,AchievmentAlchemicalAge);
		achievementsList.add(AchievementMakeGlassIngot);
		AchievementMakeFireIngot = new Achievement("achievement.makefireingot", "makefireingot", 1, 2, fire_ingot,AchievementMakeGlassIngot);
		achievementsList.add(AchievementMakeFireIngot);
		AchievementMakeIceIngot = new Achievement("achievement.makeiceingot", "makeiceingot",2,4, ice_ingot,AchievementMakeGlassIngot);
		achievementsList.add(AchievementMakeIceIngot);
		AchievementMakeEnderIngot = new Achievement("achievement.makeenderingot", "makeenderingot",4,4, ender_ingot,AchievementMakeGlassIngot);
		achievementsList.add(AchievementMakeEnderIngot);
		AchievementMakeNaturaIngot = new Achievement("achievement.makenaturaingot", "makenaturaingot", 5, 2, natura_ingot,AchievementMakeGlassIngot);
		achievementsList.add(AchievementMakeNaturaIngot);
	}
	
	public void addMaterials(){
		AlchemicIngot  = new EnumHelper().addToolMaterial("ALCHEMICINGOT", 4, 1561, 8.0F, 5.0F, 20);
	}
	
	public void addTab(){
		ASwordsTab = new CreativeTabs("ASwordsTab") {
		    @Override public Item getTabIconItem() {
		        return soul_crystal;
		    }
		};
	}
	
	public void addItems(){
		soul_crystal = new ItemSoulCrystal();
		//wither_bone = new ItemWitherBone();
		glass_ingot = new ItemGlassIngot();
		philosopher_stone = new ItemPhilosopherStone();
		ender_ingot = new ItemAlchemicalIngot(0).setUnlocalizedName("ender_ingot");
		ice_ingot = new ItemAlchemicalIngot(1).setUnlocalizedName("ice_ingot");
		fire_ingot = new ItemAlchemicalIngot(2).setUnlocalizedName("fire_ingot");
		natura_ingot = new ItemAlchemicalIngot(3).setUnlocalizedName("natura_ingot");
		
		ender_sword = new ItemEnderSword(AlchemicalSwords.AlchemicIngot);
		glass_sword = new ItemGlassSword(AlchemicalSwords.AlchemicIngot);
		soul_sword = new ItemSoulSword(AlchemicalSwords.AlchemicIngot);
		natura_sword = new ItemNaturaSword(AlchemicalSwords.AlchemicIngot);
		fire_sword = new ItemFireSword(AlchemicalSwords.AlchemicIngot);
		ice_sword = new ItemIceSword(AlchemicalSwords.AlchemicIngot);
	}
	
	public void addBlocks(){
		alchemical_soul_sand = new BlockAlchemicalSoulSand(Material.SAND);
		infuser = new BlockTileEntityInfuser();
		soul_crystal_block = new BlockSoulCrystal();
		runic_bricks = new BlockRunicBricks();
	}
	
	public static void registerWorld(){
		GameRegistry.registerWorldGenerator( new WorldGeneratorMod(),  1);
	}
	
	public void registerCraftings(){
		GameRegistry.addSmelting(alchemical_soul_sand, new ItemStack(soul_crystal,1 ),3.0F);
	    
		GameRegistry.addShapedRecipe(new ItemStack(glass_ingot),"GSG","SDS","GSG",'S',soul_crystal,'G',Blocks.GLASS,'D',Items.DIAMOND);
		GameRegistry.addShapedRecipe(new ItemStack(glass_sword),"G","G","S",'S',Items.STICK,'G',glass_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ender_sword),"E","E","S",'S',Items.STICK,'E',ender_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(ice_sword),"I","I","S",'S',Items.STICK,'I',ice_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(fire_sword),"F","F","B",'B',Items.BLAZE_ROD,'F',fire_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(natura_sword),"N","N","S",'S',Items.STICK,'N',natura_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(soul_crystal_block),"CCC","CCC","CCC",'C',soul_crystal);
		GameRegistry.addShapedRecipe(new ItemStack(soul_crystal,9),"C",'C',soul_crystal_block);
		
	}
	public void registerBlocks(){
		GameRegistry.registerBlock(alchemical_soul_sand, "alchemical_soul_sand");
		GameRegistry.registerBlock(infuser, "infuser");
		GameRegistry.registerBlock(soul_crystal_block, "soul_crystal_block");
		GameRegistry.registerBlock(runic_bricks, "runic_bricks");
	}
	
    public void registerItems(){
	GameRegistry.registerItem(soul_crystal, "soul_crystal");
	//GameRegistry.registerItem(wither_bone, "wither_bone");
	GameRegistry.registerItem(ender_sword, "ender_sword");
	GameRegistry.registerItem(fire_sword, "fire_sword");
	GameRegistry.registerItem(natura_sword, "natura_sword");
	GameRegistry.registerItem(glass_sword, "glass_sword");
	GameRegistry.registerItem(soul_sword, "soul_sword");
	GameRegistry.registerItem(glass_ingot, "glass_ingot");
	GameRegistry.registerItem(ice_sword, "ice_sword");
	GameRegistry.registerItem(philosopher_stone, "philosopher_stone");
	GameRegistry.registerItem(ender_ingot, "ender_ingot");
	GameRegistry.registerItem(ice_ingot, "ice_ingot");
	GameRegistry.registerItem(fire_ingot, "fire_ingot");
	GameRegistry.registerItem(natura_ingot, "natura_ingot");
	}
	

	
	public static void registerRenders(){
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(alchemical_soul_sand), 0, new ModelResourceLocation(MODID + ":" + "alchemical_soul_sand", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(infuser), 0, new ModelResourceLocation(MODID + ":" + "infuser", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(soul_crystal_block), 0, new ModelResourceLocation(MODID + ":" + "soul_crystal_block", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(runic_bricks), 0, new ModelResourceLocation(MODID + ":" + "runic_bricks", "inventory"));
		
		ModelLoader.setCustomModelResourceLocation(soul_crystal, 0, new ModelResourceLocation(MODID + ":" + "soul_crystal", "inventory"));
		//ModelLoader.setCustomModelResourceLocation(wither_bone, 0, new ModelResourceLocation(MODID + ":" + "wither_bone", "inventory"));
		ModelLoader.setCustomModelResourceLocation(ender_sword, 0, new ModelResourceLocation(MODID + ":" + "ender_sword", "inventory"));		
		ModelLoader.setCustomModelResourceLocation(natura_sword, 0, new ModelResourceLocation(MODID + ":" + "natura_sword", "inventory"));		
		ModelLoader.setCustomModelResourceLocation(fire_sword, 0, new ModelResourceLocation(MODID + ":" + "fire_sword", "inventory"));		
		ModelLoader.setCustomModelResourceLocation(ice_sword, 0, new ModelResourceLocation(MODID + ":" + "ice_sword", "inventory"));		
		ModelLoader.setCustomModelResourceLocation(glass_sword, 0, new ModelResourceLocation(MODID + ":" + "glass_sword", "inventory"));
		ModelLoader.setCustomModelResourceLocation(soul_sword, 0, new ModelResourceLocation(MODID + ":" + "soul_sword", "inventory"));
		ModelLoader.setCustomModelResourceLocation(glass_ingot, 0, new ModelResourceLocation(MODID + ":" + "glass_ingot", "inventory"));
		ModelLoader.setCustomModelResourceLocation(philosopher_stone, 0, new ModelResourceLocation(MODID + ":" + "philosopher_stone", "inventory"));		
		ModelLoader.setCustomModelResourceLocation(ender_ingot, 0, new ModelResourceLocation(MODID + ":" + "ender_ingot", "inventory"));	
		ModelLoader.setCustomModelResourceLocation(fire_ingot, 0, new ModelResourceLocation(MODID + ":" + "fire_ingot", "inventory"));	
		ModelLoader.setCustomModelResourceLocation(ice_ingot, 0, new ModelResourceLocation(MODID + ":" + "ice_ingot", "inventory"));	
		ModelLoader.setCustomModelResourceLocation(natura_ingot, 0, new ModelResourceLocation(MODID + ":" + "natura_ingot", "inventory"));	
	}
}
