package com.gmail.therealcodecube.singularityplugin;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util 
{
	public static ItemStack createStack ( Material mat, String name )
	{
		ItemStack tr = new ItemStack ( mat );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, int quantity, String name )
	{
		ItemStack tr = new ItemStack ( mat, quantity );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, short damage, String name )
	{
		ItemStack tr = new ItemStack ( mat, 1, damage );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, int quantity, short damage, String name )
	{
		ItemStack tr = new ItemStack ( mat, quantity, damage );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack createStack ( Material mat, String name, String[] lore )
	{
		ItemStack tr = new ItemStack ( mat );
		ItemMeta im = tr.getItemMeta ( );
		im.setDisplayName ( name );
		im.setLore ( Arrays.asList ( lore ) );
		tr.setItemMeta ( im );
		return tr;
	}
	
	public static ItemStack addLore ( ItemStack tr, String[] lore )
	{
		ItemMeta im = tr.getItemMeta ( );
		im.setLore ( Arrays.asList ( lore ) );
		tr.setItemMeta ( im );
		return tr;
	}
	
	//Returns the center of two locations.
	public static Location center ( Location a, Location b )
	{
		double  x = ( a.getX ( ) + b.getX ( ) ) / 2.0,
				y = ( a.getY ( ) + b.getY ( ) ) / 2.0,
				z = ( a.getZ ( ) + b.getZ ( ) ) / 2.0;
		return new Location ( a.getWorld ( ), x, y, z );
	}
	
	//Returns the sum of two locations.
	public static Location add ( Location a, Location b )
	{
		double  x = a.getX ( ) + b.getX ( ),
				y = a.getY ( ) + b.getY ( ),
				z = a.getZ ( ) + b.getZ ( );
		return new Location ( a.getWorld ( ), x, y, z );
	}
	
	//Returns the difference of two locations.
	public static Location subtract ( Location a, Location b )
	{
		double  x = a.getX ( ) - b.getX ( ),
				y = a.getY ( ) - b.getY ( ),
				z = a.getZ ( ) - b.getZ ( );
		return new Location ( a.getWorld ( ), x, y, z );
	}
	
	//Divides the coordinates of a location, chopping it into a fraction of the original length.
	public static Location divide ( Location a, double b )
	{
		double  x = a.getX ( ) / b,
				y = a.getY ( ) / b,
				z = a.getZ ( ) / b;
		return new Location ( a.getWorld ( ), x, y, z );
	}
	
	//Multiplies the coordinates of a location, lengthening it into a multiple of the original length.
	public static Location multiply ( Location a, double b )
	{
		double  x = a.getX ( ) * b,
				y = a.getY ( ) * b,
				z = a.getZ ( ) * b;
		return new Location ( a.getWorld ( ), x, y, z );
	}
	
	private static Random random = new Random ( );
	//Returns a random int in the specified range.
	public static int random ( int lower, int upper )
	{
		return ( int ) Math.floor ( ( random.nextDouble ( ) * ( upper - lower ) ) + lower ); 
	}
	
	//Returns a random boolean
	public static boolean randomBoolean ( )
	{
		return random.nextBoolean ( );
	}
	
	//Sorts the two locations. One location will contain all the smaller coords, and the other al the larger coords.
	//The points will still describe the same rectangular volume (provided that's what they are being used for)
	//but will be optimized for for-loop applications.
	public static void sortLocations ( Location s, Location e )
	{
		double  x1 = s.getX ( ),
				y1 = s.getY ( ),
				z1 = s.getZ ( ),
				x2 = e.getX ( ),
				y2 = e.getY ( ),
				z2 = e.getZ ( );
		if ( x1 > x2 )
		{
			s.setX ( x2 );
			e.setX ( x1 );
		}
		if ( y1 > y2 )
		{
			s.setY ( y2 );
			e.setY ( y1 );
		}
		if ( z1 > z2 )
		{
			s.setZ ( z2 );
			e.setZ ( z1 );
		}
	}
	
	//Returns a clone of the given location.
	public static Location clone ( Location l )
	{
		return new Location ( l.getWorld ( ), l.getX ( ), l.getY ( ), l.getZ ( ) );
	}
}
