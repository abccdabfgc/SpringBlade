/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smallchill.common.config;

import com.smallchill.common.intercept.DefaultSelectFactory;
import com.smallchill.common.plugins.GlobalPlugin;
import com.smallchill.core.constant.Cst;
import com.smallchill.core.interfaces.ICache;
import com.smallchill.core.interfaces.IConfig;
import com.smallchill.core.interfaces.IPluginFactroy;
import com.smallchill.core.shiro.DefaultShiroFactory;
import com.smallchill.core.toolbox.cache.EhcacheFactory;
import com.smallchill.core.toolbox.file.DefaultFileProxyFactory;
import com.smallchill.core.toolbox.grid.JqGridFactory;
import com.smallchill.core.toolbox.kit.DateKit;
import com.smallchill.core.toolbox.kit.Prop;
import com.smallchill.core.toolbox.kit.PropKit;

public class WebConfig implements IConfig {

	/** 
	 * 全局参数设置
	 */
	public void globalConstants(Cst me) {
		Prop prop = PropKit.use("config/config.properties");
		
		//设定开发模式
		me.setDevMode(prop.getBoolean("config.devMode", false));
		
		//设定文件上传是否为远程模式
		me.setRemoteMode(prop.getBoolean("config.remoteMode", false));
		
		//远程上传地址
		me.setRemotePath(prop.get("config.remotePath", ""));
		
		//设定文件上传头文件夹
		me.setUploadPath(prop.get("config.uploadPath", "/upload"));
		
		//设定文件下载头文件夹
		me.setDownloadPath(prop.get("config.downloadPath", "/download"));

		//设定grid工厂类
		me.setDefaultGridFactory(new JqGridFactory());
		
		//设定select工厂类
		me.setDefaultSelectFactory(new DefaultSelectFactory());
		
		//设定shiro工厂类
		me.setDefaultShiroFactory(new DefaultShiroFactory());
		
		//设定文件代理工厂类
		me.setDefaultFileProxyFactory(new DefaultFileProxyFactory());
		
		me.setDefaultCacheFactory(getDefaultCacheFactory());
	}

	/** 
	 * 自定义插件注册
	 */
	public void registerPlugins(IPluginFactroy plugins) {
		plugins.register(new GlobalPlugin());
		
		
	}

	/** 
	 * 全局自定义设置
	 */
	public void globalSettings() {
		
	}

	/** 
	 * 工程启动完毕执行逻辑
	 */
	public void afterBladeStart() {
		System.out.println(DateKit.getMsTime() + "	after blade start, you can do something~~~~~~~~~~~~~~~~");
	}

	/** 
	 * 工程关闭执行逻辑
	 */
	public void afterBladeStop() {
		
	}


	/**
	 * 缓存工厂
	 */
	private ICache defaultCacheFactory = new EhcacheFactory();
	
	public ICache getDefaultCacheFactory() {
		return defaultCacheFactory;
	}

	public void setDefaultCacheFactory(ICache defaultCacheFactory) {
		this.defaultCacheFactory = defaultCacheFactory;
	}
}
