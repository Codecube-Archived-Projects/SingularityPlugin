package com.gmail.therealcodecube.singularityplugin.player;

import com.gmail.therealcodecube.singularityplugin.sql.SQLValue;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;

public class SQLStat implements SBoardStat
{
	private String player;
	private String stat;
	private String format;
	
	//Initializes this SBoardStat with a player name, stat name, and format code.
	public SQLStat ( String p, String s, String f )
	{
		player = p;
		stat = s;
		format = f;
	}
	
	public String formatStat ( )
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
		
		String toReturn = format;
		return toReturn.replaceAll ( "#", value );
	}
}
