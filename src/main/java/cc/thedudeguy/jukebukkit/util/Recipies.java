package cc.thedudeguy.jukebukkit.util;

import java.util.ArrayList;
import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.Material;
import org.getspout.spoutapi.material.MaterialData;

import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.materials.Items;

public class Recipies {
	
	public static final char getWoodMatRefLetter(Material mat) {
		if (mat.equals(MaterialData.wood)) return '!';
		if (mat.equals(MaterialData.jungleWood)) return '@';
		if (mat.equals(MaterialData.birchWood)) return '#';
		if (mat.equals(MaterialData.spruceWood)) return '$';
		return '!';
	}
	
	public static final String buildWoodRefString( Object mat1, Object mat2, Object mat3 ) {
		String bstr = "";
		
		if ( mat1 instanceof Character ) bstr = bstr + (Character)mat1;
		else bstr = bstr + getWoodMatRefLetter((Material)mat1);
		
		if ( mat2 instanceof Character ) bstr = bstr + (Character)mat2;
		else bstr = bstr + getWoodMatRefLetter((Material)mat2);
		
		if ( mat3 instanceof Character ) bstr = bstr + (Character)mat3;
		else bstr = bstr + getWoodMatRefLetter((Material)mat3);
		
		return bstr;
	}
	
	public static final List<Material> getWoodUniqueMats(Material... mats) {
		List<Material> l = new ArrayList<Material>();
		
		for (Material mat : mats) {
			if(!l.contains(mat)) l.add(mat);
		}
		return l;
	}
	
	public static final List<Material> getWoods() {
		List<Material> woods = new ArrayList<Material>();
		woods.add(MaterialData.wood);
		woods.add(MaterialData.birchWood);
		woods.add(MaterialData.jungleWood);
		woods.add(MaterialData.spruceWood);
		
		return woods;
	}
	
	public static void load() {
		
		//////////////////
		// Speaker Wire //
		//////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
		new SpoutShapedRecipe( new SpoutItemStack(Items.speakerWire, 3) )
			.shape("sss", "rrr")
			.setIngredient('s', MaterialData.string)
			.setIngredient('r', MaterialData.redstone)
			);
		
		//////////////////////////////
		// Wood-Flint Record Needle //
		//////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(Items.woodflintNeedle, 1) )
			.shape("ttt", "sss", "  f")
			.setIngredient('t', Items.speakerWire)
			.setIngredient('s', MaterialData.stick)
			.setIngredient('f', MaterialData.flint)
			);
		
		//////////////////////////////
		// Blaze-Flint Record Needle //
		//////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(Items.blazeflintNeedle, 1) )
			.shape("ttt", "sss", "  f")
			.setIngredient('t', Items.speakerWire)
			.setIngredient('s', MaterialData.blazeRod)
			.setIngredient('f', MaterialData.flint)
			);
		
		//////////////////////
		// Disc Burner //
		//////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(Blocks.discBurner, 1) )
			.shape("jf")
			.setIngredient('j', MaterialData.jukebox)
			.setIngredient('f', MaterialData.furnace)
			);
		
		
		/////////////////////
		// Blank Obsidyisc //
		/////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapedRecipe( new SpoutItemStack(Items.blankDiscWhite, 1) )
			.shape(" o ", "oRo", " o ")
			.setIngredient('o', MaterialData.obsidian)
			.setIngredient('R', MaterialData.redstone)
			);
		
		
	}
	
}
