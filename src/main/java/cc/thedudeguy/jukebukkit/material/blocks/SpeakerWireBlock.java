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

import org.bukkit.block.BlockFace;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.material.Items;
import cc.thedudeguy.jukebukkit.material.blocks.designs.WireData;
import cc.thedudeguy.jukebukkit.util.Debug;

public class SpeakerWireBlock extends GenericCustomBlock {
	
	public SpeakerWireBlock() {
		super(JukeBukkit.getInstance(), "Speaker Wire Block", 36);
		this.setName("Speaker Wire Block");
		this.setItemDrop(new SpoutItemStack(Items.speakerWire));
		this.setHardness(0.1F);
		
		//load designs.
		for (WireData w : WireData.values()) {
			this.setBlockDesign(w.getDesign(), w.getTypeId());
		}
		
	}
	
	public WireData getWireData(SpoutBlock block) {
		return  WireData.getByTypeId(block.getCustomBlockData());
	}
	
	public boolean hasOpenEnd(SpoutBlock block) {
		Debug.debug("Custom Block Data: ", block.getCustomBlockData());
		WireData wire = getWireData(block);
		if (!this.isFaceConnected(block, wire.getEnd1())) return true;
		if (!this.isFaceConnected(block, wire.getEnd2())) return true;
		return false;
	}
	
	public boolean isFaceConnected(SpoutBlock block, BlockFace face) {
		
		if (
				!(((SpoutBlock)block.getRelative(face)).getCustomBlock() instanceof SpeakerWireBlock)
				) {
			return false;
		}
		if (face.equals(BlockFace.NORTH)) {
			if (
					
					getWireData(block.getRelative(BlockFace.NORTH)).getTypeId() == WireData.SOUTHtoNORTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.NORTH)).getTypeId() == WireData.SOUTHtoEAST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.NORTH)).getTypeId() == WireData.SOUTHtoWEST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.NORTH)).getTypeId() == WireData.SOUTHtoDOWN.getTypeId() ||
					getWireData(block.getRelative(BlockFace.NORTH)).getTypeId() == WireData.SOUTHtoUP.getTypeId()
					) {
				return true;
			}
			
		}
		
		if (face.equals(BlockFace.EAST)) {
			if (
					getWireData(block.getRelative(BlockFace.EAST)).getTypeId() == WireData.WESTtoEAST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.EAST)).getTypeId() == WireData.WESTtoNORTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.EAST)).getTypeId() == WireData.WESTtoSOUTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.EAST)).getTypeId() == WireData.WESTtoUP.getTypeId() ||
					getWireData(block.getRelative(BlockFace.EAST)).getTypeId() == WireData.WESTtoDOWN.getTypeId()
					) {
				return true;
			}
			
		} 
		
		if (face.equals(BlockFace.SOUTH)) {
			if (
					getWireData(block.getRelative(BlockFace.SOUTH)).getTypeId() == WireData.NORTHtoEAST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.SOUTH)).getTypeId() == WireData.NORTHtoSOUTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.SOUTH)).getTypeId() == WireData.NORTHtoWEST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.SOUTH)).getTypeId() == WireData.NORTHtoDOWN.getTypeId() ||
					getWireData(block.getRelative(BlockFace.SOUTH)).getTypeId() == WireData.NORTHtoUP.getTypeId()
					) {
				return true;
			}
			
		} 
		
		if (face.equals(BlockFace.WEST)) {
			if (
					getWireData(block.getRelative(BlockFace.WEST)).getTypeId() == WireData.EASTtoNORTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.WEST)).getTypeId() == WireData.EASTtoSOUTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.WEST)).getTypeId() == WireData.EASTtoWEST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.WEST)).getTypeId() == WireData.EASTtoDOWN.getTypeId() ||
					getWireData(block.getRelative(BlockFace.WEST)).getTypeId() == WireData.EASTtoUP.getTypeId()
					) {
				return true;
			}
			
		}
		
		if (face.equals(BlockFace.UP)) {
			if (
					getWireData(block.getRelative(BlockFace.UP)).getTypeId() == WireData.DOWNtoEAST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.UP)).getTypeId() == WireData.DOWNtoNORTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.UP)).getTypeId() == WireData.DOWNtoSOUTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.UP)).getTypeId() == WireData.DOWNtoUP.getTypeId() ||
					getWireData(block.getRelative(BlockFace.UP)).getTypeId() == WireData.DOWNtoWEST.getTypeId()
					) {
				return true;
			}
			
		}
		
		if (face.equals(BlockFace.DOWN)) {
			if (
					getWireData(block.getRelative(BlockFace.DOWN)).getTypeId() == WireData.UPtoDOWN.getTypeId() ||
					getWireData(block.getRelative(BlockFace.DOWN)).getTypeId() == WireData.UPtoEAST.getTypeId() ||
					getWireData(block.getRelative(BlockFace.DOWN)).getTypeId() == WireData.UPtoNORTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.DOWN)).getTypeId() == WireData.UPtoSOUTH.getTypeId() ||
					getWireData(block.getRelative(BlockFace.DOWN)).getTypeId() == WireData.UPtoWEST.getTypeId()
					) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
}
