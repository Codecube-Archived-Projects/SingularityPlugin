package com.gmail.therealcodecube.singularityplugin.sgui;

import org.bukkit.inventory.ItemStack;

public class ButtonInfo 
{
	private int slot = 1, rawSlot = 1;
	private Panel panel = Panel.TOP;
	private ItemStack itemClicked;
	public enum Panel { TOP, BOTTOM }
	
	public ButtonInfo ( int s, int r, Panel p, ItemStack i )
	{
		slot = s; rawSlot = r;
		panel = p; itemClicked = i;
	}
	
	public int getSlot ( )
	{
		return slot;
	}
	
	public int getRawSlot ( )
	{
		return rawSlot;
	}
	
	public Panel getPanel ( )
	{
		return panel;
	}
	
	public ItemStack getItemClicked ( )
	{
		return itemClicked;
	}
}
