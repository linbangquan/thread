package com.lbq.thread;

public class ThreadTest {

	public static void main(String[] args) {
		System.out.println("main start ...");
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
//		t1.run();
		t1.start();
		
//		t1.run();
		t2.start();
		System.out.println(t1.isAlive());
//		t1.start();
		for(int i = 0; i < 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" + i + " --");
			}
		}
		System.out.println(t1.isAlive());
		System.out.println("main end ...");
	}

}

class MyThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("MyThread start ...");
		for(int i = 0; i < 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
		}
		System.out.println("MyThread end ...");
	}
}
