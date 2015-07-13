package com.gmail.therealcodecube.singularityplugin.sql;

public class SQLProperty 
{
	private String name;
	private SQLValue defaultValue;
	
	public SQLProperty ( String s, SQLValue d )
	{
		name = s;
		defaultValue = d;
	}
	
	//Gets the value that all entities should be set to by default for this property
	public SQLValue getDefaultValue ( )
	{
		return defaultValue;
	}
	
	//Gets the set name of this SQL property
	public String getName ( )
	{
		return name;
	}
	
	//Returns an SQL-style formatted version of this property.
	//For example: justAnotherProperty INT
	public String getDeclaration ( )
	{
		if ( defaultValue.getType ( ) == SQLValue.INT_TYPE )
		{
			return name + " INTEGER";
		}
		else
		{
			return name + " TEXT";
		}
	}
}
