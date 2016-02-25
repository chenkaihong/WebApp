package com.bear.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bear.common.model.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

public class SimpleController extends Controller{

	public void index(){
		setAttr("user", new User("Jack", 18));
		setAttr("lists", Lists.newArrayList("a","b","c"));
		setAttr("citys", Lists.newArrayList("广州","北京","上海"));
		setAttr("name", "Tom");
		
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
	
	public void toJson() throws IOException{
		String json = getJson(getRequest());
		User user = JsonKit.parse(json, User.class);
		
		final long index = user.getId();
		CacheKit.get("user", "User"+user.getId(), new IDataLoader() {
			@Override
			public Object load() {
				return User.dao.findById(index);
			}
		});
		
		renderText(JsonKit.toJson(user));
	}
	
	public String getJson(HttpServletRequest req) throws IOException{
		String line = new BufferedReader(req.getReader()).readLine();
		return line;
	}
}
