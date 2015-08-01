package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonPressed;

public class GriefClassClicked implements ButtonPressed 
{
	private GriefClasses griefClass;
	
	public GriefClassClicked ( GriefClasses c )
	{
		griefClass = c;
	}
	
	@Override
	public boolean buttonPressed ( SPlayer p, ButtonInfo i ) 
	{
		p.setField ( "class", griefClass.getName ( ) );
		p.getPlayer ( ).closeInventory ( );
		return true;
	}

}
