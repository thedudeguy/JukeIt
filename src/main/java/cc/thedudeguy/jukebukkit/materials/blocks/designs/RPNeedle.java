package cc.thedudeguy.jukebukkit.materials.blocks.designs;

public enum RPNeedle {
	NONE		(0),
	WOOD_FLINT	(32),
	BLAZE_FLINT	(33);
	
	private final Integer textureId;
	
	RPNeedle(Integer textureId) {
		this.textureId = textureId;
	}
	
	public Integer textureId() {
		return this.textureId;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public static RPNeedle getById(int id) {
		for (RPNeedle d : RPNeedle.values()) {
			if (d.id() == id) return d;
		}
		
		//throw exception instead?
		return null;
	}
}
