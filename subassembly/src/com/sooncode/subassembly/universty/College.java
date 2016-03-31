package com.sooncode.subassembly.universty;
/**
 * 高校的学院
 * @author hechen
 *
 */
public class College {
	
    /**学院编号*/
	private  Integer collegeId;
	
	/**学院名称*/
	private String college;
	
	/**高校编号*/
	private Integer universityId;
	
	//------------------------------------------------------------

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Integer getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}

	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", college=" + college + ", universityId=" + universityId + "]";
	}
	
	

}
