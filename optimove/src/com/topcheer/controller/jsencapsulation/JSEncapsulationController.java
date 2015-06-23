package com.topcheer.controller.jsencapsulation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.service.jsEncapsulationService.IJSEncapsulationService;

@Controller
@RequestMapping("/JSEncapsulation")
public class JSEncapsulationController {
	
	@Autowired
	private IJSEncapsulationService jsEncapsulationService;
	
	@RequestMapping("/getNBCBScripts")
	@ResponseBody
	public String getNBCBScripts(HttpServletRequest request) throws IOException {
		StringBuffer nbcbScripts = new StringBuffer("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("api", "http://api.map.baidu.com/getscript?v=2.0&ak=");
		map.put("key", "7d57dd79762ff3bb9aa21e651b44ba6e");
		map.put("provider", "baidu");
		
		map.put("api", "http://webapi.amap.com/maps?v=1.3&key=");
		map.put("key", "51793b5dac3a4367e632ef84f4e15c64");
		map.put("provider", "gaode");
		String mapScripts = jsEncapsulationService.getMapScripts(map);
		nbcbScripts.append(mapScripts);
		map.put("mainScriptsPath", request.getSession().getServletContext().getRealPath("/js/js-encapsulation/js-encapsulation."+map.get("provider")+".1.0.js"));
		String mainScripts = jsEncapsulationService.getMainScripts(map);
		nbcbScripts.append(";"+mainScripts);
		return nbcbScripts.toString();
	}

	public static void main(String[] args) {
		
	}

}
