package com.gmail.therealcodecube.singularityplugin.player;

public abstract class FormattedStat implements SBoardStat 
{	
	private String formatCode;
	public abstract String getValue ( );

	public FormattedStat ( String fc )
	{
		formatCode = fc;
	}
	
	@Override
	public String formatStat ( ) 
	{
		String tr = formatCode;
		//Replace all instances of # with the value.
		tr.replaceAll ( "#", getValue ( ) );
		return tr;
	}

}
