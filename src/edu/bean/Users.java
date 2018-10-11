package edu.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users implements Bean{
	
	private int userId;
	private String loginName;
	private String loginPwd;
	private String email;

	
	public Users(int userId, String loginName, String loginPwd, String email) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.email = email;
	}


	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Users [userId=" + userId + ", loginName=" + loginName + ", loginPwd=" + loginPwd + ", email=" + email
				+ "]";
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getLoginPwd() {
		return loginPwd;
	}


	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public Users parseRst(ResultSet rs) throws SQLException {
		
		this.userId = rs.getInt("user_id");
		this.loginName = rs.getString("login_name");
		this.loginPwd = rs.getString("login_pwd");
		this.email = rs.getString("email");

		return this;
	}
	
	
}
