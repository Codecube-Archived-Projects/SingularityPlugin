package com.gmail.therealcodecube.singularityplugin.props;

public class Timer extends Prop
{
	private long startTime;
	private long duration;
	private TimerTask task;
	
	public Timer ( long d )
	{
		startTime = System.currentTimeMillis ( );
		duration = d;
	}
	
	public Timer ( long d, TimerTask t )
	{
		startTime = System.currentTimeMillis ( );
		duration = d;
		task = t;
	}
	
	@Override
	public boolean update ( )
	{
		//Note: could cause overflow for extremely large durations.
		if ( System.currentTimeMillis ( ) > ( startTime + duration ) )
		{
			
			//This allows the task to determine whether or not the timer should be discarded.
			if ( task != null )
			{
				return task.run ( this );
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	public void setTask ( TimerTask t )
	{
		task = t;
	}
	
	public TimerTask getTask ( )
	{
		return task;
	}
	
	public void setDuration ( long d )
	{
		startTime = System.currentTimeMillis ( );
		duration = d;
	}
	
	public String getTime ( )
	{
		int timerTime = (int) ( duration - ( System.currentTimeMillis ( ) - startTime ) ) / 1000 ;
		int seconds = timerTime % 60, 
			minutes = ( int ) Math.floor ( ( timerTime - seconds ) / 60.0 ), 
			hours = ( int ) Math.floor( ( timerTime - ( minutes * 60 ) - seconds ) / 60.0 );
		String sSeconds = String.format( "%02d", seconds ),
			sMinutes = String.format( "%02d", minutes );
		
		if ( hours == 0 )
		{
			return sMinutes + ":" + sSeconds;
		}
		else
		{
			return hours + ":" + sMinutes + ":" + sSeconds;
		}
	}
}
