package com.sooncode.subassembly.dictionary.entity;

 
import java.util.Date;

import net.sf.json.JSONObject;
 
 
/**
 *  数据字典分组 
 * 
 *  @author 代码生成器 
 *
 */
 
public class Groups implements java.io.Serializable{

    /** 序列化  */
    private static final long serialVersionUID = 1L; 
    
   
    /**数据字典分组编号*/
    private Integer groupId; 
    
    
    /**组代码*/
    private String groupCode; 
    
    
    /**组名称*/
    private String groupName; 
    
    
    /**组状态*/
    private Integer groupState; 
    /**创建时间*/
    private Date createDate;
     
    //------------------------get,set 方法----------------------------
 
 
    /**数据字典分组编号*/ 
    public Integer getGroupId(){  
      return groupId;  
    }  
     /**数据字典分组编号*/
    public void setGroupId(Integer groupId){  
      this.groupId = groupId;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**组代码*/ 
    public String getGroupCode(){  
      return groupCode;  
    }  
     /**组代码*/
    public void setGroupCode(String groupCode){  
      this.groupCode = groupCode;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**组名称*/ 
    public String getGroupName(){  
      return groupName;  
    }  
     /**组名称*/
    public void setGroupName(String groupName){  
      this.groupName = groupName;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**组状态*/ 
    public Integer getGroupState(){  
      return groupState;  
    }  
     /**组状态*/
    public void setGroupState(Integer groupState){  
      this.groupState = groupState;  
    } 
    
    
    
     public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		
		
		
		
		
		return  JSONObject.fromObject(this).toString();
	}
	 
 

}
