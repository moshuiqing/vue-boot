
package com.home.lh.system.controller;
 
 
 
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.home.lh.system.entity.SysLog;
import com.home.lh.system.service.ISysLogService;
import com.home.lh.util.po.LayuiPage;
import com.home.lh.util.po.LayuiResult;
import com.home.lh.util.po.ReturnMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author 刘浩
 * @since 2021-05-06
 * @version v1.0
 */
@Api(value="SysLogController",tags="")
@RestController
@RequestMapping("/system/sys-log")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
 
    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @RequestMapping(value = "/pageFound",method=RequestMethod.POST)
    public ReturnMap pageFound(SysLog sysLog, LayuiPage p){
    	ReturnMap rm = new ReturnMap();
        LayuiResult result = new LayuiResult();
		Page<SysLog> page = new Page<>(p.getPage(), p.getLimit());
		QueryWrapper<SysLog> queryWrapper =new QueryWrapper<SysLog>();
		queryWrapper.orderByDesc("operatingTime");
		queryWrapper.setEntity(sysLog);
		
		IPage<SysLog> iPage = sysLogService.page(page, queryWrapper);
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
    public ReturnMap simpleFound(SysLog sysLog){
    	ReturnMap rm = new ReturnMap();
    	QueryWrapper<SysLog> queryWrapper =new QueryWrapper<SysLog>();
    	queryWrapper.setEntity(sysLog);
    	List<SysLog> sysLogs =sysLogService.list(queryWrapper);
    	rm.setCode(1);
		rm.setMsg("查询成功");
		rm.setData(sysLogs);   
        return rm;
    }
    
    
    /**
   	  * 新增
     */
    @ApiOperation(value = "新增数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReturnMap add(SysLog sysLog){
    	ReturnMap rm = new ReturnMap();
    	
    	Boolean flag=sysLogService.save(sysLog);
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
			List<SysLog> sysLogs = JSONArray.parseArray(jsonArr, SysLog.class);
			Boolean flag = sysLogService.saveBatch( sysLogs);
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
    public ReturnMap update(SysLog sysLog){
   		ReturnMap rm = new ReturnMap();
    	boolean flag= sysLogService.updateById(sysLog);
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
			boolean flag = sysLogService.removeById(id);
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
			boolean flag = sysLogService.removeByIds(list);
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
