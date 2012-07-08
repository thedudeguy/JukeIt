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
package cc.thedudeguy.jukebukkit.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.thedudeguy.jukebukkit.materials.blocks.designs.RPNeedle;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="jb_rp_data")
public class RecordPlayerData {

	@Id
    private int id;
	
	@NotNull
    private double x;

    @NotNull
    private double y;

    @NotNull
    private double z;

    @NotEmpty
    private String worldName;
    
    @NotNull
    private int needleType;
    
    private String discKey;
    
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public int getNeedleType() {
		return needleType;
	}
	
	public void setNeedleType(int needleType) {
		this.needleType = needleType;
	}
	
	public void setNeedleType(RPNeedle needleType) {
		this.setNeedleType(needleType.id());
	}

	public String getDiscKey() {
		return discKey;
	}

	public void setDiscKey(String discKey) {
		this.discKey = discKey;
	}
	
	public boolean hasDisc() {
		if (discKey == null) return false;
		if (discKey.equals("")) return false;
		return true;
	}
}
