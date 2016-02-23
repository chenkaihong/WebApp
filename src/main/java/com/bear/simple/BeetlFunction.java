package com.bear.simple;

import org.beetl.core.Context;
import org.beetl.core.Function;

public class BeetlFunction implements Function{

	@Override
	public Object call(Object[] paras, Context ctx) {
		return "Hi beetl function!";
	}
}
