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

import java.util.concurrent.TimeUnit;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.gui.GenericSlider;

import com.chrischurchwell.jukeit.database.RepeaterChipData;
import com.chrischurchwell.jukeit.util.Debug;


public class TimeSlider extends GenericSlider {

	long minTime = 60000;	//1 minute
	long maxTime = 5400000;	//90 minutes
	
	SpoutBlock block;
	
	public TimeSlider(SpoutBlock block) {
		
		this.block = block;
		
		//get the data.
		RepeaterChipData data = RepeaterChipData.getData(block);
		
		Debug.debug("Current time set: ", data.getTime());
		Debug.debug("Formated time: ", formatTime(data.getTime()));
		Debug.debug("Position: ", getPositionFromTime(data.getTime()));
		
		//set current position.
		setSliderPosition(getPositionFromTime(data.getTime()));
		setText(formatTime(data.getTime()));
	}
	
	private long getTimeFromPosition(float position) {
		
		long timeFrame = maxTime - minTime;
		long t = (long)(timeFrame*position);
		
		return minTime + t;
	}
	
	private float getPositionFromTime(long time) {
		
		if (time <= minTime) return 0;
		if (time >= maxTime) return 1;
		
		long timeFrame = maxTime - minTime;
		float t = ((float)time - (float)minTime)/(float)timeFrame;
		
		return t;
	}
	
	private String formatTime(long time) {
		return String.format(
				"%02d:%02d", 
			    TimeUnit.MILLISECONDS.toMinutes(time),
			    TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))
			);
	}
	
	private void update() {
		setText(formatTime(getTimeFromPosition(getSliderPosition())));
	}
	
	@Override
	public void onSliderDrag(SliderDragEvent event) {
		setSliderPosition(event.getNewPosition());
		update();
		
		RepeaterChipData data = RepeaterChipData.getData(block);
		
		data.setTime(getTimeFromPosition(event.getNewPosition()));
		RepeaterChipData.saveData(data);
	}
}
