package cc.thedudeguy.jukebukkit.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="jb_rp_data")
public class RecordPlayerData {

	@Id
    private int id;
	
	@NotNull
    private double x;

    @NotNull
    private double y;

    @NotNull
    private double z;

    @NotEmpty
    private String worldName;
    
    @NotNull
    private int needleType;
    
    private String discKey;
    
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public int getNeedleType() {
		return needleType;
	}

	public void setNeedleType(int needleType) {
		this.needleType = needleType;
	}

	public String getDiscKey() {
		return discKey;
	}

	public void setDiscKey(String discKey) {
		this.discKey = discKey;
	}
	
	public boolean hasDisc() {
		if (discKey == null) return false;
		if (discKey.equals("")) return false;
		return true;
	}
}
