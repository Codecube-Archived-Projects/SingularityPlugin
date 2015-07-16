package com.gmail.therealcodecube.singularityplugin.props;

import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import com.darkblade12.particleeffect.ParticleEffect;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEMobs;

public class ParticleTrailMobSpawner extends Prop
{
	private long startTime, endTime;
	private Location startPos, endPos;
	private ParticleEffect particleTrail;
	private PVEMobs mob;
	private Vector < LivingEntity > entityListPointer;
	
	public ParticleTrailMobSpawner ( Location s, Location e, long d, ParticleEffect t )
	{
		super ( s );
		startPos = s;
		endPos = e;
		startTime = System.currentTimeMillis ( );
		endTime = startTime + d;
		particleTrail = t;
		//Just a default mob.
		mob = PVEMobs.ZOMBIE_UNDERLING;
	}
	
	public ParticleTrailMobSpawner ( Location s, Location e, long d, ParticleEffect t, PVEMobs m )
	{
		super ( s );
		startPos = s;
		endPos = e;
		startTime = System.currentTimeMillis ( );
		endTime = startTime + d;
		particleTrail = t;
		mob = m;
	}
	
	public ParticleTrailMobSpawner ( Location s, Location e, long d, ParticleEffect t, PVEMobs m, Vector < LivingEntity > l )
	{
		//Make sure we copy the location, not just make another pointer to the same thing.
		super ( new Location (
				s.getWorld ( ),
				s.getX ( ),
				s.getY ( ),
				s.getZ ( )) );
		startPos = s;
		endPos = e;
		startTime = System.currentTimeMillis ( );
		endTime = startTime + d;
		particleTrail = t;
		mob = m;
		entityListPointer = l;
	}
	
	//Updates the prop.
	//Returns true if it should continue to exist.
	public boolean update ( )
	{
		if ( System.currentTimeMillis ( ) < endTime )
		{
			//Render the particle trail effect
			//This is basically a lerp function for the two locations specified
			double lerpAmount = ( System.currentTimeMillis ( ) - startTime ) / ( ( double ) ( endTime - startTime ) );
			location.setX ( ( ( endPos.getX ( ) - startPos.getX ( ) ) * lerpAmount ) + startPos.getX ( ) );
			location.setY ( ( ( endPos.getY ( ) - startPos.getY ( ) ) * lerpAmount ) + startPos.getY ( ) );
			location.setZ ( ( ( endPos.getZ ( ) - startPos.getZ ( ) ) * lerpAmount ) + startPos.getZ ( ) );
			
			particleTrail.display ( 0.1f, 0.1f, 0.1f, 0.0f, 10, location, 1000 );
		}
		else
		{
			//Render a cloud right before the mob appearance
			particleTrail.display( 0.5f, 1.0f, 0.5f, 0.0f, 40, endPos, 1000 );
			
			if ( System.currentTimeMillis ( ) >= ( endTime + 800L ) )
			{
				//Summon the mob.
				if ( entityListPointer == null )
				{
					mob.spawnEntity ( endPos );
				}
				else
				{
					entityListPointer.add ( mob.spawnEntity ( endPos ) );
				}
			}
		}
		
		return System.currentTimeMillis ( ) < ( endTime + 800L );
	}
}
