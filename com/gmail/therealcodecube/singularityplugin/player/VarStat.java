package com.gmail.therealcodecube.singularityplugin.player;

public class VarStat implements SBoardStat
{
	private SPlayer player;
	private String propName;
	private String format;
	
	//p is the player this stat is tracking
	//n is the var this stat is looking for
	//f is the formatting code
	public VarStat ( SPlayer p, String n )
	{
		player = p;
		propName = n;
		format = propName + ": #";
	}
	
	public VarStat ( SPlayer p, String n, String f )
	{
		player = p;
		propName = n;
		format = f;
	}

	@Override
	public String formatStat ( ) 
	{
		String value = Integer.toString ( player.getVar ( propName ) );
		String toReturn = format;
		return toReturn.replaceAll ( "#", value );
	}
}
