package com.Leon.main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest {
	public static void main(String[] args){
		/*
		 * 第一种方法
		 */
//		function1();
		/*
		 * 第二种方法
		 */
//		function2();
		/*
		 * 第三种方法
		 */
		function3();
		
	}

	/*
	 * 第一种方法：常见的创建一个thread，放入一个while循环中
	 * 通过sleep方法完成相应的效果
	 * author Leon
	 */
	public static void function1(){
		final long time =1000;
		Runnable runnable = new Runnable(){

			
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					System.out.println("你好，世界！");
					try{
						Thread.sleep(time);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
			
		};
		Thread thread = new Thread(runnable);
		thread.start();		
	}
	
	/*
	 *  
	 * 于第一种方式相比，优势 1>当启动和去取消任务时可以控制 2>第一次执行任务时可以指定你想要的delay时间 
	 *  
	 * 在实现时，Timer类可以调度任务，TimerTask则是通过在run()方法里实现具体任务。 Timer实例可以调度多任务，它是线程安全的。 
	 * 当Timer的构造器被调用时，它创建了一个线程，这个线程可以用来调度任务。 下面是代码： 
	 *  
	 * @author Leon 
	 *  
	 */
	public static void function2(){
		TimerTask task = new TimerTask(){

			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("你好，世界！！");
			}
			
		};
		Timer timer =new Timer();
		long delay =0;
		long intevalPeriod = 1*1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}
	
	/*
	 *  
	 *  
	 * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。  
	 * 相比于上两个方法，它有以下好处： 
	 * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的  
	 * 2>可以很灵活的去设定第一次执行任务delay时间 
	 * 3>提供了良好的约定，以便设定执行的时间间隔 
	 *  
	 * 下面是实现代码，我们通过ScheduledExecutorService#scheduleAtFixedRate展示这个例子，通过代码里参数的控制，首次执行加了delay时间。 
	 *  
	 *  
	 * @author Leon
	 *  
	 */
	public static void function3(){
		Runnable runnable =new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("你好，世界！！！");
			}
			
		};
		ScheduledExecutorService service =Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
	}
}
