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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.material.items.BlankDisc;
import cc.thedudeguy.jukebukkit.material.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.util.Debug;

public class MachineRecipe {

	public static enum RecipeDiscType {
		BURNED(),
		BLANK(),
		ANY();
		
		RecipeDiscType() {
			
		}
		
		public static boolean matches(RecipeDiscType itemDiscType, RecipeDiscType compareDiscType) {
			if (compareDiscType.equals(RecipeDiscType.ANY)) return true;
			if(itemDiscType.equals(compareDiscType)) return true;
			return false;
		}
		
		public static RecipeDiscType getItemDiscType(SpoutItemStack item) {
			if (item.getMaterial() instanceof BurnedDisc) return RecipeDiscType.BURNED;
			if (item.getMaterial() instanceof BlankDisc) return RecipeDiscType.BLANK;
			return null;
		}
	}
	
	public static List<MachineRecipe> recipes = new ArrayList<MachineRecipe>();
	
	public static void addMachineRecipe(MachineRecipe recipe) {
		recipes.add(recipe);
	}
	
	/**
	 * check the itemstacks for a valid recipe. if found returns the recipe itemstack. returns null otherwise.
	 */
	public static ItemStack getRecipeMatch(SpoutItemStack discItem, SpoutItemStack ingredientItem) {
		Debug.sdebug("getRecipeMatch", discItem, ingredientItem);
		
		//currently dont support custom items.
		if (ingredientItem.isCustomItem()) return null;
		
		Debug.debug("Ingredient is not a custom item");
		
		RecipeDiscType discType = RecipeDiscType.getItemDiscType(discItem);
		Material ingredient = ingredientItem.getType();
		
		for ( MachineRecipe recipe : recipes ) {
			if (ingredient.equals(recipe.getIngredient())) {
				Debug.debug("Ingrediant Matches");
				
				if (RecipeDiscType.matches(discType, recipe.getRecipeDiscType()) ) {
					Debug.debug("Disc Matches");
					return recipe.getResult();
				}
				
			}
		}
		
		return null;
	}
	
	private RecipeDiscType discType;
	private Material ingredient;
	private ItemStack result;
	
	public MachineRecipe(RecipeDiscType discType, Material ingredient, ItemStack result) {
		setRecipeDiscType(discType);
		setIngredient(ingredient);
		setResult(result);
	}
	
	public void setRecipeDiscType(RecipeDiscType discType) {
		this.discType = discType;
	}
	
	public void setIngredient(Material ingredient) {
		this.ingredient = ingredient;
	}
	
	public void setResult(ItemStack result) {
		this.result = result;
	}
	
	public RecipeDiscType getRecipeDiscType() {
		return this.discType;
	}
	
	public Material getIngredient() {
		return this.ingredient;
	}
	
	public ItemStack getResult() {
		return this.result;
	}
}
