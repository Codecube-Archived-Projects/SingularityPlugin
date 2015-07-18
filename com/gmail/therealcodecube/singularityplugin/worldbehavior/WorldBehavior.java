package com.gmail.therealcodecube.singularityplugin.worldbehavior;

import java.util.Iterator;
import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.props.Prop;

public class WorldBehavior 
{
	//The hard limit of how many players can be on a world.
	public static final int PLAYER_HARD_LIMIT = 128;
	protected Vector < Prop > props = new Vector < Prop > ( );
	protected Vector < SPlayer > players = new Vector < SPlayer > ( );
	private BukkitRunnable updateLoop;
	protected World world;
	protected Location spawn;
	
	public WorldBehavior ( World w )
	{
		world = w;
	}
	
	public void addProp ( Prop p )
	{
		props.add ( p );
	}
	
	public void updateProps ( )
	{
		Iterator < Prop > propIterator = props.iterator ( );
		while ( propIterator.hasNext ( ) )
		{
			Prop p = propIterator.next ( );
			if ( !p.update ( ) )
			{
				propIterator.remove ( );
			}
		}
	}
	
	protected void runUpdateLoop ( long ticks )
	{
		class WorldUpdateLoop extends BukkitRunnable
		{
			private WorldBehavior w;
			
			public WorldUpdateLoop ( WorldBehavior s )
			{
				w = s;
			}
			
			public void run ( )
			{
				w.updateProps ( );
				for ( SPlayer p : w.players )
				{
					w.updatePlayer ( p );
				}
			}
		};
		
		updateLoop = new WorldUpdateLoop ( this );
		updateLoop.runTaskTimer ( SingularityPlugin.getPlugin ( ) , 0L, ticks );
	}
	
	public void init ( )
	{
		runUpdateLoop ( 1L );
	}

	public void leaveWorld ( SPlayer p )
	{
		players.remove ( p );
	}

	public void joinWorld ( SPlayer p )
	{
		players.add ( p );
		p.getPlayer ( ).teleport ( spawn );
	}

	public void updatePlayer ( SPlayer p )
	{

	}

	public void setWorld ( World w )
	{
		world = w;
	}
	
	public World getWorld ( )
	{
		return world;
	}
	
	//Returns the size of the player list.
	public int getPlayerCount ( )
	{
		return players.size ( );
	}
	
	//Returns the player list.
	public Vector < SPlayer > getPlayers ( )
	{
		return players;
	}
	
	//Computes a numeric value ranking this server as far as joining.
	public int getJoinRank ( )
	{
		//The more players, the farther down in the rankings.
		//This evenly distributes the load across servers.
		return PLAYER_HARD_LIMIT - players.size ( );
	}
	
	//Lots of placeholder functions so that various worlds can override various events.
	public void onRightClick ( PlayerInteractEvent e )
	{
		
	}
	
	public void onLeftClick ( PlayerInteractEvent e )
	{
		
	}

	public void onEntityDamage ( EntityDamageByEntityEvent e ) 
	{	
		
	}
	
	public void onEntityDeath ( EntityDeathEvent e )
	{
		
	}
	
	public void onPlayerDeath ( PlayerDeathEvent e )
	{
		
	}
	
	@Deprecated
	public void onInventoryClick ( InventoryClickEvent e )
	{
		
	}
}
