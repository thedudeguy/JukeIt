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

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.item.GenericCustomTool;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.SoundEffect;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.material.blocks.MachineRecipe;
import com.chrischurchwell.jukeit.material.blocks.MachineRecipe.RecipeDiscType;
import com.chrischurchwell.jukeit.util.Debug;


public class DiscOnAStick extends GenericCustomTool implements Listener {
	
	public DiscOnAStick() {
		super(JukeIt.getInstance(), "Disc on a Stick", "disconastick.png");
		this.setMaxDurability((short)100);
		this.setStackable(false);
		
		MachineRecipe.addMachineRecipe(new MachineRecipe(RecipeDiscType.ANY, Material.STICK, new SpoutItemStack(this)));
		Bukkit.getServer().getPluginManager().registerEvents(this, JukeIt.getInstance());
	}
	
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		Debug.debug(player, "DOAS - Durability: ", getDurability(player.getItemInHand()));
		
		return true;
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (
				!event.getCause().equals(DamageCause.ENTITY_ATTACK) ||
				!(event.getDamager() instanceof Player)
			) {
			return;
		}
		ItemStack is = ((Player)event.getDamager()).getItemInHand();
		if ((new SpoutItemStack(is)).getMaterial() instanceof DiscOnAStick) {
			Random rGen = new Random();
			
			//random durability hit from 0 - 100
			int duraDmg = rGen.nextInt(101);
			//random damage from 0 - 5
			int healDmg = rGen.nextInt(6);
			
			int newDura = getDurability(is) + duraDmg;
			
			//item breakage
			if (newDura > this.getMaxDurability()) {
				if (((Player)event.getDamager()).getItemInHand().getAmount() > 1) {
					((Player)event.getDamager()).getItemInHand().setAmount(((Player)event.getDamager()).getItemInHand().getAmount() -1);
					setDurability(is, (short)0);
				} else {
					((Player)event.getDamager()).setItemInHand(null);
				}
				SpoutManager.getSoundManager().playGlobalSoundEffect(SoundEffect.BREAK, ((Player)event.getDamager()).getLocation(), 8, 100);
				event.setCancelled(true);
				return;
			}
			
			setDurability(is, (short)newDura);
			event.setDamage(healDmg);
			
			Debug.debug("-- Tool Hit --");
			Debug.debug("Damage: ", event.getDamage());
			Debug.debug("Dura Damage: ", duraDmg);
			Debug.debug("New Dura: ", getDurability(is));
			
		}
	}
}
