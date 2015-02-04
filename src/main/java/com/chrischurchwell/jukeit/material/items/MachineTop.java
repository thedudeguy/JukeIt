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
package com.chrischurchwell.jukeit.material.items;

import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.texture.TextureFile;
import com.chrischurchwell.jukeit.util.Recipies;


public class MachineTop extends GenericCustomItem implements CraftPermissible {
	
	public MachineTop() {
		super(JukeIt.getInstance(), "Machine Top Half", TextureFile.ITEM_MACHINE_TOP.getFile());
		this.setName("Machine Part");
		setRecipe();
	}
	
	@Override
	public String getCraftPermission() {
		return "jukeit.craft.machinepart";
	}
	
	private void setRecipe() {
		List<org.getspout.spoutapi.material.Material> woods = Recipies.getWoods();
		for (org.getspout.spoutapi.material.Material mat1 : woods) {
			for (org.getspout.spoutapi.material.Material mat2 : woods) {
				for (org.getspout.spoutapi.material.Material mat3 : woods) {
					SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
					r.shape(Recipies.buildWoodRefString(mat1, mat2, mat3), "ipi","i i" );
					r.setIngredient('i', MaterialData.ironIngot);
					r.setIngredient('p', MaterialData.pistonBase);
					
					for (org.getspout.spoutapi.material.Material umat : Recipies.getWoodUniqueMats(mat1, mat2, mat3) ) {
						r.setIngredient(Recipies.getWoodMatRefLetter(umat), umat);
					}
					
					SpoutManager.getMaterialManager().registerSpoutRecipe(r);
				}
			}
		}
	}
}
