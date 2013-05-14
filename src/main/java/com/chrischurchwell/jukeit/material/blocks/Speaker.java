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
package com.chrischurchwell.jukeit.material.blocks;

import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.permission.CraftPermissible;
import com.chrischurchwell.jukeit.util.Recipies;


public class Speaker extends GenericCubeCustomBlock implements WireConnector, CraftPermissible {

	public Speaker() {
		super(JukeIt.getInstance(), "Universal Speaker", 5, "speaker.png", 16);		
		setRecipe();
	}

	@Override
	public String getCraftPermission() {
		return "jukeit.craft.speaker";
	}
	
	public void setRecipe() {
		
		//stupid wood
		
		List<org.getspout.spoutapi.material.Material> woods = Recipies.getWoods();
		
		for (org.getspout.spoutapi.material.Material mat1 : woods) {
			for (org.getspout.spoutapi.material.Material mat2 : woods) {
				for (org.getspout.spoutapi.material.Material mat3 : woods) {
					for (org.getspout.spoutapi.material.Material mat4 : woods) {
						for (org.getspout.spoutapi.material.Material mat5 : woods) {
							for (org.getspout.spoutapi.material.Material mat6 : woods) {
								
								SpoutShapedRecipe r = new SpoutShapedRecipe( new SpoutItemStack(this, 1) );
								r.shape(Recipies.buildWoodRefString(mat1, mat2, mat3), "cnc",Recipies.buildWoodRefString(mat4, mat5, mat6));
								r.setIngredient('c', MaterialData.whiteWool);
								r.setIngredient('n', MaterialData.noteblock);
								
								for (org.getspout.spoutapi.material.Material umat : Recipies.getWoodUniqueMats(mat1, mat2, mat3, mat4, mat5, mat6) ) {
									r.setIngredient(Recipies.getWoodMatRefLetter(umat), umat);
								}
								
								SpoutManager.getMaterialManager().registerSpoutRecipe(r);
								
							}
						}
					}
				}
			}
		}
	}
}
