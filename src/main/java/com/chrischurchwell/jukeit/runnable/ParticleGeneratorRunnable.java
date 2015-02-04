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
package com.chrischurchwell.jukeit.runnable;

import org.bukkit.scheduler.BukkitRunnable;
import org.getspout.spoutapi.particle.Particle;

public class ParticleGeneratorRunnable extends BukkitRunnable {
	
	Particle particle;
	long length;
	long start;
	
	/**
	 * Spawns a particle every cycle for a given amount of time. Best used with a bukkit repeating task.
	 * @param particle - the particle to spawn
	 * @param length - how long this runnable should run for in milliseconds.
	 */
	public ParticleGeneratorRunnable(Particle particle, long length) {
		this.particle = particle;
		this.length = length;
		this.start = System.currentTimeMillis();
	}
	
	@Override
	public void run() {
		particle.spawn();
		
		if (System.currentTimeMillis() > start + length) {
			this.cancel();
		}
	}

}
