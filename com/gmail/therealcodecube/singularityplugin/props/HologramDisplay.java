package com.gmail.therealcodecube.singularityplugin.props;

import com.gmail.therealcodecube.singularityplugin.hologram.ParticleHologram;

public class HologramDisplay extends Prop 
{
	private ParticleHologram hologram;
	
	public HologramDisplay ( ParticleHologram h )
	{
		hologram = h;
		location = hologram.getOrigin ( );
	}
	
	public ParticleHologram getHologram ( )
	{
		return hologram;
	}

	@Override
	public boolean update() 
	{
		hologram.render ( );
		return true;
	}
}
