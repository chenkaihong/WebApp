package com.bear.login;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Object obj = inv.getController().getSession().getAttribute("isLogin");
		if(obj == null){
			controller.render("/login/login.html");
			return;
		}
		inv.invoke();
	}
}
