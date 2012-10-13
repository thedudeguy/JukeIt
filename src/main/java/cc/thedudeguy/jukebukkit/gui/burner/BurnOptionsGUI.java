package cc.thedudeguy.jukebukkit.gui.burner;

import org.bukkit.block.Block;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.CloseButton;

public class BurnOptionsGUI extends GenericPopup {

	public BurnOptionsGUI(Block block) {
		
		// Blue Border
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
		
		// Label
		GenericLabel label = new GenericLabel("Burn Method");
		label.setX(175).setY(25);
		label.setPriority(RenderPriority.Lowest);
		label.setWidth(-1).setHeight(-1);
		
		// Close button
		CloseButton close = new CloseButton();
		close.setX(95).setY(195);
		close.setWidth(60).setHeight(20);
		close.setPriority(RenderPriority.Lowest);
		
		this.attachWidgets(JukeBukkit.instance, border, gradient, label, close);
		
		if (!canUseURL() && !canUseServer()) {
			//no options available.
			GenericLabel message = new GenericLabel();
			message.setX(90).setY(50);
			message.setWidth(250).setHeight(125);
			message.setPriority(RenderPriority.Lowest);
			message.setText("No burn options available.");
			this.attachWidget(JukeBukkit.instance, message);
			return;
		}
		
		//info
		GenericLabel info = new GenericLabel();
		info.setX(150).setY(50);
		info.setWidth(250).setHeight(20);
		info.setPriority(RenderPriority.Lowest);
		info.setText("Select Burn Method");
		this.attachWidget(JukeBukkit.instance, info);
		
		int y = 80;
		
		// Server List
		if (canUseServer()) {
			ServerListButton serverlist = new ServerListButton(block);
			serverlist.setX(150).setY(y);
			serverlist.setWidth(120).setHeight(20);
			serverlist.setPriority(RenderPriority.Lowest);
			this.attachWidget(JukeBukkit.instance, serverlist);
			y = y + 30;
		}
		
		// URL Burn Type
		if (canUseURL()) {
			// switch to custom URL
			CustomURLButton urlbutton = new CustomURLButton(block);
			urlbutton.setX(150).setY(y);
			urlbutton.setWidth(120).setHeight(20);
			urlbutton.setPriority(RenderPriority.Lowest);
			this.attachWidget(JukeBukkit.instance, urlbutton);
			y = y+30;
		}
		
		this.setTransparent(true);
	}
	
	public static void openBurnGUI(SpoutPlayer player, Block block) {
		
		if (canUseURL() && !canUseServer()) {
			player.getMainScreen().attachPopupScreen(new CustomURLSelecter(player, block, true));
		} else if (canUseServer() && !canUseURL()){
			player.getMainScreen().attachPopupScreen(new BurnSelector(player, block, true));
		} else {
			player.getMainScreen().attachPopupScreen(new BurnOptionsGUI(block));
		}
	}
	
	public static boolean canUseURL() {
		if (JukeBukkit.instance.getConfig().getBoolean("allowExternalURLs")) {
			return true;
		}
		return false;
	}
	
	public static boolean canUseServer() {
		if (
				JukeBukkit.instance.getConfig().getBoolean("enableWebServer") &&
				JukeBukkit.instance.HTTPserver.isRunning()
				) {
			return true;
		}
		return false;
	}
}
