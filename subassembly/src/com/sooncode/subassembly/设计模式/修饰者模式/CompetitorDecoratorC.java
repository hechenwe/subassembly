package com.sooncode.subassembly.设计模式.修饰者模式;

/**
 * 加入悲惨背景介绍
 * @author pc
 *
 */
public class CompetitorDecoratorC extends CompetitorDecorator {  
   
	public CompetitorDecoratorC(Competitor competitor) {  
        super(competitor);  
    }  
    public  void sing()  
    {  
        super.sing();  
        this.introduceBackground();  
    }  
    
    
    private void introduceBackground()  
    {  
        System.out.println("悲惨背景介绍，博取同情，赢感情牌....");  
    }  
}  