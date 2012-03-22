package cc.thedudeguy.jukebukkit.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="jb_rp_block_designs")
public class RecordPlayerBlockDesigns {
	
	@Id
    private int id;
	
	@NotNull
	private int disc;
	
	@NotNull
	private int needle;
	
	@NotNull
	private int indicator;
	
	public void setId(int id) {
        this.id = id;
    }
	
	public int getId() {
        return id;
    }
	
	public void setDisc(int disc) {
		this.disc = disc;
	}
	
	public int getDisc() {
		return disc;
	}
	
	public void setNeedle(int needle) {
		this.needle = needle;
	}
	
	public int getNeedle() {
		return needle;
	}
	
	public void setIndicator(int indicator) {
		this.indicator = indicator;
	}
	
	public int getIndicator() {
		return indicator;
	}
}
