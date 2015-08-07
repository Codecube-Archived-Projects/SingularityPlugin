package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import org.bukkit.Location;

import com.gmail.therealcodecube.singularityplugin.hologram.ParticleGrid;
import com.gmail.therealcodecube.singularityplugin.player.CustomPlayerData;

public class GriefContractorData extends CustomPlayerData
{
	private ParticleGrid grid;
	
	public GriefContractorData ( ParticleGrid g )
	{
		grid = g;
	}
	
	public void setGrid ( ParticleGrid g )
	{
		grid = g;
	}
	
	public ParticleGrid getGrid ( )
	{
		return grid;
	}
	
	public void setFirstPoint ( Location l )
	{
		grid.setFirstPoint ( l );
	}
	
	public void setSecondPoint ( Location l )
	{
		grid.setSecondPoint ( l );
	}
}
