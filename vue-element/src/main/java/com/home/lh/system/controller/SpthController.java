package com.home.lh.system.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.lh.util.po.ReturnMap;
import com.home.lh.util.spth.SpthQmUtil;

@RestController
@RequestMapping("/system/spth")
public class SpthController {
	
	@RequestMapping(value="getInfo",method=RequestMethod.POST)
	public ReturnMap getInfo() {
		ReturnMap rm = new ReturnMap();
		Map<String, Object> map= SpthQmUtil.getQm();
		map.put("userId","123");
		map.put("userName", "admin");
		map.put("roomId", "1");
		rm.setCode(1);
		rm.setData(map);
		return rm;
	}

}
