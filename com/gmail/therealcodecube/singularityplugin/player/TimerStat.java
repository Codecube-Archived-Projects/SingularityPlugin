package com.gmail.therealcodecube.singularityplugin.player;

import com.gmail.therealcodecube.singularityplugin.props.Timer;

public class TimerStat implements SBoardStat 
{
	private Timer timer;
	private String format;
	
	public TimerStat ( Timer t, String f )
	{
		timer = t;
		format = f;
	}
	
	public TimerStat ( Timer t )
	{
		timer = t;
		//This format will just display the clock without any other text.
		format = "#";
	}
	
	@Override
	public String formatStat() 
	{
		String value = timer.getTime ( );
		String toReturn = format;
		return toReturn.replaceAll ( "#", value );
	}

}
