package com.gmail.therealcodecube.singularityplugin.player;

import com.gmail.therealcodecube.singularityplugin.sql.SQLValue;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;

public class SQLStat extends FormattedStat
{
	private String player;
	private String stat;
	
	//Initializes this SBoardStat with a player name, stat name, and format code.
	public SQLStat ( String p, String s, String f )
	{
		super ( f );
		player = p;
		stat = s;
	}
	
	public String getValue ( )
	{
		SQLValue v = DefaultTables.players.getProperty ( player, stat );
		String value;
		
		if ( v.getType ( ) == SQLValue.INT_TYPE )
		{
			value = Integer.toString ( v.getIntValue ( ) );
		}
		else
		{
			value =  v.getStringValue ( );
		}
		
		return value;
	}
}
