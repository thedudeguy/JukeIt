package cc.thedudeguy.jukebukkit.materials.blocks.designs;

public enum RPIndicator {
	RED		(7),
	GREEN	(8);
	
	private final Integer textureId;
	
	RPIndicator(Integer textureId) {
		this.textureId = textureId;
	}
	
	public Integer textureId() {
		return this.textureId;
	}
	
	public int id() {
		return this.ordinal();
	}
	
	public static RPIndicator getById(int id) {
		for (RPIndicator d : RPIndicator.values()) {
			if (d.id() == id) return d;
		}
		
		//throw exception instead?
		return null;
	}
}
