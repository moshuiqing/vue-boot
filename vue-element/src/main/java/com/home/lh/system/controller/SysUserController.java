
package com.home.lh.system.controller;
 
 
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.home.lh.system.service.ISysUserService;
import com.home.lh.system.entity.SysMenu;
import com.home.lh.system.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.home.lh.util.po.Global;
import com.home.lh.util.po.LayuiPage;
import com.home.lh.util.po.LayuiResult;
import com.home.lh.util.po.ReturnMap;
import com.home.lh.util.sysutil.SimpleUtils;

import org.springframework.web.bind.annotation.RestController;
 
/**
 * <p>
    * 系统用户 前端控制器
    * </p>
 *
 * @author 刘浩
 * @since 2021-05-06
 * @version v1.0
 */
@Api(value="SysUserController",tags="")
@RestController
@RequestMapping("/system/sys-user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;
 
    
    @ApiOperation(value = "后台登录")
   	@RequestMapping(value = "/login", method = RequestMethod.POST)
   	public ReturnMap login(@RequestBody SysUser user,HttpServletRequest request) {
   		ReturnMap rm  = new ReturnMap();
   		if(SimpleUtils.isEmpty(user.getUserName(),user.getPassWord())) {
   			rm.setCode(-1);
   			rm.setMsg("用户名密码不能为空");
   			
   		}else {
   			QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
   			queryWrapper.eq("userName", user.getUserName());
   			SysUser u= sysUserService.getOne(queryWrapper);
   			if(u==null) {
   				rm.setCode(-1);
   				rm.setMsg("用户不存在");
   			}else{
   				if(u.getPassWord().equalsIgnoreCase(user.getPassWord())) {
   					rm.setCode(1);
   					rm.setData(u);
   					rm.setMsg("登录成功");
   					request.getSession().setAttribute(Global.SYSUSER, u);
   					//System.out.println(request.getSession().getId());
   				}else {
   					rm.setCode(-1);
   					rm.setMsg("密码错误");
   				}
   			}
   		}
   		
   		return rm;
   	}
    @ApiOperation(value = "获取用户信息")
   	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public ReturnMap getUserInfo(HttpServletRequest request) {
    	ReturnMap rm = new ReturnMap();
    	System.out.println(request.getSession().getId());
    	Object u = request.getSession().getAttribute("SYSUSER");
    	if(u==null) {
    		rm.setCode(-100);
    		rm.setMsg("登录已失效");
    	}else {
    		rm.setCode(1);
    		rm.setData(u);
    		rm.setMsg("获取成功");
    	}   	
		return rm;
    	
    }
    

    @ApiOperation(value = " 获取我的菜单")
   	@RequestMapping(value = "/getMyMenus", method = RequestMethod.POST)
    public ReturnMap getMyMenus(HttpServletRequest request) {
    	//获取我的菜单
    	ReturnMap rm = new ReturnMap();
    	List<SysMenu> list = new ArrayList<SysMenu>();
    	SysMenu m = new SysMenu();
    	m.setId("3122222");
    	m.setPath("/form");
    	m.setComponent("Layout");
    	//m.setIcon("el-icon-s-platform");
    	
    	m.setHidden(false);
    	m.setParentId("");
    	m.setSort(0);
    	m.setIsdel("0");
    	List<SysMenu> list2 = new ArrayList<SysMenu>();
    	SysMenu m2 = new SysMenu();
    	
    	m2.setPath("index");
    	m2.setComponent("form/index");
    	m2.setTitle("测试管理");
    	m2.setHidden(false);
    	m2.setIcon("el-icon-s-platform");
    	m2.setChildren(null);
    	list2.add(m2);
    	m.setChildren(list2);
    	list.add(m);
    	rm.setCode(1);
    	rm.setData(list);
		return rm;
    	
    }

 
    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @RequestMapping(value = "/pageFound",method=RequestMethod.POST)
    public ReturnMap pageFound(SysUser sysuser, LayuiPage p){
    	ReturnMap rm = new ReturnMap();
        LayuiResult result = new LayuiResult();
		Page<SysUser> page = new Page<>(p.getPage(), p.getLimit());
		QueryWrapper<SysUser> queryWrapper =new QueryWrapper<SysUser>();
		queryWrapper.setEntity(sysuser);
		
		IPage<SysUser> iPage = sysUserService.page(page, queryWrapper);
		result.setCode(0);
		result.setCount(iPage.getTotal());
		result.setData(iPage.getRecords());
		result.setMsg("获取成功");
		rm.setCode(1);
		rm.setData(result);
		rm.setMsg("获取成功");
		return rm;
    }
    
    
    
 
    /**
   	  * 普通查询
     */
    @ApiOperation(value = "普通查询")
    @RequestMapping(value = "/simpleFound",method=RequestMethod.POST)
    public ReturnMap simpleFound(SysUser sysuser){
    	ReturnMap rm = new ReturnMap();
    	QueryWrapper<SysUser> queryWrapper =new QueryWrapper<SysUser>();
    	queryWrapper.setEntity(sysuser);
    	List<SysUser> sysusers =sysUserService.list(queryWrapper);
    	rm.setCode(1);
		rm.setMsg("查询成功");
		rm.setData(sysusers);   
        return rm;
    }
    
    
    /**
   	  * 新增
     */
    @ApiOperation(value = "新增数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReturnMap add(SysUser sysuser){
    	ReturnMap rm = new ReturnMap();
    	
    	Boolean flag=sysUserService.save(sysuser);
    	if (flag) {
			rm.setCode(1);
			rm.setMsg("操作成功");
		}else{
			rm.setCode(1);
			rm.setMsg("操作失败");
		}
        return rm;
    }
    
    
	/**
	 * 批量新增
	 */
	@ApiOperation(value = "批量新增")
	@RequestMapping(value = "/addBatch", method = RequestMethod.POST)
	public ReturnMap addBatch(String jsonArr) {
		ReturnMap rm = new ReturnMap();
		if (StringUtils.isBlank(jsonArr)) {
				rm.setCode(-1);
				rm.setMsg("缺少参数");
		} else {
			List<SysUser> sysusers = JSONArray.parseArray(jsonArr, SysUser.class);
			Boolean flag = sysUserService.saveBatch( sysusers);
			if (flag) {
				rm.setCode(1);
				rm.setMsg("操作成功");
			} else {
				rm.setCode(1);
				rm.setMsg("操作失败");
			}
		}

		return rm;
	}
	
	
	 /**
         * 修改
     */
    @ApiOperation(value = "更新数据")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnMap update(SysUser sysuser){
   		ReturnMap rm = new ReturnMap();
    	boolean flag= sysUserService.updateById(sysuser);
    	if(flag) {
				rm.setCode(1);
				rm.setMsg("操作成功");
			}else {
				rm.setCode(-1);
				rm.setMsg("操作失败");				
			}
    
        return rm;
     }

    
    
    
     /**
      * 批量删除
      */
    @ApiOperation(value = "删除数据")
    @RequestMapping(value = "/del",method=RequestMethod.POST)
    public ReturnMap delete(String str){
    	ReturnMap rm = new ReturnMap();
    	if(StringUtils.isEmpty(str)) {
			rm.setCode(-1);
			rm.setMsg("参数异常");
		}else {
			String[] arrids = str.split(",");			
			List<String> list = Arrays.asList(arrids);	
			boolean flag = sysUserService.removeByIds(list);
			if(flag) {
				rm.setCode(1);
				rm.setMsg("删除成功");
			}else {
				rm.setCode(-1);
				rm.setMsg("删除失败");				
			}
		}
        return rm;
    }

}
