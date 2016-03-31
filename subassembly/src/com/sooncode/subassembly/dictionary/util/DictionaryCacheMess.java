package com.sooncode.subassembly.dictionary.util;

import java.util.Date;

/**
 * 字典缓存数据被查询的统计信息
 * 
 * @author pc
 *
 */
public class DictionaryCacheMess {
	
	/** 组 */
	private String groupCode;
	
	/** 被访问的次数 */
	private Integer times;
	
	/** 最后一次访问的时间 */
	private Date lastVisitDate;
	
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Date getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	
	
}
