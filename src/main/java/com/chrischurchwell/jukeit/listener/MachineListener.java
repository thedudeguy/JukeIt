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
package com.chrischurchwell.jukeit.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.event.MachineCompleteEvent;
import com.chrischurchwell.jukeit.event.MachineEvent;
import com.chrischurchwell.jukeit.event.MachineProcessEvent;
import com.chrischurchwell.jukeit.event.MachineStartEvent;
import com.chrischurchwell.jukeit.material.Blocks;
import com.chrischurchwell.jukeit.material.DiscColor;
import com.chrischurchwell.jukeit.material.Items;
import com.chrischurchwell.jukeit.material.blocks.MachineBlock;
import com.chrischurchwell.jukeit.material.blocks.MachineRecipe;
import com.chrischurchwell.jukeit.material.items.BlankDisc;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.runnable.MachineRunnable;
import com.chrischurchwell.jukeit.runnable.ParticleGeneratorRunnable;
import com.chrischurchwell.jukeit.sound.Sound;
import com.chrischurchwell.jukeit.sound.SoundEffect;
import com.chrischurchwell.jukeit.util.Debug;


public class MachineListener implements Listener {
	
	@EventHandler
	public void onPistonPull(BlockPistonRetractEvent event) {
		if (event.isCancelled() || !event.isSticky()) {
			return;
		}
		SpoutBlock block = (SpoutBlock)event.getBlock().getRelative(event.getDirection(), 2);
		
		if ( block.getCustomBlock() == null) {
			return;
		}
		if (block.getCustomBlock() instanceof MachineBlock) {
			Debug.debug("canceling piston event");
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onPistonPush(BlockPistonExtendEvent event) {
		if (event.isCancelled()) return;
		for(Block block : event.getBlocks()) {
			if ( ((SpoutBlock)block).getCustomBlock() != null) {
				if ( ((SpoutBlock)block).getCustomBlock() instanceof MachineBlock ) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (
				((SpoutBlock)event.getBlock()).getCustomBlock() == null ||
				!(((SpoutBlock)event.getBlock()).getCustomBlock() instanceof MachineBlock)
				){
			return;
			
		}
		
		if (
				!event.getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR)
			) {
			
			((SpoutBlock)event.getBlock()).setCustomBlock(null);
			((SpoutBlock)event.getBlock()).setType(Material.AIR);
			
			Location loc = event.getBlock().getLocation();
			loc.setX(loc.getX()+0.5);
			loc.setZ(loc.getZ()+0.5);
			//loc.setY(loc.getY()+1.0);
			loc.getWorld().dropItem(loc, new SpoutItemStack(Blocks.machineBlock));
			return;
		}
		
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.machineBlock, (byte)1);
	}
	
	@EventHandler
	public void onStart(MachineStartEvent event) {
		Debug.debug("LabelMachineStartEvent heard - starting up. ");
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.machineBlock, (byte)2);
		
		new Sound(SoundEffect.MACHINE_PRESS, event.getBlock(), 8).play();
		
		Particle particle = new Particle(ParticleType.LARGESMOKE, event.getBlock().getLocation().add(0.5d, 1d, 0.5d), new Vector(0,0,0));
		particle.setAmount(50);
		particle.setMaxAge(20);
		particle.spawn();
		
		MachineRunnable r = new MachineRunnable(event.getBlock(), event.getPrimaryItem(), event.getAdditionItem(), event.getLabel()) {
			@Override
			public void run() {
				MachineProcessEvent pEvent = new MachineProcessEvent(getBlock(), getPrimaryItem(), getAdditionalItem(), getLabel());
				Bukkit.getServer().getPluginManager().callEvent(pEvent);
			}
		};
		r.runTaskLater(JukeIt.getInstance(), 10L);
	}
	
	@EventHandler
	public void onProcess(MachineProcessEvent event) {
		Debug.debug("LabelMachineProcessEvent heard - processing graphics. bzzrrt! whirr!! chjkchjk ");
		
		Particle particle = new Particle(ParticleType.SMOKE, event.getBlock().getLocation().add(0.5d, 1d, 0.5d), new Vector(0,0.2,0));
		particle.setMaxAge(10);
		particle.setAmount(5);
		new ParticleGeneratorRunnable(particle, 5000).runTaskTimer(JukeIt.getInstance(), 0L, 5L);
		
		MachineRunnable r = new MachineRunnable(event.getBlock(), event.getPrimaryItem(), event.getAdditionItem(), event.getLabel()) {
			@Override
			public void run() {
				MachineCompleteEvent pEvent = new MachineCompleteEvent(getBlock(), getPrimaryItem(), getAdditionalItem(), getLabel());
				Bukkit.getServer().getPluginManager().callEvent(pEvent);
			}
		};
		r.runTaskLater(JukeIt.getInstance(), 110L);
	}
	
	@EventHandler
	public void onComplete(MachineCompleteEvent event) {
		Debug.debug("LabelMachineCompleteEvent heard - its magic.");
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.machineBlock, (byte)1);
		
		Particle particle = new Particle(ParticleType.CLOUD, event.getBlock().getLocation().add(0.5d, 1d, 0.5d), new Vector(0,0,0));
		particle.setGravity(0);
		particle.setScale(0.5F);
		particle.setAmount(100);
		particle.spawn();
		
		//if either item is air, transaction is a flop, just abort.
		Debug.sdebug("values -- ", event.getPrimaryItem(), event.getAdditionItem(), event.hasLabel());
		if (event.getPrimaryItem().getType().equals(Material.AIR) || event.getAdditionItem().getType().equals(Material.AIR)) {
			abortEject(event);
			return;
		}
		
		//checking to see if we had a labeling disc setup.
		ItemStack cPrimItem = event.getPrimaryItem();
		ItemStack cAddItem = event.getAdditionItem();
		SpoutItemStack sPrimItem = new SpoutItemStack(cPrimItem);
		SpoutItemStack sAddItem = new SpoutItemStack(cAddItem);
		
		if (
				cAddItem.getType().equals(Material.PAPER) &&
				event.hasLabel() &&
				(new SpoutItemStack(cPrimItem)).getMaterial() instanceof BurnedDisc &&
				!BurnedDisc.decodeDisc(cPrimItem).isEmpty()
				) {
			
			ItemStack newDisc = BurnedDisc.labelDisc(cPrimItem, event.getLabel());
			
			eject(event.getBlock(), newDisc);
			if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			return;
		}
		
		//check if were cloning a disc.
		if ( 
				(sPrimItem.getMaterial() instanceof BlankDisc && sAddItem.getMaterial() instanceof BurnedDisc) ||
				(sPrimItem.getMaterial() instanceof BurnedDisc && sAddItem.getMaterial() instanceof BlankDisc)
				) {
			
			ItemStack master;
			ItemStack target;
			//which one are we making a copy of?
			if (sPrimItem.getMaterial() instanceof BurnedDisc) {
				master =  cPrimItem;
				target =  cAddItem;
			} else {
				master = cAddItem;
				target = cPrimItem;
			}
			
			
			ItemStack newDisc = BurnedDisc.createDisc(
					((BlankDisc)(new SpoutItemStack(target)).getMaterial()).getColor(), 
					BurnedDisc.decodeDisc(master), 
					BurnedDisc.getDiscLabel(master)
					);
			
			this.eject(event.getBlock(), newDisc);
			
			//eject leftovers. since were cloning the master disc, should not destroy it.
			if (sPrimItem.getMaterial() instanceof BurnedDisc) {
				eject(event.getBlock(), event.getPrimaryItem());
				if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			} else {
				eject(event.getBlock(), event.getAdditionItem());
				if (!lastOne(event.getPrimaryItem())) eject(event.getBlock(), removeOne(event.getPrimaryItem()));
			}
			
			return;
		}
		
		//check if were coloring a blank disc
		if ( (sPrimItem.getMaterial() instanceof BlankDisc && sAddItem.getType().equals(Material.INK_SACK)) ) {
			Debug.debug("attempting to color blank disc");
			Debug.debug("Dye: ", sAddItem.getData().getData(), " : ", event.getAdditionItem().getData().getData());
			
			if (sPrimItem.getAmount() == sAddItem.getAmount()) {
				//will use all the dye, dont return any.
				//will use all the discs. dont return any.
				//return amount of new color discs.
				eject(event.getBlock(), getBlankDiscByDyeColor(sAddItem.getData().getData(), sPrimItem.getAmount()));
				return;
			}
			if (sPrimItem.getAmount() > sAddItem.getAmount()) {
				//will use all the die, dont return any.
				//will use a portion of the discs. return some.
				int amountLeft = sPrimItem.getAmount() - sAddItem.getAmount();
				sPrimItem.setAmount(amountLeft);
				eject(event.getBlock(), sPrimItem);
				//return equal number of disc in new color.
				eject(event.getBlock(), getBlankDiscByDyeColor(sAddItem.getData().getData(), sAddItem.getAmount()));
				return;
			}
			if (sAddItem.getAmount() > sPrimItem.getAmount()) {
				//will use a portion of the dye, return some.
				int amountLeft = sAddItem.getAmount() - sPrimItem.getAmount();
				sAddItem.setAmount(amountLeft);
				eject(event.getBlock(), sAddItem);
				//will us all the discs. dont return any.
				//return equal number of discs used.
				eject(event.getBlock(), getBlankDiscByDyeColor(sAddItem.getData().getData(), sPrimItem.getAmount()));
				return;
			}
			
			abortEject(event);
			return;
		}
		
		//check for coloring already burned discs.
		if ( (sPrimItem.getMaterial() instanceof BurnedDisc && sAddItem.getType().equals(Material.INK_SACK)) ) {
			Debug.debug("coloring burned disc");
			
			ItemStack disc = sPrimItem;
			
			ItemStack newDisc = BurnedDisc.createDisc(getDiscColor(sAddItem.getData().getData()), BurnedDisc.decodeDisc(disc), BurnedDisc.getDiscLabel(disc));
			
			//eject left over dye.
			if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			//eject disc (should automatically have new color)
			eject(event.getBlock(), newDisc);
			return;
		}
		
		//set if we have a recipe match
		ItemStack recipeResult = MachineRecipe.getRecipeMatch(sPrimItem, sAddItem);
		if (recipeResult != null) {
			eject(event.getBlock(), recipeResult);
			if (!lastOne(event.getAdditionItem())) eject(event.getBlock(), removeOne(event.getAdditionItem()));
			return;
		}
		
		
		//if were here, than there was nothing but unmatchable junk.
		abortEject(event);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		
		
		if (!(((SpoutBlock)event.getBlock()).getCustomBlock() instanceof MachineBlock)){
			return;
		}
		
		destroyOtherHalf(event.getBlock());
		
		((SpoutBlock)event.getBlock()).setCustomBlock(null);
		((SpoutBlock)event.getBlock()).setType(Material.AIR);
		
		if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			Location loc = event.getBlock().getLocation();
			loc.setX(loc.getX() + 0.5);
			loc.setY(loc.getY() + 0.5);
			event.getBlock().getWorld().dropItem(loc, new SpoutItemStack(Blocks.machineBlock, 1));
		}
		
		event.setCancelled(true);
	}
	
	private void destroyOtherHalf(Block block) {
		//possibly broken top block need to destroy bottom block
		if (
				((SpoutBlock)block).getCustomBlockData() > 0 &&
				(
					((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock() instanceof MachineBlock &&
					((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlockData() == 0
				)
			) {
				((SpoutBlock)block).getRelative(BlockFace.DOWN).setCustomBlock(null);
				((SpoutBlock)block).getRelative(BlockFace.DOWN).setType(Material.AIR);
		}
		
		//possibly broke bottom block need to destroy top block
		else if (
				((SpoutBlock)block).getCustomBlockData() == 0 &&
				(
					((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock() instanceof MachineBlock &&
					((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlockData() > 0
				)
			) {
				((SpoutBlock)block).getRelative(BlockFace.UP).setCustomBlock(null);
				((SpoutBlock)block).getRelative(BlockFace.UP).setType(Material.AIR);
		}
	}
	
	private ItemStack removeOne(ItemStack item) {
		item.setAmount(item.getAmount()-1);
		return item;
	}
	
	private boolean lastOne(ItemStack item) {
		if (item.getAmount() == 1) return true;
		return false;
	}
	
	private boolean notAir(ItemStack item) {
		if (item.getType().equals(Material.AIR)) return false;
		return true;
	}
	
	private void eject(Block block, ItemStack item) {
		Location loc = block.getLocation();
		//loc.setX(loc.getX()+0.5);
		//loc.setZ(loc.getZ()+0.5);
		loc.setY(loc.getY()+1.0);
		block.getWorld().dropItem(loc, item);
	}
	
	private void abortEject(MachineEvent event) {
		if (notAir(event.getPrimaryItem())) {
			eject(event.getBlock(), event.getPrimaryItem());
		}
		if (notAir(event.getAdditionItem())) {
			eject(event.getBlock(), event.getAdditionItem());
		}
	}
	 /*
	private Location getParticleLocation(Block block) {
		Location loc = block.getLocation();
		loc.setX(loc.getX()+0.5);
		loc.setY(loc.getY()+1.0);
		loc.setZ(loc.getZ()+0.5);
		
		return loc;
	}
	*/
	
	/**
	 * Yes. I know. this is absolutely horrible. shame on me. bad!
	 */
	private ItemStack getBlankDiscByDyeColor(byte data, int amountOfDiscs) {
		Debug.debug("MachineListener:getBlankDiscByDyeColr: ", data, amountOfDiscs);
		
		if (data == 0) return new SpoutItemStack(Items.blankDiscBlack, amountOfDiscs);
		if (data == 4) return new SpoutItemStack(Items.blankDiscBlue, amountOfDiscs);
		if (data == 3) return new SpoutItemStack(Items.blankDiscBrown, amountOfDiscs);
		if (data == 6) return new SpoutItemStack(Items.blankDiscCyan, amountOfDiscs);
		if (data == 8) return new SpoutItemStack(Items.blankDiscGray, amountOfDiscs);
		if (data == 2) return new SpoutItemStack(Items.blankDiscGreen, amountOfDiscs);
		if (data == 12) return new SpoutItemStack(Items.blankDiscLightBlue, amountOfDiscs);
		if (data == 10) return new SpoutItemStack(Items.blankDiscLime, amountOfDiscs);
		if (data == 13) return new SpoutItemStack(Items.blankDiscMagenta, amountOfDiscs);
		if (data == 14) return new SpoutItemStack(Items.blankDiscOrange, amountOfDiscs);
		if (data == 9) return new SpoutItemStack(Items.blankDiscPink, amountOfDiscs);
		if (data == 5) return new SpoutItemStack(Items.blankDiscPurple, amountOfDiscs);
		if (data == 1) return new SpoutItemStack(Items.blankDiscRed, amountOfDiscs);
		if (data == 7) return new SpoutItemStack(Items.blankDiscLightGray, amountOfDiscs);
		if (data == 11) return new SpoutItemStack(Items.blankDiscYellow, amountOfDiscs);
		return new SpoutItemStack(Items.blankDiscWhite, amountOfDiscs);
	}
	
	/**
	 * Yes. also horrible. but im feelin really lazy right now
	 */
	private DiscColor getDiscColor(byte data ) {
		
		if (data ==  0) return DiscColor.BLACK;
		if (data ==  4) return DiscColor.BLUE;
		if (data ==  3) return DiscColor.BROWN;
		if (data ==  6) return DiscColor.CYAN;
		if (data ==  8) return DiscColor.GRAY;
		if (data ==  2) return DiscColor.GREEN;
		if (data ==  12) return DiscColor.LIGHTBLUE;
		if (data ==  10) return DiscColor.LIME;
		if (data ==  13) return DiscColor.MAGENTA;
		if (data ==  14) return DiscColor.ORANGE;
		if (data ==  9) return DiscColor.PINK;
		if (data ==  5) return DiscColor.PURPLE;
		if (data ==  1) return DiscColor.RED;
		if (data ==  7) return DiscColor.LIGHTGRAY;
		if (data ==  11) return DiscColor.YELLOW;
		return DiscColor.WHITE;
	}
}
