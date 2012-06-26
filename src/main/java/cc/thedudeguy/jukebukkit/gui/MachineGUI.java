package cc.thedudeguy.jukebukkit.gui;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlot;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.widget.DiscSlot;
import cc.thedudeguy.jukebukkit.gui.widget.MachineLabelButton;
import cc.thedudeguy.jukebukkit.gui.widget.MachineStartButton;
import cc.thedudeguy.jukebukkit.gui.widget.PlayerInventorySlot;
import cc.thedudeguy.jukebukkit.materials.items.BurnedDisc;
import cc.thedudeguy.jukebukkit.util.Debug;

public class MachineGUI extends GenericPopup {
	
	protected GenericContainer labelContainer;
	
	private Player player;
	private Block block;
	
	private DiscSlot discSlot;
	private GenericSlot discAddSlot;
	
	public MachineGUI(Player player, Block block) {
		
		this.player = player;
		this.block = block;
		
		GenericTexture border = new GenericTexture("machinegui.png");
		border.setX(125).setY(20);
		border.setPriority(RenderPriority.Highest);
		border.setWidth(176).setHeight(166);
		border.setFixed(true);
		border.setAnchor(WidgetAnchor.TOP_LEFT);
		
		discSlot = new DiscSlot();
		discSlot.setX(232).setY(69); //teeheehee
		discSlot.setWidth(16).setHeight(16);
		discSlot.setPriority(RenderPriority.Normal);
		discSlot.setFixed(true);
		discSlot.setAnchor(WidgetAnchor.TOP_LEFT);
		
		discAddSlot = new GenericSlot();
		discAddSlot.setX(178).setY(40);
		discAddSlot.setWidth(16).setHeight(16);
		discAddSlot.setPriority(RenderPriority.Normal);
		discAddSlot.setFixed(true);
		discAddSlot.setAnchor(WidgetAnchor.TOP_LEFT);
		
		// Select button
		MachineStartButton startButton = new MachineStartButton((SpoutPlayer)player, (SpoutBlock) block, discSlot, discAddSlot);
		startButton.setX(95).setY(195);
		startButton.setWidth(60).setHeight(20);
		startButton.setPriority(RenderPriority.Normal);
		startButton.setFixed(true);
		startButton.setAnchor(WidgetAnchor.TOP_LEFT);
		
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, border, discAddSlot, discSlot, startButton);
		
		Inventory inventory = player.getInventory();
		
		int xposition = 0;
		int yposition = 58;
		
		//i know theres some kind of cool math equation to do this, but i cant remember how to figure it out. doin it the cheap way
		for (int i = 0; i < 36; i++) {
			
			PlayerInventorySlot slot = new PlayerInventorySlot(player, i);
			if (xposition == 9) xposition = 0;
			if (i > 8) yposition = 0;
			if (i > 17) yposition = 18;
			if (i > 26) yposition = 36;
			
			slot.setY(104 + yposition);
			slot.setX(133 + (xposition*18));
			slot.setWidth(16).setHeight(16);
			slot.setPriority(RenderPriority.Normal);
			slot.setFixed(true);
			slot.setAnchor(WidgetAnchor.TOP_LEFT);
			slot.setItem(inventory.getItem(i));
			this.attachWidget(JukeBukkit.instance, slot);
			
			xposition++;
		}
	}
	
	protected void createLabelWriter() {
		
		if (this.labelContainer != null) {
			return;
		}
		
		labelContainer = new GenericContainer();
		labelContainer.setAnchor(WidgetAnchor.CENTER_CENTER);
		labelContainer.setPriority(RenderPriority.Low);
		
		GenericTexture paperTexture = new GenericTexture("paper.png");
		paperTexture.setPriority(RenderPriority.Lowest);
		paperTexture.setWidth(128).setHeight(64);
		paperTexture.setFixed(true);
		//paperTexture.setAnchor(WidgetAnchor.CENTER_CENTER);
		//paperTexture.setX(125).setY(20);
		labelContainer.addChild(paperTexture);
		
		GenericTextField labelInput = new GenericTextField();
		labelInput.setMaximumCharacters(500);
		labelInput.setHeight(15).setWidth(200);
		//labelInput.setY(25);
		//labelInput.setX(5);
		labelInput.setMaximumLines(1);
		labelInput.setFocus(true);
		labelInput.setPriority(RenderPriority.Lowest);
		labelInput.setFixed(true);
		labelContainer.addChild(labelInput);
		
		MachineLabelButton button = new MachineLabelButton((SpoutPlayer)player, (SpoutBlock) block, discSlot, discAddSlot, labelInput);
		//button.setY(45).setX(5);
		button.setWidth(75).setHeight(20);
		//button.setAnchor(WidgetAnchor.BOTTOM_CENTER);
		button.setPriority(RenderPriority.Lowest);
		button.setFixed(true);
		labelContainer.addChild(button);
		
		this.attachWidget(JukeBukkit.instance, labelContainer);
		
	}
	
	protected void hideLabelWriter() {
		if (this.labelContainer != null) {
			this.removeWidget(this.labelContainer);
			this.labelContainer = null;
		}
	}
	
	@Override
	public void onScreenClose(ScreenCloseEvent event) {
		Debug.debug("MachineGUI:onScreenClose");
		
		//if any items are in the slots we should give them back to the player.
		if (
				this.discSlot.getItem() != null &&
				!this.discSlot.getItem().getType().equals(Material.AIR)
				) {
			tossItem(event.getPlayer(), discSlot.getItem());
		}
		if (
				this.discAddSlot.getItem() != null &&
				!this.discAddSlot.getItem().getType().equals(Material.AIR)
				) {
			tossItem(event.getPlayer(), discAddSlot.getItem());
		}
	}
	
	@Override
	public void handleItemOnCursor(org.bukkit.inventory.ItemStack itemOnCursor) {
		if (
				itemOnCursor != null &&
				!itemOnCursor.getType().equals(Material.AIR)
				) {
			tossItem((SpoutPlayer) player, itemOnCursor);
		}
	}
	
	@Override
	public void onTick() {
		SpoutItemStack sItem = new SpoutItemStack(this.discSlot.getItem());
		if (
				this.labelContainer == null &&
				this.discAddSlot.getItem().getType().equals(Material.PAPER) &&
				(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				) {
			createLabelWriter();
		} else if (
				this.labelContainer != null &&
				!this.discAddSlot.getItem().getType().equals(Material.PAPER) ||
				!(sItem.isCustomItem() && sItem.getMaterial() instanceof BurnedDisc)
				){
			hideLabelWriter();
		} 
		super.onTick();
	}
	
	private void tossItem(SpoutPlayer player, ItemStack dropItem) {
		Location loc = player.getLocation();
        loc.setY(loc.getY() + 1);
        
        Item item = loc.getWorld().dropItem(loc, dropItem);
        Vector v = loc.getDirection().multiply(0.2);
        v.setY(0.2);
        item.setVelocity(v);
	}
}
