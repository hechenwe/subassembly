package com.sooncode.subassembly.exception;

class AgeTooOldException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2217226571101630772L;
	private String info;

	public AgeTooOldException() {
	}

	public AgeTooOldException(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}