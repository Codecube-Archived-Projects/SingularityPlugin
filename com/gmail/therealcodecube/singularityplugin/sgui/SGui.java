package com.gmail.therealcodecube.singularityplugin.sgui;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;

public class SGui 
{
	private Inventory inventory;
	private String name;
	private int size;
	private HashMap < Integer, SButton > buttons = new HashMap < Integer, SButton > ( );
	private boolean canClickInventory = false;
	
	public SGui ( )
	{
		//Because _DEFAULT_ sounds really technical.
		name = "_DEFAULT_";
		size = 9;
	}
	
	//Constructs an SGui from a name and a size.
	//NOTE: SGuis use chest inventories, so the size MUST be a multiple of 9, and MUST NOT exceed 54!
	public SGui ( String n, int s )
	{
		name = n;
		size = s;
	}
	
	//Builds the inventory from the list of buttons.
	public void buildInventory ( )
	{
		inventory = Bukkit.createInventory ( null, size, name );
		for ( Entry < Integer, SButton > b : buttons.entrySet ( ) )
		{
			inventory.setItem ( b.getKey ( ), b.getValue ( ).getDisplayItem ( ) );
		}
	}
	
	//Returns the built inventory.
	public Inventory getInventory ( )
	{
		return inventory;
	}
	
	public void showInventory ( SPlayer p )
	{
		buildInventory ( );
		p.setGui ( this );
	}
	
	//Clears all the buttons from this GUI
	public void clear ( )
	{
		buttons.clear ( );
	}
	
	//Adds a button in the next available slot. If there is no available slot, the item will not be added.
	public void addButton ( SButton b )
	{
		for ( int i = 0; i < size; i++ )
		{
			if ( !buttons.containsKey ( i ) )
			{
				buttons.put ( i, b );
				return;
			}
		}
	}
	
	//Sets a button in a particular slot.
	public void setButton ( int slot, SButton b )
	{
		if ( buttons.containsKey ( slot ) )
		{
			buttons.remove ( slot );
		}
		buttons.put ( slot, b );
	} 
	
	//Runs a button's onClick function based on an inventoryClickEvent
	public void clickButton ( InventoryClickEvent e )
	{
		//The player can only click in their inventory if canClickInventory is true.
		if ( canClickInventory || e.getRawSlot ( ) < e.getInventory ( ).getSize ( ) )
		{
			//Automatically cancel it if the player cannot click in their inventory, because they must have clicked outside of their inventory.
			if ( !canClickInventory ) 
			{ 
				e.setCancelled ( true ); 
				buttons.get ( e.getSlot ( ) ).press ( e );
			}
		}
		e.setCancelled ( true );
	}
}