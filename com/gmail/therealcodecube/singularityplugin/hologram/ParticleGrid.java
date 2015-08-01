package com.gmail.therealcodecube.singularityplugin.hologram;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.Util;

public class ParticleGrid extends ParticleShape 
{
	private int xSteps = 0;
	private int ySteps = 0;
	private int zSteps = 0;
	private int frame = 0;
	
	public ParticleGrid ( Location s, Location e, OrdinaryColor o )
	{
		super ( s, DEFAULT_RESOLUTION, o );
		Util.sortLocations ( s, e );
		e.setX ( e.getX ( ) + 1 );
		e.setY ( e.getY ( ) + 1 );
		e.setZ ( e.getZ ( ) + 1 );
		origin = s;
		xSteps = ( int ) Math.ceil ( e.getX ( ) - s.getX ( ) );
		ySteps = ( int ) Math.ceil ( e.getY ( ) - s.getY ( ) );
		zSteps = ( int ) Math.ceil ( e.getZ ( ) - s.getZ ( ) );
	}
	
	private void renderXY ( )
	{
		for ( int x = 0; x <= xSteps; x++ )
		{
			for ( int y = 0; y <= ySteps; y++ )
			{
				for ( double z = 0; z <= zSteps; z += ( 1.0 / resolution ) )
				{
					drawParticle ( new Location ( origin.getWorld ( ), 
							origin.getX ( ) + x,
							origin.getY ( ) + y,
							origin.getZ ( ) + z ) );
				}
			}
		}
	}
	
	private void renderXZ ( )
	{
		for ( int x = 0; x <= xSteps; x++ )
		{
			for ( int y = 0; y <= zSteps; y++ )
			{
				for ( double z = 0; z <= ySteps; z += ( 1.0 / resolution ) )
				{
					drawParticle ( new Location ( origin.getWorld ( ), 
							origin.getX ( ) + x,
							origin.getY ( ) + z,
							origin.getZ ( ) + y ) );
				}
			}
		}
	}
	
	private void renderZY ( )
	{
		for ( int x = 0; x <= zSteps; x++ )
		{
			for ( int y = 0; y <= ySteps; y++ )
			{
				for ( double z = 0; z <= xSteps; z += ( 1.0 / resolution ) )
				{
					drawParticle ( new Location ( origin.getWorld ( ), 
							origin.getX ( ) + z,
							origin.getY ( ) + y,
							origin.getZ ( ) + x ) );
				}
			}
		}
	}
	
	@Override
	public void render ( ) 
	{
		frame++;
		if ( frame == 3 ) { frame = 0; }
		
		switch ( frame )
		{
			case 0:
				renderXY ( );
				break;
			case 1:
				renderXZ ( );
				break;
			case 2:
				renderZY ( );
				break;
			default:
				break;
		}
			
	}
}
