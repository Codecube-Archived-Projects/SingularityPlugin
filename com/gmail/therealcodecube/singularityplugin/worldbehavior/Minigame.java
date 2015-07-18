package com.gmail.therealcodecube.singularityplugin.worldbehavior;

import java.util.Vector;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import be.maximvdw.titlemotd.ui.Title;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.VarStat;
import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.props.Timer;
import com.gmail.therealcodecube.singularityplugin.props.TimerTask;

public class Minigame extends WorldBehavior 
{
	//A 96 hour delay
	public static final long MAX_DELAY = 96 * 60 * 60 * 1000L;
	private int baseDelay = 40;
	private int maxDelay = 10;
	private int maxPlayers = 8;
	protected int livingPlayers = 0;
	private TimerTask gameStart;
	private TimerTask gameEnd;
	protected Timer gameTimer;
	protected String name;
	protected boolean pvp = false;
	
	public Minigame ( World w ) 
	{
		super ( w );
		init ( );
	}
	
	@Override
	public void init ( )
	{
		//This initializes the update loop.
		super.init ( );
		
		class MinigameTimerTask extends TimerTask
		{
			protected Minigame game;
			public MinigameTimerTask ( Minigame g )
			{
				game = g; 
			}
			
			public boolean run ( Timer t )
			{
				return true;
			}
		}
		
		//The task to run when the game start timer runs out.
		gameStart = new MinigameTimerTask ( this )
		{
			@Override
			public boolean run ( Timer t )
			{
				if ( game.players.size ( ) > 0 )
				{
					//If there are enough players, start the game.
					game.startGame ( );
				}
				else
				{
					//If not enough players, set the timer for another 96 hours.
					t.setDuration ( MAX_DELAY );
				}
				//This timer should continue to exist.
				return true;
			}
		};
		
		//The task to run when the game ends.
		gameEnd = new MinigameTimerTask ( this )
		{
			@Override
			public boolean run ( Timer t )
			{
				for ( SPlayer p : game.players )
				{
					leaveWorld ( p );
					p.getPlayer ( ).teleport ( SingularityPlugin.getSpawn ( ) );
					Worlds.getWorld ( "world" ).joinWorld ( p );
				}
				
				game.cleanup ( );
				t.setDuration ( MAX_DELAY );
				t.setTask ( game.gameStart );
				
				return true;
			}
		};
		
		gameTimer = new Timer ( MAX_DELAY, gameStart );
		addProp ( gameTimer );
	}
	
	//This does several things wen a player joins:
	// -Sets up the point tracker
	// -Properly increments the living player count
	// -Displays a title, and shortens the timer
	// -Rejects the join if the game is full
	@Override
	public void joinWorld ( SPlayer p )
	{
		//Just a standard call to super, does a couple things for us.
		super.joinWorld ( p );
		
		//Reject the join if the game is full.
		if ( livingPlayers >= maxPlayers )
		{
			p.rejectJoin ( "This minigame is full. (" + maxPlayers + "/" + maxPlayers + ")" );
		}
		
		//Increment the living player count, set up point tracker.
		p.setVar ( "points", 0 );
		livingPlayers++;
		
		//Gives the player a scoreboard.
		SBoard board = getBoard ( p );
		p.setBoard ( board );
		
		//Display titles and such.
		Title title = null;
		if ( livingPlayers == 1 )
		{
			gameTimer.setDuration ( baseDelay * 1000L );
			title = new Title ( p.getDisplayName ( ) + "joined! (1/" + maxPlayers + ")",
									"Timer shortened to " + baseDelay + " seconds" );
		}
		else if ( livingPlayers == maxPlayers )
		{
			gameTimer.setDuration ( maxDelay * 1000L );
			title = new Title ( p.getDisplayName ( ) + "joined! (" + maxPlayers + "/" + maxPlayers + ")",
					"Timer shortened to " + maxDelay + " seconds" );
		}
		else
		{
			title = new Title ( p.getDisplayName ( ) + "joined! (" + livingPlayers + "/" + maxPlayers + ")",
					" " );
		}
		
		for ( SPlayer s : players )
		{
			title.send ( s.getPlayer ( ) );
		}
	}
	
	//When a player leaves, decrement the player count accordingly.
	@Override
	public void leaveWorld ( SPlayer p )
	{
		//Just a standard call to super, does a couple things for us.
		super.leaveWorld ( p );
				
		p.changePoints ( p.getVar ( "points" ) );
		p.removeVar ( "points" );
		//Only decrement the living player count if the player is actually living.
		if ( p.getPlayer ( ).getGameMode ( ) == GameMode.SURVIVAL )
		{
			livingPlayers--;
		}
		//If everyone left, clean up the world.
		if ( players.size ( ) == 0 )
		{
			cleanup ( );
			gameTimer.setDuration ( MAX_DELAY );
			gameTimer.setTask ( gameStart );
		}
	}
	
	//If a player is killed, make them a spectator, and call the onPlayerDeath function.
	@Override
	public void onEntityDamage ( EntityDamageByEntityEvent e )
	{
		if ( e.getEntity ( ) instanceof Player )
		{
			if ( e.getDamager ( ) instanceof Player && !pvp )
			{
				e.setCancelled ( true );
				return;
			}
			Player p = null;
			p = ( Player ) e.getEntity ( );
			if ( p.getHealth ( ) - e.getDamage ( ) <= 0 )
			{
				e.setCancelled ( true );
				p.setGameMode ( GameMode.SPECTATOR );
				p.setHealth ( 20 );
				p.getInventory ( ).clear ( );
				livingPlayers--;
				onPlayerDeath ( e );
			}
		}
	}
	
	//A function that can be overridden, it is called when the game starts.
	protected void startGame ( )
	{
		
	}
	
	//A function that is called when the game ends. It can be overridden, but it should contain a call to super.
	protected void endGame ( )
	{
		gameTimer.setDuration ( 10 * 1000L );
		gameTimer.setTask ( gameEnd );
	}
	
	//A function that returns a scoreboard for a particular player for this game.
	protected SBoard getBoard ( SPlayer p )
	{
		//A default scoreboard, looks like this:
		// DEFAULT
		// ---------
		// PlayerName
		// GameName
		// 0 Points
		Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
		stats.add ( new StaticStat ( p.getDisplayName ( ) ) );
		stats.add ( new VarStat ( p, "points", "# Points" ) );
		
		return new SBoard ( "DEFAULT", stats );
	}
	
	//Returns true if the game has started.
	protected boolean gameStarted ( )
	{
		return gameTimer.getTask ( ) != gameStart;
	}
	
	//Sets the countdown clock values for minimum number of players and maximum number of players.
	protected void setDelays ( int min, int max )
	{
		baseDelay = min; maxDelay = max;
	}
	
	//Sets the maximum number of players allowed in the game.
	protected void setMaxPlayers ( int max )
	{
		maxPlayers = max;
	}
	
	//Sets the TimerTask for when the game starts.
	protected void setStartTask ( TimerTask t )
	{
		gameStart = t;
	}
	
	//Sets the TimerTask for when the game ends.
	protected void setEndTask ( TimerTask t )
	{
		gameEnd = t;
	}
	
	//A function that can be overridden, it is called when a player dies.
	//By default, it ends the game once all the players are dead.
	protected void onPlayerDeath ( EntityDamageByEntityEvent e )
	{
		if ( livingPlayers == 0 )
		{
			endGame ( );
		}
	}
	
	//A function that is called when the world needs to be cleaned up.
	//It is usually called when all the players have left.
	protected void cleanup ( )
	{
		
	}
	
	//For minigames, the more players, the higher the ranking. This concentrates the players into several packed servers, instead of many sparse servers.
	@Override
	public int getJoinRank ( )
	{
		int rank = players.size ( ) + 1;
		if ( players.size ( ) >= maxPlayers )
		{
			//give it the lowest possible ranking if the server is filled.
			rank = 0;
		}
		
		return rank;
	}
}
