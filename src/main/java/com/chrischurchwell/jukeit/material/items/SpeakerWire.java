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
package com.chrischurchwell.jukeit.material.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.event.SpeakerWirePlaceEvent;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.texture.TextureFile;
import com.chrischurchwell.jukeit.util.Debug;


public class SpeakerWire extends GenericCustomItem implements CraftPermissible {
	
	public SpeakerWire() {
		super(JukeIt.getInstance(), "Speaker Wire");
		setTexture(TextureFile.ITEM_SPEAKER_WIRE.getFile());
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		if (block != null && !block.getType().equals(Material.AIR) && face.equals(BlockFace.UP)) {
			
			SpoutBlock placeBlock = block.getRelative(face);
			if (placeBlock == null || placeBlock.getType().equals(Material.AIR)) {
				
				SpeakerWirePlaceEvent event = new SpeakerWirePlaceEvent(player, placeBlock);
				Bukkit.getServer().getPluginManager().callEvent(event);
				
				//remove 1 from hand.
				//if (!player.getGameMode().equals(GameMode.CREATIVE)) {
					Debug.debug(player, "Removing item from hand");
					ItemStack inHand = player.getItemInHand();
					if (inHand.getAmount()<2) {
						player.setItemInHand(new ItemStack(Material.AIR));
					} else {
						player.getItemInHand().setAmount(inHand.getAmount()-1);
					}
				//}
				
			}
			
		}
		return false;
	}

	@Override
	public String getCraftPermission() {
		return "jukeit.craft.wire";
	}
	
}
