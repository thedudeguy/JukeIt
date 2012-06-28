package cc.thedudeguy.jukebukkit.util;

import java.util.ArrayList;
import java.util.List;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutShapelessRecipe;
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
		
		///////////////////////////
		// Black Blank Obsidyisc //
		///////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack(Items.blankDiscBlack, 1) )
			.addIngredient(MaterialData.inkSac)
			.addIngredient(Items.blankDiscWhite)
			);
		
		/////////////////////////
		// Red Blank Obsidyisc //
		/////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscRed, 1) )
			.addIngredient(MaterialData.roseRed)
			.addIngredient(Items.blankDiscWhite)
			);
		
		///////////////////////////
		// Green Blank Obsidyisc //
		///////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscGreen, 1) )
			.addIngredient(MaterialData.cactusGreen)
			.addIngredient(Items.blankDiscWhite)
			);
		
		/////////////////////////
		// Brown Blank Obsidyisc //
		/////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscBrown, 1) )
			.addIngredient(MaterialData.cocoaBeans)
			.addIngredient(Items.blankDiscWhite)
			);
		
		//////////////////////////
		// Blue Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscBlue, 1) )
			.addIngredient(MaterialData.lapisLazuli)
			.addIngredient(Items.blankDiscWhite)
			);
		
		////////////////////////////
		// Purple Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscPurple, 1) )
			.addIngredient(MaterialData.purpleDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		//////////////////////////
		// Cyan Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscCyan, 1) )
			.addIngredient(MaterialData.cyanDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		////////////////////////////////
		// Light Gray Blank Obsidyisc //
		////////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscLightGray, 1) )
			.addIngredient(MaterialData.lightGrayDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		//////////////////////////
		// Gray Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscGray, 1) )
			.addIngredient(MaterialData.grayDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		//////////////////////////
		// Pink Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscPink, 1) )
			.addIngredient(MaterialData.pinkDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		//////////////////////////
		// Lime Blank Obsidyisc //
		//////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscLime, 1) )
			.addIngredient(MaterialData.limeDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		////////////////////////////
		// Yellow Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscYellow, 1) )
			.addIngredient(MaterialData.dandelionYellow)
			.addIngredient(Items.blankDiscWhite)
			);
		
		////////////////////////////////
		// Light Blue Blank Obsidyisc //
		////////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscLightBlue, 1) )
			.addIngredient(MaterialData.lightBlueDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		/////////////////////////////
		// Magenta Blank Obsidyisc //
		/////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscMagenta, 1) )
			.addIngredient(MaterialData.magentaDye)
			.addIngredient(Items.blankDiscWhite)
			);
		
		////////////////////////////
		// Orange Blank Obsidyisc //
		////////////////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
			new SpoutShapelessRecipe( new SpoutItemStack( Items.blankDiscOrange, 1) )
			.addIngredient(MaterialData.orangeDye)
			.addIngredient(Items.blankDiscWhite)
			);
	}
	
}
