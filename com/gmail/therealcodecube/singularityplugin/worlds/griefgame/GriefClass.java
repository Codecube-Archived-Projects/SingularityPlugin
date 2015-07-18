package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sgui.SGui;

public enum GriefClass 
{
	LUMBERJACK ( Material.IRON_AXE, ChatColor.RED + "Lumberjack", Arrays.asList ( new String [ ] { "Has a nice axe." } ) )
	{
		
	},
	PYROTECHNIC ( Material.FLINT_AND_STEEL, ChatColor.GOLD + "Pyrotechnic", Arrays.asList ( new String [ ] { "Just wants to see the world burn." } ) )
	{
		
	},
	FIREMAN ( Material.WATER_BUCKET, ChatColor.BLUE + "Fireman", Arrays.asList ( new String [ ] { "Can't be around the Pyrotechnic", "for more than 3 seconds." } ) )
	{
		
	};
	
	private Material displayItem;
	private String name;
	private List < String > description = new ArrayList < String > ( );
	
	private GriefClass ( Material i, String n, List < String > l )
	{
		displayItem = i;
		name = n;
		description = l;
	}
	
	public ItemStack getDisplayItem ( )
	{
		ItemStack tr = new ItemStack ( displayItem );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		im.setLore ( description );
		tr.setItemMeta ( im );
		return tr;
	}
	
	//Returns a button for the particular class.
	public SButton getButton ( )
	{
		return new SButton ( getDisplayItem ( ) );
	}
	
	public static SGui getClassGui ( )
	{
		SGui tr = new SGui ( );
		for ( GriefClass c : GriefClass.values ( ) )
		{
			tr.addButton ( c.getButton ( ) );
		}
		return tr;
	}
}
