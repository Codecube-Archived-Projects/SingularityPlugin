package com.gmail.therealcodecube.singularityplugin.player;

public class VarStat extends NumericalFormattedStat
{
	private SPlayer player;
	private String propName;
	
	//p is the player this stat is tracking
	//n is the var this stat is looking for
	//f is the formatting code
	public VarStat ( SPlayer p, String n )
	{
		super ( p + ": #" );
		player = p;
		propName = n;
	}
	
	public VarStat ( SPlayer p, String n, String f )
	{
		super ( f );
		player = p;
		propName = n;
	}

	@Override
	public int getValue ( )
	{
		return player.getVar ( propName );
	}
}
