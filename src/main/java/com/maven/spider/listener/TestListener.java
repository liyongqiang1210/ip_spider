package com.maven.spider.listener;

public class TestListener {
	public static void main(String[] args) {
        Person person=new Person();
        person.registerListener(new MyPersonListener());
        person.run();
        person.eat();
    }
}

//实现监听器接口中的方法
class MyPersonListener implements PersonListener{

	public void dorun(Even even) {
		Person p = even.getPerson();
		System.out.println("人在跑之前执行的动作");
	}

	public void doeat(Even even) {
		System.out.println("人在吃之前执行的动作");
	}

}
