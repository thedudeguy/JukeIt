/**
 * This file is part of JukeIt
 *
 * Copyright (C) 2011-2013  Chris Churchwell
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
package com.chrischurchwell.jukeit.material.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.material.Blocks;
import com.chrischurchwell.jukeit.texture.TextureFile;
import com.chrischurchwell.jukeit.util.DiscUtil;


public class JukeboxWorldRange extends JukeboxBlock {
	
	public JukeboxWorldRange()
	{
		super("World Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeIt.getInstance(), 
				TextureFile.BLOCK_JUKEBOX_WORLD.getTexture(), 
				new int[] { 0, 2, 2, 2, 2, 1 }
			);
	}
	
	@Override
	public String getCraftPermission() {
		return "jukeit.craft.jukebox.world";
	}
	
	@Override
	public String getUsePermission() {
		return "jukeit.use.jukebox.world";
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}
	
	@Override
	public void playMusic(String url, Location location) {
		 url = DiscUtil.finishIncompleteURL(url);
		 for (Player player : Bukkit.getOnlinePlayers()) {
			 if (player.getWorld().equals(location.getWorld())) {
				 SpoutManager.getSoundManager().playCustomMusic(JukeIt.getInstance(), SpoutManager.getPlayer(player), url, true);
			 }
		 }
	}
	
	@Override
	public void stopMusic(Location location) {
		for(SpoutPlayer p:SpoutManager.getOnlinePlayers()) {
			if (p.isSpoutCraftEnabled()) {
				SpoutManager.getSoundManager().stopMusic(p);
			}
		}
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRecipe() {
		SpoutManager.getMaterialManager().registerSpoutRecipe(
		new SpoutShapedRecipe( new SpoutItemStack(this, 1) )
		.shape("njn", "jdj", "njn")
		.setIngredient('j', Blocks.jukeboxMaxRange)
		.setIngredient('n', MaterialData.noteblock)
		.setIngredient('d', MaterialData.diamondBlock)
		);
	}
}
