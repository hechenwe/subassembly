package com.sooncode.subassembly.exception;

class AgeTooYoungException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7262969865033227646L;
	private String info;

	public AgeTooYoungException() {
	}

	public AgeTooYoungException(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}