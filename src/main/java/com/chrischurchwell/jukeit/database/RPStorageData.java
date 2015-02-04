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
package com.chrischurchwell.jukeit.database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;
import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.material.items.BurnedDisc;
import com.chrischurchwell.jukeit.util.DiscUtil;

@Entity()
@Table(name="jb_rp_storage")
public class RPStorageData {
	
	public static RPStorageData removeDisc(Block block) {
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		rpdata.setColor(0);
		rpdata.setAuthor("");
		rpdata.setTitle("");
		rpdata.setUrl("");
		
		JukeIt.getInstance().getDatabase().save(rpdata);
		
		return rpdata;
	}
	
	public static RPStorageData setDisc(Block block, ItemStack discIs) {
		RPStorageData rpdata = RPStorageData.getOrCreateEntry(block);
		
		rpdata.setColor(((BurnedDisc)(new SpoutItemStack(discIs)).getMaterial()).getColor().identifier());
		rpdata.setAuthor("");
		rpdata.setTitle(DiscUtil.getLabel(discIs));
		rpdata.setUrl(DiscUtil.decodeDisc(discIs));
		
		JukeIt.getInstance().getDatabase().save(rpdata);
		
		return rpdata;
	}
	
	public static void deleteEntries(Block block) {
		//delete ALL data associated to this location, just incase somehow multiples got into the database this will take care of that.
		List<RPStorageData> rpdall = JukeIt.getInstance().getDatabase().find(RPStorageData.class)
			.where()
				.eq("x", block.getX())
				.eq("y", block.getY())
				.eq("z", block.getZ())
				.ieq("world", block.getWorld().getName())
			.findList();
		if (!rpdall.isEmpty()) {
			JukeIt.getInstance().getDatabase().delete(rpdall);
		}
	}
	
	public static RPStorageData getOrCreateEntry(Block block) {
		
		RPStorageData entry = JukeIt.getInstance().getDatabase().find(RPStorageData.class)
			.where()
				.eq("x", block.getX())
				.eq("y", block.getY())
				.eq("z", block.getZ())
				.ieq("world", block.getWorld().getName())
			.findUnique();
		
		if (entry == null) {
			
			entry = new RPStorageData();
			entry.setAuthor("");
			entry.setTitle("");
			entry.setColor(0);
			entry.setNeedle(0);
			entry.setUrl("");
			
			entry.setWorld(block.getWorld().getName());
			entry.setX(block.getX());
			entry.setY(block.getY());
			entry.setZ(block.getZ());
		}
		
		return entry;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	private String world;
	
	@NotNull
	private int x;
	
	@NotNull
	private int y;

	@NotNull
	private int z;
	
	private String url;
	
	private String title;
	
	private String author;
	
	private int color;
	
	@NotNull
	private int needle;

	public boolean hasDisc() {
		if (color > 0) return true;
		return false;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getNeedle() {
		return needle;
	}

	public void setNeedle(int needle) {
		this.needle = needle;
	}

}
