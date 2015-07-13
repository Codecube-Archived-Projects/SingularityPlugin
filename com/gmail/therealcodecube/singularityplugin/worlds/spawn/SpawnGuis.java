package com.gmail.therealcodecube.singularityplugin.worlds.spawn;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.gmail.therealcodecube.singularityplugin.Util;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonPressed;
import com.gmail.therealcodecube.singularityplugin.sgui.DynamicGuiLink;
import com.gmail.therealcodecube.singularityplugin.sgui.DynamicSGui;
import com.gmail.therealcodecube.singularityplugin.sgui.PveItemButton;
import com.gmail.therealcodecube.singularityplugin.sgui.SGui;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sgui.SGuiLink;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;

public enum SpawnGuis implements DynamicSGui
{
	//Inventory slot reference:
	// 0  1  2  3  4  5  6  7  8
	// 9  10 11 12 13 14 15 16 17
	// 18 19 20 21 22 23 24 25 26
	// 27 28 29 30 31 32 33 34 35
	// 36 37 38 39 40 41 42 43 44
	PVE_STORE
	{
		@Override
		public SGui buildGui ( SPlayer p )
		{
			//bg: buy glass, ig: inventory glass.
			SButton bg, ig;
			ItemStack i;
			ButtonPressed dummy = new ButtonPressed ( )
			{
				public boolean buttonPressed ( SPlayer s, ButtonInfo b )
				{
					//Just cancel the event without doing anything.
					return true;
				}
			};
			SGui tr = new SGui ( "PVE Store", 45 );
			
			i = Util.createStack ( Material.STAINED_GLASS_PANE, ( short ) 5, "Purchase Items Here" );
			bg = new SButton ( i, dummy );
			i = Util.createStack ( Material.STAINED_GLASS_PANE, ( short ) 13, "Your Inventory" );
			ig = new SButton ( i, dummy );
			//Buy glass slots. It forms a strip across the screen, with gaps for items for sale.
			tr.setButton ( 9, bg );
			tr.setButton ( 10, bg );
			tr.setButton ( 12, bg );
			tr.setButton ( 14, bg );
			tr.setButton ( 16, bg );
			tr.setButton ( 17, bg );
			//Inventory glass slots. Basically the same, except that it has gaps for what you already have.
			tr.setButton ( 27, ig );
			tr.setButton ( 28, ig );
			tr.setButton ( 30, ig );
			tr.setButton ( 32, ig );
			tr.setButton ( 34, ig );
			tr.setButton ( 35, ig );
			
			int cloth, stone, metal;
			cloth = DefaultTables.players.getProperty ( p.getName ( ) , "pve_cloth" ).getIntValue ( );
			stone = DefaultTables.players.getProperty ( p.getName ( ) , "pve_stone" ).getIntValue ( );
			metal = DefaultTables.players.getProperty ( p.getName ( ) , "pve_metal" ).getIntValue ( );
			
			//Cloth, stone, metal, zero.
			ItemStack cl, st, mt, zr;
			zr = Util.createStack ( Material.STAINED_GLASS_PANE, ( short ) 15, "You don't have any of this item!" );
			
			//If the player has any of a particular item, then show that quantity. Otherwise, show the firework star.
			if ( cloth > 0 )
			{
				cl = Util.createStack ( Material.CARPET, cloth, "You have " + cloth + " cloth." );
			}
			else { cl = zr; }
			
			if ( stone > 0 )
			{
				st = Util.createStack ( Material.MOSSY_COBBLESTONE, stone, "You have " + stone + " stone." );
			}
			else { st = zr; }
			
			if ( metal > 0 )
			{
				mt = Util.createStack ( Material.IRON_FENCE, metal, "You have " + metal + " metal." );
			}
			else { mt = zr; }
			
			//Cloth button, Stone button, Metal button.
			tr.setButton ( 29, new SButton ( cl, dummy ) );
			tr.setButton ( 31, new SButton ( st, dummy ) );
			tr.setButton ( 33, new SButton ( mt, dummy ) );
			
			//Purchase buttons for the resources
			tr.setButton ( 11, PveItemButton.CLOTH.getButton ( ) );
			tr.setButton ( 13, PveItemButton.STONE.getButton ( ) );
			tr.setButton ( 15, PveItemButton.METAL.getButton ( ) );
			
			return tr;
		}
	},
	STORE
	{
		@Override
		public SGui buildGui ( SPlayer p )
		{
			ItemStack i;
			SGui tr = new SGui ( "Store", 27 );
			
			//Link to PVE menu
			i = Util.createStack ( Material.ROTTEN_FLESH, ChatColor.RED + "PVE Store" );
			tr.setButton ( 13, new DynamicGuiLink ( i, PVE_STORE ) );
			return tr;
		}
	},;
	
	public SGui buildGui ( SPlayer p )
	{
		return null;
	}
}
