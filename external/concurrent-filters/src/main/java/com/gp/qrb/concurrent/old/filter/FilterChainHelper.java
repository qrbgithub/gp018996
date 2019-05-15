package com.gp.qrb.concurrent.old.filter;

import com.gp.qrb.concurrent.old.entity.Pack;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Descriptioin 过滤器链统一注入启动类
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 11:23
 */
@Component
public class FilterChainHelper {

	private List<AbstractFilter> list = new LinkedList<>();

	private ExecutorService pool = new ThreadPoolExecutor(10, 30, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));

	public void doFilter(Pack pack) {
		if (this.list.isEmpty()) {
			return;
		}

		this.list.get(0).filter(pack);
	}

	public void register(AbstractFilter filter) {
		list.add(filter);
		pool.execute(filter);

		//设置nextFilter
		int count = 2;
		if (list.size() < count) {
			return;
		}

		list.get(list.size() - count).setNextFilter(filter);
	}

}
