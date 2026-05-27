package com.tight.coupling;

public class UserManager {
	private UserDatabase userdatabase = new UserDatabase();
	
	public String  getUserInformation() {
		return userdatabase.getUserDetails();
	}
}
