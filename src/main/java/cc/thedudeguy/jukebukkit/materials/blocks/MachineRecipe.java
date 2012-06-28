package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import cc.thedudeguy.jukebukkit.materials.items.BlankDisc;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;
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
