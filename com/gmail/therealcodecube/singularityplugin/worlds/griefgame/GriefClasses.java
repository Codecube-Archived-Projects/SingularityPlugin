package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sgui.SGui;

public enum GriefClasses 
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
			m.addEnchant ( Enchantment.DIG_SPEED, 2, false );
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
		@Override
		public void equip ( SPlayer p )
		{
			p.clearInventory ( );
			ItemStack i = new ItemStack ( Material.FLINT_AND_STEEL );
			ItemMeta m = i.getItemMeta ( );
			m.setDisplayName ( ChatColor.RESET.toString ( ) + ChatColor.GOLD + "Firestarter" );
			i.setItemMeta ( m );
			i.setDurability ( ( short ) ( i.getDurability ( ) / 2 ) );
			p.getPlayer ( ).getInventory ( ).addItem ( i );
			
			i = new ItemStack ( Material.LEATHER_CHESTPLATE );
			m = i.getItemMeta ( );
			LeatherArmorMeta lm = ( LeatherArmorMeta ) m;
			lm.setColor ( Color.MAROON );
			lm.setDisplayName ( ChatColor.RESET.toString ( ) + ChatColor.GOLD + "Pyrotechnic's Shirt" );
			lm.addEnchant ( Enchantment.PROTECTION_FIRE, 2, false );
			i.setItemMeta ( lm );
			p.getPlayer ( ).getInventory ( ).setChestplate ( i );
			
			i = new ItemStack ( Material.LEATHER_LEGGINGS );
			m = i.getItemMeta ( );
			lm = ( LeatherArmorMeta ) m;
			lm.setColor ( Color.MAROON );
			lm.setDisplayName ( ChatColor.RESET.toString ( ) + ChatColor.GOLD + "Pyrotechnic's Pants" );
			lm.addEnchant ( Enchantment.PROTECTION_FIRE, 2, false );
			i.setItemMeta ( lm );
			p.getPlayer ( ).getInventory ( ).setLeggings ( i );
		}
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
		@Override
		public void equip ( SPlayer p )
		{
			p.clearInventory ( );
			ItemStack i = new ItemStack ( Material.PRISMARINE_SHARD );
			ItemMeta m = i.getItemMeta ( );
			m.setDisplayName ( ChatColor.RESET.toString ( ) + ChatColor.BLUE + "Fire Extinguisher" );
			i.setItemMeta ( m );
			p.getPlayer ( ).getInventory ( ).addItem ( i );
			
			i = new ItemStack ( Material.LEATHER_CHESTPLATE );
			m = i.getItemMeta ( );
			LeatherArmorMeta lm = ( LeatherArmorMeta ) m;
			lm.setColor ( Color.BLACK );
			lm.setDisplayName ( ChatColor.RESET.toString ( ) + ChatColor.BLUE + "Fireman's Shirt" );
			lm.addEnchant ( Enchantment.PROTECTION_FIRE, 4, false );
			lm.addEnchant ( Enchantment.PROTECTION_ENVIRONMENTAL, 3, true );
			i.setItemMeta ( lm );
			p.getPlayer ( ).getInventory ( ).setChestplate ( i );
			
			i = new ItemStack ( Material.LEATHER_LEGGINGS );
			m = i.getItemMeta ( );
			lm = ( LeatherArmorMeta ) m;
			lm.setColor ( Color.BLACK );
			lm.setDisplayName ( ChatColor.RESET.toString ( ) + ChatColor.BLUE + "Fireman's Pants" );
			lm.addEnchant ( Enchantment.PROTECTION_FIRE, 4, false );
			lm.addEnchant ( Enchantment.PROTECTION_ENVIRONMENTAL, 3, true );
			i.setItemMeta ( lm );
			p.getPlayer ( ).getInventory ( ).setLeggings ( i );
		}
	},
	TRAPPER ( Material.IRON_TRAPDOOR, ChatColor.RESET.toString ( ) + ChatColor.DARK_PURPLE + "Trapper",  
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Master of traps!\"", 
			ChatColor.GRAY + "Can create traps from iron. Because",
			ChatColor.GRAY + "of this, the trapper can also mine",
			ChatColor.GRAY + "more iron than other players.",
			"",
			ChatColor.GREEN + "Defensive",  } ) )
	{
		@Override
		public void equip ( SPlayer p )
		{
			//Allows this player to mine 3 more iron, since this var needs to be incremented to 5 to consider the player full on iron.
			p.setVar ( "oreMined", -3 );
		}
	},
	FIGHTER ( Material.IRON_SWORD, ChatColor.RESET.toString ( ) + ChatColor.YELLOW + "Fighter",  
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Wields a nice sword.\"", 
			ChatColor.GRAY + "Starts with a special iron sword. The",
			ChatColor.GRAY + "fighter can also use iron to craft",
			ChatColor.GRAY + "throwing knives.",
			"",
			ChatColor.RED + "Offensive",
			ChatColor.GREEN + "Defensive",  } ) )
	{
		@Override
		public void equip ( SPlayer p )
		{
			p.clearInventory ( );
			ItemStack sword = new ItemStack ( Material.IRON_SWORD );
			ItemMeta m = sword.getItemMeta ( );
			m.addEnchant ( Enchantment.KNOCKBACK, 2, false );
			m.setDisplayName ( "Glowing Sword" );
			sword.setItemMeta ( m );
			p.getInventory ( ).addItem ( sword );
		}	
	},
	WELDER ( Material.REDSTONE_TORCH_ON, ChatColor.RESET.toString ( ) + ChatColor.LIGHT_PURPLE + "Welder",  
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Makes durable blocks.\"", 
			ChatColor.GRAY + "The welder can take iron and make it",
			ChatColor.GRAY + "into iron plates. These can them be",
			ChatColor.GRAY + "welded into durable metallic blocks.",
			"",
			ChatColor.BLUE + "Builder",
			ChatColor.GREEN + "Defensive",  } ) )
	{
		@Override
		public void equip ( SPlayer p )
		{
			p.clearInventory ( );
			ItemStack weld = new ItemStack ( Material.REDSTONE_TORCH_ON );
			ItemMeta m = weld.getItemMeta ( );
			m.addEnchant ( Enchantment.FIRE_ASPECT, 1, true );
			m.setDisplayName ( "Arc Welder" );
			weld.setItemMeta ( m );
			p.getInventory ( ).addItem ( weld );
		}
	},
	CONTRACTOR ( Material.CLAY_BRICK, ChatColor.RESET.toString ( ) + ChatColor.GREEN + "Contractor",  
			Arrays.asList ( new String [ ] { 
			ChatColor.RESET + "\"Controls a legion of builders.\"", 
			ChatColor.GRAY + "The contractor can mark out an area,",
			ChatColor.GRAY + "then hire a bunch of builders to fill",
			ChatColor.GRAY + "it quiclky with whatever block you give.",
			"",
			ChatColor.BLUE + "Builder", } ) )
	{
		@Override
		public void equip ( SPlayer p )
		{
			p.clearInventory ( );
			ItemStack tape = new ItemStack ( Material.STRING );
			ItemMeta m = tape.getItemMeta ( );
			m.setDisplayName ( "Tape Measure" );
			tape.setItemMeta ( m );
			p.getInventory ( ).addItem ( tape );
			
			ItemStack hire = new ItemStack ( Material.PAPER );
			m = hire.getItemMeta ( );
			m.setDisplayName ( "Hire Builders" );
			hire.setItemMeta ( m );
			p.getInventory ( ).addItem ( hire );
			
			GriefContractorData data = new GriefContractorData ( null );
			SingularityPlugin.info ( data.toString ( ) );
			p.setCustomPlayerData ( data );
		}
	};
	
	private Material displayItem;
	private String name;
	private List < String > description = new ArrayList < String > ( );
	
	private GriefClasses ( Material i, String n, List < String > l )
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
		for ( GriefClasses c : GriefClasses.values ( ) )
		{
			tr.addButton ( c.getButton ( ) );
		}
		return tr;
	}
	
	public static GriefClasses getClass ( String n )
	{
		for ( GriefClasses c : GriefClasses.values ( ) )
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
