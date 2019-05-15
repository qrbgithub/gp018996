package com.gp.usutu.optim;

import com.gp.usutu.entity.Frame;
import com.gp.usutu.entity.MtRequest;
import com.gp.usutu.entity.Pack;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * @Descriptioin
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/14 17:56
 */
public class MtrReceiver {

	private BlockingQueue<Frame> buffer = new LinkedBlockingQueue<>(1000000 * 5);

	private ExecutorService executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(500));

	private int sendThreadSize;

	@PostConstruct
	public void init() {
		//由于存网络抖动，存在发送的cen模块偶尔耗时很长，导致队列积压
		// 故此处优化为多线程发送，并且cen模块接收发送消息改为异步方式，提高cen的吞吐量
		for (int i = 0; i < sendThreadSize; i++) {
			executor.execute(new SendTask());
		}
	}


	/**
	 * MtRequest在netty的handler中处理
	 *
	 * @param request
	 */
	public void receiver(MtRequest request) {
		//1、数据转换
		Pack pack = convert(request);
		//2、一系列的过滤器对数据进行校验

		//3、剩余的合法数据放入到队列
		buffer.offer(pack.getFrame());
	}

	private Pack convert(MtRequest request) {

		return new Pack();
	}

	class SendTask implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Frame frame = buffer.take();
					send2CenServer(frame);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void send2CenServer(Frame frame) {
		//通过netty基于socket发送到下一模块
	}
}
