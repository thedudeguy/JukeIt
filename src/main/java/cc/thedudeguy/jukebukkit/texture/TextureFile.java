package cc.thedudeguy.jukebukkit.texture;

public enum TextureFile {
	BLANK_DISC_BLACK(		"blank_disc_black.png"),
	BLANK_DISC_BLUE(		"blank_disc_blue.png"),
	BLANK_DISC_BROWN(		"blank_disc_brown.png"),
	BLANK_DISC_CYAN(		"blank_disc_cyan.png"),
	BLANK_DISC_GRAY(		"blank_disc_gray.png"),
	BLANK_DISC_GREEN(		"blank_disc_green.png"),
	BLANK_DISC_LIGHT_BLUE(	"blank_disc_lightblue.png"),
	BLANK_DISC_LIGHT_GRAY(	"blank_disc_lightgray.png"),
	BLANK_DISC_LIME(		"blank_disc_lime.png"),
	BLANK_DISC_MAGENTA(		"blank_disc_magenta.png"),
	BLANK_DISC_ORANGE(		"blank_disc_orange.png"),
	BLANK_DISC_PINK(		"blank_disc_pink.png"),
	BLANK_DISC_PURPLE(		"blank_disc_purple.png"),
	BLANK_DISC_RED(			"blank_disc_red.png"),
	BLANK_DISC_WHITE(		"blank_disc_white.png"),
	BLANK_DISC_YELLOW(		"blank_disc_yellow.png"),
	
	BURNED_DISC_BLACK(		"burned_disc_black.png"),
	BURNED_DISC_BLUE(		"burned_disc_blue.png"),
	BURNED_DISC_BROWN(		"burned_disc_brown.png"),
	BURNED_DISC_CYAN(		"burned_disc_cyan.png"),
	BURNED_DISC_GRAY(		"burned_disc_gray.png"),
	BURNED_DISC_GREEN(		"burned_disc_green.png"),
	BURNED_DISC_LIGHT_BLUE(	"burned_disc_lightblue.png"),
	BURNED_DISC_LIGHT_GRAY(	"burned_disc_lightgray.png"),
	BURNED_DISC_LIME(		"burned_disc_lime.png"),
	BURNED_DISC_MAGENTA(	"burned_disc_magenta.png"),
	BURNED_DISC_ORANGE(		"burned_disc_orange.png"),
	BURNED_DISC_PINK(		"burned_disc_pink.png"),
	BURNED_DISC_PURPLE(		"burned_disc_purple.png"),
	BURNED_DISC_RED(		"burned_disc_red.png"),
	BURNED_DISC_WHITE(		"burned_disc_white.png"),
	BURNED_DISC_YELLOW(		"burned_disc_yellow.png"),
	
	NEEDLE_BLAZE_FLINT(		"needle_blaze-flint.png"),
	NEEDLE_STICK_FLINT(		"needle_stick-flint.png"),
	
	ITEM_DISC_ON_A_STICK(	"disconastick.png"),
	ITEM_SPEAKER_WIRE(		"speakerwire.png"),
	ITEM_MACHINE_TOP(		"machineitemtop.png"),
	ITEM_MACHINE_BOTTOM(	"machineitembot.png"),
	
	GUI_BG_BLUE(			"borderblue.png"),
	GUI_BG_MACHINE(			"machinegui.png"),
	GUI_BG_LABEL_WRITER(	"paper.png"),
	
	BLOCKS_DEPRECATED(		"blocks_deprecated.png"),
	BLOCK_MACHINE(			"machineblock.png"),
	BLOCK_RECORD_PLAYER(	"recordplayer.png"),
	BLOCK_SPEAKER(			"speaker.png"),
	BLOCK_SPEAKER_WIRE(		"speakerwireblock.png");
	
	private String file;
	
	TextureFile(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return file;
	}
}
