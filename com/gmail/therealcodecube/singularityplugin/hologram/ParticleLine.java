package com.gmail.therealcodecube.singularityplugin.hologram;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.Util;

public class ParticleLine extends ParticleShape 
{
	private Location offset, step;
	private int steps;
	
	public ParticleLine ( Location s, Location f, int r, OrdinaryColor o )
	{
		super ( r, o );
		origin = s;
		offset = f;
		calculateStep ( );
	}
	
	//This set of factory methods creates a line from two endpoints in space.
	//E.G. 192,78,1092 and 194,76,1092
	public static ParticleLine fromEndpoints ( Location s, Location e, int r, OrdinaryColor o )
	{
		return new ParticleLine ( s, Util.subtract ( e, s ), r, o );
	}
	public static ParticleLine fromEndpoints ( Location s, Location e, OrdinaryColor o ) { return fromEndpoints ( s, e, DEFAULT_RESOLUTION, o ); }
	public static ParticleLine fromEndpoints ( Location s, Location e, int r ) { return fromEndpoints ( s, e, r, DEFAULT_COLOR ); }
	
	//This set of factory methods creates a line from an origin point, as well as an endpoint described in relation to the origin point. 
	//E.G. 192,78,1092 and 2,-2,0.
	public static ParticleLine fromOffset ( Location o, Location f, int r, OrdinaryColor c )
	{
		return new ParticleLine ( o, f, r, c );
	}
	public static ParticleLine fromOffset ( Location o, Location f, OrdinaryColor c ) { return fromOffset ( o, f, DEFAULT_RESOLUTION, c ); }
	public static ParticleLine fromOffset ( Location o, Location f, int r ) { return fromOffset ( o, f, r, DEFAULT_COLOR ); }
	
	//This set of factory methods creates a line from a center point, as well as an offset that will be applied in both directions.
	//E.G. 193,77,1092 and 1,-1,0.
	public static ParticleLine fromCenter ( Location c, Location of, int r, OrdinaryColor co )
	{
		Location or = Util.subtract ( c, of );
		Location ofs = Util.multiply ( of, 2.0 );
		return new ParticleLine ( or, ofs, r, co );
	}
	public static ParticleLine fromCenter ( Location c, Location of, OrdinaryColor co ) { return fromCenter ( c, of, DEFAULT_RESOLUTION, co ); }
	public static ParticleLine fromCenter ( Location c, Location of, int r ) { return fromCenter ( c, of, r, DEFAULT_COLOR ); }
	
	private void calculateStep ( )
	{
		double size = offset.length ( );
		//How many particles we need to make to achieve the desired resolution.
		steps = ( int ) ( size * resolution );
		step = Util.divide ( offset, steps );
	}
	
	@Override
	public void render() 
	{
		Location dl = new Location ( origin.getWorld ( ), 0, 0, 0 );
		for ( int i = 0; i < steps; i++ )
		{
			dl.setX ( origin.getX ( ) + ( step.getX ( ) * i ) );
			dl.setY ( origin.getY ( ) + ( step.getY ( ) * i ) );
			dl.setZ ( origin.getZ ( ) + ( step.getZ ( ) * i ) );
			drawParticle ( dl );
		}
	}
	
	@Override
	public String toString ( )
	{
		String tr = "";
		tr += "Origin: " + origin.toString ( );
		tr += "\nOffset: " + offset.toString ( );
		return tr;
	}
}
