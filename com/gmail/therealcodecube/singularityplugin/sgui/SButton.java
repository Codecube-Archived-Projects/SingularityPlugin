package com.gmail.therealcodecube.singularityplugin.sgui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo.Panel;

public class SButton 
{
	protected ItemStack displayItem;
	protected ButtonPressed onButtonPress;
	
	public SButton ( ItemStack d )
	{
		displayItem = d;
	}
	
	public SButton ( ItemStack d, ButtonPressed e )
	{
		displayItem = d;
		onButtonPress = e;
	}
	
	public void setDisplayItem ( ItemStack e )
	{
		displayItem = e;
	}
	
	public ItemStack getDisplayItem ( )
	{
		return displayItem;
	}
	
	public void setPressEvent ( ButtonPressed b )
	{
		onButtonPress = b;
	}
	
	public void press ( InventoryClickEvent e )
	{
		//Determine which part of the inventory the player clicked.
		Panel p;
		if ( e.getRawSlot ( ) < e.getInventory ( ).getSize ( ) ) { p = Panel.TOP; } else { p = Panel.BOTTOM; }
		//Package all of the data in to a universal format, so that it doesn't matter if they clicked it with the inventory open or closed.
		ButtonInfo i = new ButtonInfo ( e.getSlot ( ), e.getRawSlot ( ), p, e.getCurrentItem ( ) );
		SPlayer s = SPlayer.getPlayer ( e.getWhoClicked ( ).getName ( ) );
		boolean shouldCancel = onButtonPress.buttonPressed ( s, i );
		e.setCancelled ( shouldCancel );
	}
	
	public void press ( PlayerInteractEvent e )
	{
		//They automatically clicked on the bottom half, since they clicked without an inventory open.
		Panel p = Panel.BOTTOM;
		PlayerInventory pi = e.getPlayer ( ).getInventory ( );
		//Package all of the data in to a universal format, so that it doesn't matter if they clicked it with the inventory open or closed.
		ButtonInfo i = new ButtonInfo ( pi.getHeldItemSlot ( ), pi.getHeldItemSlot ( ), p, e.getItem ( ) );
		SPlayer s = SPlayer.getPlayer ( e.getPlayer ( ) );
		boolean shouldCancel = onButtonPress.buttonPressed ( s, i );
		e.setCancelled ( shouldCancel );
	}
}
