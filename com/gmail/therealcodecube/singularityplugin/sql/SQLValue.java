package com.gmail.therealcodecube.singularityplugin.sql;

public class SQLValue 
{
	public static final boolean INT_TYPE = false;
	public static final boolean STRING_TYPE = true;
	
	private String sValue = null;
	private int iValue = 0;
	private boolean type = INT_TYPE;
	
	public SQLValue ( int i )
	{
		type = INT_TYPE;
		iValue = i;
	}
	
	public SQLValue ( String s )
	{
		type = STRING_TYPE;
		sValue = s;
	}
	
	public boolean getType ( )
	{
		return type;
	}
	
	public String getStringValue ( )
	{
		return sValue;
	}
	
	public int getIntValue ( )
	{
		return iValue;
	}
	
	//Formats an SQL value for assignment based on its type
	//For example, asdf becomes "asdf" and 0 becomes 0.
	public String formatValue ( )
	{
		if ( getType ( ) == SQLValue.INT_TYPE )
		{
			return Integer.toString ( getIntValue ( ) );
		}
		else
		{
			return "\"" + getStringValue ( ) + "\"";
		}
	}
}
