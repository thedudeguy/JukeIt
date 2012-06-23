package cc.thedudeguy.jukebukkit.model;

public class ModelFace {
	
	private ModelVertex vertex1;
	private ModelVertex vertex2;
	private ModelVertex vertex3;
	private ModelVertex vertex4;
	
	private ModelUV uv1;
	private ModelUV uv2;
	private ModelUV uv3;
	private ModelUV uv4;
	
	public ModelFace() {
		
	}

	public ModelVertex getVertex1() {
		return vertex1;
	}

	public void setVertex1(ModelVertex vertex1) {
		this.vertex1 = vertex1;
	}

	public ModelVertex getVertex2() {
		return vertex2;
	}

	public void setVertex2(ModelVertex vertex2) {
		this.vertex2 = vertex2;
	}

	public ModelVertex getVertex3() {
		return vertex3;
	}

	public void setVertex3(ModelVertex vertex3) {
		this.vertex3 = vertex3;
	}

	public ModelVertex getVertex4() {
		return vertex4;
	}

	public void setVertex4(ModelVertex vertex4) {
		this.vertex4 = vertex4;
	}

	public ModelUV getUv1() {
		return uv1;
	}

	public void setUv1(ModelUV uv1) {
		this.uv1 = uv1;
	}

	public ModelUV getUv2() {
		return uv2;
	}

	public void setUv2(ModelUV uv2) {
		this.uv2 = uv2;
	}

	public ModelUV getUv3() {
		return uv3;
	}

	public void setUv3(ModelUV uv3) {
		this.uv3 = uv3;
	}

	public ModelUV getUv4() {
		return uv4;
	}

	public void setUv4(ModelUV uv4) {
		this.uv4 = uv4;
	}
	
	public float getLowestU() {
		float low = uv1.getU();
		if (uv2.getU() < low) low = uv2.getU();
		if (uv3.getU() < low) low = uv3.getU();
		if (uv4.getU() < low) low = uv4.getU();
		return low;
	}
	
	public float getLowestV() {
		float low = uv1.getV();
		if (uv2.getV() < low) low = uv2.getV();
		if (uv3.getV() < low) low = uv3.getV();
		if (uv4.getV() < low) low = uv4.getV();
		return low;
	}
	
	public float getHighestU() {
		float high = uv1.getU();
		if (uv2.getU() > high) high = uv2.getU();
		if (uv3.getU() > high) high = uv3.getU();
		if (uv4.getU() > high) high = uv4.getU();
		return high;
	}
	
	public float getHighestV() {
		float high = uv1.getV();
		if (uv2.getV() > high) high = uv2.getV();
		if (uv3.getV() > high) high = uv3.getV();
		if (uv4.getV() > high) high = uv4.getV();
		return high;
	}
	
}
