package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.Location;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;

public class PlayerSpawnPoint extends Prop 
{
	public PlayerSpawnPoint ( Location l )
	{
		super ( l );
		//Elevate it off the ground to give it some more space.
		location.setY ( location.getY ( ) + 1.5 );
	}
	
	public void spawnPlayer ( SPlayer p )
	{
		p.getPlayer ( ).teleport ( location );
	}
	
	@Override
	public boolean update ( ) 
	{
		return true;
	}
}
