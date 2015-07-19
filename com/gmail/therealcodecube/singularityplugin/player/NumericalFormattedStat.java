package com.gmail.therealcodecube.singularityplugin.player;

public abstract class NumericalFormattedStat implements SBoardStat 
{
	private String formatCode;
	public abstract int getValue ( ); 

	public NumericalFormattedStat ( String fc )
	{
		formatCode = fc;
	}
	
	@Override
	public String formatStat ( )
	{
		String tr = formatCode;
		//Replace all instances of # with the value
		tr = tr.replace ( "#", getValue ( ) + "" );
		//Replace all instances of ~ with either an empty space or an s.
		//E.G. # plum~ could become 1 plum or 200 plums
		tr = tr.replace ( "~", getValue ( ) == 1 ? "" : "s" );
		return tr;
	}
}
