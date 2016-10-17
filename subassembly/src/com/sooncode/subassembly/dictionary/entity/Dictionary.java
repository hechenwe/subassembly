package com.sooncode.subassembly.dictionary.entity;

import java.util.Date;
 

/**
 *  数据字典数据项
 * 
 *  @author Hechen
 *
 */
 
public class Dictionary implements java.io.Serializable{

    /** 序列化  */
    private static final long serialVersionUID = 1L; 
    
   
    /**字段编号*/
    private Integer dictionaryId; 
    
    
    /**组编号*/
    private Integer groupId; 
    
    
    /**字典代码*/
    private String dictionaryCode; 
    
    
    /**字典名称*/
    private String dictionaryName; 
    
    
    /**数据状态*/
    private Integer dictionaryState; 
    
    /**创建时间*/
    private Date createDate ;
                                                      
   
    //------------------------get,set 方法----------------------------
 
 
    /**字段编号*/ 
    public Integer getDictionaryId(){  
      return dictionaryId;  
    }  
     /**字段编号*/
    public void setDictionaryId(Integer dictionaryId){  
      this.dictionaryId = dictionaryId;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**组编号*/ 
    public Integer getGroupId(){  
      return groupId;  
    }  
     /**组编号*/
    public void setGroupId(Integer groupId){  
      this.groupId = groupId;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**字典代码*/ 
    public String getDictionaryCode(){  
      return dictionaryCode;  
    }  
     /**字典代码*/
    public void setDictionaryCode(String dictionaryCode){  
      this.dictionaryCode = dictionaryCode;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**字典名称*/ 
    public String getDictionaryName(){  
      return dictionaryName;  
    }  
     /**字典名称*/
    public void setDictionaryName(String dictionaryName){  
      this.dictionaryName = dictionaryName;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**数据状态*/ 
    public Integer getDictionaryState(){  
      return dictionaryState;  
    }  
     /**数据状态*/
    public void setDictionaryState(Integer dictionaryState){  
      this.dictionaryState = dictionaryState;  
    } 
    
    
   
     public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	 
	 
	
 

}
