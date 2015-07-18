package com.gmail.therealcodecube.singularityplugin.props;

public class ErrorCarrier extends Prop 
{
	private String [ ] errors;
	public ErrorCarrier ( String s ) { errors [ 0 ] = s; }
	public ErrorCarrier ( String [ ] s ) { errors = s; }
	public String getError ( ) { return errors [ 0 ]; }
	public String [ ] getErrors ( ) { return errors; }
	public void addError ( String s ) { errors [ errors.length ] = s; }
}
