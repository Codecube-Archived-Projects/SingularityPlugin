package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Worlds;

public class Teleporter extends Prop 
{
	private Class < ? extends WorldBehavior > destination;
	private OrdinaryColor color;
	//Set automatically to the world in which this prop was created.
	private WorldBehavior origin;
	private int frame = 0;
	
	public Teleporter ( Location l, Class < ? extends WorldBehavior > d )
	{
		this ( l, d, new OrdinaryColor ( 255, 255, 255 ) );
	}
	
	public Teleporter ( Location l, Class < ? extends WorldBehavior > d, OrdinaryColor o )
	{
		super ( l );
		//This makes sure the teleporter is centered on the block.
		location.setX ( location.getBlockX ( ) + 0.5 );
		location.setY ( location.getBlockY ( ) ); //Just need to be flat on the ground.
		location.setZ ( location.getBlockZ ( ) + 0.5 );
		destination = d;
		//Plain white
		color = o;
		//Remember which world this teleporter was created in.
		origin = Worlds.getWorld ( location.getWorld ( ) );
	}
	
	@Override
	public boolean update ( )
	{
		frame++;
		int f = frame % 120;
		//Make multiples.
		for ( int i = 0; i < 5; i++ )
		{
			f += 24;
			f = f % 120;
			//Makes a nice spiral shape.
			Location p = new Location ( 
				location.getWorld ( ), 
				location.getX ( ) + ( Math.sin ( f * 3 ) / 2 ), 
				location.getY ( ) + ( f / 41.0 ), 
				location.getZ ( ) + ( Math.cos ( f * 3 ) / 2 ) );
			ParticleEffect.REDSTONE.display ( color, p, 100.0 );
		}
		f = f / 4;
		for ( int i = 0; i < 2; i++ )
		{
			f += 15;
			f = f % 30;
			Location p = new Location ( 
					location.getWorld ( ), 
					location.getX ( ), 
					location.getY ( ) + ( f / 10.25 ), 
					location.getZ ( ) );
			ParticleEffect.REDSTONE.display ( color, p, 100.0 );
		}
		
		//Check if any players are inside the teleporter.
		for ( SPlayer p : origin.getPlayers ( ) )
		{
			if ( p.getPlayer ( ).getLocation ( ).getBlockX ( ) == location.getBlockX ( ) &&
					p.getPlayer ( ).getLocation ( ).getBlockZ ( ) == location.getBlockZ ( ) &&
					p.getPlayer ( ).getLocation ( ).getBlockY ( ) == location.getBlockY ( ) )
			{
				//Player coords match the teleporter coords, so teleport them to the destination.
				p.joinWorld ( destination );
			}
		}
		
		return true;
	}
}
