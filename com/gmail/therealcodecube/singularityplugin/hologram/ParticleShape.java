package com.gmail.therealcodecube.singularityplugin.hologram;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;

public abstract class ParticleShape 
{
	public static final int DEFAULT_RESOLUTION = 8;
	public static final OrdinaryColor DEFAULT_COLOR = new OrdinaryColor ( 128, 128, 128 );
	
	protected int resolution;
	protected Location origin;
	protected OrdinaryColor color;
	
	public ParticleShape ( Location c, int r, OrdinaryColor o )
	{
		color = o;
		origin = c;
		resolution = r;
	}
	
	public ParticleShape ( int r, OrdinaryColor o )
	{
		resolution = r;
		color = o;
	}
	
	public void shift ( Location l )
	{
		origin.add ( l );
	}
	
	public Location getOrigin ( )
	{
		return origin;
	}
	
	protected final void drawParticle ( Location l )
	{
		ParticleEffect.REDSTONE.display ( color, l, 5000.0 );
	}
	
	public abstract void render ( );
}
