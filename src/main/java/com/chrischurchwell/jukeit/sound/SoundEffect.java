/**
 * This file is part of JukeIt-Free
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
package com.chrischurchwell.jukeit.sound;

public enum SoundEffect {
	RECORD_PLAYER_EJECT(	"disc_eject.ogg" ),
	RECORD_PLAYER_LOAD(		"disc_load.ogg"	),
	RECORD_PLAYER_START(	"disc_start.ogg"),
	RECORD_PLAYER_STOP(		"disc_stop.ogg"),
	JUKEBOX_START(			"jb_startup.ogg"),
	JUKEBOX_STOP(			"jb_error.ogg"),
	MACHINE_PRESS(			"machine.ogg"),
	NEEDLE_ATTACH(			"needle_attach.ogg"),
	NEEDLE_EJECT(			"needle_eject.ogg"),
	SKIPPING_RECORD(		"skipping-vudu-record.ogg");
	
	private String soundFileName;
	
	SoundEffect(String soundFileName) {
		this.soundFileName = soundFileName;
	}
	
	public String getSoundFileName() {
		return soundFileName;
	}
	
}
