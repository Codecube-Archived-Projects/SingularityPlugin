package com.gmail.therealcodecube.singularityplugin.player;

import com.gmail.therealcodecube.singularityplugin.props.Timer;

public class TimerStat extends FormattedStat
{
	private Timer timer;
	
	public TimerStat ( Timer t, String f )
	{
		super ( f );
		timer = t;
	}
	
	public TimerStat ( Timer t )
	{
		//This format will just display the clock without any other text.
		super ( "#" );
		timer = t;
	}

	@Override
	public String getValue ( )
	{
		return timer.getTime ( );
	}
}
