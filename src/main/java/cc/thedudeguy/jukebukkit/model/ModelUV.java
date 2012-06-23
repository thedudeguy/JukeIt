package cc.thedudeguy.jukebukkit.model;

public class ModelUV {

	private float u;
	private float v;
	
	public ModelUV(float u, float v) {
		this.setU(u);
		this.setV(v);
	}

	public float getU() {
		return u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}
}
