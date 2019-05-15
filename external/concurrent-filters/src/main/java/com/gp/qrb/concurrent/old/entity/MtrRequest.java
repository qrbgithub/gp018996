package com.gp.qrb.concurrent.old.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Descriptioin 请求实体
 * @Author <a href="18729908765@163.com">RenBo.Qin</a>
 * @Date 2019/5/10 10:52
 */
@Getter
@Setter
public class MtrRequest {

	private String appId;

	private String privateKey;

	private Pack body;
}
