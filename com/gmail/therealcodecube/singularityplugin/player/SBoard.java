package com.gmail.therealcodecube.singularityplugin.player;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class SBoard 
{	
	private static ScoreboardManager manager = null;
	
	private Scoreboard board;
	private Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
	private String name = "NEW_SBOARD";
	private Team team;
	private Objective objective;
	private Player player;
	
	public SBoard ( String n, Vector < SBoardStat > s)
	{
		name = n;
		stats = s;
		player = null;
	}
	
	public SBoard ( String n, Vector < SBoardStat > s, Player p )
	{
		name = n;
		stats = s;
		player = p;
	}
	
	public void setPlayer ( Player p )
	{
		player = p;
	}
	
	public void buildBoard ( )
	{
		if ( manager == null )
		{
			manager = Bukkit.getScoreboardManager ( );
		}
		
		if ( board == null)
		{
			board = manager.getNewScoreboard ( );
		}
		
		if ( team == null )
		{
			team = board.registerNewTeam ( player.getName ( ) );
			team.addPlayer ( player );
			team.setAllowFriendlyFire ( false );
		}
		
		if ( objective != null )
		{
			objective.unregister ( );
			objective = null;
		}
		
		if ( objective == null )
		{
			//If we try to trim the name of the player when it's already short enough, we get an error.
			if ( player.getName ( ).length ( ) < 11 )
			{
				objective = board.registerNewObjective( player.getName ( ) + "STATS", "dummy" );
			}
			else
			{
				objective = board.registerNewObjective( player.getName ( ).substring ( 0, 10) + "STATS", "dummy" );
			}
			objective.setDisplaySlot ( DisplaySlot.SIDEBAR );
			objective.setDisplayName ( name );
		}
		
		int pos = 10;
		for ( SBoardStat i : stats )
		{
			Score s = objective.getScore ( i.formatStat ( ) );
			s.setScore ( --pos );
		}

		player.setScoreboard ( board );
	}
}
