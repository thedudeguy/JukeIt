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

import org.bukkit.World;
import org.getspout.spoutapi.block.design.Axis;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.repeater.RepeaterChipGUI;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

import com.chrischurchwell.meshit.Model;

public class RepeaterChipBlock extends GenericCustomBlock {

	public RepeaterChipBlock() {
		super(JukeBukkit.getInstance(), "Repeater Chip", 36);
		
		Model model = new Model(JukeBukkit.getInstance().getResource("models/repeater.obj"));
		GenericBlockDesign design = model.getDesign();
		design.setTexture(JukeBukkit.getInstance(), TextureFile.BLOCK_REPEATER_CHIP.getTexture());
		
		setBlockDesign(design, 0);
		setBlockDesign(design.rotate(90, Axis.Y), 1);
		setBlockDesign(design.rotate(180, Axis.Y), 2);
		setBlockDesign(design.rotate(270, Axis.Y), 3);
	}
	
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		player.getMainScreen().attachPopupScreen(new RepeaterChipGUI());
		
		return true;
	}
}
