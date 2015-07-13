package com.gmail.therealcodecube.singularityplugin.sgui;

import org.bukkit.inventory.ItemStack;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;

public class DynamicGuiLink extends SButton 
{
	class DynamicGuiLinkPressed implements ButtonPressed
	{
		private DynamicSGui gui;
		
		public void setGui ( DynamicSGui g ) { gui = g; }
		
		public boolean buttonPressed ( SPlayer p, ButtonInfo i )
		{
			gui.buildGui ( p ).showInventory ( p );
			//It should be cancelled
			return true;
		}
	}
	
	public DynamicGuiLink ( ItemStack d, DynamicSGui g ) 
	{
		super ( d );
		
		DynamicGuiLinkPressed b = new DynamicGuiLinkPressed ( );
		b.setGui ( g );
		onButtonPress = b;
	}
	
	//Sets the gui this button will open
	public void setGui ( DynamicSGui g )
	{
		( ( DynamicGuiLinkPressed ) onButtonPress ).setGui ( g );
	}
}
