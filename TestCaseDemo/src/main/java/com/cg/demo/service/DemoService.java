package com.cg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cg.demo.util.CommonUtil;

@Service
public class DemoService {
	
	@Value("${emp.name}")
	private String name;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public String processString(String str) {
		
		if(name == null) {
			throw new RuntimeException();
		}
		
		return commonUtil.appendString(str + name);
	}
}
