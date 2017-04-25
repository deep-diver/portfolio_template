package indi.csp.web.vo;

public class UserVO {
	private boolean success;
	private int 	totalCount;
	
	private String id;
	private String name;
	private String department;
	private String role;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("success: " + success  + ", ");
		str.append("totalCount: " +  totalCount  + ", ");
		str.append("id: " + id + ", ");
		str.append("name: " + name + ", ");
		str.append("department: " + department + ", ");
		str.append("role: " + role);
		return str.toString();
	}
}
