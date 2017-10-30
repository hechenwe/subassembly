package com.sooncode.subassembly.nio.test;

import java.io.IOException;

public interface DataProcessor {
	public abstract void disposeData ( byte []  onceReadData) throws IOException;
}
