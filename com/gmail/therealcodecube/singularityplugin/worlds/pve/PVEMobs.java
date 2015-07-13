package com.gmail.therealcodecube.singularityplugin.worlds.pve;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum PVEMobs 
{
	ZOMBIE_UNDERLING ( Levels.EASY, Ranks.UNDERLING, "Zombie Underling", EntityType.ZOMBIE, new PVEResource ( PVEItems.CLOTH, 3 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			return entity;
		}
	},
	WEAK_ZOMBIE ( Levels.EASY, Ranks.WEAK, "Weak Zombie", EntityType.ZOMBIE, new PVEResource ( PVEItems.STONE, 2 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.LEATHER_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.LEATHER_LEGGINGS ) );
			return entity;
		}
	},
	ZOMBIE_MINION ( Levels.EASY, Ranks.MINION, "Zombie Minion", EntityType.ZOMBIE, new PVEResource ( PVEItems.STONE,5 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.GOLD_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.GOLD_CHESTPLATE ) );
			entity.getEquipment ( ).setItemInHand ( new ItemStack ( Material.WOOD_SPADE ) );
			return entity;
		}
	},
	FIGHTER_ZOMBIE ( Levels.EASY, Ranks.FIGHTER, "Fighter Zombie", EntityType.ZOMBIE, new PVEResource ( PVEItems.METAL, 2 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.GOLD_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.GOLD_CHESTPLATE ) );
			entity.getEquipment ( ).setItemInHand ( new ItemStack ( Material.WOOD_SPADE ) );
			return entity;
		}
	},
	WARRIOR_ZOMBIE ( Levels.EASY, Ranks.WARRIOR, "Warrior Zombie", EntityType.ZOMBIE, new PVEResource ( PVEItems.METAL,5 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.CHAINMAIL_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.GOLD_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.CHAINMAIL_LEGGINGS ) );
			entity.getEquipment ( ).setItemInHand ( new ItemStack ( Material.WOOD_SWORD ) );
			return entity;
		}
	},
	ELITE_ZOMBIE ( Levels.EASY, Ranks.ELITE, "Elite Zombie", EntityType.ZOMBIE, new PVEResource ( PVEItems.CRYSTAL, 1 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.CHAINMAIL_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.GOLD_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.CHAINMAIL_LEGGINGS ) );
			entity.getEquipment ( ).setItemInHand ( new ItemStack ( Material.WOOD_SWORD ) );
			return entity;
		}
	},
	SPIDER_UNDERLING ( Levels.MEDIUM, Ranks.UNDERLING, "Spider Underling", EntityType.SPIDER, new PVEResource ( PVEItems.CLOTH, 4 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.LEATHER_LEGGINGS ) );
			return entity;
		}
	},
	WEAK_SPIDER ( Levels.MEDIUM, Ranks.WEAK, "Weak Spider", EntityType.SPIDER, new PVEResource ( PVEItems.STONE, 3 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.LEATHER_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.LEATHER_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			return entity;
		}
	},
	SPIDER_MINION ( Levels.MEDIUM, Ranks.MINION, "Spider Minion", EntityType.SPIDER, new PVEResource ( PVEItems.STONE, 6 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.IRON_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.LEATHER_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.CHAINMAIL_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.LEATHER_BOOTS ) );
			return entity;
		}
	},
	FIGHTER_SPIDER ( Levels.MEDIUM, Ranks.FIGHTER, "Poisonous Spider", EntityType.CAVE_SPIDER, new PVEResource ( PVEItems.METAL, 3 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.LEATHER_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.LEATHER_LEGGINGS ) );
			//entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			return entity;
		}
	},
	WARRIOR_SPIDER ( Levels.MEDIUM, Ranks.WARRIOR, "Strong Poisonous Spider", EntityType.CAVE_SPIDER, new PVEResource ( PVEItems.METAL, 6 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.IRON_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.CHAINMAIL_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.LEATHER_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			return entity;
		}
	},
	ELITE_SPIDER ( Levels.MEDIUM, Ranks.ELITE, "Elite Poisonous Spider", EntityType.CAVE_SPIDER, new PVEResource ( PVEItems.CRYSTAL, 2 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.IRON_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.IRON_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.CHAINMAIL_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			//This speed should make it harder to get.
			entity.addPotionEffect ( new PotionEffect ( PotionEffectType.SPEED, 4 * 60 * 20, 1 ) );
			return entity;
		}
	},
	SKELETON_UNDERLING ( Levels.HARD, Ranks.UNDERLING, "Skeleton Underling", EntityType.SKELETON, new PVEResource ( PVEItems.STONE, 4 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			//entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.IRON_CHESTPLATE ) );
			//entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.CHAINMAIL_LEGGINGS ) );
			//entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			ItemStack bow = new ItemStack ( Material.BOW );
			entity.getEquipment ( ).setItemInHand ( bow );
			return entity;
		}
	},
	WEAK_SKELETON ( Levels.HARD, Ranks.WEAK, "Weak Skeleton", EntityType.SKELETON, new PVEResource ( PVEItems.STONE, 8 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.LEATHER_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.LEATHER_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.LEATHER_BOOTS ) );
			ItemStack bow = new ItemStack ( Material.BOW );
			entity.getEquipment ( ).setItemInHand ( bow );
			return entity;
		}
	},
	SKELETON_MINION ( Levels.HARD, Ranks.MINION, "Skeleton Minion", EntityType.SKELETON, new PVEResource ( PVEItems.METAL, 4 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.CHAINMAIL_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.CHAINMAIL_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.LEATHER_BOOTS ) );
			ItemStack bow = new ItemStack ( Material.BOW );
			entity.getEquipment ( ).setItemInHand ( bow );
			return entity;
		}
	},
	FIGHTER_SKELETON ( Levels.HARD, Ranks.FIGHTER, "Fighter Skeleton", EntityType.SKELETON, new PVEResource ( PVEItems.METAL, 8 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.LEATHER_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.CHAINMAIL_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.IRON_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			//Adding this flaming bow should heat things up a bit...                                                                                                                                                                                                                            *groan*
			ItemStack bow = new ItemStack ( Material.BOW );
			bow.addEnchantment ( Enchantment.ARROW_FIRE, 1 );
			entity.getEquipment ( ).setItemInHand ( bow );
			return entity;
		}
	},
	WARRIOR_SKELETON ( Levels.HARD, Ranks.WARRIOR, "Warrior Skeleton", EntityType.SKELETON, new PVEResource ( PVEItems.CRYSTAL, 2 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.IRON_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.IRON_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.IRON_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			//Adding this flaming bow should heat things up a bit...                                                                                                                                                                                                                            *groan*
			ItemStack bow = new ItemStack ( Material.BOW );
			bow.addEnchantment ( Enchantment.ARROW_FIRE, 1 );
			entity.getEquipment ( ).setItemInHand ( bow );
			return entity;
		}
	},
	ELITE_SKELETON ( Levels.HARD, Ranks.ELITE, "Elite Skeleton", EntityType.SKELETON, new PVEResource ( PVEItems.CRYSTAL, 4 ) )
	{
		public LivingEntity spawnEntity ( Location l )
		{
			LivingEntity entity = super.spawnEntity ( l );
			entity.getEquipment ( ).setHelmet ( new ItemStack ( Material.DIAMOND_HELMET ) );
			entity.getEquipment ( ).setChestplate ( new ItemStack ( Material.IRON_CHESTPLATE ) );
			entity.getEquipment ( ).setLeggings ( new ItemStack ( Material.IRON_LEGGINGS ) );
			entity.getEquipment ( ).setBoots ( new ItemStack ( Material.IRON_BOOTS ) );
			//Adding this flaming bow should heat things up a bit...                                                                                                                                                                                                                            *groan*
			ItemStack bow = new ItemStack ( Material.BOW );
			bow.addEnchantment ( Enchantment.ARROW_FIRE, 1 );
			bow.addEnchantment ( Enchantment.ARROW_KNOCKBACK, 2 );
			entity.getEquipment ( ).setItemInHand ( bow );
			return entity;
		}
	},
	;
	
	public enum Levels 
	{ 
		EASY ( 0 ), MEDIUM ( 1 ), HARD ( 2 ); 
		private int rank;
		private Levels ( int r ) { rank = r; }
		public int getRank ( ) { return rank; }
	}
	
	public enum Ranks 
	{ 
		UNDERLING ( 0 ), WEAK ( 1 ), MINION ( 2 ), FIGHTER ( 3 ), WARRIOR ( 4 ), ELITE ( 5 );
		private int rank;
		private Ranks ( int r ) { rank = r; }
		public int getRank ( ) { return rank; }
	}
	
	Levels level;
	Ranks rank;
	String name;
	EntityType type;
	PVEResource drop;
	
	private PVEMobs ( Levels l, Ranks c, String n, EntityType t, PVEResource r )
	{
		level = l;
		rank = c;
		name = n;
		type = t;
		drop = r;
	}
	
	//Spawns an entity at the specified location.
	//This is usually overridden by the particular enum entry to add various things to the enemy.
	public LivingEntity spawnEntity ( Location l )
	{
		LivingEntity entity = ( LivingEntity ) l.getWorld ( ).spawnEntity ( l, type );
		entity.setCustomName ( name );
		entity.setCustomNameVisible ( true );
		entity.getEquipment ( ).clear ( );
		//This prevents baby zombies.
		if ( entity.getType ( ) == EntityType.ZOMBIE )
		{
			Zombie z = ( Zombie ) entity;
			z.setBaby ( false );
		}
		
		//This prevents spider jockeys and chicken mounts.
		try
		{
			entity.getVehicle ( ).setPassenger ( null );
		}
		catch ( NullPointerException e )
		{
			
		}
		
		return entity;
	}
	
	//Gets the mob enum entry's display name.
	public String getName ( )
	{
		return name;
	}
	
	//Gets the mob enum entry's difficulty.
	public Levels getLevel ( )
	{
		return level;
	}
	
	//Gets the mob enum entry's class.
	public Ranks getRank ( )
	{
		return rank;
	}
	
	//Gets the mob enum entry's custom drop.
	//They usually drop things like scrap cloth and scrap stone.
	public ItemStack getLoot ( )
	{
		return drop.getItemStack ( );
	}
	
	//Gets a mob enum entry from its display name.
	public static PVEMobs getMob ( String n )
	{
		for ( PVEMobs m : PVEMobs.values ( ) )
		{
			if ( m.getName ( ).equalsIgnoreCase ( n ) )
			{
				return m;
			}
		}
		//Default case, return the default mob.
		return ZOMBIE_UNDERLING;
	}
	
	//Gets a mob enum entry from its difficulty and class.
	public static PVEMobs getMob ( Levels l, Ranks r )
	{
		for ( PVEMobs m : PVEMobs.values ( ) )
		{
			if ( ( m.getLevel ( ) == l ) && ( m.getRank ( ) == r ) )
			{
				return m;
			}
		}
		//Default case, return the default mob.
		return ZOMBIE_UNDERLING;
	}
	
	//Gets a level from its number.
	public static Levels getLevel ( int level )
	{
		if ( level < 0 ) { level = 0; }
		if ( level > 4 ) { level = 5; }
		
		for ( Levels l : Levels.values ( ) )
		{
			if ( l.getRank ( ) == level )
			{
				return l;
			}
		}
		//Unreachable case, but I have to put it here anyway.
		return Levels.EASY;
	}
	
	//Gets a rank from its number.
	public static Ranks getRank ( int rank )
	{
		for ( Ranks r : Ranks.values ( ) )
		{
			if ( r.getRank ( ) == rank )
			{
				return r;
			}
		}
		//Default case, return the default rank.
		return Ranks.UNDERLING;
	}
}
