package com.astraldragon.AlchemicalSwordsMod.proxy;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
    public void dropEvent() {
		MinecraftForge.EVENT_BUS.register(new DropEvent());
		
	}
    
    public void registerObject(){
		AlchemicalSwords.registerRenders();
		AlchemicalSwords.registerWorld();
	}

}