package com.sooncode.subassembly.soontest.test;

public class DataModel {
private Integer id;
private Boolean bool;
private String name;

private Long state;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Boolean getBool() {
	return bool;
}

public void setBool(Boolean bool) {
	this.bool = bool;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Long getState() {
	return state;
}

public void setState(Long state) {
	this.state = state;
}

 DataModel() {
	
}

@Override
public String toString() {
	return "DataModel [id=" + id + ", bool=" + bool + ", name=" + name + ", state=" + state + "]";
}

 
 
 
}
