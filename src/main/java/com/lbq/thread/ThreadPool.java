package com.lbq.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/*
 * 创建线程的方式四：使用线程池
 */

class NumberThread implements Runnable{

	@Override
	public void run() {
		for(int i = 0; i <= 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" +i);
			}
		}
		
	}
	
}

class NumberThread2 implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i = 0; i <= 100; i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" +i);
				sum += i;
			}
		}
		return sum;
	}
	
}

public class ThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		service.execute(new NumberThread());//适合于runnable接口
		
//		FutureTask<Integer> future = new FutureTask<>(new NumberThread2());
//		service.submit(future);//适合于callable接口
		
//		Future<Integer> future = service.submit(new NumberThread2());
		
		try {
//			Integer sum = future.get();
			FutureTask<Integer> future = new FutureTask<>(new NumberThread2());
			Integer sum = 0;
			service.submit(future, sum);
			Integer sum2 = future.get();
			System.out.println("sum2:" + sum2);//2550
			System.out.println("sum:" + sum);//0
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		finally {
			
		}
		service.shutdown();
	}
}
