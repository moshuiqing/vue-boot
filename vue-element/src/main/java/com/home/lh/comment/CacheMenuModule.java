package com.home.lh.comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.home.lh.config.redis.RedisService;
import com.home.lh.system.entity.SysMenu;
import com.home.lh.system.service.GlobalService;
import com.home.lh.util.po.Global;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ai996
 *  自定义初始化加载  （在所有配置类加载完成之后加载）
 */
@Component
@Order(1)
@Slf4j
public class CacheMenuModule implements CommandLineRunner {
	
	
	@Autowired
	private GlobalService globalService;
	
	@Resource
	private RedisService redisService;

	@Override
	public void run(String... args) throws Exception {
		try {
			List<SysMenu> menus= globalService.treeList();
			redisService.saveValue(Global.SYSMENUS, menus);
			log.info("菜单缓存");
		} catch (Exception e) {
			log.info("缓存失败");
			e.printStackTrace();
		}
		
	}

}
