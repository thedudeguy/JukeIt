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
package cc.thedudeguy.jukebukkit.materials.blocks.designs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.design.GenericBlockDesign;

public enum WireData {
	EASTtoWEST(		0, 	BlockFace.EAST, BlockFace.WEST, SpeakerWireStraightDesign.class,	90),
	WESTtoEAST(		0, 	BlockFace.WEST, BlockFace.EAST, SpeakerWireStraightDesign.class,	90),
	
	NORTHtoSOUTH(	1, 	BlockFace.NORTH, BlockFace.SOUTH, SpeakerWireStraightDesign.class,	0),
	SOUTHtoNORTH(	1, 	BlockFace.SOUTH, BlockFace.NORTH, SpeakerWireStraightDesign.class,	0),
	
	NORTHtoEAST(	2, 	BlockFace.NORTH, BlockFace.EAST, SpeakerWireTurnDesign.class,		270),
	EASTtoNORTH(	2, 	BlockFace.EAST, BlockFace.NORTH, SpeakerWireTurnDesign.class,		270),
	
	EASTtoSOUTH(	3, 	BlockFace.EAST, BlockFace.SOUTH, SpeakerWireTurnDesign.class,		0),
	SOUTHtoEAST(	3, 	BlockFace.SOUTH, BlockFace.EAST, SpeakerWireTurnDesign.class,		0),
	
	SOUTHtoWEST(	4, 	BlockFace.SOUTH, BlockFace.WEST, SpeakerWireTurnDesign.class,		90),
	WESTtoSOUTH(	4, 	BlockFace.WEST, BlockFace.SOUTH, SpeakerWireTurnDesign.class,		90),
	
	WESTtoNORTH(	5, 	BlockFace.WEST, BlockFace.NORTH, SpeakerWireTurnDesign.class,		180),
	NORTHtoWEST(	5, 	BlockFace.NORTH, BlockFace.WEST, SpeakerWireTurnDesign.class,		180),
	
	UPtoDOWN(		6, 	BlockFace.UP, BlockFace.DOWN, SpeakerWireStraightDesign.class,	0,		0,		90,	-0.46875F,	-0.46875F,	0),
	DOWNtoUP(		6,	BlockFace.DOWN, BlockFace.UP, SpeakerWireStraightDesign.class,	0,		0,		90,	-0.46875F,	-0.46875F,	0),
	
	EASTtoUP(		7,	BlockFace.EAST, BlockFace.UP, SpeakerWireTurnDesign.class,		270,	270,	0,	0.46875F,	-0.46875F,	0),
	UPtoEAST(		7,	BlockFace.UP, BlockFace.EAST, SpeakerWireTurnDesign.class,		270,	270,	0,	0.46875F,	-0.46875F,	0),
	
	WESTtoUP(		8,	BlockFace.WEST, BlockFace.UP, SpeakerWireTurnDesign.class,		270,	90,		0,	-0.46875F,	-0.46875F,	0),
	UPtoWEST(		8,	BlockFace.UP, BlockFace.WEST, SpeakerWireTurnDesign.class,		270,	90,		0,	-0.46875F,	-0.46875F,	0),
	
	NORTHtoUP(		9,	BlockFace.NORTH, BlockFace.UP, SpeakerWireTurnDesign.class,		270,	180,	0,	0,			-0.46875F,	-0.46875F),
	UPtoNORTH(		9,	BlockFace.UP, BlockFace.NORTH, SpeakerWireTurnDesign.class,		270,	180,	0,	0,			-0.46875F,	-0.46875F),
	
	SOUTHtoUP(		10,	BlockFace.SOUTH, BlockFace.UP, SpeakerWireTurnDesign.class,		270,	0,		0,	0,			-0.46875F,	0.46875F),
	UPtoSOUTH(		10, BlockFace.UP, BlockFace.SOUTH, SpeakerWireTurnDesign.class,		270,	0,		0,	0,			-0.46875F,	0.46875F),
	
	WESTtoDOWN(		11, BlockFace.WEST, BlockFace.DOWN, SpeakerWireTurnDesign.class,		90,		90,		0,	0.46875F,	-0.46875F,	0),
	DOWNtoWEST(		11, BlockFace.DOWN, BlockFace.WEST, SpeakerWireTurnDesign.class,		90,		90,		0,	0.46875F,	-0.46875F,	0),
	
	EASTtoDOWN(		12, BlockFace.EAST, BlockFace.DOWN, SpeakerWireTurnDesign.class,		90,		270,	0,	-0.46875F,	-0.46875F,	0),
	DOWNtoEAST(		12, BlockFace.DOWN, BlockFace.EAST, SpeakerWireTurnDesign.class,		90,		270,	0,	-0.46875F,	-0.46875F,	0),
	
	NORTHtoDOWN(	13, BlockFace.NORTH, BlockFace.DOWN, SpeakerWireTurnDesign.class,		90,		180,	0,	0,			-0.46875F,	0.46875F),
	DOWNtoNORTH(	13, BlockFace.DOWN, BlockFace.NORTH, SpeakerWireTurnDesign.class,		90,		180,	0,	0,			-0.46875F,	0.46875F),
	
	SOUTHtoDOWN(	14, BlockFace.SOUTH, BlockFace.DOWN, SpeakerWireTurnDesign.class,		90,		0,		0,	0,			-0.46875F,	-0.46875F),
	DOWNtoSOUTH(	14, BlockFace.DOWN, BlockFace.SOUTH, SpeakerWireTurnDesign.class,		90,		0,		0,	0,			-0.46875F,	-0.46875F);
	
	private final int typeId;
	private final Class<? extends GenericBlockDesign> typeClass;
	private final int rotationX;
	private final int rotationY;
	private final int rotationZ;
	private final float moveX;
	private final float moveY;
	private final float moveZ;
	private final BlockFace end1;
	private final BlockFace end2;
	
	WireData(int typeId, BlockFace end1, BlockFace end2, Class<? extends GenericBlockDesign> typeClass, int rotationY) {
		this(typeId, end1, end2, typeClass, 0, rotationY, 0, 0, 0, 0);
	}
	
	WireData(int typeId, BlockFace end1, BlockFace end2, Class<? extends GenericBlockDesign> typeClass, int rotationX, int rotationY, int rotationZ, float moveX, float moveY, float moveZ) {
		this.typeId = typeId;
		this.typeClass = typeClass;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		this.moveX = moveX;
		this.moveY = moveY;
		this.moveZ = moveZ;
		this.end1 = end1;
		this.end2 = end2;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public BlockFace getEnd1() {
		return end1;
	}
	
	public BlockFace getEnd2() {
		return end2;
	}
	
	public static WireData getByTypeId(int typeId) {
		for (WireData d : WireData.values()) {
			if (d.getTypeId() == typeId) return d;
		}
		
		//throw exception instead?
		return null;
	}
	
	public GenericBlockDesign getDesign() {
		try {
			Constructor<? extends GenericBlockDesign> ctor = typeClass.getConstructor(int.class, int.class, int.class, float.class, float.class, float.class);
			return ctor.newInstance(rotationX, rotationY, rotationZ, moveX, moveY, moveZ);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
