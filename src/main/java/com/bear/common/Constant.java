package com.bear.common;

import java.util.Map;

import com.google.common.collect.Maps;

public class Constant {

	public static Map<String, Object> viewConstantMap = Maps.newHashMap();
	
	static{
		// beetl渲染全局静态常量配置
		viewConstantMap.put("logoPath", "#");
	}
}
