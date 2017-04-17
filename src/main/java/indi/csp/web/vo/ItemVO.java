package indi.csp.web.vo;

public class ItemVO {
	private String 		id;
	private String 		title;
	private String 		department;
	private String 		description;
	private VersionVO 	lastestVersion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public VersionVO getLastestVersion() {
		return lastestVersion;
	}
	public void setLastestVersion(VersionVO lastestVersion) {
		this.lastestVersion = lastestVersion;
	}
}
