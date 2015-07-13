package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.World;

import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.player.TimerStat;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Minigame;

public class GriefGame extends Minigame 
{
	private static final int MAX_ORE = 5;
	
	public GriefGame ( World w ) 
	{
		super(w);
	}

	@Override
	public void init ( )
	{
		setMaxPlayers ( 8 );
		
		//Set spawn point
		if ( spawn == null )
		{
			spawn = new Location ( getWorld ( ), 13, 33, 7.5 );
		}
		
		super.init ( );
	}
	
	@Override
	public void joinWorld ( SPlayer p )
	{
		super.joinWorld ( p );
		
		p.getPlayer ( ).setHealth ( 20.0 );
		p.getPlayer ( ).getInventory ( ).clear ( );
	}
	
	
	
	@Override
	protected SBoard getBoard ( SPlayer p )
	{
		Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
		stats.add ( new StaticStat ( p.getDisplayName ( ) ) );
		//The player's class name should go here.
		stats.add ( new StaticStat ( "Someone" ) );
		stats.add ( new StaticStat ( " " ) );
		stats.add ( new TimerStat  ( gameTimer ) );
		return new SBoard ( "----GriefWars----", stats );
	}
	
}
