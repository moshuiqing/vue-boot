
package com.home.lh.system.controller;
 
 
 
import java.util.Arrays;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.home.lh.system.service.ISysMenuService;
import com.home.lh.system.entity.SysMenu;
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
import com.home.lh.util.po.LayuiPage;
import com.home.lh.util.po.LayuiResult;
import com.home.lh.util.po.ReturnMap;


import org.springframework.web.bind.annotation.RestController;
 
/**
 * <p>
    * 系统菜单 前端控制器
    * </p>
 *
 * @author 刘浩
 * @since 2021-05-06
 * @version v1.0
 */
@Api(value="SysMenuController",tags="")
@RestController
@RequestMapping("/system/sys-menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;
 
    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @RequestMapping(value = "/pageFound",method=RequestMethod.POST)
    public LayuiResult pageFound(SysMenu sysMenu, LayuiPage p){
        LayuiResult result = new LayuiResult();
		Page<SysMenu> page = new Page<>(p.getPage(), p.getLimit());
		QueryWrapper<SysMenu> queryWrapper =new QueryWrapper<SysMenu>();
		queryWrapper.setEntity(sysMenu);
		
		IPage<SysMenu> iPage = sysMenuService.page(page, queryWrapper);
		result.setCode(0);
		result.setCount(iPage.getTotal());
		result.setData(iPage.getRecords());
		result.setMsg("获取成功");
		return result;
    }
    
    
    
 
    /**
   	  * 普通查询
     */
    @ApiOperation(value = "普通查询")
    @RequestMapping(value = "/simpleFound",method=RequestMethod.POST)
    public ReturnMap simpleFound(SysMenu sysMenu){
    	ReturnMap rm = new ReturnMap();
    	QueryWrapper<SysMenu> queryWrapper =new QueryWrapper<SysMenu>();
    	queryWrapper.setEntity(sysMenu);
    	List<SysMenu> sysMenus =sysMenuService.list(queryWrapper);
    	rm.setCode(1);
		rm.setMsg("查询成功");
		rm.setData(sysMenus);   
        return rm;
    }
    
    
    /**
   	  * 新增
     */
    @ApiOperation(value = "新增数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReturnMap add(SysMenu sysMenu){
    	ReturnMap rm = new ReturnMap();
    	
    	Boolean flag=sysMenuService.save(sysMenu);
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
			List<SysMenu> sysMenus = JSONArray.parseArray(jsonArr, SysMenu.class);
			Boolean flag = sysMenuService.saveBatch( sysMenus);
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
    public ReturnMap update(SysMenu sysMenu){
   		ReturnMap rm = new ReturnMap();
    	boolean flag= sysMenuService.updateById(sysMenu);
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
         * 删除
     */
    @ApiOperation(value = "删除数据")
    @RequestMapping(value = "/delete",method=RequestMethod.POST)
    public ReturnMap delete(String id) {
    	ReturnMap rm = new ReturnMap();
    	if(StringUtils.isEmpty(id)) {
			rm.setCode(-1);
			rm.setMsg("参数异常");
		}else {
			boolean flag = sysMenuService.removeById(id);
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

    
    
    
     /**
      * 批量删除
      */
    @ApiOperation(value = "批量删除数据")
    @RequestMapping(value = "/deletes",method=RequestMethod.POST)
    public ReturnMap deletes(String str){
    	ReturnMap rm = new ReturnMap();
    	if(StringUtils.isEmpty(str)) {
			rm.setCode(-1);
			rm.setMsg("参数异常");
		}else {
			String[] arrids = str.split(",");			
			List<String> list = Arrays.asList(arrids);	
			boolean flag = sysMenuService.removeByIds(list);
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
