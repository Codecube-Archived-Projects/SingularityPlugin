package com.gmail.therealcodecube.singularityplugin.player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.sgui.SGui;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;
import com.gmail.therealcodecube.singularityplugin.sql.SQLValue;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Worlds;

public class SPlayer
{
	private static HashMap < String, SPlayer > sPlayers = new HashMap < String, SPlayer > ( );
	private static BukkitRunnable updateBoardsTask;
	
	private HashMap < String, Integer > tempData = new HashMap < String, Integer > ( );
	private HashMap < Integer, SButton > specialItems = new HashMap < Integer, SButton > ( );
	
	private Player player;
	private SBoard board = null;
	private SGui shownGui = null;
	
	public SPlayer ( Player p )
	{
		player = p;
	}
	
	//Sends the player to spawn with a message as to why they were rejected from joining.
	public void rejectJoin ( String reason )
	{
		player.teleport ( SingularityPlugin.getSpawn ( ) );
		player.sendMessage ( "You were rejected from joining for the following reason: " + reason );
	}
	
	//Shows the player the GUI, and stores it for later use.
	public void setGui ( SGui s )
	{
		shownGui = s;
		player.openInventory ( shownGui.getInventory ( ) );
	}
	
	//Presses a button on the currently opened inventory.
	public void pressGui ( InventoryClickEvent e )
	{
		//This makes sure that the GUI only gets clicked if the clicked inventory is actually the GUI.
		if ( ( shownGui != null ) && ( e.getInventory ( ).getName ( ) == shownGui.getInventory ( ).getName ( ) ) )
		{
			shownGui.clickButton ( e );
		}
	}
	
	//Sets an item in the player's inventory to be a special item with a right-click event.
	public void setSpecialItem ( int pos, SButton item )
	{
		if ( specialItems.containsKey ( pos ) )
		{
			specialItems.remove ( pos );
		}
		
		specialItems.put ( pos, item );
		player.getInventory ( ).setItem ( pos, item.getDisplayItem ( ) );
	}
	
	//Uses a special item. 
	public void useSpecialItem ( int pos, PlayerInteractEvent e )
	{
		if ( specialItems.containsKey ( pos ) )
		{
			specialItems.get ( pos ).press ( e );
		}
	}
	
	//Uses a special item, but is safer to use, since it has more checks to check that the particular item is actually special.
	public void useSpecialItem ( int pos, ItemStack d, PlayerInteractEvent e )
	{
		if ( specialItems.containsKey ( pos ) )
		{
			SButton i = specialItems.get ( pos );
			if ( i.getDisplayItem ( ).getItemMeta ( ).getDisplayName ( ) == d.getItemMeta ( ).getDisplayName ( ) )
			{
				i.press ( e );
			}
		}
	}
	
	//Uses a special item, if the player has it.
	public void useSpecialItem ( ItemStack d, PlayerInteractEvent e )
	{
		for ( SButton s : specialItems.values ( ) )
		{
			if ( s.getDisplayItem ( ).getItemMeta ( ).getDisplayName ( ) == d.getItemMeta ( ).getDisplayName ( ) )
			{
				s.press ( e );
				return;
			}
		}
	}
	
	//Returns true if the item is a special item.
	public boolean hasSpecialItem ( int pos )
	{
		return specialItems.containsKey ( pos );
	}
	
	//Returns true if the item is a special item, and the player has it.
	public boolean hasSpecialItem ( ItemStack i )
	{
		if ( i != null && i.hasItemMeta ( ) )
		{
			for ( SButton s : specialItems.values ( ) )
			{
				if ( s.getDisplayItem ( ).getItemMeta ( ).getDisplayName ( ) == i.getItemMeta ( ).getDisplayName ( ) )
				{
					return true;
				}
			}
		}
		return false;
	}
	
	//Gets the player's inventory as a raw PlayerInventory
	public PlayerInventory getInventory ( )
	{
		return player.getInventory ( );
	}
	
	//Clears the player's inventory, including any special items.
	public void clearInventory ( )
	{
		PlayerInventory pi = player.getInventory ( );
		pi.clear ( );
		pi.setHelmet ( null );
		pi.setChestplate ( null );
		pi.setLeggings ( null );
		pi.setBoots ( null );
		
		specialItems.clear ( );
	}
	
	//Stores the SBoard for future updates
	public void setBoard ( SBoard b )
	{
		board = b;
		board.setPlayer ( player );
		board.buildBoard ( );
	}
	
	//Rebuilds the stored SBoard
	public void updateBoard ( )
	{
		if ( board != null )
		{
			board.buildBoard ( );
		}
	}
	
	//Returns this SPlayer's instance of Bukkit.Player
	public Player getPlayer ( )
	{
		return player;
	}
	
	//Send this player a message.
	public void sendMessage ( String message )
	{
		player.sendMessage ( message );
	}
	
	//Checks whether or not this player has a property named after the argument.
	public boolean isProp ( String i )
	{
		return tempData.containsKey ( i );
	}
	
	//Gets the value of a certain property.
	public int getProp ( String i )
	{
		if ( isProp ( i ) )
		{
			return tempData.get ( i );
		}
		else
		{
			return 0;
		}
	}
	
	//Sets the value of a certain property.
	public void setProp ( String i, int v )
	{
		if ( isProp ( i ) )
		{
			tempData.remove ( i );
		}
		
		tempData.put ( i, v );
	}
	
	//Offsets the value of a certain property.
	public void changeProp ( String i, int c )
	{
		int v = 0;
		if ( isProp ( i ) )
		{
			v = tempData.get ( i );
			tempData.remove ( i );
		}
		tempData.put ( i, v + c );
	}
	
	//Removes the named prop
	public void removeProp ( String i )
	{
		if ( isProp ( i ) )
		{
			tempData.remove ( i );
		}
	}
	
	//Gets the player's technical name.
	public String getName ( )
	{
		return player.getName ( );
	}
	
	//Gets the name that shows above the player's head.
	public String getDisplayName ( )
	{
		return player.getDisplayName ( );
	}
	
	//Sends the player to a particular type of world, if it can find an open one.
	public boolean joinWorld ( Class < ? extends WorldBehavior > world )
	{
		WorldBehavior tj = Worlds.findJoinableWorld ( world );
		WorldBehavior in = Worlds.getWorld ( player.getWorld ( ) );
		if ( tj != null )
		{
			in.leaveWorld ( this );
			tj.joinWorld ( this );
			return true;
		}
		return false;
	}
	
	//Gets how many points the player has.
	public int getPoints ( )
	{
		return DefaultTables.players.getProperty ( getName ( ), "points" ).getIntValue ( );
	}
	
	//Offsets this player's point value in the EntityPropertyDatabase based on the inputed value.
	public void changePoints ( int a )
	{
		//Get the value, offset it, then set the value to the new versoin.
		int c = DefaultTables.players.getProperty ( getName ( ), "points" ).getIntValue ( );
		c += a;
		DefaultTables.players.setProperty ( getName ( ), "points", new SQLValue ( c ) );
	}
	
	//Public functions for getting and setting players
	public static SPlayer addPlayer ( Player p )
	{
		SPlayer sp = new SPlayer ( p );
		sp.checkSQL();
		sPlayers.put ( p.getName ( ), sp );
		return sp;
	}
	
	//Functions for adding and removing players based on their name, the actual SPlayer object, or their corresponding Bukkit Player object.
	public static SPlayer getPlayer ( String s )
	{
		return sPlayers.get ( s );
	}
	
	public static SPlayer getPlayer ( Player s )
	{
		if ( !sPlayers.containsKey ( s.getName ( ) ) )
		{
			addPlayer ( s );
		}
		return sPlayers.get ( s.getName ( ) );
	}
	
	public static void removePlayer ( SPlayer p )
	{
		sPlayers.remove ( p.getPlayer ( ).getName ( ) );
	}
	
	public static void removePlayer ( Player p )
	{
		sPlayers.remove ( p.getName ( ) );
	}
	
	public static void removePlayer ( String p )
	{
		sPlayers.remove ( p );
	}
	
	//Rebuilds all of the players' boards.
	public static void rebuildBoards ( )
	{
		if ( updateBoardsTask == null )
		{
			updateBoardsTask = new BukkitRunnable ( )
			{
				public void run ( )
				{
					SPlayer.rebuildBoards ( );
				}
			};
			
			updateBoardsTask.runTaskTimer ( SingularityPlugin.getPlugin ( ) , 0L, 10L );
		}
		
		Iterator < Entry < String, SPlayer > > i = sPlayers.entrySet ( ).iterator ( );
		while ( i.hasNext ( ) ) 
		{
			Map.Entry < String, SPlayer > p = i.next ( );
			p.getValue ( ).updateBoard ( );
		}
	}
	
	//Public function for making sure that this player is in the SQL database
	public void checkSQL ( )
	{
		DefaultTables.players.addEntity ( player.getName ( ) );
	}
}
