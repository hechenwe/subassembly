package com.sooncode.subassembly.class_loader;

public class User {

	
	
	
	private String userName;

	static {
		System.out.println("User.enclosing_method()");
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	class Book {
		private String bookName;

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		
	}
	
	
}
