package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Worlds;

public class Teleporter extends Prop 
{
	private Class < ? extends WorldBehavior > destination;
	private OrdinaryColor color, lcolor;
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
		
		color = o;
		//Makeshift color desaturation.
		int r = color.getRed ( ),
			g = color.getGreen ( ),
			b = color.getBlue ( );
		r = r + ( 255 - r ) / 2;
		g = g + ( 255 - g ) / 2;
		b = b + ( 255 - b ) / 2;
		lcolor = new OrdinaryColor ( r, g, b );
				
		//Remember which world this teleporter was created in.
		SingularityPlugin.info ( location.getWorld ( ).getName ( ) );
		origin = Worlds.getWorld ( location.getWorld ( ) );
		SingularityPlugin.info ( origin.getWorld ( ).getName ( ) );
	}
	
	@Override
	public boolean update ( )
	{
		frame++;
		int f = frame % 60;
		//Make multiples.
		for ( int i = 0; i < 5; i++ )
		{
			f += 12;
			f = f % 60;
			//Makes a nice spiral shape.
			Location p = new Location ( 
				location.getWorld ( ), 
				location.getX ( ) + ( Math.sin ( f * 6 ) / 2 ), 
				location.getY ( ) + ( f / 20.5 ), 
				location.getZ ( ) + ( Math.cos ( f * 6 ) / 2 ) );
			ParticleEffect.REDSTONE.display ( color, p, 100.0 );
			ParticleEffect.REDSTONE.display ( color, p, 100.0 );
		}
		
		for ( int i = 0; i < 5; i++ )
		{
			f += 12;
			f = f % 60;
			//Makes a nice spiral shape.
			Location p = new Location ( 
				location.getWorld ( ), 
				location.getX ( ) + ( -Math.sin ( f * 6 ) / 2 ), 
				location.getY ( ) + ( f / 20.5 ), 
				location.getZ ( ) + ( -Math.cos ( f * 6 ) / 2 ) );
			ParticleEffect.REDSTONE.display ( color, p, 100.0 );
			ParticleEffect.REDSTONE.display ( color, p, 100.0 );
		}
		
		f = f % 15;
		for ( int i = 0; i < 12; i++ )
		{
			Location l = new Location ( 
					location.getWorld ( ), 
					location.getX ( ) + ( Math.sin ( i * 30 ) / 3 ) , 
					location.getY ( ) + ( f / 5.125 ), 
					location.getZ ( ) + ( Math.cos ( i * 30 ) / 3 ) );
			ParticleEffect.REDSTONE.display ( lcolor, l, 100.0 );
			ParticleEffect.REDSTONE.display ( lcolor, l, 100.0 );
		}
		
		//Check if any players are inside the teleporter.
		if ( origin.getPlayerCount ( ) > 0 )
		{
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
		}
		
		return true;
	}
}
