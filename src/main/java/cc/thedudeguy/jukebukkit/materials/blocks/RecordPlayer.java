package cc.thedudeguy.jukebukkit.materials.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.database.RecordPlayerBlockDesigns;
import cc.thedudeguy.jukebukkit.materials.blocks.designs.RecordPlayerDesign;

public class RecordPlayer extends GenericCustomBlock {
	
	public static HashMap<String, RecordPlayer> subBlocks = new HashMap<String, RecordPlayer>();
	
	public static class RecordPlayerSubBlock extends RecordPlayer {
		
		public RecordPlayerSubBlock(RecordPlayerDesign rpDesign) {
			super(rpDesign.getDesignTypeId());
			
			this.setName("Record Player SubBlock (Do Not Use)");
			this.setBlockDesign(rpDesign);
		}
	}
	
	public static RecordPlayer getSubBlock(int needle, int disc, int indicator) {
		
		RecordPlayerDesign rpDesign = new RecordPlayerDesign(needle, disc, indicator);
		
		if (!subBlocks.containsKey(rpDesign.getDesignTypeId())) {
			subBlocks.put(rpDesign.getDesignTypeId(), new RecordPlayerSubBlock(rpDesign));
			
			//save the combo to the db if it doesnt already exist.
			RecordPlayerBlockDesigns rpbd = JukeBukkit.instance.getDatabase().find(RecordPlayerBlockDesigns.class)
					.where()
						.eq("needle", rpDesign.getNeedle())
						.eq("disc", rpDesign.getDisc())
						.eq("indicator", rpDesign.getIndicator())
					.findUnique();
			if (rpbd == null) {
				rpbd = new RecordPlayerBlockDesigns();
				rpbd.setDisc(rpDesign.getDisc());
				rpbd.setNeedle(rpDesign.getNeedle());
				rpbd.setIndicator(rpDesign.getIndicator());
				JukeBukkit.instance.getDatabase().save(rpbd);
			}
		} 
		
		return subBlocks.get(rpDesign.getDesignTypeId());
	}
	
	
	public RecordPlayer() {
		super(JukeBukkit.instance, "Record Player", 3);
		
		RecordPlayerDesign rpDesign = new RecordPlayerDesign();
		this.setBlockDesign(rpDesign);
		
		this.setHardness(MaterialData.wood.getHardness());
		this.setLightLevel(1);
		
		//store this into the hashmap since it will always be the defaul
		subBlocks.put(rpDesign.getDesignTypeId(), this);
		
		//Bukkit.getLogger().log(Level.INFO, rpDesign.getDesignTypeId());
		
		initDesigns();
	}
	
	public RecordPlayer(String nameId) {
		super(JukeBukkit.instance, nameId, 3);
	}
	
	public boolean onBlockInteract(org.bukkit.World world, int x, int y, int z, SpoutPlayer player) {
		
		player.sendMessage("Yeah Baby");
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		block.setCustomBlock(getSubBlock(RecordPlayerDesign.NEEDLE_WOOD_FLINT, RecordPlayerDesign.DISC_BLUE, RecordPlayerDesign.INDICATOR_GREEN));
		return false;
	}
	
	private void initDesigns() {
		 List<RecordPlayerBlockDesigns> rpbd = JukeBukkit.instance.getDatabase().find(RecordPlayerBlockDesigns.class).findList();
		 
		 if (rpbd.isEmpty()) {
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] No RecordPlayer Designs to load.");
		 } else {
			 int count = 0;
			 for (RecordPlayerBlockDesigns record : rpbd) {
				 RecordPlayerDesign rpDesign = new RecordPlayerDesign(record.getNeedle(), record.getDisc(), record.getIndicator());
				 subBlocks.put(rpDesign.getDesignTypeId(), new RecordPlayerSubBlock(rpDesign));
				 count++;
	         }
			 Bukkit.getLogger().log(Level.INFO, "[JukeBukkit] Initialized "+ String.valueOf(count) +" RecordPlayer Designs.");
		 }
		 
	}
	
}
