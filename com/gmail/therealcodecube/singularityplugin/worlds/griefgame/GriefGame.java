package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.player.TimerStat;
import com.gmail.therealcodecube.singularityplugin.sgui.SGuiLink;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Minigame;

public class GriefGame extends Minigame 
{
	private static final int MAX_ORE = 5;
	private static SGuiLink classSelector;
	
	public GriefGame ( World w ) 
	{
		super(w);
	}

	@Override
	public void init ( )
	{
		setMaxPlayers ( 8 );
		
		//Init the classSelector button
		if ( classSelector == null )
		{
			ItemStack i = new ItemStack ( Material.ENCHANTED_BOOK );
			ItemMeta m = i.getItemMeta ( );
			m.setDisplayName ( "Choose class" );
			i.setItemMeta ( m );
			classSelector = new SGuiLink ( i, GriefClass.getClassGui ( ) );
		}
		
		//Set spawn point
		if ( spawn == null )
		{
			spawn = new Location ( getWorld ( ), 13, 33, 7.5 );
		}
		
		super.init ( );
	}
	
	@Override
	public void joinWorld ( SPlayer p )
	{
		super.joinWorld ( p );
		
		p.getPlayer ( ).setHealth ( 20.0 );
		p.getPlayer ( ).getInventory ( ).clear ( );
		p.setVar ( "oreMined", 0 );
		
		p.setSpecialItem ( 4, classSelector );
	}
	
	@Override
	public void onLeftClick ( PlayerInteractEvent e )
	{
		if ( e.getClickedBlock ( ).getType ( ) != Material.IRON_ORE )
		{
			e.setCancelled ( true );
		}
	}
	
	@Override
	public void onBlockBreak ( BlockBreakEvent e )
	{
		SPlayer p = SPlayer.getPlayer ( e.getPlayer ( ) );
		e.setCancelled ( true );
		
		if ( e.getBlock ( ).getType ( ) == Material.IRON_ORE )
		{
			if ( p.getVar ( "oreMined" ) < MAX_ORE )
			{
				e.getBlock ( ).setType ( Material.AIR );
				p.changeVar ( "oreMined", 1 );
				p.getInventory ( ).addItem ( new ItemStack ( Material.IRON_INGOT ) );
			}
		}
	}
	
	@Override
	protected SBoard getBoard ( SPlayer p )
	{
		Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
		stats.add ( new StaticStat ( p.getDisplayName ( ) ) );
		//The player's class name should go here.
		stats.add ( new StaticStat ( "Someone" ) );
		stats.add ( new StaticStat ( " " ) );
		stats.add ( new TimerStat  ( gameTimer ) );
		return new SBoard ( "----GriefWars----", stats );
	}
	
}
