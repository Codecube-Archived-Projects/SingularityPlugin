package com.gmail.therealcodecube.singularityplugin.sgui;

import org.bukkit.inventory.ItemStack;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;

public class SGuiLink extends SButton 
{
	class GuiLinkPressed implements ButtonPressed
	{
		private SGui gui;
		
		public void setGui ( SGui g ) { gui = g; }
		
		public boolean buttonPressed ( SPlayer p, ButtonInfo i )
		{
			gui.showInventory ( p );
			//It should be cancelled
			return true;
		}
	}
	
	public SGuiLink ( ItemStack d, SGui g ) 
	{
		super(d);
		
		GuiLinkPressed b = new GuiLinkPressed ( );
		b.setGui ( g );
		onButtonPress = b;
	}
	
	//Sets the gui this button will open
	public void setGui ( SGui g )
	{
		( ( GuiLinkPressed ) onButtonPress ).setGui ( g );
	}
}
