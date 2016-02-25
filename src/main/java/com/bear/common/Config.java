package com.bear.common;

import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.bear.common.model._MappingKit;
import com.bear.index.IndexController;
import com.bear.login.LoginController;
import com.bear.show.ShowController;
import com.bear.simple.SimpleController;
import com.bear.wx.ConfigController;
import com.bear.wx.HandleController;
import com.bear.wx.PayController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.json.JacksonFactory;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;

/**
 * API引导式配置
 */
public class Config extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// beetl渲染
		me.setMainRenderFactory(new BeetlRenderFactory());
		BeetlRenderFactory.groupTemplate.setSharedVars(Constant.viewConstantMap);
		
		me.setError404View("/404.html");
        me.setError500View("/500.html");
        me.setJsonFactory(new JacksonFactory());
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/simple", 	SimpleController.class);
		me.add("/show", 	ShowController.class);
		me.add("/login",	LoginController.class);
		
		me.add("/",    		IndexController.class,		"/index");
		me.add("/pay",		PayController.class,		"/pay");
		
		me.add("/handle", 	HandleController.class);
		me.add("/config", 	ConfigController.class);
	}
	
	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl").trim(), 
							   PropKit.get("user").trim(), 
							   PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		
		// ehcache配置
		me.add(new EhCachePlugin(PathKit.getRootClassPath() + "/ehcache.xml"));
		
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("base"));
	}
	
	public static void main(String[] args) {
		JFinal.start(PathKit.getWebRootPath() + "\\src\\main\\webapp", 8080, "/WebApp", 5);
	}
}
