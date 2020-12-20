package com.lbq.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * CountDownLatch:闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 * @author 14378
 *
 */
public class CountDownLatchTest {

	
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(5);
		LatchDemo ld = new LatchDemo(latch);
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start));
	}
	
	@Test
	public void test1() {
		final CountDownLatch latch = new CountDownLatch(5);
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < 5; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						for(int i = 0; i < 50000; i++) {
							if(i % 2 == 0) {
								System.out.println(i);
							}
						}
					}finally {
						latch.countDown();
					}
				}
				
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start));
	}
}

class LatchDemo implements Runnable{

	private CountDownLatch latch;
	
	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		synchronized(this) {
			try {
				for(int i = 0; i < 50000; i++) {
					if(i % 2 == 0) {
						System.out.println(i);
					}
				}
			}finally {
				latch.countDown();
			}
		}
	}
	
}
