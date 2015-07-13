package com.gmail.therealcodecube.singularityplugin.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;

import lib.PatPeter.SQLibrary.SQLite;

public class SQLInterface 
{
	private static SQLite data;
	
	public static void init ( )
	{
		data = new SQLite ( SingularityPlugin.getPlugin ( ).getLogger ( ), 
				"[SingularityPlugin]",
				SingularityPlugin.getPlugin ( ).getDataFolder ( ).getAbsolutePath ( ),
				"data" );
		
		if ( !data.isOpen ( ) ) 
		{ 
			data.open ( ); 
		}
	}
	
	public static ResultSet sendQuery ( String q )
	{
		try 
		{
			return data.query ( q );
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			Bukkit.getLogger ( ).info ( "Failed to process SQL query: " + q );
			return null;
		}
	}
	
	public static boolean isTable ( String t )
	{
		return data.isTable ( t );
	}
	
	//Checks if there is a row with the particular property set as the SQLProperty's default value
	//T is the table to search in
	//P is the column to look from
	//V is the value to look for
	public static boolean isRow ( String t, String p, SQLValue v )
	{
		ResultSet r;
		if ( v.getType ( ) == SQLValue.INT_TYPE )
		{
			r = sendQuery ( "SELECT COUNT(*) FROM " + t + " WHERE " + p + " = " + v.getIntValue ( ) );
		}
		else
		{
			r = sendQuery ( "SELECT COUNT(*) FROM " + t + " WHERE " + p + " = \"" + v.getStringValue ( ) + "\"" );
		}
		
		try 
		{
			return r.getInt ( 1 ) == 1;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//Close the database
	public static void disable ( )
	{
		data.close ( );
	}
}
