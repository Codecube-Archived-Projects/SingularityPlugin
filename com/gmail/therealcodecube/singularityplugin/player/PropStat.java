package com.gmail.therealcodecube.singularityplugin.player;

public class PropStat implements SBoardStat
{
	private SPlayer player;
	private String propName;
	private String format;
	
	//p is the player this stat is tracking
	//n is the property this stat is looking for
	//f is the formatting code
	public PropStat ( SPlayer p, String n )
	{
		player = p;
		propName = n;
		format = propName + ": #";
	}
	
	public PropStat ( SPlayer p, String n, String f )
	{
		player = p;
		propName = n;
		format = f;
	}

	@Override
	public String formatStat ( ) 
	{
		String value = Integer.toString ( player.getProp ( propName ) );
		String toReturn = format;
		return toReturn.replaceAll ( "#", value );
	}
}
