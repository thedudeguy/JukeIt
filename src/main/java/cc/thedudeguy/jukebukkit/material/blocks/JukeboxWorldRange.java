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
package cc.thedudeguy.jukebukkit.material.blocks;

import org.bukkit.Location;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.material.Blocks;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class JukeboxWorldRange extends JukeboxBlock {
	
	public JukeboxWorldRange()
	{
		super("World Range Jukebox");
	}
	
	@Override
	public GenericCubeBlockDesign getCustomBlockDesign() {
		
		return new GenericCubeBlockDesign(
				JukeBukkit.getInstance(), 
				TextureFile.BLOCK_JUKEBOX_WORLD.getTexture(), 
				new int[] { 0, 2, 2, 2, 2, 1 }
			);
	}
	
	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.jukebox.world";
	}
	
	@Override
	public String getUsePermission() {
		return "jukebukkit.use.jukebox.world";
	}
	
	@Override
	public boolean canRedstoneActivate() {
		return true;
	}
	
	@Override
	public void playMusic(String url, Location location)
	
	{
		 url = JukeBukkit.finishIncompleteURL(url);
	SpoutManager.getSoundManager().playGlobalCustomMusic(JukeBukkit.getInstance(), url, true);
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
