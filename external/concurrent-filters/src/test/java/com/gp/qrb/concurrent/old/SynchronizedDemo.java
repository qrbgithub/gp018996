package com.gp.qrb.concurrent.old;

/**
 * @Descriptioin
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/13 17:59
 */

public class SynchronizedDemo implements Runnable {
	int x = 100;

	public synchronized void m1() {
		x = 1000;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("x=" + x);
	}

	public synchronized void m2() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		x = 2000;
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizedDemo sd = new SynchronizedDemo();
		new Thread(() -> sd.m1()).start();
		new Thread(() -> sd.m2()).start();
		sd.m2();
		System.out.println("Main x=" + sd.x);

	}

	@Override
	public void run() {
		m1();
	}
}
