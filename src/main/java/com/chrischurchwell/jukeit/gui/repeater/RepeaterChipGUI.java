/**
 * This file is part of JukeIt
 *
 * Copyright (C) 2011-2013  Chris Churchwell
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
package com.chrischurchwell.jukeit.gui.repeater;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;

import com.chrischurchwell.jukeit.JukeIt;
import com.chrischurchwell.jukeit.gui.CloseButton;
import com.chrischurchwell.jukeit.texture.TextureFile;


public class RepeaterChipGUI extends GenericPopup {
	
	public RepeaterChipGUI(SpoutBlock block) {
		
		//background
		GenericTexture border = new GenericTexture(TextureFile.GUI_BG_REPEATER.getFile());
		border.setX(-88).setY(-30);
		border.setPriority(RenderPriority.Highest);
		border.setWidth(176).setHeight(59);
		border.setFixed(true);
		border.setAnchor(WidgetAnchor.CENTER_CENTER);
		attachWidget(JukeIt.getInstance(), border);
		
		//label
		GenericLabel label = new GenericLabel("Set Repeat Time:");
		label.setAnchor(WidgetAnchor.CENTER_CENTER);
		label.setAlign(WidgetAnchor.CENTER_CENTER);
		label.setX(0).setY(-21);
		label.setTextColor(new Color(255, 255, 255));
		attachWidget(JukeIt.getInstance(), label);
		
		//slider
		TimeSlider slider = new TimeSlider(block);
		slider.setAlign(WidgetAnchor.CENTER_CENTER);
		slider.setAnchor(WidgetAnchor.CENTER_CENTER);
		slider.setWidth(160).setHeight(20);
		slider.setX(-80).setY(-16);
		attachWidget(JukeIt.getInstance(), slider);
		
		//close button
		CloseButton closeButton = new CloseButton();
		closeButton.setWidth(50).setHeight(20);
		closeButton.setX(-25).setY(6);
		closeButton.setFixed(true);
		closeButton.setAnchor(WidgetAnchor.CENTER_CENTER);
		attachWidget(JukeIt.getInstance(), closeButton);
	}
}
