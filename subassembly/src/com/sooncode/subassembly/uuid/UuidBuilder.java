package com.sooncode.subassembly.uuid;

import java.util.UUID;

public class UuidBuilder {
	 
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toUpperCase();
	}

}
