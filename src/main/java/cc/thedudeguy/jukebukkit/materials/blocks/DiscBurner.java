/**
 * Copyright (C) 2011  Chris Churchwell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.BurnSelector;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.DiscBurnerDesign;
import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;


/**
 * The Prototype Disc Burner. Basic Custom Block in which I can expand with.
 * @author Chris Churchwell
 */
public class DiscBurner extends GenericCubeCustomBlock {
	
	public GenericCubeBlockDesign blockDesign;
	
	/**
	 * Construct
	 * @param JukeBukkit plugin - JukeBukkit instance
	 */
	public DiscBurner()
	{
		super(
			JukeBukkit.instance, 
			"Obsidyisc Burner", 4,
			new DiscBurnerDesign(DiscBurnerDesign.SOUTH)
		);
		
		this.setName("Obsidyisc Burner");
		//int faces => { bottom, north, ?, south (should default for inventory faceing), ?, top}
	}
	
	public DiscBurner(int[] direction)
	{
		super(
			JukeBukkit.instance, 
			"discburner_"+direction.toString(), 4,
			new DiscBurnerDesign(direction)
		);
		
		this.setName("Disc Burner SubBlock (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Blocks.discBurner, 1));
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		//plugin.log.info("onBlockInteract");
		
		Location location = new Location(world, (double)x, (double)y, (double)z);
		ItemStack inHand = player.getItemInHand();
		
		if (inHand != null && (new SpoutItemStack(inHand).isCustomItem()))
		{
			//checks if item in hand is white disc.
			CustomItem customInHand = (CustomItem)new SpoutItemStack(inHand).getMaterial();
			if (customInHand instanceof BlankDisc)
			{
				player.getMainScreen().attachPopupScreen(new BurnSelector(player, world.getBlockAt(x, y, z)));
				return true;
			}
		}
		
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(JukeBukkit.instance, "jb_error.wav", false, location, 8);
		return true;
	}
	
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity entity) {
		
		//Location location = new Location(world, (double)x, (double)y, (double)z);
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		
		//find which way the player is facing...
		double rot = (entity.getLocation().getYaw() -90) % 360;
		if (rot < 0) {
            rot += 360.0;
        }
		
		if (0 <= rot && rot < 45) {
            //WEST
			//Bukkit.getLogger().log(Level.INFO, "west");
			SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerWest);
			return;
        } else if (45 <= rot && rot < 135) {
        	//NORTH
        	//Bukkit.getLogger().log(Level.INFO, "north");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerNorth);
        	return;
        } else if (135 <= rot && rot < 215) {
            //EAST
        	//Bukkit.getLogger().log(Level.INFO, "east");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerEast);
        	return;
        } else if (215 <= rot && rot < 305) {
            //SOUTH
        	//Bukkit.getLogger().log(Level.INFO, "south");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerSouth);
        	return;
        } else if (305 <= rot && rot <= 360) {
        	//WEST
        	//Bukkit.getLogger().log(Level.INFO, "west");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerWest);
        	return;
        } else {
        	//unknown
        	//Bukkit.getLogger().log(Level.INFO, "unknown");
        	SpoutManager.getMaterialManager().overrideBlock(block, Blocks.discBurnerSouth);
        	return;
        }
	}
	
}
