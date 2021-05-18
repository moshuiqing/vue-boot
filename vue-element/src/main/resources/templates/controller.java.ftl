
package ${package.Controller};
 
 
 
import java.util.Arrays;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.home.lh.system.service.${table.serviceName};
import com.home.lh.system.entity.${entity};
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


<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
 
/**
 * <p>
    * ${table.comment!} 前端控制器
    * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version v1.0
 */
<#if restControllerStyle>
@Api(value="${table.controllerName}",tags="")
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    @Autowired
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};
 
    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @RequestMapping(value = "/pageFound",method=RequestMethod.POST)
    public ReturnMap pageFound(${entity} ${entity?uncap_first}, LayuiPage p){
    	ReturnMap rm = new ReturnMap();
        LayuiResult result = new LayuiResult();
		Page<${entity}> page = new Page<>(p.getPage(), p.getLimit());
		QueryWrapper<${entity}> queryWrapper =new QueryWrapper<${entity}>();
		queryWrapper.setEntity(${entity?uncap_first});
		
		IPage<${entity}> iPage = ${(table.serviceName?substring(1))?uncap_first}.page(page, queryWrapper);
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
    public ReturnMap simpleFound(${entity} ${entity?uncap_first}){
    	ReturnMap rm = new ReturnMap();
    	QueryWrapper<${entity}> queryWrapper =new QueryWrapper<${entity}>();
    	queryWrapper.setEntity(${entity?uncap_first});
    	List<${entity}> ${(entity)?uncap_first}s =${(table.serviceName?substring(1))?uncap_first}.list(queryWrapper);
    	rm.setCode(1);
		rm.setMsg("查询成功");
		rm.setData(${(entity)?uncap_first}s);   
        return rm;
    }
    
    
    /**
   	  * 新增
     */
    @ApiOperation(value = "新增数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReturnMap add(${entity} ${entity?uncap_first}){
    	ReturnMap rm = new ReturnMap();
    	
    	Boolean flag=${(table.serviceName?substring(1))?uncap_first}.save(${entity?uncap_first});
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
			List<${entity}> ${entity?uncap_first}s = JSONArray.parseArray(jsonArr, ${entity}.class);
			Boolean flag = ${(table.serviceName?substring(1))?uncap_first}.saveBatch( ${entity?uncap_first}s);
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
    public ReturnMap update(${entity} ${entity?uncap_first}){
   		ReturnMap rm = new ReturnMap();
    	boolean flag= ${(table.serviceName?substring(1))?uncap_first}.updateById(${entity?uncap_first});
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
			boolean flag = ${(table.serviceName?substring(1))?uncap_first}.removeById(id);
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
			boolean flag = ${(table.serviceName?substring(1))?uncap_first}.removeByIds(list);
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
</#if>
