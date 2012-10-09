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
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.widget.BackButton;
import cc.thedudeguy.jukebukkit.gui.widget.BurnButton;
import cc.thedudeguy.jukebukkit.gui.widget.CloseButton;

/**
 * This class is based off of WindWakers class in TextureMe.
 *
 */
public class CustomURLSelecter extends GenericPopup {
	
	public CustomURLSelecter(Player player, Block block) {
		this(player, block, false);
	}
	
	public CustomURLSelecter(Player player, Block block, boolean primary) {
		
		// Label
		GenericLabel label = new GenericLabel("Burn Custom URL");
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
		
		if (primary == true) {
			// Back button
			CloseButton close = new CloseButton();
			close.setX(95).setY(195);
			close.setWidth(60).setHeight(20);
			close.setPriority(RenderPriority.Lowest);
			this.attachWidget(JukeBukkit.instance, close);
		} else {
			// Back button
			BackButton back = new BackButton(block);
			back.setX(95).setY(195);
			back.setWidth(60).setHeight(20);
			back.setPriority(RenderPriority.Lowest);
			this.attachWidget(JukeBukkit.instance, back);
		}
		
		// Select button
		BurnButton burnButton = new BurnButton(urlInput, block);
		burnButton.setX(275).setY(195);
		burnButton.setWidth(60).setHeight(20);
		burnButton.setPriority(RenderPriority.Lowest);
		
		this.setTransparent(true);
		this.attachWidgets(JukeBukkit.instance, border, gradient, burnButton, label, urlInput);
		
	}
	
}
