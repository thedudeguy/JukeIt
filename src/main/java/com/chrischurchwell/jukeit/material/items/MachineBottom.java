/**
 * This file is part of JukeIt-Free
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

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.texture.TextureFile;


public class MachineBottom extends GenericCustomItem implements CraftPermissible {

	public MachineBottom() {
		super(JukeIt.getInstance(), "Machine Bottom Half", TextureFile.ITEM_MACHINE_BOTTOM.getFile());
		this.setName("Machine Part");
		setRecipe();
	}
	
	@Override
	public String getCraftPermission() {
		return "jukeit.craft.machinepart";
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
