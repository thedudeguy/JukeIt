package cc.thedudeguy.jukebukkit.sound;

public enum SoundEffect {
	RECORD_PLAYER_EJECT(	"disc_eject.ogg" ),
	RECORD_PLAYER_LOAD(		"disc_load.ogg"	),
	RECORD_PLAYER_START(	"disc_start.ogg"),
	RECORD_PLAYER_STOP(		"disc_stop.ogg"),
	JUKEBOX_START(			"jb_startup.ogg"),
	JUKEBOX_STOP(			"jb_error.ogg"),
	MACHINE_PRESS(			"machine.ogg"),
	NEEDLE_ATTACH(			"needle_attach.ogg"),
	NEEDLE_EJECT(			"needle_eject.ogg");
	
	private String soundFileName;
	
	SoundEffect(String soundFileName) {
		this.soundFileName = soundFileName;
	}
	
	public String getSoundFileName() {
		return soundFileName;
	}
	
}
