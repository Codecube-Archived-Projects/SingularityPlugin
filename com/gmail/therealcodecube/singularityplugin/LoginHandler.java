package com.gmail.therealcodecube.singularityplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import be.maximvdw.titlemotd.ui.Title;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Worlds;

public class LoginHandler implements Listener 
{
	@EventHandler
	public void onPlayerJoin ( PlayerJoinEvent e )
	{
		//Adds this player to the SPlayer database and
		//Use the code in the spawn world's world manager to manipulate the player.
		Worlds.joinWorld ( SPlayer.addPlayer( e.getPlayer ( ) ), SingularityPlugin.getSpawn ( ).getWorld ( ) );
		
		Title joinTitle = new Title ( "Hello, " + e.getPlayer ( ).getDisplayName ( ) + "!", 
				"You have " + DefaultTables.players.getProperty ( e.getPlayer ( ).getName ( ), "points" ).formatValue ( ) + " points." );
		joinTitle.send ( e.getPlayer ( ) );
	}
	
	@EventHandler
	public void onPlayerQuit ( PlayerQuitEvent e )
	{
		Worlds.leaveWorld ( SPlayer.getPlayer ( e.getPlayer ( ) ), e.getPlayer ( ).getWorld ( ) );
		SPlayer.removePlayer ( e.getPlayer ( ) );
	}
}
