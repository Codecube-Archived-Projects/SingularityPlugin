package com.gmail.therealcodecube.singularityplugin;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util 
{
	public static ItemStack createStack ( Material mat, String name )
	{
		ItemStack tr = new ItemStack ( mat );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, int quantity, String name )
	{
		ItemStack tr = new ItemStack ( mat, quantity );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, short damage, String name )
	{
		ItemStack tr = new ItemStack ( mat, 1, damage );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, int quantity, short damage, String name )
	{
		ItemStack tr = new ItemStack ( mat, quantity, damage );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, String name, String[] lore )
	{
		ItemStack tr = new ItemStack ( mat );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		im.setLore ( Arrays.asList ( lore ) );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack addLore ( ItemStack tr, String[] lore )
	{
		ItemMeta im = tr.getItemMeta ( );
		im.setLore ( Arrays.asList ( lore ) );
		tr.setItemMeta ( im );
		return tr;
	}
}
