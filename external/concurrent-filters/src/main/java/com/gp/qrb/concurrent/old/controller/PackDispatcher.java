package com.gp.qrb.concurrent.old.controller;

import com.gp.qrb.concurrent.old.entity.Frame;
import com.gp.qrb.concurrent.old.entity.MtResponse;
import com.gp.qrb.concurrent.old.entity.MtrRequest;
import com.gp.qrb.concurrent.old.entity.Pack;
import com.gp.qrb.concurrent.old.filter.FilterChainHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descriptioin
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 10:51
 */
@RestController
@RequestMapping("/pack")
public class PackDispatcher {

	@Autowired
	private FilterChainHelper filterChainHelper;

	@RequestMapping("/dispatcher")
	public MtResponse dispatcher(MtrRequest request) {
		request = new MtrRequest();
		request.setBody(Pack.builder().body("test").build());

		Pack pack = request.getBody();

		//1、经过一系列的过滤器链，对数据进行一些合法性的校验的预处理
		filters(pack);

		//2、pack转化为frame数据，并把pack和frame持久化到db
		Frame frame = pack2Frame(pack);

		//3、将frame数据发送到下一模块
		String msg = send2Centrum(frame);

		//4、返回结果
		return MtResponse.builder().msg(msg).build();
	}

	private String send2Centrum(Frame frame) {

		System.out.println("发送到centrum");
		return "success";
	}

	private Frame pack2Frame(Pack pack) {

		return new Frame();
	}

	private void filters(Pack pack) {
		filterChainHelper.doFilter(pack);
	}

	@GetMapping("/test")
	public void test() {
		System.out.println("come ...");
	}

}
