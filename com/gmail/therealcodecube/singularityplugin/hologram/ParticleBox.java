package com.gmail.therealcodecube.singularityplugin.hologram;

import java.util.Vector;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.Util;

public class ParticleBox extends ParticleShape 
{
	private int x, y, z;
	private Location firstPoint, secondPoint;
	private Vector < ParticleLine > lines = new Vector < ParticleLine > ( );
	public ParticleBox ( Location c, int x, int y, int z )
	{
		super ( c, DEFAULT_RESOLUTION, DEFAULT_COLOR );
		firstPoint = Util.clone ( c );
		this.x = x;
		this.y = y;
		this.z = z;
		sortCoords ( );
		construct ( );
	}
	
	public ParticleBox ( Location c, int x, int y, int z, OrdinaryColor o )
	{
		super ( c, DEFAULT_RESOLUTION, o );
		firstPoint = Util.clone ( c );
		this.x = x;
		this.y = y;
		this.z = z;
		sortCoords ( );
		construct ( );
	}
	
	private void sortCoords ( )
	{
		if ( x < 0 )
		{
			//Make it positive.
			x *= -1;
			origin.setX ( origin.getX ( ) - x );
		}
		
		if ( y < 0 )
		{
			//Make it positive.
			y *= -1;
			origin.setY ( origin.getY ( ) - y );
		}
		
		if ( z < 0 )
		{
			//Make it positive.
			z *= -1;
			origin.setZ ( origin.getZ ( ) - z );
		}
	}
	
	//Calculate the offsets from two points.
	private void calculateOffsets ( )
	{
		x = ( int ) ( secondPoint.getX ( ) - firstPoint.getX ( ) );
		y = ( int ) ( secondPoint.getY ( ) - firstPoint.getY ( ) );
		z = ( int ) ( secondPoint.getZ ( ) - firstPoint.getZ ( ) );
		origin = Util.clone ( firstPoint );
		sortCoords ( );
		x += 1;
		y += 1;
		z += 1;
	}
	
	private void construct ( )
	{
		lines.clear ( );
		
		//Lines coming out from the origin, pointing in the x, y, and z directions.
		Location s = new Location ( origin.getWorld ( ), origin.getX ( ), origin.getY ( ), origin.getZ ( ) ), 
				 e = new Location ( origin.getWorld ( ), origin.getX ( ), origin.getY ( ), origin.getZ ( ) );
		e.setX ( origin.getX ( ) + x );
		//We have to use Util.clone, otherwise the line will just store a pointer to the location. (that's bad)
		//e does not have to be cloned since it is not used beyond the constructor.
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setX ( origin.getX ( ) );
		e.setY ( origin.getY ( ) + y );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setY ( origin.getY ( ) );
		e.setZ ( origin.getZ ( ) + z );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		
		//Lines coming out from the vertex opposite the origin, pointing in the x, y, and z directions.
		//Lines that are commented out are that way because they are redundant or not used.
		s.setX ( origin.getX ( ) + x );
		s.setY ( origin.getY ( ) + y );
		s.setZ ( origin.getZ ( ) + z );
//		e.setX ( origin.getX ( ) + x );
		e.setY ( origin.getY ( ) + y );
//		e.setZ ( origin.getZ ( ) + z );
		
		e.setX ( origin.getX ( ) );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setX ( origin.getX ( ) + x );
		e.setY ( origin.getY ( )  );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setY ( origin.getY ( ) + y );
		e.setZ ( origin.getZ ( )  );
		
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		
		//s.setX ( origin.getX ( ) + x );
		//s.setY ( origin.getY ( ) + y );
		s.setZ ( origin.getZ ( ) );
		//e.setX ( origin.getX ( ) + x );
		//e.setY ( origin.getY ( ) + y );
		//e.setZ ( origin.getZ ( ) );
		
		e.setX ( origin.getX ( ) );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setX ( origin.getX ( ) + x );
		e.setY ( origin.getY ( ) );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		
		s.setX ( origin.getX ( ) );
		//s.setY ( origin.getY ( ) + y );
		s.setZ ( origin.getZ ( ) + z );
		e.setX ( origin.getX ( ) );
		e.setY ( origin.getY ( ) + y );
		//e.setZ ( origin.getZ ( ) + z );
		
		//e.setZ ( origin.getZ ( ) );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setZ ( origin.getZ ( ) + z );
		e.setY ( origin.getY ( ) );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		
		s.setX ( origin.getX ( ) + x );
		s.setY ( origin.getY ( ) );
		//s.setZ ( origin.getZ ( ) + z );
		//e.setX ( origin.getX ( ) + x );
		//e.setY ( origin.getY ( ) );
		//e.setZ ( origin.getZ ( ) + z );
		
		//e.setX ( origin.getX ( ) );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setX ( origin.getX ( ) + x );
		e.setZ ( origin.getZ ( ) );
		//Don't have to use clone here, since s will not change anymore.
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
	}
	
	public void setFirstPoint ( Location l )
	{
		firstPoint = l;
		calculateOffsets ( );
		construct ( );
	}
	
	public void setSecondPoint ( Location l )
	{
		secondPoint = l;
		calculateOffsets ( );
		construct ( );
	}
	
	public void setOffsets ( int x, int y, int z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
		sortCoords ( );
		construct ( );
	}
	
	@Override
	public void render ( ) 
	{
		for ( ParticleLine l : lines )
		{
			l.render ( );
		}
	}
}
