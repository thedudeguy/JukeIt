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
package cc.thedudeguy.jukebukkit.materials.blocks;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.MachineGUI;
import cc.thedudeguy.jukebukkit.materials.Items;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.permission.UsePermissible;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

import com.chrischurchwell.meshit.Model;

public class MachineBlock extends GenericCustomBlock implements CraftPermissible, UsePermissible {

	public MachineBlock() {
		super(JukeBukkit.instance, "Disc Machine", Material.CAULDRON.getId());
		
		this.setBlockDesign(
				new Model(JukeBukkit.instance.getResource("models/machineBlock.obj")).getDesign().setTexture(JukeBukkit.instance, TextureFile.BLOCK_MACHINE.getTexture()), 
				0
			);
		this.setBlockDesign(
				new Model(JukeBukkit.instance.getResource("models/machineBlockTop.obj")).getDesign().setTexture(JukeBukkit.instance, TextureFile.BLOCK_MACHINE.getTexture()), 
				1
			);
		this.setBlockDesign(
				new Model(JukeBukkit.instance.getResource("models/machineBlockTopPressed.obj")).getDesign().setTexture(JukeBukkit.instance, TextureFile.BLOCK_MACHINE.getTexture()), 
				2
			);
		
		this.setItemDrop(null);
		setRecipe();
	}

	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.machine";
	}
	
	@Override
	public String getUsePermission() {
		return "jukebukkit.use.machine";
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		SpoutBlock block = (SpoutBlock) world.getBlockAt(x, y, z);
		
		//always start from the bottom oriented block.
		if (
			block.getCustomBlockData() > 0
			) {
			block = block.getRelative(BlockFace.DOWN);
		}
		
		//only access if the whole 2 peice combo is available
		if (
				!(block.getCustomBlock() instanceof MachineBlock) ||
				!(block.getRelative(BlockFace.UP).getCustomBlock() instanceof MachineBlock)
				) {
			return false;
		}
		
		//if the thing is in use, skip
		if (block.getRelative(BlockFace.UP).getCustomBlockData() > 1) {
				return false;
		}
		
		if (!player.hasPermission(getUsePermission())) {
			player.sendMessage("You do not have permission to perform this action.");
			player.sendMessage("("+getUsePermission()+")");
			return false;
		}
		
		player.getMainScreen().attachPopupScreen(new MachineGUI(player, block));
		
		return true;
	}
	
	public void setRecipe() {
		SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
		r.shape("t", "b");
		r.setIngredient('t', Items.machineTop);
		r.setIngredient('b', Items.machineBottom);
		
		SpoutManager.getMaterialManager().registerSpoutRecipe(r);
	}
}
