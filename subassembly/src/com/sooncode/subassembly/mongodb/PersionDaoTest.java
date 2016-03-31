package com.sooncode.subassembly.mongodb;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.log4j.Logger;

 
 
/**
 *'感悟模块_感悟' Dao接口测试类
 *                                                                     
 *  @author hechen
 */

@RunWith(SpringJUnit4ClassRunner.class) // 使用Springtest测试框架
@ContextConfiguration("/applicationContext.xml") // 加载配置
public class PersionDaoTest {

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
    //                                                      日志
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
    private static Logger logger = Logger.getLogger("InspirationDaoInterfaceTest.class");
    
    
    
    
	@Autowired
	private PersonDao persionDao;
	
    //------------------------------------------------------------------------------ 单表部分 -------------------------------------------------------------------------------------------------
	
	
    
	@Test
	public void saveTest() {
		Persion p = new Persion();
		p.setName("hechen");
		p.setAge(23);
		p.setSex("1");
		persionDao.save(p);
	}
	}
	   
	    