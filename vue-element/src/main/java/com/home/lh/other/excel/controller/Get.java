package com.home.lh.other.excel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.lh.other.excel.utile.Dome;
import com.home.lh.system.entity.SysUser;
import com.home.lh.system.service.ISysUserService;

@RequestMapping("/excel")
@RestController
public class Get {
	
	@Autowired
	private ISysUserService iSysuserService;
	
	@RequestMapping("/get")
	public void get(HttpServletResponse response) {
		
		Dome d = new Dome();
		String[] name = {"模板"};
		List<SysUser> list = new ArrayList<SysUser>();
		d.daoRu(response,name , "用户信息", list);
	}

}
