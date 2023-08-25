package com.crm.CRMBackend.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UtilServices {
	
	public String timeParser(Long time) {
		Long days, hours, mins, sec;
		String ret = "";
		if(time > 86400) {
			days = time / 86400;
			time %= 86400;
			ret+= days+"d, ";
		}
		if(time > 3600) {
			hours = time/3600;
			time %= 3600;
			ret += hours+"h, ";
		}
		if(time > 60) {
			mins = time / 60;
			time %= 60;
			ret += mins +"m, ";
		}
		ret += time+"s";
		return ret;
	}
	
	
	private String queryBuilder(String base, Map<String, Object> params) {
		String query = base;
		int count=0;
		for(String key : params.keySet()) {
			if(params.get(key) != null && count==0) {
				query+="where "+key+"=? ";
				count++;
			}
			else if(params.get(key) != null) {
				query+=" and "+key+"=? ";
			}
			else {
				//Do nothing.
			}
		}
		
		return query;
	}
	
	
	private Map<String, Object> validParamValues(Map<String, Object> params){
		Map<String, Object> validMap = new HashMap<String, Object>();
		for(String key: params.keySet()) {
			if(params.get(key) != null) validMap.put(key, params.get(key));
		}
		return validMap;
	}
}
