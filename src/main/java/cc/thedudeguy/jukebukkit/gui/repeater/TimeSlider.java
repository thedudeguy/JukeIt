package cc.thedudeguy.jukebukkit.gui.repeater;

import java.util.concurrent.TimeUnit;

import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.gui.GenericSlider;

import cc.thedudeguy.jukebukkit.util.Debug;

public class TimeSlider extends GenericSlider {

	long minTime = 60000;	//1 minute
	long maxTime = 5400000;	//90 minutes
	long timeFrame = maxTime - minTime;
	
	public TimeSlider() {
		
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
		float t = (time - minTime)/timeFrame;
		
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
	}
}
