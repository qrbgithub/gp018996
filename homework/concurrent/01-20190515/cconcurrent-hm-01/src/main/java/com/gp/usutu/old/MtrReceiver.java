package com.gp.usutu.old;

import com.gp.usutu.entity.Frame;
import com.gp.usutu.entity.MtRequest;
import com.gp.usutu.entity.Pack;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Descriptioin 基于netty实现的一盒http服务端（netty部分省略）
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/14 17:42
 */
public class MtrReceiver {

	private BlockingQueue<Frame> buffer = new LinkedBlockingQueue<>(1000000 * 5);

	@PostConstruct
	public void init() {
		new Thread(new SendTask()).start();
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
