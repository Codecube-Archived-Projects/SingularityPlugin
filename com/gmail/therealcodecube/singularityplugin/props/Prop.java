package com.gmail.therealcodecube.singularityplugin.props;

import org.bukkit.Location;

public class Prop 
{
	protected Location location;
	
	public Prop ( )
	{
		
	}
	
	public Prop ( Location l )
	{
		location = l;
	}
	
	public boolean update ( )
	{
		return true;
	}
}
