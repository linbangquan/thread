package com.lbq.thread;
/*
 * 例子：创建三个窗口卖票，总票数为100张
 */

class Window extends Thread{
//	private int ticket = 100;
	public static int ticket = 100;
	
	private static Object obj = new Object();
	@Override
	public void run() {
		while(true) {
//			synchronized(Window.class) {
//				if(ticket > 0) {
////					try {
////						Thread.sleep(1000);
////					} catch (InterruptedException e) {
////						e.printStackTrace();
////					}
//					System.out.println(getName() + ": 卖票，票号为：" + ticket);
//					ticket --;
//				}else {
//					break;
//				}
//			}
			show();
		}
	}
	
	private static synchronized void show() {
		if(ticket > 0) {
			System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
			ticket --;
		}
	}
}
public class WindowTest {

	public static void main(String[] args) {
		Window t1 = new Window();
		Window t2 = new Window();
		Window t3 = new Window();
		
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
