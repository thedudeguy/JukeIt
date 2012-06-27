package cc.thedudeguy.jukebukkit.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.block.design.Texture;

import cc.thedudeguy.jukebukkit.JukeBukkit;
import cc.thedudeguy.jukebukkit.util.Debug;

public class Model {
	
	public static final int OBJECT = 0;
	public static final int VERTEX = 1;
	public static final int VERTEX_TEXTURE = 2;
	public static final int VERTEX_NORMAL = 3;
	public static final int FACE = 4;
	public static final int OTHER = 5;
	
	private List<ModelVertex> v = new ArrayList<ModelVertex>();
	private List<ModelUV> vt = new ArrayList<ModelUV>();
	private List<ModelVertex> vn = new ArrayList<ModelVertex>();
	private List<ModelFace> f = new ArrayList<ModelFace>();
	
	private Texture texture;
	
	public Model(InputStream inputStream, Texture texture) {
		this.texture = texture;
		
		//since obj v indexes start at 1, going to insert the first list array (0) so it is preocupied
		v.add(null);
		vt.add(null);
		vn.add(null);
		
		loadObj(inputStream);
	}
	
	public GenericBlockDesign getDesign() {
		
		GenericBlockDesign design = new GenericBlockDesign();
		
		design.setTexture(JukeBukkit.instance, texture);
		design.setMinBrightness(0.0F);
		design.setMaxBrightness(1.0F);
		design.setBoundingBox(0, 0, 0, 1, 1, 1);
		design.setQuadNumber(f.size());
		
		Debug.debug("Generating Design Quads");
		Debug.debug("Number of faces: ", f.size());
		int counter = 0;
		for(ModelFace face : f) {
			Debug.debug("Generating quad: ", counter);
			
			design.setVertex(counter, 0, face.getVertex1().getX(), face.getVertex1().getY(), face.getVertex1().getZ(), face.getUv1().getU(), face.getUv1().getV());
			design.setVertex(counter, 1, face.getVertex2().getX(), face.getVertex2().getY(), face.getVertex2().getZ(), face.getUv2().getU(), face.getUv2().getV());
			design.setVertex(counter, 2, face.getVertex3().getX(), face.getVertex3().getY(), face.getVertex3().getZ(), face.getUv3().getU(), face.getUv3().getV());
			design.setVertex(counter, 3, face.getVertex4().getX(), face.getVertex4().getY(), face.getVertex4().getZ(), face.getUv4().getU(), face.getUv4().getV());
			
			//TODO: add light source.... probably to normals
			//setLightSource(counter, 0, 0, 1);
			
			counter++;
		}
		
		return design;
	}
	
	public void loadObj(InputStream inputStream) {
		Debug.debug("Loading Obj");
		
		Scanner scanner = new Scanner(inputStream);
		
		while (scanner.hasNextLine()) {
			
			String[] data = getLineData(scanner.nextLine());
			
			switch(getLineDataType(data)) {
			case OBJECT:
				extractObject(data);
				break;
			case VERTEX:
				extractVertex(data);
				break;
			case VERTEX_TEXTURE:
				extractVertexTexture(data);
				break;
			case VERTEX_NORMAL:
				extractVertexNormal(data);
				break;
			case FACE:
				Debug.debug("EXTRACTING FACE");
				extractFace(data);
				break;
			}
		}
	}
	
	private String[] getLineData(String line) {
		Debug.debug(line);
		return line.toLowerCase().trim().split(" ");
	}
	
	private int getLineDataType(String[] data) {
		String type = data[0].trim();
		if (type.equalsIgnoreCase("o")) return OBJECT;
		if (type.equalsIgnoreCase("v")) return VERTEX;
		if (type.equalsIgnoreCase("vt")) return VERTEX_TEXTURE;
		if (type.equalsIgnoreCase("vn")) return VERTEX_NORMAL;
		if (type.equalsIgnoreCase("f")) return FACE;
		return OTHER;
	}
	
	/**
	 * Extracts the Object data from the data array
	 * @param data raw data from a line split by spaces
	 */
	private void extractObject(String[] data) {
		
	}
	
	/**
	 * Extracts the vertex data from a obj line
	 * The data strin[] should always have x y z under index 1 2 3. If not than there is
	 * something wrong with the line and this should probably throw some kind of exception.
	 * @param data line data returned by getLineData()
	 * @see getLineData()
	 */
	private void extractVertex(String[] data) {
		//TODO: complain if not a vertex data line
		//TODO: complain if data is corrupt or missing
		float x = Float.parseFloat(data[1]);	  
		float y = Float.parseFloat(data[2]);	
		float z = Float.parseFloat(data[3]);
		v.add(new ModelVertex(x, y, z));
	}
	
	/**
	 * Extracts the uv data from an obj line
	 * the data string[] should always have 2 floats under index 1 and 2. if not there is
	 * something wrong with the data line.
	 * @param data - data returned by getLineData()
	 * @see getLineData()
	 */
	private void extractVertexTexture(String[] data) {
		//TODO: complain if not a uv data line
		//TODO: complain if data is corrupt or missing
		float u = Float.parseFloat(data[1]);
		float v = Float.parseFloat(data[2]);		  
		vt.add(new ModelUV(u, v));
	}
	
	/**
	 * Extracts the normal data from an obj line
	 * the data string[] should always have 3 floats under index 1 and 2 and 3. if not there is
	 * something wrong with the data line.
	 * @param data - data returned by getLineData()
	 * @see getLineData()
	 */
	private void extractVertexNormal(String[] data) {
		//TODO: complain if not a vn data line
		//TODO: complain if data is corrupt or missing
		float nx = Float.parseFloat(data[1]);	  
		float ny = Float.parseFloat(data[2]);	
		float nz = Float.parseFloat(data[3]);	
		vn.add(new ModelVertex(nx, ny, nz));
		
	}
	
	/** Extract face data from an obj line.
	 * will have 3 sets starting at index one for tri, or 4 sets for quad.
	 * each set should contain a a vertex index, uv index, and normal index, but doesnt require uv or normal to be present
	 * values in a set are seperated by a /
	 * examples of sets: 1 or 1/4/3 or 1/4 or 1//3
	 * examining an obj, it appears that a face will contain indexes that have already been defined
	 * so each index present should be present in this class' arrays.
	 * @param data
	 */
	private void extractFace(String[] data) {
		//TODO: complain if not a f data line
		//TODO: complain if data is corrupt or missing
		//TODO: check what data is present in a set, right now assuming all 3 values are present
		//TODO: double check that an index exists
		//TODO: do something about tris if data is a tri(spoutplugin needs quads)
		//TODO: pretty much everything
		ModelFace face = new ModelFace();
		
		String[] set1 = data[1].split("/");
		String[] set2 = data[2].split("/");
		String[] set3 = data[3].split("/");
		String[] set4 = data[4].split("/");
		
		face.setVertex1(v.get(Integer.parseInt(set1[0])));
		face.setVertex2(v.get(Integer.parseInt(set2[0])));
		face.setVertex3(v.get(Integer.parseInt(set3[0])));
		face.setVertex4(v.get(Integer.parseInt(set4[0])));
		
		face.setUv1(vt.get(Integer.parseInt(set1[1])));
		face.setUv2(vt.get(Integer.parseInt(set2[1])));
		face.setUv3(vt.get(Integer.parseInt(set3[1])));
		face.setUv4(vt.get(Integer.parseInt(set4[1])));
		Debug.debug("adding face");
		f.add(face);
	}
}
