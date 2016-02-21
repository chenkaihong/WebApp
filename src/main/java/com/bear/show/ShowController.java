package com.bear.show;

import com.bear.login.LoginInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(LoginInterceptor.class)
public class ShowController extends Controller{
	
	public void toExample(){
		redirect("/static/show/hospital_example/hospital_example_1.0.html");
	}
	
	public void toModel(){
		redirect("/static/show/hospital_model/index.html");
	}
	
	public void toSuggest(){
		render("/suggest.html");	
	}
}
