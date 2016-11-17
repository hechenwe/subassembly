package com.sooncode.subassembly.设计模式.修饰者模式;

/**
 * 加入舞蹈
 * @author pc
 *
 */
public class CompetitorDecoratorA extends CompetitorDecorator {
	public CompetitorDecoratorA(Competitor competitor) {
		super(competitor);
	}

	public void sing() {
		super.sing();
		this.dance();
	}

	private void dance() {
		System.out.println("偏偏起舞....");
	}

}