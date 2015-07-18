package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.World;

public class TimeMachine extends Prop 
{
	private World world;
	private int targetTime;
	private int timeStep;
	
	public TimeMachine ( World w, int t )
	{
		world = w;
		targetTime = t;
		timeStep = 55;
	}
	
	public TimeMachine ( World w, int t, int s )
	{
		world = w;
		targetTime = t;
		timeStep = s;
	}
	
	public boolean update ( )
	{
		if ( Math.abs ( targetTime - ( world.getTime ( ) - timeStep ) ) > timeStep  )
		{
			world.setTime ( world.getTime ( ) + timeStep );
			return true;
		}
		else if ( world.getTime ( ) < targetTime )
		{
			world.setTime ( targetTime );
			return true;
		}
		return false;
	}
}
