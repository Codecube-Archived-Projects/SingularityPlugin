package com.gmail.therealcodecube.singularityplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.therealcodecube.singularityplugin.node.Node;
import com.gmail.therealcodecube.singularityplugin.player.SPlayer;

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
			}
			else
			{
				Node.getNode ( e.getPlayer ( ).getWorld ( ) ).getBehavior ( ).onRightClick ( e );
			}
		}
		else if ( e.getAction ( ) == Action.LEFT_CLICK_BLOCK || e.getAction ( ) == Action.LEFT_CLICK_AIR )
		{
			Node.getNode ( e.getPlayer ( ).getWorld ( ) ).getBehavior ( ).onLeftClick ( e );
		}
	}
	
	@EventHandler
	public void onBlockBreak ( BlockBreakEvent e )
	{
		Node.getNode ( e.getBlock ( ).getWorld ( ) ).getBehavior ( ).onBlockBreak ( e );
	}
	
	@EventHandler
	public void onDamage ( EntityDamageByEntityEvent e )
	{
		Node.getNode ( e.getEntity ( ).getWorld ( ) ).getBehavior ( ).onEntityDamage ( e );
	}
	
	@EventHandler
	public void onEntityDeath ( EntityDeathEvent e )
	{
		Node.getNode ( e.getEntity ( ).getWorld ( ) ).getBehavior ( ).onEntityDeath ( e );
	}
	
	@EventHandler
	public void onPlayerDeath ( PlayerDeathEvent e )
	{
		Node.getNode ( e.getEntity ( ).getWorld ( ) ).getBehavior ( ).onPlayerDeath ( e );
	}
	
	@EventHandler
	public void onInventoryClick ( InventoryClickEvent e )
	{
		SPlayer p = SPlayer.getPlayer ( e.getWhoClicked ( ).getName ( ) );
		p.pressGui ( e );
	}
}
