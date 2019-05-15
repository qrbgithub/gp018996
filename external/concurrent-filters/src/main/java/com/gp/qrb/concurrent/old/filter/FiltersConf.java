package com.gp.qrb.concurrent.old.filter;

import com.gp.qrb.concurrent.old.filter.filters.BlackListFilter;
import com.gp.qrb.concurrent.old.filter.filters.CheckSignFilter;
import com.gp.qrb.concurrent.old.filter.filters.KeyWordFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Descriptioin
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 15:57
 */
@Configuration
public class FiltersConf {

	@Bean
	@Order(1)
	public CheckSignFilter checkSignFilter() {

		return new CheckSignFilter();
	}

	@Bean
	@Order(2)
	public BlackListFilter blackListFilter() {

		return new BlackListFilter();
	}

	@Bean
	@Order(3)
	public KeyWordFilter keyWordFilter() {

		return new KeyWordFilter();
	}

}
