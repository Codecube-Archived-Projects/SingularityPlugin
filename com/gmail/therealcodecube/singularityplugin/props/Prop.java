package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.Location;

import com.gmail.therealcodecube.singularityplugin.hologram.ParticleHologram;

public abstract class Prop 
{
	protected Location location;
	protected ParticleHologram debugHologram = null;
	
	public Prop ( )
	{
		
	}
	
	public Prop ( Location l )
	{
		location = l;
	}
	
	public Location getLocation ( )
	{
		return location;
	}
	
	//This function can be used to create new props from in-game commands, and is intended to be overridden by subclasses.
	//public abstract static Prop create ( Location l, String [ ] args )
	
	public void renderDebugHologram ( )
	{
		if ( debugHologram != null )
		{
			debugHologram.move ( location );
			debugHologram.render ( );
		}
	}
	
	public abstract boolean update ( );
}
