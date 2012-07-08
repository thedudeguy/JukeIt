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
package cc.thedudeguy.jukebukkit.materials.items;

import org.bukkit.Material;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineRecipe;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineRecipe.RecipeDiscType;

public class DiscOnAStick extends GenericCustomItem {

	/* maybe one day this will be a crappy weapon, but for now its just a useless item */
	public DiscOnAStick() {
		super(JukeBukkit.instance, "Disc on a Stick", "disconastick.png");
		MachineRecipe.addMachineRecipe(new MachineRecipe(RecipeDiscType.ANY, Material.STICK, new SpoutItemStack(this)));
		
	}
}
