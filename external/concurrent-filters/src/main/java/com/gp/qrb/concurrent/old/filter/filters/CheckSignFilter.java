package com.gp.qrb.concurrent.old.filter.filters;

import com.gp.qrb.concurrent.old.entity.Pack;
import com.gp.qrb.concurrent.old.filter.AbstractFilter;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Descriptioin 签名filter
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 11:02
 */
public class CheckSignFilter extends AbstractFilter {

	@Override
	@PostConstruct
	public void registerFilter() {

		super.register(this);
	}

	@Override
	public boolean doFilter(Pack pack) {
		System.out.println("签名校验...");

		return true;
	}

}
