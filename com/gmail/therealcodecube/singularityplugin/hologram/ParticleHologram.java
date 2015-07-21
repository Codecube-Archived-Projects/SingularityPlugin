package com.gmail.therealcodecube.singularityplugin.hologram;

import java.util.Vector;

import org.bukkit.Location;

import com.gmail.therealcodecube.singularityplugin.Util;

public class ParticleHologram 
{
	private Vector < ParticleShape > shapes = new Vector < ParticleShape > ( );
	private Location origin;
	
	public ParticleHologram ( ) { origin = null; }
	public ParticleHologram ( Location o ) { origin = o; }
	
	public void addShape ( ParticleShape p )
	{
		shapes.add ( p );
	}
	
	public void move ( Location l )
	{
		Location s = Util.subtract ( l, origin );
		for ( ParticleShape p : shapes )
		{
			p.shift ( s );
		}
		origin = l;
	}
	
	public Location getOrigin ( )
	{
		return origin;
	}
	
	public void render ( )
	{
		for ( ParticleShape p : shapes )
		{
			p.render ( );
		}
	}
}
