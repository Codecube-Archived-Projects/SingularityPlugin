package com.gmail.therealcodecube.singularityplugin.worlds.spawn;

import java.util.Vector;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;

import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.SQLStat;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.props.Teleporter;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonPressed;
import com.gmail.therealcodecube.singularityplugin.sgui.DynamicGuiLink;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEGame;

public class SpawnWorld extends WorldBehavior 
{
	private SButton navigation, store;
	
	public SpawnWorld ( World w )
	{
		super ( w );
	}
	
	@Override
	public void init ( )
	{
		super.init ( );
		
		//Set up a teleporter
		Teleporter tp = new Teleporter ( 
				new Location ( world, -278, 163, 318),
				PVEGame.class,
				new OrdinaryColor ( 200, 20, 20 ) );
		addProp ( tp );
		
		//Set up the navigation button thing.
		ItemStack navItem = new ItemStack ( Material.EYE_OF_ENDER );
		ItemMeta m = navItem.getItemMeta ( );
		m.setDisplayName ( "Navigation" );
		navItem.setItemMeta ( m );
		
		ButtonPressed navAction = new ButtonPressed ( )
		{
			@Override
			public boolean buttonPressed ( SPlayer p, ButtonInfo i ) 
			{
				p.sendMessage ( "WIP" );
				return true;
			}
		};
		
		navigation = new SButton ( navItem, navAction );
		
		//Set up the store button thing.
		ItemStack storeItem = new ItemStack ( Material.GOLD_NUGGET );
		m = storeItem.getItemMeta ( );
		m.setDisplayName ( "Store" );
		storeItem.setItemMeta ( m );
		
		store = new DynamicGuiLink ( storeItem, SpawnGuis.STORE );
		
		//Set spawn location
		if ( spawn == null )
		{
			spawn = new Location ( getWorld ( ), -280, 162, 312 );
		}
	}
	
	@Override
	public void joinWorld ( SPlayer p ) 
	{
		super.joinWorld ( p );
		p.getPlayer ( ).setGameMode ( GameMode.SURVIVAL );
		p.getPlayer ( ).teleport ( SingularityPlugin.getSpawn ( ) );
		p.setBoard ( getBoard ( p.getPlayer ( ).getDisplayName ( ), p.getPlayer ( ).getName ( ) ) );
		p.clearInventory();
		
		//Set stats
		p.getPlayer ( ).setHealth ( 20 );
		p.getPlayer ( ).setFoodLevel ( 20 );
		
		//Give them the standard toolbar
		p.setSpecialItem ( 2, navigation );
		p.setSpecialItem ( 6, store );
	}
	
	@Override
	public void updatePlayer ( SPlayer p )
	{
		//Reset stats
		p.getPlayer ( ).setHealth ( 20 );
		p.getPlayer ( ).setFoodLevel ( 20 );
	}

	//Creates an SBoard for spawn, using the player's display name and technical name.
	private SBoard getBoard ( String n, String id )
	{
		Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
		stats.add ( new StaticStat ( n ) );
		stats.add ( new StaticStat ( "  " ) );
		stats.add ( new StaticStat ( "Spawn" ) );
		stats.add ( new StaticStat ( " " ) );
		stats.add ( new SQLStat ( id, "points", "# Points" ) );
		stats.add ( new SQLStat ( id, "mega_points", "# Mega-points" ) );
		
		return new SBoard ( "---SINGULARITY---", stats );
	}	
}
