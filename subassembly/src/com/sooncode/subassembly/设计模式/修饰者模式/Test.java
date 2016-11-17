package com.sooncode.subassembly.设计模式.修饰者模式;
public class Test {  
  
    public static void main(String[] args) {  
  
         Competitor decorator = new Competitor();  
  
        // 加入了跳舞修饰，decorator 的sing() 方法中被动态添加了dance 修饰  
         
        decorator = new CompetitorDecoratorA(decorator);  
  
        // 在上面修饰的基础上，加入背景音乐修饰  
  
        decorator = new CompetitorDecoratorB(decorator);  
  
        // 在上面修饰的基础上，加上悲惨的身世介绍  
  
        decorator = new CompetitorDecoratorC(decorator);  
  
        decorator.sing();  
  
       // System.out.println("满场尖叫，满分通过！！！！！");  
  
    }  
  
}  
