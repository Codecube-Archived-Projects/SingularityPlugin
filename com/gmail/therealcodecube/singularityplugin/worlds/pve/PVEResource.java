package com.gmail.therealcodecube.singularityplugin.worlds.pve;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PVEResource 
{
	private PVEItems resource;
	private int quantity;
	
	public PVEResource ( PVEItems r, int q )
	{
		resource = r;
		quantity = q;
	}
	
	public Material getDisplayItem ( )
	{
		return resource.getDisplayItem ( );
	}
	
	public String getDisplayName ( )
	{
		return resource.getDisplayName ( );
	}
	
	public int getQuantity ( )
	{
		return quantity;
	}
	
	public ItemStack getItemStack ( )
	{
		ItemStack i = resource.craft ( );
		i.setAmount ( quantity );
		return i;
	}
}
