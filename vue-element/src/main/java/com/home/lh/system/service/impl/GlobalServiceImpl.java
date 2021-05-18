package com.home.lh.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.home.lh.system.entity.SysMenu;
import com.home.lh.system.service.GlobalService;
import com.home.lh.system.service.ISysMenuService;

@Service
public class GlobalServiceImpl implements GlobalService {

	@Autowired
	private ISysMenuService menuService;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public List<SysMenu> treeList() {
		List<SysMenu> menuList = menuService.list();// 此处默认查所有的，可以改成根据用户id去查菜单，或者用户role去查询菜单，mapper里面配置自己写下
		List<SysMenu> result = menuList.stream().filter(menu -> menu.getParentId() == null)
				.map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
		return result;
	}

	/*
	 * 将UmsMenu转化为UmsMenuNode并设置children属性
	 */
	private SysMenu covertMenuNode(SysMenu menu, List<SysMenu> menuList) {
		SysMenu node = new SysMenu();
		BeanUtils.copyProperties(menu, node);
		List<SysMenu> children = menuList.stream().filter(subMenu -> subMenu.getParentId().equals(menu.getId())) // 过滤
				.map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
		node.setChildren(children);
		return node;
	}

}
