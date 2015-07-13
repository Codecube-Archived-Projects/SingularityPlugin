package com.gmail.therealcodecube.singularityplugin.sql;

import java.util.Vector;

public class DefaultTables 
{
	public static EntityPropertyDatabase players;
	
	public static void init ( )
	{
		Vector < SQLProperty > p = new Vector < SQLProperty > ( );
		
		//Define the list of properties for the players database
		p.add ( new SQLProperty ( "points", new SQLValue ( 0 ) ) );
		p.add ( new SQLProperty ( "mega_points", new SQLValue ( 0 ) ) );
		p.add ( new SQLProperty ( "survival_inventory", new SQLValue ( "" ) ) );
		p.add ( new SQLProperty ( "survival_armor", new SQLValue ( "" ) ) );
		p.add ( new SQLProperty ( "pve_cloth", new SQLValue ( 0 ) ) );
		p.add ( new SQLProperty ( "pve_stone", new SQLValue ( 0 ) ) );
		p.add ( new SQLProperty ( "pve_metal", new SQLValue ( 0 ) ) );
		players = new EntityPropertyDatabase ( "players", p );
		players.init ( );
	}
}
