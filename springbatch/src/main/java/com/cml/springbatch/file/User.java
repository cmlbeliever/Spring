package com.cml.springbatch.file;

public class User {

	private Integer userId;
	private String name;
	private String pass;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", pass=" + pass + "]";
	}

}
