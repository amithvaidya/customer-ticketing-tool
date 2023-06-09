package com.crm.CRMBackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Webpages {
	
	@RequestMapping(value = "/views/ticket-view", method = RequestMethod.GET)
	public String tickets(@RequestParam String ticketId, Model model) {
		model.addAttribute("ticketId",1);
		return "tickets";
	}
}
