/**
 * This file is part of JukeBukkit
 *
 * Copyright (C) 2011-2012  Chris Churchwell
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
 */
package cc.thedudeguy.jukebukkit.material.items;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.material.Blocks;
import cc.thedudeguy.jukebukkit.material.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.texture.TextureFile;
import cc.thedudeguy.jukebukkit.util.Debug;

public class RepeaterChipItem extends GenericCustomItem {
	
	public RepeaterChipItem() {
		super(JukeBukkit.getInstance(), "Repeater Chip Item", TextureFile.ITEM_REPEATER_CHIP.getFile());
		this.setName("Repeater Chip");
		
		//recipe
		SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
		r.shape("oio", "iri","oio");
		r.setIngredient('o', MaterialData.obsidian);
		r.setIngredient('i', MaterialData.ironIngot);
		r.setIngredient('r', MaterialData.redstone);
		SpoutManager.getMaterialManager().registerSpoutRecipe(r);
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		if (
				block == null || 
				block.getType().equals(Material.AIR) ||
				face.equals(BlockFace.UP) ||
				face.equals(BlockFace.DOWN) ||
				!(block.getCustomBlock() instanceof RecordPlayer)
				) {
			return true;
		}
		
		SpoutBlock target = block.getRelative(face);
		
		if (target == null || target.getType().equals(Material.AIR)) {
			int rotationDesign = 0;
			
			if (face.equals(BlockFace.NORTH)) {
				rotationDesign = 3;
			} else if (face.equals(BlockFace.EAST)) {
				rotationDesign = 2;
			} else if (face.equals(BlockFace.SOUTH)) {
				rotationDesign = 1;
			} else if (face.equals(BlockFace.WEST)) {
				rotationDesign = 0;
			} else {
				rotationDesign = 0;
			}
			
			Debug.debug(player, face, rotationDesign);
			
			SpoutManager.getMaterialManager().overrideBlock(target, Blocks.repeaterChipBlock, (byte)rotationDesign);
			
			//remove 1 from hand.
			//if (!player.getGameMode().equals(GameMode.CREATIVE)) {
				ItemStack inHand = player.getItemInHand();
				if (inHand.getAmount()<2) {
					player.setItemInHand(new ItemStack(Material.AIR));
				} else {
					player.getItemInHand().setAmount(inHand.getAmount()-1);
				}
			//}
			
			return false;
		}
		
		
		
		return true;
	}
}
