package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sgui.SGui;

public enum GriefClass 
{
	LUMBERJACK ( Material.IRON_AXE, ChatColor.RESET.toString ( ) + ChatColor.RED + "Lumberjack", 
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Has a nice axe.\"", 
			ChatColor.GRAY + "Starts with a speedy axe.",
			"",
			ChatColor.RED + "Offensive",
			ChatColor.BLUE + "Builder", } ) )
	{
		@Override
		public void equip ( SPlayer p )
		{
			p.clearInventory ( );
			ItemStack axe = new ItemStack ( Material.STONE_AXE );
			ItemMeta m = axe.getItemMeta ( );
			m.addEnchant ( Enchantment.DIG_SPEED, 5, false );
			axe.setItemMeta ( m );
			p.getInventory ( ).addItem ( axe ); 
		}
	}, 
	PYROTECHNIC ( Material.FLINT_AND_STEEL, ChatColor.RESET.toString ( ) + ChatColor.GOLD + "Pyrotechnic",  
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Just wants to see the world burn\"", 
			ChatColor.GRAY + "Starts with a lighter and some weak",
			ChatColor.GRAY + "fireproof armor.",
			"",
			ChatColor.RED + "Offensive", } ) )
	{
		
	},
	FIREMAN ( Material.WATER_BUCKET, ChatColor.RESET.toString ( ) + ChatColor.BLUE + "Fireman",  
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Can't stand the Pyrotechnic.\"", 
			ChatColor.GRAY + "Starts with a water hose to put out",
			ChatColor.GRAY + "fires, as well as some strong fire-",
			ChatColor.GRAY + "proof armor.",
			"",
			ChatColor.GREEN + "Defensive",  } ) )
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
		return new SButton ( getDisplayItem ( ), new GriefClassClicked ( this ) );
	}
	
	//Gets this class's name
	public String getName ( )
	{
		return name;
	}
	
	//Equip the kit. This function is intended to be overridden by individual enums.
	public void equip ( SPlayer p )
	{
		
	}
	
	public static SGui getClassGui ( )
	{
		SGui tr = new SGui ( "Choose a class", 9 );
		for ( GriefClass c : GriefClass.values ( ) )
		{
			tr.addButton ( c.getButton ( ) );
		}
		return tr;
	}
	
	public static GriefClass getClass ( String n )
	{
		for ( GriefClass c : GriefClass.values ( ) )
		{
			if ( c.getName ( ) == n )
			{
				return c;
			}
		}
		//If we couldn't find anything, return the default.
		return LUMBERJACK;
	}
}
