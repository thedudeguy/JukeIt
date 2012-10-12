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
package cc.thedudeguy.jukebukkit.material;

import cc.thedudeguy.jukebukkit.material.blocks.DiscBurner;
import cc.thedudeguy.jukebukkit.material.blocks.JukeboxBasic;
import cc.thedudeguy.jukebukkit.material.blocks.JukeboxLongRange;
import cc.thedudeguy.jukebukkit.material.blocks.JukeboxLowRange;
import cc.thedudeguy.jukebukkit.material.blocks.JukeboxMaxRange;
import cc.thedudeguy.jukebukkit.material.blocks.JukeboxMidRange;
import cc.thedudeguy.jukebukkit.material.blocks.JukeboxWorldRange;
import cc.thedudeguy.jukebukkit.material.blocks.MachineBlock;
import cc.thedudeguy.jukebukkit.material.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.material.blocks.Speaker;
import cc.thedudeguy.jukebukkit.material.blocks.SpeakerWireBlock;


public class Blocks {
	
	public static RecordPlayer recordPlayer;
	public static JukeboxBasic jukeboxBasic;
	public static JukeboxLowRange jukeboxLowRange;
	public static JukeboxMidRange jukeboxMidRange;
	public static JukeboxLongRange jukeboxLongRange;
	public static JukeboxMaxRange jukeboxMaxRange;
	public static JukeboxWorldRange jukeboxWorldRange;
	
	public static MachineBlock machineBlock;
	
	public static DiscBurner discBurner;
	
	public static Speaker speaker;
	
	public static SpeakerWireBlock speakerWireBlock;
	
	public Blocks() {
		
		//speaker must load before recordPlayer
		speaker = new Speaker();
		
		recordPlayer = new RecordPlayer();
		
		discBurner = new DiscBurner();
		
		machineBlock = new MachineBlock();
		
		//the must be initialized in order
		jukeboxBasic = new JukeboxBasic();
		jukeboxLowRange = new JukeboxLowRange();
		jukeboxMidRange = new JukeboxMidRange();
		jukeboxLongRange = new JukeboxLongRange();
		jukeboxMaxRange = new JukeboxMaxRange();
		jukeboxWorldRange = new JukeboxWorldRange();
		
		
		
		speakerWireBlock = new SpeakerWireBlock();
	}
	
}
