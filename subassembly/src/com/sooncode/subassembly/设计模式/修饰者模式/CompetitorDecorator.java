package com.sooncode.subassembly.设计模式.修饰者模式;


/**
 * 装饰者模式 核心类  
 * @author pc
 * 1.继承被装饰类（Competitor）
 * 2.组合被装饰类
 */
public class CompetitorDecorator extends Competitor{  
    /**
     * 组合被装饰类
     */
    private Competitor competitor;  
  
    /**
     * 有参构造器
     * @param competitor
     */
    public CompetitorDecorator(Competitor competitor)  
    {  
        this.competitor= competitor;  
    }  
    
    /**
     * 重写父类的方法 调用当前属性的 方法 
     */
    public void sing()  
    {  
        this.competitor.sing();  
    }   
}  