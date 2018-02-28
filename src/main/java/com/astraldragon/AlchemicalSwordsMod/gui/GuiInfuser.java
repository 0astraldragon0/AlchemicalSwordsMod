package com.astraldragon.AlchemicalSwordsMod.gui;

import com.astraldragon.AlchemicalSwordsMod.tileentities.TileEntityInfuser;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiInfuser extends GuiContainer {
	
	private TileEntityInfuser tileEntityInventory;
	private InventoryPlayer playerInventory;
	
	public GuiInfuser(IInventory playerInv, TileEntityInfuser te) {
        super(new ContainerInfuser((InventoryPlayer) playerInv, te));
        
        this.tileEntityInventory = te;
        this.playerInventory = (InventoryPlayer)playerInv;
        
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("aswords:textures/gui/infuser.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
    
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileEntityInventory.getDisplayName().getUnformattedText();
        String playerInventory = this.playerInventory.getDisplayName().getUnformattedText();
        
        this.fontRendererObj.drawString(s, this.xSize - this.fontRendererObj.getStringWidth(s) - 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(),this.xSize - this.fontRendererObj.getStringWidth(playerInventory) - 10, this.ySize - 96 + 2, 4210752);
    }

}
