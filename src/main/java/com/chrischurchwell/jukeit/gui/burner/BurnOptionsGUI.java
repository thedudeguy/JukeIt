/**
 * This file is part of JukeIt
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
package com.chrischurchwell.jukeit.gui.burner;

import org.bukkit.block.Block;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.gui.CloseButton;


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
		
		this.attachWidgets(JukeIt.getInstance(), border, gradient, label, close);
		
		//info
		GenericLabel info = new GenericLabel();
		info.setX(150).setY(50);
		info.setWidth(250).setHeight(20);
		info.setPriority(RenderPriority.Lowest);
		info.setText("Select Burn Method");
		this.attachWidget(JukeIt.getInstance(), info);
		
		// URL Burn Type
		CustomURLButton urlbutton = new CustomURLButton(block);
		urlbutton.setX(150).setY(80);
		urlbutton.setWidth(120).setHeight(20);
		urlbutton.setPriority(RenderPriority.Lowest);
		this.attachWidget(JukeIt.getInstance(), urlbutton);
		
		this.setTransparent(true);
	}
	
	public static void openBurnGUI(SpoutPlayer player, Block block) {
		player.getMainScreen().attachPopupScreen(new BurnOptionsGUI(block));
	}
	
}
