package com.gmail.therealcodecube.singularityplugin.worlds.pve;

import java.util.Random;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import be.maximvdw.titlemotd.ui.Title;

import com.darkblade12.particleeffect.ParticleEffect;
import com.gmail.therealcodecube.singularityplugin.SingularityPlugin;
import com.gmail.therealcodecube.singularityplugin.player.PropStat;
import com.gmail.therealcodecube.singularityplugin.player.SBoard;
import com.gmail.therealcodecube.singularityplugin.player.SBoardStat;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.player.StaticStat;
import com.gmail.therealcodecube.singularityplugin.player.TimerStat;
import com.gmail.therealcodecube.singularityplugin.props.ParticleTrailMobSpawner;
import com.gmail.therealcodecube.singularityplugin.props.TimeMachine;
import com.gmail.therealcodecube.singularityplugin.props.Timer;
import com.gmail.therealcodecube.singularityplugin.props.TimerTask;
import com.gmail.therealcodecube.singularityplugin.sgui.SGui;
import com.gmail.therealcodecube.singularityplugin.sql.DefaultTables;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Minigame;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEMobs.Levels;
import com.gmail.therealcodecube.singularityplugin.worlds.pve.PVEMobs.Ranks;

public class PVEGame extends Minigame
{
	private TimerTask waveStart;
	private TimerTask waveEnd;
	
	//Lists of locations where the particle trails start and end.
	private static Vector < Location > particleStartPoints = new Vector < Location > ( );
	private static Vector < Location > particleEndPoints = new Vector < Location > ( );
	
	// [wave] [mob] [rank]
	private static final int [ ] [ ] [ ] waves = 
	{
		//Wave 1
		{ 	//Ranks go across this way
			/*Zombies*/  { 3, 0, 0, 0, 0, 0 },
			/*Spiders*/  { 0, 0, 0, 0, 0, 0 },
			/*Skeletons*/{ 0, 0, 0, 0, 0, 0 },
		},
		//Wave 2
		{ 	//Ranks go across this way
			/*Zombies*/  { 4, 2, 0, 0, 0, 0 },
			/*Spiders*/  { 0, 0, 0, 0, 0, 0 },
			/*Skeletons*/{ 0, 0, 0, 0, 0, 0 },
		},
		//Wave 3
		{ 	//Ranks go across this way
			/*Zombies*/  { 5, 4, 0, 0, 0, 0 },
			/*Spiders*/  { 0, 0, 0, 0, 0, 0 },
			/*Skeletons*/{ 0, 0, 0, 0, 0, 0 },
		},
		//Wave 4
		{ 	//Ranks go across this way
			/*Zombies*/  { 3, 4, 2, 0, 0, 0 },
			/*Spiders*/  { 2, 0, 0, 0, 0, 0 },
			/*Skeletons*/{ 0, 0, 0, 0, 0, 0 },
		},
		//Wave 5
		{ 	//Ranks go across this way
			/*Zombies*/  { 2, 3, 4, 0, 0, 0 },
			/*Spiders*/  { 3, 3, 0, 0, 0, 0 },
			/*Skeletons*/{ 0, 0, 0, 0, 0, 0 },
		},
		//Wave 6
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 2, 5, 0, 0, 0 },
			/*Spiders*/  { 3, 3, 0, 0, 0, 0 },
			/*Skeletons*/{ 2, 0, 0, 0, 0, 0 },
		},
		//Wave 7
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 2, 5, 0, 0, 0 },
			/*Spiders*/  { 2, 3, 2, 0, 0, 0 },
			/*Skeletons*/{ 3, 1, 0, 0, 0, 0 },
		},
		//Wave 8
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 3, 6, 0, 0, 0 },
			/*Spiders*/  { 1, 3, 3, 0, 0, 0 },
			/*Skeletons*/{ 3, 2, 0, 0, 0, 0 },
		},
		//Wave 9
		{ 	//Ranks go across this way
			/*Zombies*/  { 2, 3, 5, 2, 0, 0 },
			/*Spiders*/  { 1, 3, 5, 0, 0, 0 },
			/*Skeletons*/{ 2, 2, 4, 0, 0, 0 },
		},
		//Wave 10
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 3, 5, 0, 0 },
			/*Spiders*/  { 1, 2, 4, 2, 0, 0 },
			/*Skeletons*/{ 2, 3, 5, 0, 0, 0 },
		},
		//Wave 11
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 3, 6, 0, 0 },
			/*Spiders*/  { 1, 2, 3, 5, 0, 0 },
			/*Skeletons*/{ 1, 2, 3, 4, 0, 0 },
		},
		//Wave 12
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 2, 4, 2, 0 },
			/*Spiders*/  { 1, 2, 4, 6, 0, 0 },
			/*Skeletons*/{ 1, 2, 2, 5, 0, 0 },
		},
		//Wave 13
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 2, 4, 4, 0 },
			/*Spiders*/  { 1, 1, 3, 5, 2, 0 },
			/*Skeletons*/{ 1, 1, 2, 6, 0, 0 },
		},
		//Wave 14
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 2, 3, 5, 0 },
			/*Spiders*/  { 1, 1, 3, 3, 4, 0 },
			/*Skeletons*/{ 1, 1, 2, 4, 2, 0 },
		},
		//Wave 15
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 2, 2, 7, 0 },
			/*Spiders*/  { 1, 1, 2, 3, 6, 0 },
			/*Skeletons*/{ 1, 1, 2, 3, 4, 0 },
		},
		//Wave 16
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 1, 1, 10, 0  },
			/*Spiders*/  { 1, 1, 1, 2, 9 , 0 },
			/*Skeletons*/{ 1, 1, 1, 2, 6 , 0 },
		},
		//Wave 17
		{ 	//Ranks go across this way
			/*Zombies*/  { 1, 1, 1, 1, 12, 0 },
			/*Spiders*/  { 1, 1, 1, 1, 11, 0 },
			/*Skeletons*/{ 1, 1, 1, 1, 8, 0 },
		},
	};
	// [mob] [rank] This array stores how many points the player should recieve upon killing a mob.
	private static final int [ ] [ ] points = 
	{
		{ 10, 15, 25, 40, 60, 85 },
		{ 15, 25, 40, 60, 85, 110 },
		{ 25, 40, 60, 85, 115, 140 }
	};
	
	private static Location house;
	private int wave = 0;
	private SGui craftMenu;
	private SGui smeltMenu;
	
	private Vector < LivingEntity > spawnedMobs = new Vector < LivingEntity > ( );
	
	public PVEGame ( World w )
	{
		super ( w );
	}
	
	@Override
	public void init ( )
	{	
		//Set the maximum number of players.
		//By default, the minigame class sets it to 8.
		setMaxPlayers ( 4 );
		
		//A basic timer task that allows a world and another timertask to be passed to it.
		//This makes it so the task can set the time and call various PVE functions when the time has come.
		class PVETimerTask extends TimerTask
		{
			protected PVEGame world;
			protected TimerTask task;
			public PVETimerTask ( PVEGame w, TimerTask t )
			{
				world = w; task = t;
			}
			
			public void setTask ( TimerTask t )
			{
				task = t;
			}
			
			public boolean run ( Timer t )
			{
				return true;
			}
		}
		
		//This task is called when a new wave should start. It sets the time to night, spawns a new wave, and sets the timer to two minutes.
		waveStart = new PVETimerTask ( this, waveEnd )
		{
			//Set the timer for 2 minutes, and set the time to be night.
			public boolean run ( Timer t )
			{
				Title waveOver = new Title ( "New Wave!", "Wave #" + ( world.wave + 1 ) + " is incoming!", 10, 40, 10 );
				waveOver.setTimingsToTicks ( );
				for ( SPlayer p : world.players )
				{
					waveOver.send ( p.getPlayer ( ) );
					p.setProp ( "wave", world.wave + 1 );
				}
				t.setDuration ( 120 * 1000L );
				t.setTask ( task );
				world.addProp ( new TimeMachine ( world.getWorld ( ), 20000 ) );
				world.spawnWave ( );
				return true;
			}
		}; 
		
		//This task is called when the wave is over. It sets the time to day, and sets the timer to one minute. It also sets the waveStart task to run when the timer is done.
		waveEnd = new PVETimerTask ( this, waveStart ) 
		{	
			//set the timer for 1 minute, and make sure it doesn't get destroyed.
			@Override
			public boolean run ( Timer t ) 
			{
				Title waveOver = new Title ( "Wave Over!", "Prepare for the next one.", 10, 40, 10 );
				waveOver.setTimingsToTicks ( );
				for ( SPlayer p : world.players )
				{
					waveOver.send ( p.getPlayer ( ) );
				}
				t.setDuration ( 60 * 1000L );
				t.setTask ( task );
				world.addProp ( new TimeMachine ( world.getWorld ( ), 10000 ) );
				return true;				
			}
		};
		
		//This refreshes the waveStart task's reference to the waveEnd task.
		//Since it was assigned to a new task in the previous section, the reference must be updated.
		( ( PVETimerTask ) waveStart ).setTask ( waveEnd );
		
		//Set up the crafting bench's GUI, as well as the smelter's GUI.
		craftMenu = new SGui ( ChatColor.DARK_BLUE + "Crafting", 27 );
		smeltMenu = new SGui ( ChatColor.GOLD + "Smelting", 9 );
		
		World t = getWorld ( ); 
		
		if ( house == null )
		{
			house = new Location ( t, 14.5, 69, 12.5 );
			spawn = new Location ( t, 3, 68, 15 );
		}
		
		if ( particleStartPoints.size ( ) == 0 )
		{
			//Load in some start and end locations
			particleEndPoints.add ( new Location ( t, 12, 69, 29 ) );
			particleEndPoints.add ( new Location ( t, 7, 68, 36 ) );
			particleEndPoints.add ( new Location ( t, 0, 67, 41 ) );
			particleEndPoints.add ( new Location ( t, -8, 65, 44 ) );
			particleEndPoints.add ( new Location ( t, -6, 67, 51 ) );
			particleEndPoints.add ( new Location ( t, 3, 67, 56 ) );
			particleEndPoints.add ( new Location ( t, -5, 67, 60 ) );
			particleEndPoints.add ( new Location ( t, -24, 70, 63 ) );
			particleEndPoints.add ( new Location ( t, -19, 65, 73 ) );
			particleEndPoints.add ( new Location ( t, -5, 67, 71 ) );
			particleEndPoints.add ( new Location ( t, -2, 69, 83 ) );
			
			particleStartPoints.add ( new Location ( t, 4, 71, 120 ) );
			particleStartPoints.add ( new Location ( t, -24, 65, 107 ) );
			particleStartPoints.add ( new Location ( t, -40, 65, 104 ) );
			particleStartPoints.add ( new Location ( t, -56, 67, 91 ) );
			particleStartPoints.add ( new Location ( t, -69, 65, 71 ) );
			particleStartPoints.add ( new Location ( t, -68, 65, 49 ) );
			particleStartPoints.add ( new Location ( t, -57, 65, 39 ) );
			particleStartPoints.add ( new Location ( t, -48, 65, 39 ) );
			particleStartPoints.add ( new Location ( t, -41, 65, 3 ) );
			particleStartPoints.add ( new Location ( t, -23, 67, -7 ) );
			particleStartPoints.add ( new Location ( t, -10, 67, -22 ) );
			particleStartPoints.add ( new Location ( t, 13, 69, -27 ) );
			particleStartPoints.add ( new Location ( t, 32, 71, -41 ) );
			particleStartPoints.add ( new Location ( t, 53, 67, -37 ) );
		}
		
		super.init ( );
	}
	
	private Random random = null;
	
	private int getRandom ( int upTo )
	{
		if ( random == null )
		{
			random = new Random ( );
		}
		
		return random.nextInt ( upTo );
	}
	
	@Override
	public void joinWorld ( SPlayer p )
	{
		//The minigame class handles lots of stuff for us.
		super.joinWorld ( p );
		
		p.clearInventory ( );
		
		//If it is the first player, set the time to day.
		if ( livingPlayers == 1 )
		{
			world.setTime ( 1000L );
		}
		
		//Set a variable so that we know that this player has not opened the start chest.
		p.setProp ( "openedStartChest", 0 );
		//These are for scoreboard purposes.
		p.setProp ( "wave", 1 );
		p.setProp ( "mobs", 0 );
	}

	@Override
	public void leaveWorld ( SPlayer p )
	{
		//The minigame class handles lots of stuff for us.
		super.leaveWorld ( p );
	}
	
	@Override
	protected void cleanup ( )
	{
		//Reset the wave.
		wave = 0;
		//Kill all spawned mobs.
		for ( LivingEntity e : spawnedMobs )
		{
			e.damage ( 31415926.535 );
		}
		spawnedMobs.clear ( );
		
		//Remove all items.
		for ( Item i : getWorld ( ).getEntitiesByClass ( Item.class ) )
		{
			i.remove ( );
		}
	}
	
	@Override
	public void updatePlayer ( SPlayer p )
	{
		super.updatePlayer ( p );
		p.setProp ( "mobs", spawnedMobs.size ( ) );
		Player pl = p.getPlayer ( );
		pl.setFoodLevel ( 17 );
	}
	
	@Override
	protected void startGame ( )
	{
		Title start = new Title ( "PVE Has Started!", 
							"You have 1 minute to prepare.",
									10, 30, 10 );
		start.setTimingsToTicks ( );
		for ( SPlayer p : players )
		{
			start.send ( p.getPlayer ( ) );
			Player m = p.getPlayer ( );
			m.teleport ( house );
			m.sendMessage ( "Right-click the start chest to get the start kit." );
			m.sendMessage ( "Kill mobs to get points and resources." );
			m.sendMessage ( "Use the piston and fire pit to craft items." );
		}
		addProp ( new TimeMachine ( world, 10000 ) );
		gameTimer.setDuration ( 60 * 1000L );
		gameTimer.setTask ( waveStart );
	}
	
	@Override 
	public void onRightClick ( PlayerInteractEvent e )
	{
		e.setCancelled ( true );
		SPlayer p = SPlayer.getPlayer ( e.getPlayer ( ) );
		//Only check for things like crafting table, furnace, or start chest after the game has started.
		if ( gameStarted ( ) )
		{
			Material m = Material.AIR;
			if ( e.getAction ( ) == Action.RIGHT_CLICK_BLOCK )
			{
				m = e.getClickedBlock ( ).getType ( );
			}
			
			if ( m == Material.PISTON_BASE )
			{
				craftMenu.clear ( );
				
				for ( PVEItems i : PVEItems.values ( ) )
				{
					if ( i.canCraft ( e.getPlayer ( ), PVEItems.CraftType.CRAFT ) )
					{
						craftMenu.addButton ( i.getCraftButton ( ) );
					}
				}
				
				craftMenu.showInventory ( p );
			}
			//If clicking fire, a slab, or a cobble fence, activate the smelting menu.
			else if ( m == Material.NETHERRACK || m == Material.STONE_SLAB2 || m == Material.COBBLE_WALL )
			{
				smeltMenu.clear ( );
				
				for ( PVEItems i : PVEItems.values ( ) )
				{
					if ( i.canCraft ( e.getPlayer ( ), PVEItems.CraftType.SMELT ) )
					{
						smeltMenu.addButton ( i.getCraftButton ( ) );
					}
				}
				
				smeltMenu.showInventory ( p );
			}
			else if ( m == Material.CHEST )
			{
				SPlayer sp = SPlayer.getPlayer ( e.getPlayer ( ) );
				//Only open the start chest if they haven't opened it before.
				if ( sp.getProp ( "openedStartChest" ) == 0 )
				{
					ItemStack sword = new ItemStack ( Material.WOOD_SWORD );
					sword.addEnchantment ( Enchantment.DURABILITY, 3 );
					sp.getPlayer ( ).getInventory ( ).addItem ( sword );
					
					int cloth, stone, metal;
					cloth = DefaultTables.players.getProperty ( sp.getName ( ), "pve_cloth" ).getIntValue ( );
					stone = DefaultTables.players.getProperty ( sp.getName ( ), "pve_stone" ).getIntValue ( );
					metal = DefaultTables.players.getProperty ( sp.getName ( ), "pve_metal" ).getIntValue ( );
					
					if ( cloth > 0 ) { sp.getPlayer ( ).getInventory ( ).addItem ( PVEItems.CLOTH.craft ( cloth ) ); }
					if ( stone > 0 ) { sp.getPlayer ( ).getInventory ( ).addItem ( PVEItems.STONE.craft ( stone ) ); }
					if ( metal > 0 ) { sp.getPlayer ( ).getInventory ( ).addItem ( PVEItems.METAL.craft ( metal ) ); }
					
					sp.sendMessage ( "You open the start chest and retrieve a wooden sword." );
					sp.setProp ( "openedStartChest", 1 );
				}
			}
			else if ( m == Material.BED_BLOCK )
			{
				if ( gameTimer.getTask ( ) == waveStart )
				{
					gameTimer.setDuration ( 2000L );
				}
				
				for ( SPlayer i : players )
				{
					i.sendMessage ( e.getPlayer ( ).getDisplayName ( ) + " has slept through the day." );
				}
			}
			else
			{
				PVEItems item = PVEItems.getItem ( e.getItem ( ) );
				item.onUse ( e );
			}
		}
	}
	
	@Override
	public void onEntityDeath ( EntityDeathEvent e )
	{
		e.getDrops ( ).clear ( );
		e.setDroppedExp ( 0 );
		if ( players.size ( ) > 0 )
		{
			PVEMobs mob = PVEMobs.getMob ( e.getEntity ( ).getCustomName ( ) );
			e.getDrops ( ).add ( mob.getLoot ( ) );
			//For determining which mob it was.
			int level = 0, rank = 0;
			for ( LivingEntity i : spawnedMobs )
			{
				if ( i.getUniqueId ( ) == e.getEntity ( ).getUniqueId ( ) )
				{
					//Figure out which mob this was.
					for ( PVEMobs m : PVEMobs.values ( ) )
					{
						if ( i.getCustomName ( ) == m.getName ( ) )
						{
							//The second .getRank() calls return an integer representation of its level or rank.
							level = m.getLevel ( ).getRank ( );
							rank = m.getRank ( ).getRank ( );
							break;
						}
					}
					
					spawnedMobs.remove ( i );
					if ( spawnedMobs.size ( ) == 0 )
					{
						gameTimer.setDuration ( 500L );
					}
					break;
				}
			}
			
			Player k = e.getEntity ( ).getKiller ( );
			if ( k != null )
			{
				SPlayer sk = null;
				//Find the SPlayer matching the killer.
				for ( SPlayer p : players )
				{
					if ( p.getPlayer ( ).getName ( ) == k.getName ( ) )
					{
						sk = p;
						break;
					}
				}
				//Give them points.
				if ( sk != null )
				{
					sk.changeProp ( "points", points [ level ] [ rank ] );
				}
			}
		}
		
		for ( SPlayer p : players )
		{
			p.setProp ( "mobs", spawnedMobs.size ( ) );
		}
	}
	
	@Override
	public void onEntityDamage ( EntityDamageByEntityEvent e )
	{
		super.onEntityDamage ( e );
		//Makes snowballs (pebbles) do damage.
		if ( e.getDamager ( ) instanceof Snowball )
		{
			e.setDamage ( 5.0 );
		}
	}
	
	@Override
	protected void onPlayerDeath ( EntityDamageByEntityEvent e )
	{
		Player p = ( Player ) e.getEntity ( );
		String message = "A " + e.getDamager ( ).getCustomName ( ) + " killed you.";
		Title t;
		
		for ( SPlayer i : players )
		{
			i.sendMessage ( p.getDisplayName ( ) + " has been killed by a " + e.getDamager ( ).getCustomName ( ) + "!" );
		}
		
		if ( livingPlayers > 0 )
		{
			t = new Title ( "YOU DIED!", message, 10, 40, 10 );
			p.sendMessage ( "You are now a spectator." );
			p.sendMessage ( "You can stay and watch the rest of the game if you would like." );
			p.sendMessage ( "Alternatively, you can type /hub or use the teleporter to return to the lobby." );
		}
		else
		{
			t = new Title ( "GAME OVER!", message, 10, 40, 10 );
			Title gameOver = new Title ( "GAME OVER!", " ", 10, 40, 10 );
			gameOver.setTimingsToTicks ( );
			//Make the game reset in 10 seconds.
			endGame ( );
			for ( SPlayer i : players )
			{
				i.sendMessage ( "There are no more living players, so the game has ended." );
				i.sendMessage ( "The game will reset in 10 seconds." );
				gameOver.send ( i.getPlayer ( ) );
			}
		}
		
		t.setTimingsToTicks ( );
		t.send ( p.getPlayer ( ) );
	}
	
	//Spawns a quantity of the specified mob.
	private void spawnMonsters ( PVEMobs m, int q )
	{
		if ( q > 0 )
		{
			Location start, end;
			for ( int i = 0; i < q; i++ )
			{
				start = particleStartPoints.elementAt ( getRandom ( particleStartPoints.size ( ) ) );
				end = particleEndPoints.elementAt ( getRandom ( particleEndPoints.size ( ) ) );
				addProp ( new ParticleTrailMobSpawner (
							start,
							end,
							6000L,
							ParticleEffect.FLAME,
							m, spawnedMobs ) );
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void spawnMonsters ( Levels l, Ranks r, int q )
	{
		PVEMobs m = PVEMobs.getMob ( l, r );
		spawnMonsters ( m, q );
	}
	
	private void spawnMonsters ( int l, int r, int q )
	{
		Levels level = PVEMobs.getLevel ( l );
		Ranks rank = PVEMobs.getRank ( r );
		PVEMobs m = PVEMobs.getMob ( level, rank );
		spawnMonsters ( m, q );
	}
	
	public void spawnWave ( )
	{
		//Loop through the wave array and spawn as many of each mob as the array specifies.
		for ( int l = 0; l < 3; l++ )
		{
			for ( int r = 0; r < 6; r++ )
			{
				//Scale the amount of monsters based on how many players there are.
				spawnMonsters ( l, r, (int) Math.floor ( waves [ wave ] [ l ] [ r ] * ( players.size() / 2.0 + 0.5 ) ) );
			}
		}
		
		wave++;
	}
	
	//Constructs a board for PVE, using the name of the player.
	protected SBoard getBoard ( SPlayer p )
	{
		Vector < SBoardStat > stats = new Vector < SBoardStat > ( );
		stats.add ( new StaticStat ( p.getPlayer ( ).getDisplayName ( ) ) );
		stats.add ( new PropStat ( p, "points", "# Points" ) );
		stats.add ( new StaticStat ( " " ) );
		stats.add ( new StaticStat ( "PVE" ) );
		stats.add ( new PropStat ( p, "wave", "Wave # " ) );
		stats.add ( new PropStat ( p, "mobs", "# mobs left" ) );
		stats.add ( new StaticStat ( "  " ) );
		stats.add ( new TimerStat ( gameTimer ) );
		return new SBoard ( "-------PVE-------", stats );
	}
}
