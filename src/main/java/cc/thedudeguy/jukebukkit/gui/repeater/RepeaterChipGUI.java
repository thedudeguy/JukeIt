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
package cc.thedudeguy.jukebukkit.gui.repeater;

import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.gui.CloseButton;
import cc.thedudeguy.jukebukkit.texture.TextureFile;

public class RepeaterChipGUI extends GenericPopup {
	
	public RepeaterChipGUI() {
		
		//background
		GenericTexture border = new GenericTexture(TextureFile.GUI_BG_REPEATER.getFile());
		border.setX(-88).setY(-30);
		border.setPriority(RenderPriority.Highest);
		border.setWidth(176).setHeight(59);
		border.setFixed(true);
		border.setAnchor(WidgetAnchor.CENTER_CENTER);
		attachWidget(JukeBukkit.getInstance(), border);
		
		//label
		GenericLabel label = new GenericLabel("Set Repeat Time:");
		label.setAnchor(WidgetAnchor.CENTER_CENTER);
		label.setAlign(WidgetAnchor.CENTER_CENTER);
		label.setX(0).setY(-21);
		label.setTextColor(new Color(255, 255, 255));
		attachWidget(JukeBukkit.getInstance(), label);
		
		//slider
		TimeSlider slider = new TimeSlider();
		slider.setAlign(WidgetAnchor.CENTER_CENTER);
		slider.setAnchor(WidgetAnchor.CENTER_CENTER);
		slider.setWidth(160).setHeight(20);
		slider.setX(-80).setY(-16);
		slider.setText("00:00");
		
		attachWidget(JukeBukkit.getInstance(), slider);
		
		//close button
		CloseButton closeButton = new CloseButton();
		closeButton.setWidth(50).setHeight(20);
		closeButton.setX(-25).setY(6);
		closeButton.setFixed(true);
		closeButton.setAnchor(WidgetAnchor.CENTER_CENTER);
		attachWidget(JukeBukkit.getInstance(), closeButton);
	}
}
