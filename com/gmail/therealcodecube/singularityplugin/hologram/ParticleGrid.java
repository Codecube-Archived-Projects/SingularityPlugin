package com.gmail.therealcodecube.singularityplugin.hologram;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.Util;

public class ParticleGrid extends ParticleShape 
{
	private int xSteps = 0;
	private int ySteps = 0;
	private int zSteps = 0;
	private int frame = 0;
	private Location origSrt, origEnd, pDraw;
	
	public ParticleGrid ( Location s, Location e, OrdinaryColor o )
	{
		super ( s, DEFAULT_RESOLUTION, o );
		origSrt = s;
		origEnd = e;
		pDraw = e;
		calculateOffsets ( s, e );
	}
	
	public void setFirstPoint ( Location l )
	{
		origSrt = l;
		calculateOffsets ( origSrt, origEnd );
	}
	
	public void setSecondPoint ( Location l )
	{
		origEnd = l;
		calculateOffsets ( origSrt, origEnd );
	}
	
	private void calculateOffsets ( Location s, Location e )
	{
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
			pDraw.setX ( origin.getX ( ) + x );
			for ( int y = 0; y <= ySteps; y++ )
			{
				pDraw.setY ( origin.getY ( ) + y );
				for ( double z = 0; z <= zSteps; z += ( 1.0 / resolution ) )
				{
					pDraw.setZ ( origin.getZ ( ) + z );
					drawParticle ( pDraw );
				}
			}
		}
	}
	
	private void renderXZ ( )
	{
		for ( int x = 0; x <= xSteps; x++ )
		{
			pDraw.setX ( origin.getX ( ) + x );
			for ( int y = 0; y <= zSteps; y++ )
			{
				pDraw.setZ ( origin.getZ ( ) + y );
				for ( double z = 0; z <= ySteps; z += ( 1.0 / resolution ) )
				{
					pDraw.setZ ( origin.getZ ( ) + z );
					drawParticle ( pDraw );
				}
			}
		}
	}
	
	private void renderZY ( )
	{
		for ( int x = 0; x <= zSteps; x++ )
		{
			pDraw.setZ ( origin.getZ ( ) + x );
			for ( int y = 0; y <= ySteps; y++ )
			{
				pDraw.setY ( origin.getY ( ) + y );
				for ( double z = 0; z <= xSteps; z += ( 1.0 / resolution ) )
				{
					pDraw.setX ( origin.getX ( ) + z );
					drawParticle ( pDraw );
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
