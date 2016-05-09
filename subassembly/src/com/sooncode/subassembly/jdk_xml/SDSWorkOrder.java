package com.sooncode.subassembly.jdk_xml;

import java.io.Serializable;

public class SDSWorkOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String wo_creation_time;
	
	private String device;
	
	private String work_description;
	
	private String work_detail_id;
	
	private String work_end_time;
	
	private String work_id;
	
	private String work_name;
	
	private String work_start_time;
	
	private String worker;

	public String getWo_creation_time() {
		return wo_creation_time;
	}

	public void setWo_creation_time(String wo_creation_time) {
		this.wo_creation_time = wo_creation_time;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getWork_description() {
		return work_description;
	}

	public void setWork_description(String work_description) {
		this.work_description = work_description;
	}

	public String getWork_end_time() {
		return work_end_time;
	}

	public void setWork_end_time(String work_end_time) {
		this.work_end_time = work_end_time;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getWork_start_time() {
		return work_start_time;
	}

	public void setWork_start_time(String work_start_time) {
		this.work_start_time = work_start_time;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public String getWork_detail_id() {
		return work_detail_id;
	}

	public void setWork_detail_id(String work_detail_id) {
		this.work_detail_id = work_detail_id;
	}

	@Override
	public String toString() {
		return "{\"wo_creation_time\":\"" + wo_creation_time + "\",\"device\":\"" + device + "\",\"work_description\":\"" + work_description + "\",\"work_detail_id\":\"" + work_detail_id + "\",\"work_end_time\":\"" + work_end_time + "\",\"work_id\":\"" + work_id + "\",\"work_name\":\"" + work_name + "\",\"work_start_time\":\"" + work_start_time + "\",\"worker\":\"" + worker + "\"}  ";
	}

}
