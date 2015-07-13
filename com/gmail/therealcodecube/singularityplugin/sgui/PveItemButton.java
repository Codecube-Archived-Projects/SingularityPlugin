package com.gmail.therealcodecube.singularityplugin.sgui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.therealcodecube.singularityplugin.Util;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;
import com.gmail.therealcodecube.singularityplugin.sql.SQLValue;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEItems;

@SuppressWarnings("unused")
public enum PveItemButton
{
	CLOTH ( Material.CARPET, 800, 5, "Purchase Scrap Cloth", "pve_cloth" ),
	STONE ( Material.MOSSY_COBBLESTONE, 1600, 4, "Purchase Scrap Stone", "pve_stone" ), 
	METAL ( Material.IRON_FENCE, 3200, 3, "Purchase Scrap Metal", "pve_metal" );
	
	Material item;
	int price;
	int quantity;
	String displayName, name;
	
	private static final class PveItemButtonPress implements ButtonPressed
	{
		private int cost, quantity;
		private String propName;
		
		public PveItemButtonPress ( int c, int q, String p )
		{
			cost = c; quantity = q; propName = p;
		}
		
		public void setCost ( int c ) { cost = c; }
		public void setQuantity ( int q ) { quantity = q; }
		public void setPropName ( String n ) { propName = n; }
		
		//If the player has enough to purchase the item, the cost is deducted and the SQL value is incremented.
		public boolean buttonPressed ( SPlayer p, ButtonInfo b )
		{
			if ( p.getPoints ( ) >= cost )
			{
				int z = DefaultTables.players.getProperty ( p.getName ( ), propName ).getIntValue ( );
				z += quantity;
				DefaultTables.players.setProperty ( p.getName ( ), propName, new SQLValue ( z ) );
				p.changePoints ( -cost );
				p.getPlayer ( ).closeInventory ( );
			}
			return true;
		}
	};
	
	private PveItemButton ( Material i, int p, int q, String dn, String n )
	{
		item = i;
		price = p;
		quantity = q;
		displayName = dn;
		name = n;
	}
	
	public SButton getButton ( )
	{
		return new SButton ( Util.addLore ( Util.createStack ( item, quantity, displayName ), new String[] { "Costs " + price + " points." } ), new PveItemButtonPress ( price, quantity, name ) );
	}
}
