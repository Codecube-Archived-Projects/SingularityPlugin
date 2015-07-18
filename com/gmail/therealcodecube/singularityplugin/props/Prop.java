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
	
	//This function can be used to create new props from in-game commands, and is intended to be overridden by subclasses.
	public static Prop create ( Location l, String [ ] args )
	{
		return new Prop ( l );
	}
	
	public boolean update ( )
	{
		return true;
	}
}
