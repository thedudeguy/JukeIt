package cc.thedudeguy.jukebukkit.listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.events.SpeakerWirePlaceEvent;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.WireData;
import cc.thedudeguy.jukebukkit.util.Debug;

public class SpeakerWireListener implements Listener {
	
	public HashMap<BlockFace, SpoutBlock> getAvailableWires(SpoutBlock block) {
		List<BlockFace> faceList = Arrays.asList(BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST,  BlockFace.UP, BlockFace.DOWN);
		
		HashMap<BlockFace, SpoutBlock> wires = new HashMap<BlockFace, SpoutBlock>();
		
		//collect a list of surrounding blocks who are also speaker wire, and have atleast one open side.
		for (BlockFace face : faceList) {
			
			SpoutBlock relBlock = (SpoutBlock)block.getRelative(face);
			
			if (relBlock.getCustomBlock() != null && relBlock.getCustomBlock() instanceof SpeakerWireBlock) {
				Debug.debug("Block at ", face, " is speakerwireblock-new");
				if ( ((SpeakerWireBlock)relBlock.getCustomBlock()).hasOpenEnd(relBlock) )
				{
					//if there is a maximum of 2, we dont need anymore, so go ahead and skip
					if (wires.size() < 2) {
						wires.put(face, relBlock);
					}
				}
			}
			/*
			if (relBlock.getCustomBlock() != null && relBlock.getCustomBlock() instanceof SpeakerWireBlock) {
				Debug.debug("Block at ", face, " is speakerwireblock");
				
				if ( ((SpeakerWireBlock)relBlock.getCustomBlock()).hasOpenEnd(relBlock) )
				{
					//if there is a maximum of 2, we dont need anymore, so go ahead and skip
					if (wires.size() < 2) {
						wires.put(face, relBlock);
					}
				}
			}
			*/
			
		}
		
		return wires;
		
	}
	
	public BlockFace getConnectedFace(SpoutBlock block, BlockFace ignoreFace) {
		
		List<BlockFace> faceList = Arrays.asList(BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN);
		
		//collect a list of surrounding blocks who are also speaker wire, and have atleast one open side.
		for (BlockFace face : faceList) {
			
			if (!face.equals(ignoreFace)) {
				
				if ( ((SpeakerWireBlock)block.getCustomBlock()).isFaceConnected(block, face) ) return face;
				
			}
		}
		
		return null;
		
	}
	
	public void setBlockType(SpoutBlock wireBlock, HashMap<BlockFace, SpoutBlock> availableWires) {
		
		switch(availableWires.size()) {
		case 0:
			//do nothing;
			break;
		case 1:
			//only connect to one;
			if (
					availableWires.containsKey(BlockFace.EAST) ||
					availableWires.containsKey(BlockFace.WEST)
					) {
				
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockEastWest);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("EASTtoWEST").getTypeId());
			} else if (
					availableWires.containsKey(BlockFace.NORTH) ||
					availableWires.containsKey(BlockFace.SOUTH)
					) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockNorthSouth);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("NORTHtoSOUTH").getTypeId());
			}
			else if (
					availableWires.containsKey(BlockFace.UP) ||
					availableWires.containsKey(BlockFace.DOWN)
					) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockUpDown);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("UPtoDOWN").getTypeId());
			}
			break;
		case 2:
			//connecting to 2 wires
			if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.WEST)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockEastWest);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("EASTtoWEST").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.SOUTH)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockNorthSouth);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("NORTHtoSOUTH").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.EAST)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockNorthEast);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("NORTHtoEAST").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.SOUTH)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockEastSouth);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("EASTtoSOUTH").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.SOUTH) && availableWires.containsKey(BlockFace.WEST)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockSouthWest);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("SOUTHtoWEST").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.WEST) && availableWires.containsKey(BlockFace.NORTH)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockWestNorth);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("WESTtoNORTH").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.UP) && availableWires.containsKey(BlockFace.DOWN)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockUpDown);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("UPtoDOWN").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.UP)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockEastUp);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("EASTtoUP").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.WEST) && availableWires.containsKey(BlockFace.UP)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockWestUp);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("WESTtoUP").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.UP)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockNorthUp);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("NORTHtoUP").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.SOUTH) && availableWires.containsKey(BlockFace.UP)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockSouthUp);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("SOUTHtoUP").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.DOWN)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockEastDown);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("EASTtoDOWN").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.WEST) && availableWires.containsKey(BlockFace.DOWN)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockWestDown);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("WESTtoDOWN").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.DOWN)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockNorthDown);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("NORTHtoDOWN").getTypeId());
				
			} else if (availableWires.containsKey(BlockFace.SOUTH) && availableWires.containsKey(BlockFace.DOWN)) {
				//wireBlock.setCustomBlock(Blocks.speakerWireBlockSouthDown);
				SpoutManager.getMaterialManager().overrideBlock(wireBlock, Blocks.speakerWireBlock, (byte)WireData.valueOf("SOUTHtoDOWN").getTypeId());
				
			}
			break;
		}
		
	}
	
	@EventHandler
	public void onPlace(SpeakerWirePlaceEvent event) {
		Debug.debug(event.getPlayer(), "Placing Speaker Wire");
		
		HashMap<BlockFace, SpoutBlock> wires = getAvailableWires(event.getBlock());
		
		Debug.debug(event.getPlayer(), "Surrounding Wires with open ends: ", wires.size());
		Debug.debug(event.getPlayer(), "Placing Wire");
		
		event.getBlock().setCustomBlock(Blocks.speakerWireBlock);
		setBlockType((SpoutBlock)event.getBlock(), wires);
		
		//do the same for the wipres we connected to.
		//we know that every wire in this list has ATLEAST one available connection
		for ( Entry<BlockFace, SpoutBlock> item : wires.entrySet() ) {
			
			HashMap<BlockFace, SpoutBlock> connectTo = new HashMap<BlockFace, SpoutBlock>();
			//add our block.
			connectTo.put(item.getKey().getOppositeFace(), event.getBlock());
			
			BlockFace existingFace = getConnectedFace(item.getValue(), item.getKey().getOppositeFace());
			
			if (existingFace != null) {
				connectTo.put(existingFace, item.getValue());
			}
			
			setBlockType(item.getValue(), connectTo);
			
		}
		
	}
}
