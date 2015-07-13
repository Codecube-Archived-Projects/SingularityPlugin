package com.gmail.therealcodecube.singularityplugin.worldbehavior;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;
import com.gmail.therealcodecube.singularityplugin.worlds.griefgame.GriefGame;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEGame;
import com.gmail.therealcodecube.singularityplugin.worlds.spawn.SpawnWorld;

public class Worlds 
{
	private static Vector < WorldBehavior > worlds = new Vector < WorldBehavior > ( );
	private static Vector < String > ws = new Vector < String > ( );
	
	public static void init ( )
	{
		addWorld ( new PVEGame ( Bukkit.getWorld ( "PVE" ) ) );
		addWorld ( new SpawnWorld ( Bukkit.getWorld ( "world" ) ) );
		addWorld ( new SurvivalWorld ( Bukkit.getWorld ( "survival" ) ) );
		addWorld ( new GriefGame ( Bukkit.getWorld ( "griefWars" ) ) );
		
		for ( WorldBehavior m: worlds )
		{
			m.init ( );
		}
	}
	
	private static void addWorld ( WorldBehavior m )
	{
		worlds.addElement ( m );
		ws.addElement ( m.getWorld ( ).getName ( ) );
		
		Bukkit.getLogger ( ).info ( "Added a manager for world " + m.getWorld ( ).getName ( ) );
	}
	
	//run this when first putting a player into a world (e.g. after being teleported.)
	public static void joinWorld ( SPlayer p, World w )
	{
		try 
		{
			getWorld ( w ).joinWorld ( p );
		}
		catch ( NullPointerException n )
		{
			n.printStackTrace ( );
			Bukkit.getLogger ( ).info ( "Failed to update player in world " + w.getName ( ) + ". This was probably caused by " + w.getName ( ) + " not having a WorldBehavior associated with it." );
		}
		
		
	}
	
	//run this when having a player leave a world (e.g. before being teleported.)
	public static void leaveWorld ( SPlayer p, World w )
	{
		try
		{
			getWorld ( w ).leaveWorld ( p );
		}
		catch ( NullPointerException n )
		{
			n.printStackTrace ( );
			Bukkit.getLogger ( ).info ( "Failed to update player in world " + w.getName ( ) + ". This was probably caused by " + w.getName ( ) + " not having a WorldBehavior associated with it." );
		}
	}
	
	//run this for every player to update their status, based on the rules of the world they are in.
	public static void updatePlayer ( SPlayer p )
	{
		try
		{
			getWorld ( p.getPlayer ( ).getWorld ( ) ).updatePlayer ( p );
		}
		catch ( NullPointerException n )
		{
			n.printStackTrace ( );
			Bukkit.getLogger ( ).info ( "Failed to update player in world " + p.getPlayer ( ).getWorld ( ).getName ( ) + ". This was probably caused by " + p.getPlayer ( ).getWorld ( ).getName ( ) + " not having a WorldBehavior associated with it." );
		}
	}
	
	//updates all players based on the rules of the world they are in.
	public static void updatePlayers ( )
	{
		for ( Player p : Bukkit.getOnlinePlayers ( ) )
		{
			updatePlayer ( SPlayer.getPlayer ( p ) );
		}
	}
	
	public static WorldBehavior getWorld ( World w )
	{
		if ( ws.contains ( w.getName ( ) ) )
		{
			return worlds.elementAt ( ws.indexOf ( w.getName ( ) ) );
		}
		else
		{
			return null;
		}
	}
	
	public static WorldBehavior getWorld ( String w )
	{
		return getWorld ( Bukkit.getWorld ( w ) );
	}
	
	public static WorldBehavior findJoinableWorld ( Class < ? extends WorldBehavior > b )
	{
		//Rating used to find the best server to join.
		int rating = 0;
		//The current holder for the highest ranked server.
		WorldBehavior world = null;
		for ( WorldBehavior i : worlds )
		{
			//If the world is of the appropriate type.
			if ( b.isInstance ( i ) )
			{
				int r = i.getJoinRank ( );
				if ( r > rating )
				{
					rating = r;
					world = i;
				}
			}
		}
		return world;
	}
}
