/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.material.blocks;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.World;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.Axis;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;


import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.gui.repeater.RepeaterChipGUI;
import com.chrischurchwell.jukeit.material.Blocks;
import com.chrischurchwell.jukeit.texture.TextureFile;
import com.chrischurchwell.meshit.Model;

public class RepeaterChipBlock extends GenericCustomBlock {
	
	public static HashMap<SpoutBlock, Long> repeatQueue = new HashMap<SpoutBlock, Long>();
	
	public static void addRepeatToQueue(SpoutBlock block, long repeatIn) {
		repeatQueue.put(block, System.currentTimeMillis() + repeatIn);
	}
	
	public RepeaterChipBlock() {
		super(JukeIt.getInstance(), "Repeater Chip", 36);
		
		Model model = new Model(JukeIt.getInstance().getResource("models/repeater.obj"));
		GenericBlockDesign design = model.getDesign();
		design.setTexture(JukeIt.getInstance(), TextureFile.BLOCK_REPEATER_CHIP.getTexture());
		
		setBlockDesign(design, 0);
		setBlockDesign(design.rotate(90, Axis.Y), 1);
		setBlockDesign(design.rotate(180, Axis.Y), 2);
		setBlockDesign(design.rotate(270, Axis.Y), 3);
		
		//set up the tick thread.
		JukeIt.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(JukeIt.getInstance(), new Runnable() {
			public void run() {
				for(Entry<SpoutBlock, Long> entry : repeatQueue.entrySet()) {
					if (entry.getValue() > System.currentTimeMillis()) {
						continue;
					}
					if (entry.getKey().getCustomBlock() instanceof RecordPlayer) {
						if (RecordPlayer.isPoweredUp(entry.getKey())) {
							repeatQueue.remove(entry.getKey());
							Blocks.recordPlayer.onBlockClicked(entry.getKey().getWorld(), entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ(), null);
						} else {
							repeatQueue.remove(entry.getKey());
						}
					} else {
						repeatQueue.remove(entry.getKey());
					}
				}
			}
		}, 0, 5L);
		
	}
	
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		
		player.getMainScreen().attachPopupScreen(new RepeaterChipGUI((SpoutBlock)world.getBlockAt(x, y, z)));
		
		return true;
	}
}
