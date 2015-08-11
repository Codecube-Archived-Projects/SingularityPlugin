package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import org.bukkit.Location;

import com.gmail.therealcodecube.singularityplugin.hologram.ParticleBox;
import com.gmail.therealcodecube.singularityplugin.player.CustomPlayerData;

public class GriefContractorData extends CustomPlayerData
{
	private ParticleBox box;
	
	public GriefContractorData ( ParticleBox g )
	{
		box = g;
	}
	
	public void setBox ( ParticleBox g )
	{
		box = g;
	}
	
	public ParticleBox getBox ( )
	{
		return box;
	}
	
	public void setFirstPoint ( Location l )
	{
		box.setFirstPoint ( l );
	}
	
	public void setSecondPoint ( Location l )
	{
		box.setSecondPoint ( l );
	}
}
