package com.gmail.therealcodecube.singularityplugin.sgui;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;

public interface ButtonPressed 
{
	//This will return whether or not the event should be cancelled.
	public abstract boolean buttonPressed ( SPlayer p, ButtonInfo i );
}
