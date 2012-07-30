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
package cc.thedudeguy.jukebukkit.materials;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.materials.blocks.DiscBurner;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxBasic;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxLongRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxLowRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxMaxRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxMidRange;
import cc.thedudeguy.jukebukkit.materials.blocks.JukeboxWorldRange;
import cc.thedudeguy.jukebukkit.materials.blocks.MachineBlock;
import cc.thedudeguy.jukebukkit.materials.blocks.RecordPlayer;
import cc.thedudeguy.jukebukkit.materials.blocks.Speaker;
import cc.thedudeguy.jukebukkit.materials.blocks.SpeakerWireBlock;
import cc.thedudeguy.jukebukkit.model.Model;
import cc.thedudeguy.jukebukkit.texture.TextureFile;


public class Blocks {
	
	public static Model machineBlockModel;
	public static Model machineTopBlockModel;
	public static Model machineTopPressedBlockModel;
	
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
		
		machineBlockModel = new Model(JukeBukkit.instance.getResource("models/machineBlock.obj"), TextureFile.BLOCK_MACHINE.getTexture());
		machineTopBlockModel = new Model(JukeBukkit.instance.getResource("models/machineBlockTop.obj"), TextureFile.BLOCK_MACHINE.getTexture());
		machineTopPressedBlockModel = new Model(JukeBukkit.instance.getResource("models/machineBlockTopPressed.obj"), TextureFile.BLOCK_MACHINE.getTexture());
		
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
