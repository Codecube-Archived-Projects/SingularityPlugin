package com.gmail.therealcodecube.singularityplugin.hologram;

import java.util.Vector;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.Util;

public class ParticleBox extends ParticleShape 
{
	private int x, y, z;
	private Vector < ParticleLine > lines = new Vector < ParticleLine > ( );
	public ParticleBox ( Location c, int x, int y, int z )
	{
		super ( c, DEFAULT_RESOLUTION, DEFAULT_COLOR );
		this.x = x;
		this.y = y;
		this.z = z;
		construct ( );
	}
	
	public ParticleBox ( Location c, int x, int y, int z, OrdinaryColor o )
	{
		super ( c, DEFAULT_RESOLUTION, o );
		this.x = x;
		this.y = y;
		this.z = z;
		construct ( );
	}
	
	private void construct ( )
	{
		lines.clear ( );
		
		SingularityPlugin.info ( "X: " + x + " Y: " + y + " Z: " + z );
		
		//Lines coming out from the origin, pointing in the x, y, and z directions.
		Location s = new Location ( origin.getWorld ( ), origin.getX ( ), origin.getY ( ), origin.getZ ( ) ), 
				 e = new Location ( origin.getWorld ( ), origin.getX ( ), origin.getY ( ), origin.getZ ( ) );
		e.setX ( origin.getX ( ) + x );
		//We have to use Util.clone, otherwise the line will just store a pointer to the location. (that's bad)
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setX ( origin.getX ( ) );
		e.setY ( origin.getY ( ) + y );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		e.setY ( origin.getY ( ) );
		e.setZ ( origin.getZ ( ) + z );
		lines.add ( ParticleLine.fromEndpoints ( Util.clone ( s ), e, color) );
		
		//Lines coming out from the vertex opposite the origin, pointing in the x, y, and z directions.
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
		//Don't have to use clone here, since s will not change anymore.
		lines.add ( ParticleLine.fromEndpoints ( s, e, color) );
		
		for ( ParticleLine l : lines )
		{
			SingularityPlugin.info ( l.toString ( ) );
		}
	}
	
	public void setOrigin ( Location l )
	{
		origin = l;
		construct ( );
	}
	
	public void setOffsets ( int x, int y, int z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
