package com.sooncode.subassembly.soonJson;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test {
public static void main(String[] args) {
	ScriptEngineManager sem = new ScriptEngineManager ();
	ScriptEngine se = sem.getEngineByName ("JavaScript");
	String jsonstr = "{\"name\":1}";
    
	try
	{
		System.out.println (se.eval (jsonstr));
	}
	catch (ScriptException e)
	{
		e.printStackTrace();
		System.out.println ("json格式有误");
	}
}
	
}
