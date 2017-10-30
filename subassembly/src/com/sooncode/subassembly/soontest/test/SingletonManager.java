package com.sooncode.subassembly.soontest.test;

public class SingletonManager {

	
	private static  Object  dataModelObject;
	 
	
	@SuppressWarnings("unchecked")
	public <M> M getDataModel () {
		return (M) dataModelObject;
	}
	
	private static volatile  SingletonManager singletonManager = null;
	
	public <M> SingletonManager (M dataModel) {
			dataModelObject = dataModel;
	}
	
	public static <M> SingletonManager getSingletonManager(M dataModel) {  
		if (singletonManager == null) {  
            synchronized (SingletonManager.class) {  
                if (singletonManager == null) {  
                	singletonManager = new SingletonManager(dataModel);  
                }  
            }  
        }  
        return singletonManager;  
    }  
	 

	public static void main(String[] args) {
		DataModel dm = new DataModel();
		dm.setName("AAA");
		SingletonManager sm = SingletonManager.getSingletonManager(dm);
		DataModel dm2 =  sm.getDataModel();
		dm2.setBool(true);
		dm2.setId(2343);
		dm2.setName("hello");
		System.out.println(sm.getDataModel());
		SingletonManager sm2 = SingletonManager.getSingletonManager(null);
		System.out.println(sm2.getDataModel());
	}

	 
	
}
