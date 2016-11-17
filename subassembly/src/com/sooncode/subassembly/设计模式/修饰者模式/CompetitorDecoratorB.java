package com.sooncode.subassembly.设计模式.修饰者模式;

/**
 * 加入背景音乐
 * @author pc
 *
 */
public class CompetitorDecoratorB extends CompetitorDecorator {  
    public CompetitorDecoratorB(Competitor competitor) {  
        super(competitor);  
    }  
    public void sing()  
    {  
    	super.sing();  
        this.playMusic();  
    }  
    private void playMusic()  
    {  
        System.out.println("播放背景音乐......");  
    }  
}  
