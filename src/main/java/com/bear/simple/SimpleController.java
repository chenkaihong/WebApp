package com.bear.simple;

import com.jfinal.core.Controller;

public class SimpleController extends Controller{

	public void index(){
		setAttr("user", new User("Jack", 18));
		render("vue.html");
	}
	
}
