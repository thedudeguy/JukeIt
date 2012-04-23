package cc.thedudeguy.jukebukkit.gui;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.widget.BurnButton;
import cc.thedudeguy.jukebukkit.gui.widget.CloseButton;
import cc.thedudeguy.jukebukkit.gui.widget.ServerListButton;

/**
 * This class is based off of WindWakers class in TextureMe.
 *
 */
public class CustomURLSelecter extends GenericPopup {
	
	public CustomURLSelecter(Player player, Block block) {
		
		
		// Label
		GenericLabel label = new GenericLabel("Custom Burn URL");
		label.setX(175).setY(25);
		label.setPriority(RenderPriority.Lowest);
		label.setWidth(-1).setHeight(-1);
		
		// Border
		GenericTexture border = new GenericTexture("borderblue.png");
		border.setX(65).setY(20);
		border.setPriority(RenderPriority.High);
		border.setWidth(300).setHeight(200);

		// Background gradient
		GenericGradient gradient = new GenericGradient();
		gradient.setTopColor(new Color(0.25F, 0.25F, 0.25F, 1.0F));
		gradient.setBottomColor(new Color(0.35F, 0.35F, 0.35F, 1.0F));
		gradient.setWidth(300).setHeight(200);
		gradient.setX(65).setY(20);
		gradient.setPriority(RenderPriority.Highest);
		
		GenericTextField urlInput = new GenericTextField();
		urlInput.setMaximumCharacters(500);
		urlInput.setHeight(100).setWidth(200);
		urlInput.setY(45);
		urlInput.setX(110);
		urlInput.setMaximumLines(0);
		urlInput.setFocus(true);
		//burnPopup.attachWidget(JukeBukkit.instance, urlInput);
		
		// Close button
		CloseButton close = new CloseButton();
		close.setX(155).setY(195);
		close.setWidth(60).setHeight(20);
		close.setPriority(RenderPriority.Lowest);
		
		// Select button
		BurnButton burnButton = new BurnButton(urlInput, block);
		burnButton.setX(95).setY(195);
		burnButton.setWidth(60).setHeight(20);
		burnButton.setPriority(RenderPriority.Lowest);
		
		//switch to server list.
		ServerListButton serverlist = new ServerListButton(block);
		serverlist.setX(215).setY(195);
		serverlist.setWidth(60).setHeight(20);
		serverlist.setPriority(RenderPriority.Lowest);
		
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, border, gradient, burnButton, close, label, urlInput);
		
		if (JukeBukkit.instance.getConfig().getBoolean("enableWebServer") && JukeBukkit.instance.HTTPserver.isRunning() ) {
			this.attachWidget(JukeBukkit.instance, serverlist);
		}
		
	}
	
}
