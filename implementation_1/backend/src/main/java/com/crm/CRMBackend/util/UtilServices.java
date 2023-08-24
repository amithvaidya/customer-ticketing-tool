package com.crm.CRMBackend.util;

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
	
}
