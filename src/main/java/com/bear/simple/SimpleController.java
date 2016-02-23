package com.bear.simple;

import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;

public class SimpleController extends Controller{

	public void index(){
		setAttr("user", new User("Jack", 18));
		setAttr("lists", Lists.newArrayList("a","b","c"));
		
		Map<String, String> map = Maps.newHashMap();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("d", "4");
		setAttr("map", map);
		
		render("vue.html");
	}
	
	public void toBeetl(){
		render("index.html");
	}
}
