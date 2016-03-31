package com.sooncode.subassembly.universty;

import java.util.List;

/**
 * 全国高校
 * @author hechen
 *
 */
public class University {
	
    /**大学编号*/
	private Integer universityId;
	
	 /**大学名称*/
	private String university ;
	
	 /**省市编号*/
	private Integer provinceId;
	
	/**
	 * 高校类型
	 */
	private String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**本校的学院*/
	private List<College> colleges ;

	
	//------------------------------------------------------
	public Integer getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public List<College> getColleges() {
		return colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	@Override
	public String toString() {
		return "University [universityId=" + universityId + ", university=" + university + ", provinceId=" + provinceId + ", colleges=" + colleges + "]";
	}
	
	
	
	
}
