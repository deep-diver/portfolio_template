package indi.csp.web.vo;

import java.util.List;

public class ItemDetailVO {
	private boolean 	success;
	private int 		totalCount;
	private int 		id;
	private String 		title;
	private String 		department;
	private String 		description;
	private List<VersionVO> versions;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<VersionVO> getVersions() {
		return versions;
	}
	public void setVersions(List<VersionVO> versions) {
		this.versions = versions;
	}
}
