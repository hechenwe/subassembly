package com.sooncode.subassembly.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PersonDao extends BaseMongoDAOImpl<Persion> {

	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		// TODO Auto-generated method stub
		
	}

	
	 
}
