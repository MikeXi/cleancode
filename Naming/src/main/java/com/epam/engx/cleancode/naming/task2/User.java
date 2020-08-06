package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;

public class User {

	protected static final boolean IS_ADMIN_USER = false;

	private String birthday;

	private String name;

	private User[] users;

	private int rating;

	public User(String name, String birthday, User[] users) {
		this.birthday = birthday;
		this.name = name;
		this.users = users;
	}

	@Override
	public String toString() {
		return "User [dateOfBirth=" + birthday + ", name=" + name + ", isAdmin=" + IS_ADMIN_USER + ", subordinates="
				+ Arrays.toString(users) + ", rating=" + rating + "]";
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
