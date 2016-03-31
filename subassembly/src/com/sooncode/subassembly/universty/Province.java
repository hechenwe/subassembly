package com.sooncode.subassembly.universty;

import java.util.List;

/**
 * 高校所在省市
 * @author hechen
 *
 */
public class Province {
	
    /**
     * 省市 编号
     */
	private Integer provinceId ;
	
	/**
	 * 省市名称
	 */
	private String province ;

	/**本省，直辖市 的大学*/
	private List<University> universitys;
	
	
	//--------------------------------------------------------------
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<University> getUniversitys() {
		return universitys;
	}

	public void setUniversitys(List<University> universitys) {
		this.universitys = universitys;
	}

	@Override
	public String toString() {
		return "Province [provinceId=" + provinceId + ", province=" + province + ", universitys=" + universitys + "]";
	}
	
	
	
}
