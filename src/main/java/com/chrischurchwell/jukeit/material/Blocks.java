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
package com.chrischurchwell.jukeit.material;

import com.chrischurchwell.jukeit.material.blocks.DiscBurner;
import com.chrischurchwell.jukeit.material.blocks.JukeboxBasic;
import com.chrischurchwell.jukeit.material.blocks.JukeboxLongRange;
import com.chrischurchwell.jukeit.material.blocks.JukeboxLowRange;
import com.chrischurchwell.jukeit.material.blocks.JukeboxMaxRange;
import com.chrischurchwell.jukeit.material.blocks.JukeboxMidRange;
import com.chrischurchwell.jukeit.material.blocks.JukeboxWorldRange;
import com.chrischurchwell.jukeit.material.blocks.MachineBlock;
import com.chrischurchwell.jukeit.material.blocks.RecordPlayer;
import com.chrischurchwell.jukeit.material.blocks.RepeaterChipBlock;
import com.chrischurchwell.jukeit.material.blocks.Speaker;
import com.chrischurchwell.jukeit.material.blocks.SpeakerWireBlock;


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
	
	public static RepeaterChipBlock repeaterChipBlock;
	
	public Blocks() {
		
		//speaker must load before recordPlayer
		speaker = new Speaker();
		
		discBurner = new DiscBurner();
		
		recordPlayer = new RecordPlayer();
		
		machineBlock = new MachineBlock();
		
		//the must be initialized in order
		jukeboxBasic = new JukeboxBasic();
		jukeboxLowRange = new JukeboxLowRange();
		jukeboxMidRange = new JukeboxMidRange();
		jukeboxLongRange = new JukeboxLongRange();
		jukeboxMaxRange = new JukeboxMaxRange();
		jukeboxWorldRange = new JukeboxWorldRange();
		
		speakerWireBlock = new SpeakerWireBlock();
		
		repeaterChipBlock = new RepeaterChipBlock();
	}
	
}
