package com.levi.melodydirectory;

import java.util.Arrays;
import java.util.List;

public class user {
/*
 * Username
Password
Boolean is comment moderator
Boolean is system administrator
Sign in
Sign out
Display
Delete account
 */
	
	private String username;
	private String password;
	private boolean isCommentMod;
	private boolean isSysAdmin;
	private boolean isSignedIn;
	
	
	public String getUsername() {
		return username;
	}

	// can username be changed? snapchat rules?
	public void setUsername(String username) {
		this.username = username;
	}

	// this feels wrong.. why would we need this?
	public String getPassword() {
		return password;
	}

	// feels like you should have to enter old pw to change pw
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCommentMod() {
		return isCommentMod;
	}

	// don't know if this should be able to be set
	public void setCommentMod(boolean isCommentMod) {
		this.isCommentMod = isCommentMod;
	}

	public boolean isSysAdmin() {
		return isSysAdmin;
	}

	// don't know if this should be able to be set
	public void setSysAdmin(boolean isSysAdmin) {
		this.isSysAdmin = isSysAdmin;
	}

	public boolean isSignedIn() {
		return isSignedIn;
	}

	public void setSignedIn(boolean value) {
		isSignedIn = value;
	}
	
	public String toString() {
		return username+","+password;
	}
	
	public void decodeToString(String user) {
		List<String> pieces = Arrays.asList(user.split(","));
		username = pieces.get(0);
		password = pieces.get(1);
	}
	
	// create new user
	public user(String uName, String pw) {
		username = uName;
		password = pw;
		isCommentMod = false;
		isSysAdmin = false;
		isSignedIn = false;
	}
	
	// decode from toString
	public user(String u) {
		decodeToString(u);
		isCommentMod = false;
		isSysAdmin = false;
		isSignedIn = false;
	}
	
	public static void main(String[] args) {
	
    }
}
