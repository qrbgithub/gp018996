package com.gp.qrb.concurrent.old.filter;

import com.gp.qrb.concurrent.old.entity.Pack;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Descriptioin
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 11:03
 */
public abstract class AbstractFilter implements IFilter<Pack>, Runnable {

	protected static volatile boolean finish = false;

	protected static BlockingQueue<Pack> queue = new LinkedBlockingDeque<>(Integer.MAX_VALUE);

	/**
	 * 由于在yml中配置了filter的执行顺序，故nextFilter通过filterChainHelper处理
	 */
	private AbstractFilter nextFilter;

	@Autowired
	protected FilterChainHelper filterChainHelper;

	/**
	 * 过滤器逻辑
	 *
	 * @param pack
	 */
	protected abstract boolean doFilter(Pack pack);

	/**
	 * 注册到filterChain 调用super.register()
	 */
	protected abstract void registerFilter();


	protected void register(AbstractFilter filter) {
		//注册到过滤器链
		filterChainHelper.register(filter);
	}


	@Override
	public void filter(Pack pack) {

		queue.offer(pack);
	}

	@Override
	public void run() {
		while (!finish) {
			//进行签名校验
			try {;
				Pack pack = queue.take();

				boolean flag = doFilter(pack);

				//交给下一个filter
				if (flag && nextFilter != null) {
					nextFilter.filter(pack);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setNextFilter(AbstractFilter nextFilter) {

		this.nextFilter = nextFilter;
	}
}
