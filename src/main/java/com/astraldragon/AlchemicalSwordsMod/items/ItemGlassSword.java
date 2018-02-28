package com.astraldragon.AlchemicalSwordsMod.items;

import com.astraldragon.AlchemicalSwordsMod.AlchemicalSwords;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemGlassSword extends ItemSword{

	public ItemGlassSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(AlchemicalSwords.ASwordsTab);
		this.setUnlocalizedName("glass_sword");
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() instanceof ItemGlassIngot) return true;
        return false;
    }
}
