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
package cc.thedudeguy.jukebukkit.gui;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.player.SpoutPlayer;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.SongRepo;
import cc.thedudeguy.jukebukkit.gui.widget.CloseButton;
import cc.thedudeguy.jukebukkit.gui.widget.CustomURLButton;
import cc.thedudeguy.jukebukkit.gui.widget.RepoBurnButton;
import cc.thedudeguy.jukebukkit.gui.widget.RepoMusicList;
import cc.thedudeguy.jukebukkit.gui.widget.ServerListButton;

/**
 * This class is based off of WindWakers class in TextureMe.
 *
 */
public class RepoSelector extends GenericPopup {
	
	public RepoSelector(Player player, Block block) {
		
		// Label
		GenericLabel label = new GenericLabel("Music Repository");
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
		
		// Close button
		CloseButton close = new CloseButton();
		close.setX(155).setY(195);
		close.setWidth(60).setHeight(20);
		close.setPriority(RenderPriority.Lowest);
		
		// switch to custom URL
		CustomURLButton urlbutton = new CustomURLButton(block);
		urlbutton.setX(215).setY(195);
		urlbutton.setWidth(60).setHeight(20);
		urlbutton.setPriority(RenderPriority.Lowest);
		
		ServerListButton listButton = new ServerListButton(block);
		listButton.setX(275).setY(195);
		listButton.setWidth(60).setHeight(20);
		listButton.setPriority(RenderPriority.Lowest);
		
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, border, gradient, close, label);
		
		if (SongRepo.validSubscriber == true) {
			// Texture list
			GenericListWidget list = new RepoMusicList();
			list.setX(90).setY(50);
			list.setWidth(250).setHeight(125);
			list.setPriority(RenderPriority.Lowest);
			
			// Select button
			RepoBurnButton burnButton = new RepoBurnButton(list, block);
			burnButton.setX(95).setY(195);
			burnButton.setWidth(60).setHeight(20);
			burnButton.setPriority(RenderPriority.Lowest);
			
			this.attachWidgets(JukeBukkit.instance, list, burnButton);
		} else {
			GenericLabel message = new GenericLabel();
			message.setX(90).setY(50);
			message.setWidth(250).setHeight(125);
			message.setPriority(RenderPriority.Lowest);
			message.setText("To activate the song repo on your server,\nhave your server admin subscribe at\nhttp://" + SongRepo.repoAddress);
			this.attachWidget(JukeBukkit.instance, message);
		}
		
		if (JukeBukkit.instance.getConfig().getBoolean("enableWebServer") && JukeBukkit.instance.HTTPserver.isRunning() ) {
			this.attachWidget(JukeBukkit.instance, listButton);
		}
		
		if (JukeBukkit.instance.getConfig().getBoolean("allowExternalURLs")) {
			this.attachWidget(JukeBukkit.instance, urlbutton);
		}
		
	}
	
}
