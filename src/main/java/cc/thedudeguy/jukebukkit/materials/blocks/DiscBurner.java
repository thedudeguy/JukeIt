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
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.BurnSelector;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.DiscBurnerDesign;
import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.util.Debug;


/**
 * The Prototype Disc Burner. Basic Custom Block in which I can expand with.
 * @author Chris Churchwell
 */
public class DiscBurner extends GenericCustomBlock {
	
	public GenericCubeBlockDesign blockDesign;
	
	/*
	public static final int[] SOUTH =;
	public static final int[] NORTH = ;
	public static final int[] EAST = ;
	public static final int[] WEST = ;
	*/
	
	public static final int SOUTH = 0;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;
	
	/**
	 * Construct
	 * @param JukeBukkit plugin - JukeBukkit instance
	 */
	public DiscBurner(int direction)
	{
		super(
			JukeBukkit.instance,
			"DiscBurner_"+String.valueOf(direction), 4
		);
		this.setBlockDesign(direction);
		this.setName("Disc Burner");
		if (direction != SOUTH) {
			this.setItemDrop(new SpoutItemStack(Blocks.discBurnerSouth, 1));
		}
	}
	
	public void setBlockDesign(int direction) {
		
		if (direction == SOUTH) {
			int[] t = { 2, 3, 3, 4, 3, 2 };
			setBlockDesign(new DiscBurnerDesign(t));
		}
		else if (direction == NORTH) {
			int[] t = { 2, 4, 3, 3, 3, 2 };
			setBlockDesign(new DiscBurnerDesign(t));
		}
		else if (direction == EAST) {
			int[] t = { 2, 3, 4, 3, 3, 2 };
			setBlockDesign(new DiscBurnerDesign(t));
		}
		else if (direction == WEST) {
			int[] t = { 2, 3, 3, 3, 4, 2 };
			setBlockDesign(new DiscBurnerDesign(t));
		}
		else {
			int[] t = { 2, 3, 3, 4, 3, 2 };
			setBlockDesign(new DiscBurnerDesign(t));
		}
		
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
	
	public static final DiscBurner getBlockByYaw(double yaw) {
		
		Debug.debug("Yaw: ", yaw);
		
		//find which way the player is facing...
		double rot = (yaw -90) % 360;
		if (rot < 0) {
            rot += 360.0;
        }
		
		Debug.debug("rot: ", rot);
		
		if (0 <= rot && rot < 45) {
            //WEST, place east
			Debug.debug("burner east");
			return Blocks.discBurnerEast;
        } else if (45 <= rot && rot < 135) {
        	//NORTH, place south
        	Debug.debug("burner south");
        	return Blocks.discBurnerSouth;
        } else if (135 <= rot && rot < 215) {
            //EAST, place west
        	Debug.debug("burner west");
        	return Blocks.discBurnerWest;
        } else if (215 <= rot && rot < 305) {
            //SOUTH, place north
        	Debug.debug("burner north");
        	return Blocks.discBurnerNorth;
        } else if (305 <= rot && rot <= 360) {
        	//WEST, place east
        	Debug.debug("burner east");
        	return Blocks.discBurnerEast;
        } else {
        	//unknown
        	Debug.debug("burner unknown");
        	return Blocks.discBurnerSouth;
        }
	}
	
}
