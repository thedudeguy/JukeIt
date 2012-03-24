package cc.thedudeguy.jukebukkit.database;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;

@Entity()
@Table(name="written_labels")
public class LabelData {
	
	@Id
	private int id;
	
	@NotEmpty
	private String nameKey;
	
	@NotEmpty
	private String label;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameKey() {
		return nameKey;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String generateNameKey() {
		setNameKey(UUID.randomUUID().toString());
		return  getNameKey();
	}
}
