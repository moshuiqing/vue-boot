package com.home.lh.system.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.home.lh.system.entity.SysUser;
import com.home.lh.system.service.ISysUserService;
import com.home.lh.util.po.ReturnMap;
import com.home.lh.util.sysutil.SimpleUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sysuser/")
public class SysUserApi {

	@Autowired
	private ISysUserService sysuserService;

	@ApiOperation(value = "app登录")
	@RequestMapping(value = "/appLogin", method = RequestMethod.POST)
	public ReturnMap login(@RequestBody SysUser user, HttpServletRequest request) {
		ReturnMap rm = new ReturnMap();
		if (SimpleUtils.isEmpty(user.getUserName(), user.getPassWord())) {
			rm.setCode(-1);
			rm.setMsg("用户名密码不能为空");

		} else {
			QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("userName", user.getUserName());
			SysUser u = sysuserService.getOne(queryWrapper);
			if (u == null) {
				rm.setCode(-1);
				rm.setMsg("用户不存在");
			} else {
				if (u.getPassWord().equalsIgnoreCase(user.getPassWord())) {
					rm.setCode(1);
					rm.setData(u);
					rm.setMsg("登录成功");
					request.getSession().setAttribute("APPUSER", u);
					// System.out.println(request.getSession().getId());
				} else {
					rm.setCode(-1);
					rm.setMsg("密码错误");
				}
			}
		}

		return rm;
	}

	@ApiOperation("判断是否登录")
	@PostMapping("isLogin")
	public ReturnMap isLogin(HttpServletRequest request) {
		ReturnMap rm = new ReturnMap();
		SysUser u= (SysUser) request.getSession().getAttribute("APPUSER");
		if(u!=null) {
			rm.setCode(1);
			rm.setMsg("已经登录了");
		}else {
			rm.setCode(-1);
			rm.setMsg("你还未登录，请进行登录");
		}
		
		
		return rm;

	}

}
