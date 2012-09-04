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

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.permission.CraftPermissible;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class MachineBottom extends GenericCustomItem implements CraftPermissible {

	public MachineBottom() {
		super(JukeBukkit.instance, "Machine Bottom Half", TextureFile.ITEM_MACHINE_BOTTOM.getFile());
		this.setName("Machine Part");
		setRecipe();
	}
	
	@Override
	public String getCraftPermission() {
		return "jukebukkit.craft.machinepart";
	}
	
	private void setRecipe() {
		SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
		r.shape("sss", "sls","scs");
		r.setIngredient('s', MaterialData.cobblestone);
		r.setIngredient('l', MaterialData.lavaBucket);
		r.setIngredient('c', MaterialData.compass);
		
		SpoutManager.getMaterialManager().registerSpoutRecipe(r);
	}
}
