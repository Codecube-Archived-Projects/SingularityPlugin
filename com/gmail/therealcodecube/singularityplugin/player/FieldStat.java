package com.gmail.therealcodecube.singularityplugin.player;

public class FieldStat extends FormattedStat 
{
	private SPlayer player;
	private String field;
	
	public FieldStat ( SPlayer p, String f )
	{
		super ( f + ": #" );
		player = p;
		field = f;
	}
	
	public FieldStat ( SPlayer p, String f, String c )
	{
		super ( c );
		player = p;
		field = f;
	}
	
	@Override
	public String getValue() 
	{
		return player.getField ( field );
	}

}
