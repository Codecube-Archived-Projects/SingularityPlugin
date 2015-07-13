package com.gmail.therealcodecube.singularityplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.worldbehavior.Worlds;

public class EventForwarder implements Listener
{
	public EventForwarder ( )
	{
		this.init ( );
	}
	
	public void init ( )
	{
		
	}
	
	@EventHandler
	public void onPlayerInteract ( PlayerInteractEvent e )
	{	
		SPlayer p = SPlayer.getPlayer ( e.getPlayer ( ) );
		if ( e.getAction ( ) == Action.RIGHT_CLICK_BLOCK || e.getAction ( ) == Action.RIGHT_CLICK_AIR )
		{
			if ( p.hasSpecialItem ( e.getItem ( ) ) )
			{
				e.setCancelled ( true );
				p.useSpecialItem ( e.getItem ( ), e );
				SingularityPlugin.info ( "Used a special item" );
			}
			else
			{
				Worlds.getWorld ( e.getPlayer ( ).getWorld ( ) ).onRightClick ( e );
			}
		}
		else
		{
			
		}
	}
	
	@EventHandler
	public void onDamage ( EntityDamageByEntityEvent e )
	{
		Worlds.getWorld ( e.getEntity ( ).getWorld ( ) ).onEntityDamage ( e );
	}
	
	@EventHandler
	public void onEntityDeath ( EntityDeathEvent e )
	{
		Worlds.getWorld ( e.getEntity ( ).getWorld ( ) ).onEntityDeath ( e );
	}
	
	@EventHandler
	public void onPlayerDeath ( PlayerDeathEvent e )
	{
		Worlds.getWorld ( e.getEntity ( ).getWorld ( ) ).onPlayerDeath ( e );
	}
	
	@EventHandler
	public void onInventoryClick ( InventoryClickEvent e )
	{
		SPlayer p = SPlayer.getPlayer ( e.getWhoClicked ( ).getName ( ) );
		p.pressGui ( e );
	}
}
