package com.gmail.therealcodecube.singularityplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import be.maximvdw.titlemotd.ui.Title;

import com.gmail.therealcodecube.singularityplugin.node.Node;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;
import com.gmail.therealcodecube.singularityplugin.worlds.spawn.SpawnWorld;

public class LoginHandler implements Listener 
{
	@EventHandler
	public void onPlayerJoin ( PlayerJoinEvent e )
	{
		//Adds this player to the SPlayer database and
		//Use the code in the spawn world's world manager to manipulate the player.
		SPlayer p = SPlayer.addPlayer( e.getPlayer ( ) );
		Node.joinWorld ( SpawnWorld.class, p );
		
		Title joinTitle = new Title ( "Hello, " + e.getPlayer ( ).getDisplayName ( ) + "!", 
				"You have " + DefaultTables.players.getProperty ( e.getPlayer ( ).getName ( ), "points" ).formatValue ( ) + " points." );
		joinTitle.send ( e.getPlayer ( ) );
	}
	
	@EventHandler
	public void onPlayerQuit ( PlayerQuitEvent e )
	{
		Node.getNode ( e.getPlayer ( ).getWorld ( ) ).leave ( SPlayer.getPlayer ( e.getPlayer ( ) ) );
		SPlayer.removePlayer ( e.getPlayer ( ) );
	}
}
