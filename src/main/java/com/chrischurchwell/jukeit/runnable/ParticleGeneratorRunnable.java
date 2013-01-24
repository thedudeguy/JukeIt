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
