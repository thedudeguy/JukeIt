package cc.thedudeguy.jukebukkit.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.block.SpoutBlock;

import cc.thedudeguy.jukebukkit.events.SpeakerWirePlaceEvent;
import cc.thedudeguy.jukebukkit.materials.blocks.Blocks;
import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.util.Debug;

public class SpeakerWireListener implements Listener {

	@EventHandler
	public void onPlace(SpeakerWirePlaceEvent event) {
		Debug.debug(event.getPlayer(), "Placing Speaker Wire");
		
		List<BlockFace> faceList = Arrays.asList(BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
		
		TreeMap<BlockFace, SpoutBlock> wires = new TreeMap<BlockFace, SpoutBlock>();
		
		//collect a list of surrounding blocks who are also speaker wire, and have atleast one open side.
		for (BlockFace face : faceList) {
			SpoutBlock relBlock = (SpoutBlock)event.getBlock().getRelative(face);
			if (relBlock.getCustomBlock() != null && relBlock.getCustomBlock() instanceof SpeakerWireBlock) {
				Debug.debug(event.getPlayer(), "Block at ", face, " is speakerwireblock");
				
				if ( ((SpeakerWireBlock)relBlock.getCustomBlock()).hasOpenEnd(relBlock) )
				{
					//if there is a maximum of 2, we dont need anymore, so go ahead and skip
					if (wires.size() < 2) {
						wires.put(face, relBlock);
					}
				}
			}
			
		}
		
		Debug.debug(event.getPlayer(), "Surrounding Wires with open ends: ", wires.size());
		Debug.debug(event.getPlayer(), "Placing Wire");
		
		
		event.getBlock().setCustomBlock(Blocks.speakerWireBlockEastWest);
		
		switch(wires.size()) {
		case 0:
			//do nothing;
			break;
		case 1:
			//only connect to one;
			if (
					wires.firstEntry().getKey().equals(BlockFace.EAST) ||
					wires.firstEntry().getKey().equals(BlockFace.WEST)
					) {
				event.getBlock().setCustomBlock(Blocks.speakerWireBlockEastWest);
			} else if (
					wires.firstEntry().getKey().equals(BlockFace.NORTH) ||
					wires.firstEntry().getKey().equals(BlockFace.SOUTH)
					) {
				event.getBlock().setCustomBlock(Blocks.speakerWireBlockNorthSouth);
			}
			break;
		case 2:
			//connecting to 2 wires
			break;
		}
		
		/*
		SpeakerWireBlock wire = (SpeakerWireBlock) event.getBlock().getCustomBlock();
		
		*/
	}
}
