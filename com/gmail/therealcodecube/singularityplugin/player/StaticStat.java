package com.gmail.therealcodecube.singularityplugin.player;

//This stat is static, it never changes.
//It will always return the value assigned to it in the constructor.
//This is useful for lines of text and blank spaces.
public class StaticStat implements SBoardStat 
{
	private String data;
	
	public StaticStat ( String d )
	{
		data = d;
	}
	
	@Override
	public String formatStat() 
	{
		return data;
	}

}
