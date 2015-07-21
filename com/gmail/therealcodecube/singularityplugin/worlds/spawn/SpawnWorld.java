package com.gmail.therealcodecube.singularityplugin.worlds.spawn;

import java.util.Vector;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.hologram.ParticleHologram;
import com.gmail.therealcodecube.singularityplugin.hologram.ParticleLine;
import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.SQLStat;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.props.HologramDisplay;
import com.gmail.therealcodecube.singularityplugin.props.Teleporter;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonPressed;
import com.gmail.therealcodecube.singularityplugin.sgui.DynamicGuiLink;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.SurvivalWorld;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.WorldBehavior;
import com.gmail.therealcodecube.singularityplugin.worlds.griefgame.GriefGame;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEGame;

public class SpawnWorld extends WorldBehavior 
{
	private SButton navigation, store;
	private static int MAX_PLAYERS = 32;
	
	public SpawnWorld ( World w )
	{
		super ( w );
	}
	
	@Override
	public void init ( )
	{
		super.init ( );
		
		//Set up the teleporters
		addProp ( new Teleporter ( 
				new Location ( world, -278, 163, 318),
				PVEGame.class,
				new OrdinaryColor ( 200, 20, 20 ) ) );
		
		addProp ( new Teleporter ( 
				new Location ( world, -290, 163, 318),
				SurvivalWorld.class,
				new OrdinaryColor ( 20, 200, 20 ) ) );
		
		
		addProp ( new Teleporter ( 
				new Location ( world, -290, 163, 306),
				GriefGame.class,
				new OrdinaryColor ( 20, 20, 200 ) ) );
		
		//Test the hologram system.
		ParticleLine l = ParticleLine.fromOffset ( 
				new Location ( world, -279.0, 162.0, 307.0 ), 
				new Location ( world, 2.0, 2.0, -2.0 ), 
				new OrdinaryColor ( 0, 255, 255 ) );
		ParticleHologram h = new ParticleHologram ( );
		h.addShape ( l );
		HologramDisplay d = new HologramDisplay ( h );
		addProp ( d );
		
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

	@Override
	public void onRightClick ( PlayerInteractEvent e )
	{
		super.onRightClick ( e );
		e.setCancelled ( true );
	}
	
	@Override
	public void onLeftClick ( PlayerInteractEvent e )
	{
		super.onLeftClick ( e );
		e.setCancelled ( true );
	}
	
	//The fewer players there are, the higher it should be in the rankings. This evenly distributes the load.
	@Override
	public int getJoinRank ( )
	{
		return MAX_PLAYERS - players.size ( );
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
