package com.gmail.therealcodecube.singularityplugin.worlds.pve;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.gmail.therealcodecube.singularityplugin.player.SPlayer;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonInfo.Panel;
import com.gmail.therealcodecube.singularityplugin.sgui.ButtonPressed;
import com.gmail.therealcodecube.singularityplugin.sgui.SButton;

public enum PVEItems
{
	//Basic resources
	CLOTH ( CraftType.CRAFT, Material.CARPET, ChatColor.GRAY + "Scrap Cloth", null ),
	STONE ( CraftType.CRAFT, Material.MOSSY_COBBLESTONE, ChatColor.DARK_AQUA + "Scrap Stone", null ),
	METAL ( CraftType.CRAFT, Material.IRON_FENCE, ChatColor.BLUE + "Scrap Metal", null ),
	CRYSTAL ( CraftType.CRAFT, Material.DIAMOND, ChatColor.LIGHT_PURPLE + "Crystal", null),
	//Smeltable resources
	STONE_SCALES ( CraftType.SMELT, Material.PRISMARINE_CRYSTALS, ChatColor.DARK_GREEN + "Stone Scales", new PVEResource [ ] { new PVEResource ( STONE, 2 ) } ),
	METAL_SCALES ( CraftType.SMELT, Material.QUARTZ, ChatColor.GREEN + "Metal Scales", new PVEResource [ ] { new PVEResource ( METAL, 2 ) } ),
	CARBON_ASH ( CraftType.SMELT, Material.COAL_BLOCK, ChatColor.DARK_GRAY + "Carbon Ash", new PVEResource [ ] { new PVEResource ( CLOTH, 4 ) } ),
	METALLIC_COMPOSITE ( CraftType.SMELT, Material.IRON_TRAPDOOR, ChatColor.DARK_PURPLE + "Metallic Composite", new PVEResource [ ] { new PVEResource ( METAL_SCALES, 3 ), new PVEResource ( CARBON_ASH, 1 ) }  ),
	//DENSE_CLOTH ( Material.WOOL, ChatColor.WHITE + "Dense Cloth", null ),
	//Cloth armor
	CLOTH_HAT ( CraftType.CRAFT, Material.LEATHER_HELMET, ChatColor.DARK_RED + "Makeshift Cloth Hat", new PVEResource [ ] { new PVEResource ( CLOTH, 6 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			LeatherArmorMeta m = ( LeatherArmorMeta ) i.getItemMeta ( );
			m.setColor ( Color.WHITE );
			i.setItemMeta ( m );
			return i;
		}
	},
	CLOTH_CHESTPLATE ( CraftType.CRAFT, Material.LEATHER_CHESTPLATE, ChatColor.DARK_RED + "Makeshift Cloth Shirt", new PVEResource [ ] { new PVEResource ( CLOTH, 18 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			LeatherArmorMeta m = ( LeatherArmorMeta ) i.getItemMeta ( );
			m.setColor ( Color.WHITE );
			i.setItemMeta ( m );
			return i;
		}
	},
	CLOTH_PANTS ( CraftType.CRAFT, Material.LEATHER_LEGGINGS, ChatColor.DARK_RED + "Makeshift Cloth Pants", new PVEResource [ ] { new PVEResource ( CLOTH, 13 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			LeatherArmorMeta m = ( LeatherArmorMeta ) i.getItemMeta ( );
			m.setColor ( Color.WHITE );
			i.setItemMeta ( m );
			return i;
		}
	},
	CLOTH_SHOES ( CraftType.CRAFT, Material.LEATHER_BOOTS, ChatColor.DARK_RED + "Makeshift Cloth Shoes", new PVEResource [ ] { new PVEResource ( CLOTH, 4 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			LeatherArmorMeta m = ( LeatherArmorMeta ) i.getItemMeta ( );
			m.setColor ( Color.WHITE );
			i.setItemMeta ( m );
			return i;
		}
	},
	//Heals the player on use by 2 hearts.
	BANDAGE ( CraftType.CRAFT, Material.PAPER, ChatColor.GRAY + "Bandage", new PVEResource [ ] { new PVEResource ( CLOTH, 3 ) } )
	{
		@Override
		public void onUse ( PlayerInteractEvent e )
		{
			e.getPlayer ( ).setHealth ( e.getPlayer ( ).getHealth ( ) + 2 );
			//Tries to remove one bandage from the player's inventory.
			e.getPlayer ( ).getInventory ( ).removeItem ( new ItemStack ( craft ( ) ) );
		}
	},
	//Stone weapons and armor.
	//The clunky armor all has low durability because of the uncohesive nature of raw chunks of rock.
	STONE_CLUB ( CraftType.CRAFT, Material.STONE_SWORD, ChatColor.GRAY + "Stone Club", new PVEResource [ ] { new PVEResource ( STONE, 8 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.DAMAGE_ALL, 1 );
			return i;
		}
	},
	CLUNKY_STONE_HELMET ( CraftType.CRAFT, Material.GOLD_HELMET, ChatColor.GRAY + "Clunky Stone Helmet", new PVEResource [ ] { new PVEResource ( STONE, 4 ), new PVEResource ( CLOTH, 3 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( ( short ) 40 );
			return i;
		}
	},
	CLUNKY_STONE_CHESTPLATE ( CraftType.CRAFT, Material.GOLD_CHESTPLATE, ChatColor.GRAY + "Clunky Stone Chestplate", new PVEResource [ ] { new PVEResource ( STONE, 12 ), new PVEResource ( CLOTH, 7 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( (short) 60 );
			return i;
		}
	},
	CLUNKY_STONE_LEGGINGS ( CraftType.CRAFT, Material.GOLD_LEGGINGS, ChatColor.GRAY + "Clunky Stone Leggings", new PVEResource [ ] { new PVEResource ( STONE, 9 ), new PVEResource ( CLOTH, 5 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( (short) 50 );
			return i;
		}
	},
	CLUNKY_STONE_BOOTS ( CraftType.CRAFT, Material.GOLD_BOOTS, ChatColor.GRAY + "Clunky Stone Boots", new PVEResource [ ] { new PVEResource ( STONE, 3 ), new PVEResource ( CLOTH, 2 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( (short) 45 );
			return i;
		}
	},
	//Stone scale armor. More durable than its clunky counterpart, but more expensive.
	STONE_SCALE_HELMET ( CraftType.CRAFT, Material.CHAINMAIL_HELMET, ChatColor.GRAY + "Stone Scale Helmet", new PVEResource [ ] { new PVEResource ( STONE_SCALES, 3 ), new PVEResource ( CLOTH, 3 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	STONE_SCALE_CHESTPLATE ( CraftType.CRAFT, Material.CHAINMAIL_CHESTPLATE, ChatColor.GRAY + "Stone Scale Chestplate", new PVEResource [ ] { new PVEResource ( STONE_SCALES, 8 ), new PVEResource ( CLOTH, 7 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	STONE_SCALE_LEGGINGS ( CraftType.CRAFT, Material.CHAINMAIL_LEGGINGS, ChatColor.GRAY + "Stone Scale Leggings", new PVEResource [ ] { new PVEResource ( STONE_SCALES, 6 ), new PVEResource ( CLOTH, 5 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	STONE_SCALE_BOOTS ( CraftType.CRAFT, Material.CHAINMAIL_BOOTS, ChatColor.GRAY + "Stone Scale Chestplate", new PVEResource [ ] { new PVEResource ( STONE_SCALES, 2 ), new PVEResource ( CLOTH, 2 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	STONE_SWORD ( CraftType.SMELT, Material.STONE_SWORD, ChatColor.GRAY + "Stone Sword", new PVEResource [ ] { new PVEResource ( STONE_SCALES, 6 ), new PVEResource ( CARBON_ASH, 1 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.DAMAGE_ALL, 2 );
			return i;
		}
	},
	//Stone pebble, does a small amount of damage upon hitting an enemy.
	STONE_PEBBLE ( CraftType.CRAFT, Material.STONE_BUTTON, ChatColor.DARK_GREEN + "Stone Pebble", new PVEResource [ ] { new PVEResource ( STONE, 1 ) } )
	{
		@Override
		public void onUse ( PlayerInteractEvent e )
		{
			Player p = e.getPlayer ( );
			Snowball s = p.getWorld ( ).spawn ( p.getEyeLocation ( ), Snowball.class );
			s.setVelocity ( p.getEyeLocation ( ).getDirection ( ).multiply ( 2.0f ) );
			s.setShooter ( e.getPlayer ( ) );
			//Try to remove a pebble from the player's inventory.
			e.getPlayer ( ).getInventory ( ).removeItem ( new ItemStack ( craft ( ) ) );
		}
	},
	//Metal weapons and armor.
	MAKESHIFT_METAL_WHACKER ( CraftType.CRAFT, Material.IRON_SWORD, ChatColor.GREEN + "Makeshift Metal Whacker", new PVEResource [ ] { new PVEResource ( METAL, 8 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.DAMAGE_ALL, 2 );
			return i;
		}
	},
	CLUNKY_METAL_HELMET ( CraftType.CRAFT, Material.CHAINMAIL_HELMET, ChatColor.GREEN + "Clunky Metal Helmet", new PVEResource [ ] { new PVEResource ( METAL, 4 ), new PVEResource ( CLOTH, 3 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( ( short ) 80 );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	CLUNKY_METAL_CHESTPLATE ( CraftType.CRAFT, Material.CHAINMAIL_CHESTPLATE, ChatColor.GREEN + "Clunky Metal Chestplate", new PVEResource [ ] { new PVEResource ( METAL, 12 ), new PVEResource ( CLOTH, 7 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( ( short ) 120 );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	CLUNKY_METAL_LEGGINGS ( CraftType.CRAFT, Material.CHAINMAIL_LEGGINGS, ChatColor.GREEN + "Clunky Metal Leggings", new PVEResource [ ] { new PVEResource ( METAL, 9 ), new PVEResource ( CLOTH, 5 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( ( short ) 110 );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	CLUNKY_METAL_BOOTS ( CraftType.CRAFT, Material.CHAINMAIL_BOOTS, ChatColor.GREEN + "Clunky Metal Boots", new PVEResource [ ] { new PVEResource ( METAL, 3 ), new PVEResource ( CLOTH, 2 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.setDurability ( ( short ) 80 );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	//Metal scale armor. More durability than its clunky counterpart, but more expensive.
	METAL_SCALE_HELMET ( CraftType.CRAFT, Material.IRON_HELMET, ChatColor.BLUE + "Metal Scale Helmet", new PVEResource [ ] { new PVEResource ( METAL_SCALES, 3 ), new PVEResource ( CLOTH, 3 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 3 );
			return i;
		}
	},
	METAL_SCALE_CHESTPLATE ( CraftType.CRAFT, Material.IRON_CHESTPLATE, ChatColor.BLUE + "Metal Scale Chestplate", new PVEResource [ ] { new PVEResource ( METAL_SCALES, 8 ), new PVEResource ( CLOTH, 7 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 3 );
			return i;
		}
	},
	METAL_SCALE_LEGGINGS ( CraftType.CRAFT, Material.IRON_LEGGINGS, ChatColor.BLUE + "Metal Scale Leggings", new PVEResource [ ] { new PVEResource ( METAL_SCALES, 6 ), new PVEResource ( CLOTH, 5 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 3 );
			return i;
		}
	},
	METAL_SCALE_BOOTS ( CraftType.CRAFT, Material.IRON_BOOTS, ChatColor.BLUE + "Metal Scale Boots", new PVEResource [ ] { new PVEResource ( METAL_SCALES, 2 ), new PVEResource ( CLOTH, 2 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 3 );
			return i;
		}
	},
	METAL_SWORD ( CraftType.SMELT, Material.IRON_SWORD, ChatColor.BLUE + "Metal Sword", new PVEResource [ ] { new PVEResource ( METAL_SCALES, 6 ), new PVEResource ( CARBON_ASH, 1 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.DAMAGE_ALL, 3 );
			return i;
		}
	},
	//Composite armor. More durable than metal scale armor, but also more expensive.
	COMPOSITE_HELMET ( CraftType.CRAFT, Material.DIAMOND_HELMET, ChatColor.DARK_PURPLE + "Composite Helmet", new PVEResource [ ] { new PVEResource ( METALLIC_COMPOSITE, 1 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	COMPOSITE_VEST ( CraftType.CRAFT, Material.DIAMOND_CHESTPLATE, ChatColor.DARK_PURPLE + "Composite Vest", new PVEResource [ ] { new PVEResource ( METALLIC_COMPOSITE, 3 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	COMPOSITE_LEGGINGS ( CraftType.CRAFT, Material.DIAMOND_LEGGINGS, ChatColor.DARK_PURPLE + "Composite Leggings", new PVEResource [ ] { new PVEResource ( METALLIC_COMPOSITE, 2 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	COMPOSITE_BOOTS ( CraftType.CRAFT, Material.DIAMOND_BOOTS, ChatColor.DARK_PURPLE + "Composite Boots", new PVEResource [ ] { new PVEResource ( METALLIC_COMPOSITE, 1 ) } )
	{
		public ItemStack craft ( )
		{
			ItemStack i = super.craft ( );
			i.addEnchantment ( Enchantment.PROTECTION_ENVIRONMENTAL, 1 );
			return i;
		}
	},
	PLACEHOLDER ( CraftType.CRAFT, Material.BEDROCK, "asdf", null );
	
	private Material displayItem;
	private String displayName;
	private PVEResource [ ] ingredients;
	private CraftType type;
	private ButtonPressed onCraft = new ButtonPressed ( )
	{
		@Override
		public boolean buttonPressed ( SPlayer p, ButtonInfo i ) 
		{
			//'Stolen' code from the PVE game class.
			//Finds who clicked it, and what pve item they clicked.
			//It then subtracts ingredients accordingly, and gives the player the final item.
			if ( i.getPanel ( ) == Panel.TOP )
			{
				PVEItems pi = PVEItems.getItem ( i.getItemClicked ( ) );
				p.getInventory ( ).addItem ( pi.craft ( ) );
				pi.subtractIngredients ( p.getPlayer ( ) );
				p.getPlayer ( ).closeInventory ( );
			}
			return true;
		}
	};
	
	public enum CraftType { CRAFT, SMELT; }
	
	private PVEItems ( CraftType t, Material m, String s, PVEResource[] r )
	{
		type = t;
		displayItem = m;
		displayName = s;
		ingredients = r;
	}
	
	//Iterates over the list of ingredients to see if the player has enough to craft the particular item.
	public boolean canCraft ( Player p, CraftType t )
	{
		if ( ingredients != null && t == type )
		{
			Inventory inv = p.getInventory ( );
			boolean can = true;
			for ( int i = 0; i < ingredients.length; i++ )
			{
				PVEResource ingredient = ingredients [ i ];
				try
				{
					if ( inv.getItem ( inv.first ( ingredient.getDisplayItem ( ) ) ).getAmount ( ) < ingredient.getQuantity ( ) )
					{
						can = false;
					}
				}
				catch ( Exception e )
				{
					can = false;
				}
			}
			
			return  can;
		}
		return false;
	}
	
	//Returns an itemstack of the crafted item.
	public ItemStack craft ( )
	{
		ItemStack i = new ItemStack ( displayItem );
		ItemMeta m = i.getItemMeta ( );
		m.setDisplayName ( displayName );
		i.setItemMeta ( m );
		return i;
	}
	
	//Returns a stack of multiple crafted items.
	public ItemStack craft ( int quantity )
	{
		ItemStack tr = craft ( );
		tr.setAmount ( quantity );
		return tr;
	}
	
	//Returns an SGuiButton for putting in an SGui.
	//It uses preCraft ( ) to get an itemstack showing how it is crafted.
	//It uses the onCraft variable so that when the button is clicked, it is crafted.
	public SButton getCraftButton ( )
	{
		ItemStack displayItem = this.preCraft ( );
		SButton tr = new SButton ( displayItem, onCraft );
		return tr;
	}
	
	//Returns an itemstack of the crafted item, including lore on what it needs to be crafted.
	public ItemStack preCraft ( )
	{
		ItemStack i = this.craft ( );
		ItemMeta m = i.getItemMeta ( );
		PVEResource [ ] r = this.getIngredients ( );
		List < String > lore = new ArrayList < String > ( );
		
		if ( r != null )
		{
			for ( int a = 0; a < r.length; a++ )
			{
				lore.add ( ChatColor.GRAY.toString ( ) + ChatColor.ITALIC + "Requires " + r [ a ].getQuantity ( ) + " " + r [ a ].getDisplayName ( ) );
			}
		}
		else
		{
			lore.add ( ChatColor.DARK_GRAY.toString ( ) + ChatColor.ITALIC + "This item is uncraftable." );
		}
		
		m.setLore ( lore );
		i.setItemMeta ( m );
		return i;
	}
	
	//Subtracts the resources required to make an item from the player's inventory.
	public void subtractIngredients ( Player p )
	{
		PVEResource [ ] r = this.getIngredients ( );
		if ( r != null )
		{
			for ( int i = 0; i < r.length; i++ )
			{
				p.getInventory ( ).removeItem ( new ItemStack [ ] { r[i].getItemStack () } );
			}
		}
	}
	
	//Returns a pointer to the PVEItem with the same material and name as the argument's material.
	public static PVEItems getItem ( ItemStack s )
	{
		for ( PVEItems i : PVEItems.values ( ) )
		{
			if ( i.getDisplayItem ( ) == s.getType ( ) && i.getDisplayName ( ) == s.getItemMeta ( ).getDisplayName ( ) ) return i;
		}
		return PLACEHOLDER;
	}
	
	//Called when this item is used, or right-clicked. Not used in most items.
	public void onUse ( PlayerInteractEvent e )
	{
		
	}
	
	//Returns an array of the resources this item needs to be crafted.
	public PVEResource [ ] getIngredients ( )
	{
		return ingredients;
	}
	
	//Returns the displayItem for this item
	public Material getDisplayItem ( )
	{
		return displayItem;
	}
	
	//Returns the displayName for this item
	public String getDisplayName ( )
	{
		return displayName;
	}
	
	//Returns how this item can be crafted
	public CraftType getType ( )
	{
		return type;
	}
}
