package cc.thedudeguy.jukebukkit.listeners;

import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.events.LabelMachineCompleteEvent;
import cc.thedudeguy.jukebukkit.events.LabelMachineProcessEvent;
import cc.thedudeguy.jukebukkit.events.LabelMachineStartEvent;
import cc.thedudeguy.jukebukkit.materials.Blocks;
import cc.thedudeguy.jukebukkit.materials.blocks.LabelerBlock;
import cc.thedudeguy.jukebukkit.util.Debug;
import cc.thedudeguy.jukebukkit.util.Sound;

public class DiscLabelerListener implements Listener {

	public abstract class LabelMachineRunnable implements Runnable {
		SpoutBlock block;
		int taskToStop;
		public LabelMachineRunnable(SpoutBlock block) {
			this.block = block;
		}
		public LabelMachineRunnable(int taskIdToStop) {
			this.taskToStop = taskIdToStop;
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (
				((SpoutBlock)event.getBlock()).getCustomBlock() == null ||
				!(((SpoutBlock)event.getBlock()).getCustomBlock() instanceof LabelerBlock)
				){
			return;
			
		}
		
		if (
				!event.getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR)
			) {
			event.getBlock().breakNaturally(new SpoutItemStack(Blocks.discLabeler, 1));
			return;
		}
		
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.discLabeler, (byte)1);
	}
	
	@EventHandler
	public void onStart(LabelMachineStartEvent event) {
		Debug.debug("LabelMachineStartEvent heard - starting up. ");
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.discLabeler, (byte)2);
		
		try {
			Sound sound = new Sound(new URL("http://dev.bukkit.org/media/attachments/28/192/labelmachine.ogg"));
			sound.setRange(8);
			sound.play(event.getBlock().getLocation());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Particle particle = new Particle(ParticleType.LARGESMOKE, getParticleLocation(event.getBlock()), new Vector(0,0,0));
		particle.setAmount(50);
		particle.setMaxAge(20);
		particle.spawn();
		
		JukeBukkit.instance.getServer().getScheduler().scheduleSyncDelayedTask(JukeBukkit.instance, new LabelMachineRunnable(event.getBlock()) {
			public void run() {
				LabelMachineProcessEvent pEvent = new LabelMachineProcessEvent(block);
				Bukkit.getServer().getPluginManager().callEvent(pEvent);
			}
		}, 10L);
	}
	
	@EventHandler
	public void onProcess(LabelMachineProcessEvent event) {
		Debug.debug("LabelMachineProcessEvent heard - processing graphics. bzzrrt! whirr!! chjkchjk ");
		
		int taskID = JukeBukkit.instance.getServer().getScheduler().scheduleSyncRepeatingTask(JukeBukkit.instance, new LabelMachineRunnable(event.getBlock()) {
			public void run() {
				Particle particle = new Particle(ParticleType.SMOKE, getParticleLocation(block), new Vector(0,0.2,0));
				particle.setMaxAge(10);
				particle.setAmount(5);
				particle.spawn();
			}
		}, 0, 5L);
		
		JukeBukkit.instance.getServer().getScheduler().scheduleSyncDelayedTask(JukeBukkit.instance, new LabelMachineRunnable(taskID) {
			public void run() {
				Bukkit.getScheduler().cancelTask(taskToStop);
			}
		}, 100L);
		
		JukeBukkit.instance.getServer().getScheduler().scheduleSyncDelayedTask(JukeBukkit.instance, new LabelMachineRunnable(event.getBlock()) {
			public void run() {
				LabelMachineCompleteEvent pEvent = new LabelMachineCompleteEvent(block);
				Bukkit.getServer().getPluginManager().callEvent(pEvent);
			}
		}, 110L);
	}
	
	@EventHandler
	public void onComplete(LabelMachineCompleteEvent event) {
		Debug.debug("LabelMachineCompleteEvent heard - its magic.");
		SpoutManager.getMaterialManager().overrideBlock(event.getBlock().getRelative(BlockFace.UP), Blocks.discLabeler, (byte)1);
		
		Particle particle = new Particle(ParticleType.CLOUD, getParticleLocation(event.getBlock()), new Vector(0,0,0));
		particle.setGravity(0);
		particle.setScale(0.5F);
		particle.setAmount(100);
		particle.spawn();
		
	}
	
	@EventHandler
	public void onPlace(BlockBreakEvent event) {
		if (
				((SpoutBlock)event.getBlock()).getCustomBlock() == null ||
				!(((SpoutBlock)event.getBlock()).getCustomBlock() instanceof LabelerBlock)
				){
			return;
			
		}
		
		//possibly broken top block need to destroy bottom block
		if (
				((SpoutBlock)event.getBlock()).getCustomBlockData() > 0 &&
				(
					((SpoutBlock)event.getBlock().getRelative(BlockFace.DOWN)).getCustomBlock() instanceof LabelerBlock &&
					((SpoutBlock)event.getBlock().getRelative(BlockFace.DOWN)).getCustomBlockData() == 0
				)
			) {
				event.getBlock().getRelative(BlockFace.DOWN).breakNaturally(null);
				return;
		}
		
		//possibly broke bottom block need to destroy top block
		if (
				((SpoutBlock)event.getBlock()).getCustomBlockData() == 0 &&
				(
					((SpoutBlock)event.getBlock().getRelative(BlockFace.UP)).getCustomBlock() instanceof LabelerBlock &&
					((SpoutBlock)event.getBlock().getRelative(BlockFace.UP)).getCustomBlockData() > 0
				)
			) {
				event.getBlock().getRelative(BlockFace.UP).breakNaturally(null);
		}
	}
	
	private Location getParticleLocation(Block block) {
		Location loc = block.getLocation();
		loc.setX(loc.getX()+0.5);
		loc.setY(loc.getY()+1.0);
		loc.setZ(loc.getZ()+0.5);
		
		return loc;
	}
}
