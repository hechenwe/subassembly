package com.sooncode.subassembly.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

public class MongoDBJDBC {
	
	
	public static void main(String args[]) {
		 // MongoClient mongo = new MongoClient();//不指定host和port,默认使用本地数据库，端口：27017
		  MongoClient mongo = new MongoClient("127.0.0.1", 27017);
	  System.out.println(mongo);
	}
			  
}