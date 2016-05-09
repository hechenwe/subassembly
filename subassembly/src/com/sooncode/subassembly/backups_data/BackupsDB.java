package com.sooncode.subassembly.backups_data;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 备份数据
 * 
 * @author pc
 *
 */
public class BackupsDB<T> {
	
	private String filePath;

	public BackupsDB(String filePath) {
		this.filePath = filePath;
	}

	public void put(List<T> list) {
		    int size = list.size();
		    int sub = 100;
		    int m = size/sub;
		    Object o = list.get(0);
		    String name = o.getClass().getSimpleName();
		    List<T> subList = new ArrayList<T>();
			for(int i = 0;i<m;i++){
				int n = getMaxFileSize(name)+1;
				String fileName = name+"-"+n+".txt";
				subList =new ArrayList<T>(list.subList(i, (i+1)*sub)) ;
				SaveObjectUtil.saveObject(filePath+File.separatorChar+fileName, subList);
			}
			if(m*sub != size){
				int n = getMaxFileSize(name)+1;
				String fileName = name+"-"+n+".txt";
				subList =new ArrayList<T>( list.subList(m*sub, size));
				SaveObjectUtil.saveObject(filePath+File.separatorChar+fileName, subList);
			}
		 
		
	}
    
	
	public List<?> get(Class<?> clas) {



		return null;
	}

    private int getMaxFileSize(String className){
    	
    	  File file=new File(this.filePath);
		  String files[];
		  files=file.list();
		  int n = 0;
		  for(int i=0;i<files.length;i++)
		  {
			  String fileName = files[i];
			  if(fileName.contains(className)){
				  String number = fileName.replace(className+"-", "");
				  number = number.replace(".txt", "");
				  int size = Integer.parseInt(number);
				  if(n<size){
					  n = size;
				  }
			  }
		  }
    	
    	return n;
    }
	public static void main(String[] args) {
		BackupsDB<Persion> backupsDB = new BackupsDB<Persion>("e:/etc");
		long t1 =System.currentTimeMillis();
		List<Persion> list = new ArrayList<>();
		for (int i = 0; i <10000; i++) {
			Persion p = new Persion();
			p.setId("010" + i);
			p.setName("admin-" + i);
			p.setSex("" + (i % 2));
			p.setAge(i);
			p.setBirthday(new Date());
			list.add(p);
		}
		long t2 =System.currentTimeMillis();
      
		backupsDB.put(list);
        long t3 =System.currentTimeMillis();
		
		list.clear();
		//list = (List<Persion>) SaveObjectUtil.readObject("e:/etc/list.txt");
		long t4 =System.currentTimeMillis();
		//list = (List<Persion>) backupsDB.get(Persion.class);
		System.out.println("【构建数据】："+(t2-t1));
		System.out.println("【保存数据】："+(t3-t2));
		System.out.println("【读取数据】："+(t4-t3));
		//System.out.println("BackupsDB.main()"+list.get(888));
	}

}
