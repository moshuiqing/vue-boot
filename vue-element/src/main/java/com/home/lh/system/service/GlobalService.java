package com.home.lh.system.service;

import java.util.List;

import com.home.lh.system.entity.SysMenu;

public interface GlobalService {
	
	/**
	 * 获取菜单
	 * @return
	 */
	public List<SysMenu> treeList();

}
