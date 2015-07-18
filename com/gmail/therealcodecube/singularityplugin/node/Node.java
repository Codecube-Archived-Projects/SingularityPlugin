package com.gmail.therealcodecube.singularityplugin.node;

import java.util.Vector;

import org.bukkit.World;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;

public class Node 
{
	private static Vector < Node > nodes = new Vector < Node > ( );
	
	private WorldBehavior behavior;
	private World world;
	
	public Node ( World w )
	{
		world = w;
		SingularityPlugin.info ( "Added node for world " + w.getName ( ) );
	}
	
	public static void addNode ( Node n )
	{
		nodes.add ( n );
	}
	
	//Gets the node with the named world
	public static Node getNode ( String w )
	{
		for ( Node n : nodes )
		{
			if ( n.getWorldName ( ) == w )
			{
				return n;
			}
		}
		return null;
	}
	
	//Gets the node with the provided world
	public static Node getNode ( World w )
	{
		return getNode ( w.getName ( ) );
	}
	
	//Returns the best ranked (joinRank) node of a particular type.
	public static Node getBestNode ( Class < ? extends WorldBehavior > b )
	{
		Node tr = null;
		int rank = 0;
		for ( Node n : nodes )
		{
			if ( n.getBehaviorClass ( ) == b )
			{
				if ( n.getJoinRank ( ) > rank )
				{
					rank = n.getJoinRank ( );
					tr = n;
				}
			}
		}
		return tr;
	}
	
	//Tries to put a player in the best world, according to joinRanks
	public static void joinWorld ( Class < ? extends WorldBehavior > b, SPlayer p )
	{
		Node n = null;
		n = getBestNode ( b );
		if ( n != null )
		{
			SingularityPlugin.info ( "Found world " + n.getWorldName ( ) );
			n.join ( p );
		}
	}
	
	public void setBehavior ( Class < ? extends WorldBehavior > b )
	{
		//Try to create a new instance of the worldbehavior.
		try 
		{
			behavior = b.getDeclaredConstructor ( World.class ).newInstance ( world );
			behavior.init ( );
			SingularityPlugin.info ( "Set behavior to " + b.getName ( ) );
		} 
		catch ( Exception e ) 
		{
			e.printStackTrace();
		}
	}
	
	public Class < ? extends WorldBehavior > getBehaviorClass ( )
	{
		return behavior.getClass ( );
	}
	
	public WorldBehavior getBehavior ( )
	{
		return behavior;
	}
	
	public int getJoinRank ( )
	{
		return behavior.getJoinRank ( );
	}
	
	public void join ( SPlayer p )
	{
		//Don't join the world if it is full or otherwise unable to take players.
		if ( behavior.getJoinRank ( ) > 0 )
		{
			behavior.joinWorld ( p );
		}
	}
	
	public void leave ( SPlayer p )
	{
		behavior.leaveWorld ( p );
	}
	
	public String getWorldName ( )
	{
		return world.getName ( );
	}
}
