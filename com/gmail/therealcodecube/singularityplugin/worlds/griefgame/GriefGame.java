package com.gmail.therealcodecube.singularityplugin.worlds.griefgame;

import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import be.maximvdw.titlemotd.ui.Title;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.Util;
import com.gmail.therealcodecube.singularityplugin.hologram.ParticleGrid;
import com.gmail.therealcodecube.singularityplugin.hologram.ParticleHologram;
import com.gmail.therealcodecube.singularityplugin.player.FieldStat;
import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.player.TimerStat;
import com.gmail.therealcodecube.singularityplugin.props.HologramDisplay;
import com.gmail.therealcodecube.singularityplugin.props.PlayerSpawnPoint;
import com.gmail.therealcodecube.singularityplugin.props.Prop;
import com.gmail.therealcodecube.singularityplugin.sgui.SGuiLink;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Minigame;

public class GriefGame extends Minigame 
{
	private static final int MAX_ORE = 5;
	private static final String RANDOM_TEAM = ChatColor.RED + "R" + ChatColor.BLUE + "a" + ChatColor.RED + "n" + ChatColor.BLUE + "d" +
			ChatColor.RED + "o" + ChatColor.BLUE + "m " + ChatColor.RED + "T" + ChatColor.BLUE + "e" +
			ChatColor.RED + "a" + ChatColor.BLUE + "m";
	private static final int NO_TEAM = 0;
	public static final int RED_TEAM = 1;
	public static final int BLUE_TEAM = 2;
	
	private static SGuiLink classSelector;
	private static Vector < PlayerSpawnPoint > redSpawnPoints = null;
	private static Vector < PlayerSpawnPoint > blueSpawnPoints = null;
	
	private int redTeamMembers = 0;
	private int blueTeamMembers = 0;
	private int maxPlayersOnTeam = 0;
	
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
			classSelector = new SGuiLink ( i, GriefClasses.getClassGui ( ) );
		}
		
		//Set spawn point
		if ( spawn == null )
		{
			spawn = new Location ( getWorld ( ), 13, 33, 7.5 );
		}
		
		//Set spawn points for when the game actually starts.
		if ( redSpawnPoints == null )
		{
			redSpawnPoints = new Vector < PlayerSpawnPoint > ( );
			blueSpawnPoints = new Vector < PlayerSpawnPoint > ( );
			
			World w = world;
			
			redSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 17, 34, 28 ) ) );
			redSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 48, 33, 34 ) ) );
			redSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 53, 34, 16 ) ) );
			redSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 47, 38, 3 ) ) );
			redSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 43, 35, -12 ) ) );
			redSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 24, 32, -5 ) ) );
			
			blueSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, -2, 31, -7 ) ) );
			blueSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, -18, 33, -2 ) ) );
			blueSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, -20, 39, 17 ) ) );
			blueSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, -13, 34, 29 ) ) );
			blueSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 7, 33, 34 ) ) );
			blueSpawnPoints.add ( new PlayerSpawnPoint ( new Location ( w, 7, 33, 5 ) ) );
		}
		
		super.init ( );
	}
	
	private void calculateMaxPlayersOnTeam ( )
	{
		maxPlayersOnTeam = ( int ) Math.ceil ( players.size ( ) / 2.0 );
	}
	
	@Override
	public void joinWorld ( SPlayer p )
	{
		super.joinWorld ( p );
		
		p.getPlayer ( ).setHealth ( 20.0 );
		p.getPlayer ( ).getInventory ( ).clear ( );
		p.setVar ( "oreMined", 0 );
		p.setVar ( "team", NO_TEAM );
		p.setField ( "class", "Choose a class!" );
		p.setField ( "team", RANDOM_TEAM );
		
		p.setSpecialItem ( 4, classSelector );
		
		//If this is the first player...
		if ( players.size ( ) == 1 )
		{
			//This time puts the sun and moon in such a position that there is a red glow over the red side and a blue glow over the blue side.
			world.setTime ( 23000 );
		}
		
		calculateMaxPlayersOnTeam ( );
	}
	
	@Override
	public void leaveWorld ( SPlayer p )
	{
		super.leaveWorld ( p );
		
		if ( p.getVar ( "team" ) == RED_TEAM )
		{
			redTeamMembers--;
		}
		else if ( p.getVar ( "team" ) == BLUE_TEAM )
		{
			blueTeamMembers--;
		}
		
		calculateMaxPlayersOnTeam ( );
	}
	
	@Override
	public void onRightClick ( PlayerInteractEvent e )
	{
		//If it is a fire extinguisher...
		if ( ( e.getItem ( ).getType ( ) == Material.PRISMARINE_SHARD ) && 
				( ChatColor.stripColor ( e.getItem ( ).getItemMeta ( ).getDisplayName ( ) ).equals ( "Fire Extinguisher" ) ) )
		{
			e.setCancelled ( true );
			//If pointing at a fire block...
			Block b = e.getClickedBlock ( ).getRelative ( e.getBlockFace ( ) );
			if ( b.getType ( ) == Material.FIRE )
			{
				Location l = b.getLocation ( );
				l.setX ( l.getX ( ) + 0.5 );
				l.setY ( l.getY ( ) + 0.5 );
				l.setZ ( l.getZ ( ) + 0.5 );
				SingularityPlugin.info ( l.toString ( ) );
				ParticleEffect.CLOUD.display ( 0.5f, 0.5f, 0.5f, 0, 80, l, 500.0 );
				b.setType ( Material.AIR );
			}
		}
		
		//If it is an arc welder...
		if ( ( e.getItem ( ).getType ( ) == Material.REDSTONE_TORCH_ON ) && 
				( ChatColor.stripColor ( e.getItem ( ).getItemMeta ( ).getDisplayName ( ) ) == "Arc Welder" ) )
		{
			e.setCancelled ( true );
			//If the player has the iron.
			PlayerInventory i = e.getPlayer ( ).getInventory ( );
			if ( i.contains ( Material.IRON_INGOT ) )
			{
				Block b = e.getClickedBlock ( ).getRelative ( e.getBlockFace ( ) );
				//Make sure that we are placing the welded block in an empty space.
				if ( b.getType ( ) == Material.AIR )
				{
					b.setType ( Material.IRON_BLOCK );
				}
				
				//Remove two iron ingots.
				ItemStack [ ] isa = i.getContents ( );
				int l = 0;
				for ( l = 0; l < isa.length; l++ )
				{
					if ( isa [ l ].getType ( ) == Material.IRON_INGOT ) { break; }
				}
				ItemStack is = i.getItem ( l );
				is.setAmount ( is.getAmount ( ) - 2 );
				i.setItem ( l, is );
				
				int loc = i.getHeldItemSlot ( );
				i.remove ( Material.REDSTONE_TORCH_ON );
				//Code snippet from WELDER enum instance.
				ItemStack weld = new ItemStack ( Material.REDSTONE_TORCH_ON );
				ItemMeta m = weld.getItemMeta ( );
				m.addEnchant ( Enchantment.FIRE_ASPECT, 1, true );
				m.setDisplayName ( "Arc Welder" );
				weld.setItemMeta ( m );
				i.setItem ( loc, weld );
			}
		}
	}
	
	@Override
	public void updatePlayer ( SPlayer p )
	{
		p.getPlayer ( ).setFoodLevel ( 17 );
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
	public void startGame ( )
	{
		gameTimer.setDuration ( 5 * 60 * 1000L );
		
		Title ti = new Title ( "The War Has Begun!", "Build forts and prepare for the attack!", 15, 40, 15 );
		ti.setTimingsToTicks ( );
		for ( SPlayer p : players )
		{
			ti.send ( p.getPlayer ( ) );
			GriefClasses.getClass ( p.getField ( "class" ) ).equip ( p );
			if ( p.getVar ( "team" ) == NO_TEAM )
			{
				//Pick a random team
				if ( redTeamMembers < maxPlayersOnTeam )
				{
					p.setVar ( "team", RED_TEAM );
					redTeamMembers++;
				}
				else
				{
					p.setVar ( "team", BLUE_TEAM );
					blueTeamMembers++;
				}
			}
			
			if ( p.getVar ( "team" ) == RED_TEAM )
			{
				redSpawnPoints.get ( Util.random ( 0, redSpawnPoints.size ( ) ) ).spawnPlayer ( p );
			}
			else
			{
				blueSpawnPoints.get ( Util.random ( 0, blueSpawnPoints.size ( ) ) ).spawnPlayer ( p );
			}
		}
	}
	
	@Override
	protected SBoard getBoard ( SPlayer p )
	{
		Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
		stats.add ( new StaticStat ( p.getDisplayName ( ) ) );
		stats.add ( new FieldStat ( p, "team", "#" ) );
		stats.add ( new FieldStat ( p, "class", "#" ) );
		stats.add ( new StaticStat ( " " ) );
		stats.add ( new TimerStat  ( gameTimer ) );
		return new SBoard ( "----GriefWars----", stats );
	}
	
}
