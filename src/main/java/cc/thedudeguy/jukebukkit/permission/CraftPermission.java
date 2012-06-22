package cc.thedudeguy.jukebukkit.permission;

/**
 * Permission Type
 * @author Chris Churchwell
 *
 */
public class CraftPermission {
	private String craftPermission;
	
	public CraftPermission(String craftPermission) {
		this.craftPermission = craftPermission;
	}
	
	public boolean hasCraftPermission() {
		if (craftPermission != null) return true;
		return false;
	}
	
	public String getCraftPermission() {
		return craftPermission;
	}
	
	
	public void setCraftPermission(String permission) {
		craftPermission = permission;
	}
	
}
