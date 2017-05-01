package indi.csp.web.vo;

import java.util.Date;

public class ItemVO {
	private String 		id;
	private String 		title;
	private String 		department;
	private String 		description;
	private String	 	latestVersion;
	private String 		latestUpdate;
	
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
	public String getLatestVersion() {
		return latestVersion;
	}
	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
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
	public String getLatestUpdate() {
		return latestUpdate;
	}
	public void setLatestUpdate(String latestUpdate) {
		this.latestUpdate = latestUpdate;
	}


	
}
