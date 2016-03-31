package com.sooncode.subassembly.school;


/**
 *  全国高中以下的学校 
 * 
 *  @author HeChen 
 *
 */
 
public class School implements java.io.Serializable{

    /** 序列化  */
    private static final long serialVersionUID = 1L; 
    
   
    /**学校编号*/
    private Long schoolId; 
    
    
    /**学校名称*/
    private String schoolName; 
    
    
    /**学校地址*/
    private String schoolAddress; 
    
    
    /**学校联系电话*/
    private String schoolPhone; 
    
    
    /**学校邮政编码*/
    private String schoolPostalcode; 
    
    
    /**学校官网*/
    private String schoolWebsite; 
    
    
    /**学段*/
    private String schoolXueduan; 
    
    
    /**学校所在省市*/
    private String schoolProvince; 
    
    
    /***/
    private String schoolQu; 
    
    
    /***/
    private String schoolXian; 
    
    
    /**所在省市学校编号*/
    private Integer page; 
    
    
  
 
 
 
 
 
  
                                                               
   
    //------------------------get,set 方法----------------------------
 
 
    /**学校编号*/ 
    public Long getSchoolId(){  
      return schoolId;  
    }  
     /**学校编号*/
    public void setSchoolId(Long schoolId){  
      this.schoolId = schoolId;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学校名称*/ 
    public String getSchoolName(){  
      return schoolName;  
    }  
     /**学校名称*/
    public void setSchoolName(String schoolName){  
      this.schoolName = schoolName;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学校地址*/ 
    public String getSchoolAddress(){  
      return schoolAddress;  
    }  
     /**学校地址*/
    public void setSchoolAddress(String schoolAddress){  
      this.schoolAddress = schoolAddress;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学校联系电话*/ 
    public String getSchoolPhone(){  
      return schoolPhone;  
    }  
     /**学校联系电话*/
    public void setSchoolPhone(String schoolPhone){  
      this.schoolPhone = schoolPhone;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学校邮政编码*/ 
    public String getSchoolPostalcode(){  
      return schoolPostalcode;  
    }  
     /**学校邮政编码*/
    public void setSchoolPostalcode(String schoolPostalcode){  
      this.schoolPostalcode = schoolPostalcode;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学校官网*/ 
    public String getSchoolWebsite(){  
      return schoolWebsite;  
    }  
     /**学校官网*/
    public void setSchoolWebsite(String schoolWebsite){  
      this.schoolWebsite = schoolWebsite;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学段*/ 
    public String getSchoolXueduan(){  
      return schoolXueduan;  
    }  
     /**学段*/
    public void setSchoolXueduan(String schoolXueduan){  
      this.schoolXueduan = schoolXueduan;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**学校所在省市*/ 
    public String getSchoolProvince(){  
      return schoolProvince;  
    }  
     /**学校所在省市*/
    public void setSchoolProvince(String schoolProvince){  
      this.schoolProvince = schoolProvince;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /***/ 
    public String getSchoolQu(){  
      return schoolQu;  
    }  
     /***/
    public void setSchoolQu(String schoolQu){  
      this.schoolQu = schoolQu;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /***/ 
    public String getSchoolXian(){  
      return schoolXian;  
    }  
     /***/
    public void setSchoolXian(String schoolXian){  
      this.schoolXian = schoolXian;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**所在省市学校编号*/ 
    public Integer getPage(){  
      return page;  
    }  
     /**所在省市学校编号*/
    public void setPage(Integer page){  
      this.page = page;  
    } 
    
    
   //----------------------------------------------------------------
   
   
  
 
 
   
 
 
 


     //----------------------------------------------------------------
     @Override
	 public String toString() {
		return  "School : 全国高中以下的学校["+
		        " ;学校编号:schoolId = " + schoolId +  
		        " ;学校名称:schoolName = " + schoolName +  
		        " ;学校地址:schoolAddress = " + schoolAddress +  
		        " ;学校联系电话:schoolPhone = " + schoolPhone +  
		        " ;学校邮政编码:schoolPostalcode = " + schoolPostalcode +  
		        " ;学校官网:schoolWebsite = " + schoolWebsite +  
		        " ;学段:schoolXueduan = " + schoolXueduan +  
		        " ;学校所在省市:schoolProvince = " + schoolProvince +  
		        " ;:schoolQu = " + schoolQu +  
		        " ;:schoolXian = " + schoolXian +  
		        " ;所在省市学校编号:page = " + page + "]" ;
	}
 

}
