package com.gmail.therealcodecube.singularityplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sql.SQLInterface;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;
import com.gmail.therealcodecube.singularityplugin.sql.SQLValue;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Worlds;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEItems;
import com.gmail.therealcodecube.singularityplugin.worlds.spawn.SpawnWorld;

public class SingularityPlugin extends JavaPlugin 
{
	static Location spawn;
	
	@Override
	public void onEnable ( )
	{
		//Record the spawn location for spawning players
		spawn = new Location ( getServer().getWorld( "world" ), -279, 162, 312 );
		
		//Add the login handler class as a listener, to manipulate players once they join.
		getServer ( ).getPluginManager ( ).registerEvents ( new LoginHandler ( ), this ); //9 100 119
		getLogger ( ).info ( "Spawn event has been registered. JAHKFJHALKJHFLA" );
		
		//Add an EventForwarder to forward all world-specific events to the worlds they were originated in..
		getServer ( ).getPluginManager ( ).registerEvents ( new EventForwarder ( ), this );
		
		//Initialize SQL
		SQLInterface.init ( );
		
		//Initialize default SQL tables
		DefaultTables.init ( );
		
		//Start the scoreboard update loop
		SPlayer.rebuildBoards ( );
		
		//Initialize all dimensions listed in the Worlds class
		Worlds.init();
	}
	
	@Override
	public void onDisable ( )
	{
		SQLInterface.disable ( );
	}
	
	public static Location getSpawn ( )
	{
		return spawn;
	}
	
	@Override
	public boolean onCommand ( CommandSender s, Command cmd, String l, String[] a )
	{
		//Reset the PVE map
		if ( cmd.getName ( ).equalsIgnoreCase ( "resetpveworld" ) )
		{
			//WorldBehavior pvem = Worlds.getWorld( "PVE" );
			//( ( PVEWorld ) pvem ).resetWorld ( );
			
			return true;
		}
		//Teleport the player to the hub
		else if ( cmd.getName ( ).equalsIgnoreCase ( "hub" ) )
		{
			if ( s instanceof Player )
			{
				SPlayer p = SPlayer.getPlayer ( ( Player ) s );
				p.joinWorld ( SpawnWorld.class );
				return true;
			}
			else
			{
				getLogger ( ).info ( "You must be a player to run this command!" );
			}
		}
		//Give the player Mega-Points
		else if ( cmd.getName ( ).equalsIgnoreCase ( "givemp" ) )
		{
			if ( s instanceof Player )
			{
				Player p = ( ( Player ) s );
				DefaultTables.players.setProperty ( p.getName ( ), "mega_points", new SQLValue ( 100 ) );
				DefaultTables.players.setProperty ( p.getName ( ), "points", new SQLValue ( 98000 ) );
				return true;
			}
			else
			{
				getLogger ( ).info ( "You must be a player to run this command!" );
			}
		}
		//Print the player's position to the console
		else if ( cmd.getName ( ).equalsIgnoreCase ( "pos" ) )
		{
			if ( s instanceof Player )
			{
				Player p = ( ( Player ) s );
				p.sendMessage ( "X:" + p.getLocation ( ).getBlockX ( ) );
				p.sendMessage ( "Y:" + p.getLocation ( ).getBlockY ( ) );
				p.sendMessage ( "Z:" + p.getLocation ( ).getBlockZ ( ) );
				
				getLogger ( ).info ( p.getLocation ( ).getBlockX ( ) + " " + p.getLocation ( ).getBlockY ( ) + " " + p.getLocation ( ).getBlockZ ( ) );
			}
			else
			{
				getLogger ( ).info ( "You must be a player to run this command!" );
			}
		}
		else if ( cmd.getName ( ).equalsIgnoreCase ( "givepver" ) )
		{
			if ( s instanceof Player )
			{
				Player p = ( ( Player ) s );
				for ( int i = 0; i < 32; i++ ) p.getInventory ( ).addItem ( PVEItems.CLOTH.craft ( ) );
				for ( int i = 0; i < 32; i++ ) p.getInventory ( ).addItem ( PVEItems.STONE.craft ( ) );
				for ( int i = 0; i < 32; i++ ) p.getInventory ( ).addItem ( PVEItems.METAL.craft ( ) );
			}
		}
		
		return false;
	}
	
	public static SingularityPlugin getPlugin ( ) 
	{ 
		Plugin s = Bukkit.getPluginManager ( ).getPlugin ( "SingularityPlugin" );
		if ( s instanceof SingularityPlugin )
		{
			return ( ( SingularityPlugin ) s ); 
		}
		else
		{
			Bukkit.getLogger ( ).info ( "Error: unable to cast plugin SingularityPlugin to type SingularityPlugin!" );
			Bukkit.getLogger ( ).info ( "Something is seriously wrong." );
			return null;
		}
	}
	
	public static void info ( String i )
	{
		getPlugin ( ).getLogger ( ).info ( i );
	}
}