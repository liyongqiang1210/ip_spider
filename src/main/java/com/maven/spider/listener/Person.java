package com.maven.spider.listener;

public class Person {
	// 1.1首先定义一个私有的、空的监听器对象，用来接收传递进来的事件监听器(相当于一个全局变量)
	private PersonListener listener;

	// 1.2将传递进来的事件监听器付给1.1中的listener
	public void registerListener(PersonListener personListener) {
		this.listener = personListener;
	}

	// 1.3判断listener是否为null，如果不为空，则执行监听器中的方法,否则照常调用
	public void run() {
		if (listener != null) {
			Even even = new Even(this);
			this.listener.dorun(even);
		}
		System.out.println("人具有跑的方法");
	}

	// 1.4和1.3一个道理
	public void eat() {
		if (listener != null) {
			Even even = new Even(this);
			this.listener.doeat(even);
		}
		System.out.println("人具有吃的方法");
	}
}
