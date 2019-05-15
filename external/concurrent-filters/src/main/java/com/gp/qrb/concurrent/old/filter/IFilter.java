package com.gp.qrb.concurrent.old.filter;

/**
 * @Descriptioin
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 11:08
 */
public interface IFilter<T> {

	/**
	 * 过滤方法
	 *
	 * @param t
	 */
	void filter(T t);
}
