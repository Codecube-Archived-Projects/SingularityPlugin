package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.node.Node;
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
	
	/*@SuppressWarnings("unchecked")
	@Override
	public static Prop create ( Location l, String [ ] args )
	{
		try 
		{
			Class < ? > c =  Class.forName ( args [ 0 ] );
			Class < ? extends WorldBehavior > d;
			OrdinaryColor col = new OrdinaryColor ( 128, 128, 128 );
			//If c is of type WorldBehavior
			if ( WorldBehavior.class.isAssignableFrom ( c ) )
			{
				d = ( Class < ? extends WorldBehavior > ) c;
			}
			else
			{
				return new ErrorCarrier ( new String [ ] {
						"The class you specified:",
						args [ 0 ],
						"Does not extend the class com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior."
						});
			}
			
			if ( args.length > 1 )
			{
				//Make a color if possible.
				int r = Integer.parseInt ( args [ 1 ] ),
					g = Integer.parseInt ( args [ 2 ] ),
					b = Integer.parseInt ( args [ 3 ] );
			}
			Teleporter tr = new Teleporter ( l, d, col );
			return tr;
		}
		catch ( Exception e )
		{
			e.printStackTrace ( );
			if ( e instanceof IndexOutOfBoundsException )
			{
				return new ErrorCarrier ( new String [ ] { 
						"Invalid argument list! Arguments are:",
						"<WorldName> [ <R> <G> <B> ]",
						"WorldName is the fully qualified name of a class extending com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior",
						"R, G, and B are used for the color of the teleporter. Either they are all there, or none are.",
						"You gave " + args.length + " arguments, which does not match the syntax." 
						} );
			}
			else if ( e instanceof ClassNotFoundException )
			{
				return new ErrorCarrier ( new String [ ] {
						"The class name you provided: ",
						args [ 0 ],
						"Is not a valid class name. Please check your syntax.",
						"It needs to be a fully qualified class name, such as:",
						"com.gmail.therealcodecube.singularityplugin.worlds.spawn.SpawnWorld"
						} );
			}
		}
	}*/
	
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
		
		//Can't record the origin yet, because the origin is probably being created when this constructor is called!
		origin = null;
	}
	
	@Override
	public boolean update ( )
	{
		if ( origin == null )
		{
			origin = Node.getNode ( location.getWorld ( ) ).getBehavior ( );
		}
		
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
