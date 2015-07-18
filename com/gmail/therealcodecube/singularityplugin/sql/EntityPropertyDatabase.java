package com.gmail.therealcodecube.singularityplugin.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;

public class EntityPropertyDatabase 
{
	private String tableName;
	//A vector of properties that can be stored for each entity.
	private Vector < SQLProperty > properties = new Vector < SQLProperty > ( );
	
	public EntityPropertyDatabase ( String t, Vector < SQLProperty > v )
	{
		tableName = t;
		properties = v;
		this.init ( );
	}
	
	public void init ( )
	{
		if ( !SQLInterface.isTable ( tableName ) )
		{
			createTable ( );
		}
	}
	
	//A method for initializing the SQL table based on the provided list of properties.
	private void createTable ( )
	{
		String command = "";
		command += "CREATE TABLE " + tableName + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT";
		for ( SQLProperty i : properties )
		{
			command += ", " + i.getDeclaration ( ) ;
		}
		command += ");";
		SingularityPlugin.getPlugin ( ).getLogger ( ).info ( "Creating table with SQL query: " + command );
		SQLInterface.sendQuery ( command );
	}
	
	//Adds the named entity to the database.
	//Note: it does not have to actually represent a minecraft entity.
	public void addEntity ( String n )
	{
		if ( SQLInterface.isRow( tableName, "name", new SQLValue ( n ) ) )
		{
			SingularityPlugin.getPlugin ( ).getLogger ( ).info ( "Could not create a new row for entity " + n + ", it already has a row in the database." ); 
		}
		else
		{
			String command = "";
			command += "INSERT INTO " + tableName + " ( name";
			for ( SQLProperty i : properties )
			{
				command += ", " + i.getName ( );
			}
			
			command += " ) VALUES ( \"" + n + "\"";
			for ( SQLProperty i : properties )
			{
				command += ", " + i.getDefaultValue ( ).formatValue ( );
			}
			
			command += " );";
			SQLInterface.sendQuery ( command );
		}
	}
	
	//Gets a property for a specific named entity
	public SQLValue getProperty ( String e, String p )
	{
		String command = "SELECT " + p + " FROM " + tableName + " WHERE name = \"" + e + "\";";
		ResultSet set = SQLInterface.sendQuery ( command );
	
		try 
		{
			Object result = (Object) set.getObject ( 1 );
			
			//If the retrieved value is a string, return a string.
			//Otherwise, return an integer.
			if ( result instanceof String )
			{
				return new SQLValue ( ( String ) result );
			}
			else
			{
				return new SQLValue ( ( Integer ) result );
			}
		} 
		catch ( SQLException x ) 
		{
			x.printStackTrace();
		}
		
		SingularityPlugin.getPlugin ( ).getLogger ( ).info ( "returning null" );
		
		return null;
	}
	
	//Sets a property for a specific named entity
	public void setProperty ( String n, String p, SQLValue v )
	{
		String command = "UPDATE " + tableName + " SET " + p + " = " + v.formatValue ( ) + " WHERE name = \"" + n + "\";";
		SQLInterface.sendQuery ( command );
	}
}
