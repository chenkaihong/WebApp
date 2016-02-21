package com.bear.login;

import com.jfinal.core.Controller;

public class LoginController extends Controller{

	public void index(){
		
		LoginAccess ac = getBean(LoginAccess.class, "loginAccess");
		
		if("hospital".equals(ac.getUsername()) && "huaduquzhongyiyuan".equals(ac.getPassword())){
			setSessionAttr("isLogin", "true");
			renderText("succ");
		}else{
			renderText("error");
		}
	}
}
