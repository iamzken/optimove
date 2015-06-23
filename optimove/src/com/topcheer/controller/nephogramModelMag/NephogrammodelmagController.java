package com.topcheer.controller.nephogramModelMag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.mapModelMag.Mapmodelmag;
import com.topcheer.model.nephogramAtrribute.Nephogramatrribute;
import com.topcheer.model.nephogramModelMag.Nephogrammodelmag;
import com.topcheer.service.nephogramAtrribute.INephogramatrributeService;
import com.topcheer.service.nephogramModelMag.INephogrammodelmagService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/nephogrammodelmags")
public class NephogrammodelmagController {
	@Autowired
	private INephogrammodelmagService nephogrammodelmagService;
	@Autowired
	private INephogramatrributeService nephogramatrributeService;

	@RequestMapping("/searchNephogrammodelmag")
	@ResponseBody
	public Map<String, Object> searchNephogrammodelmag(
			HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		nephogrammodelmagService.searchNephogrammodelmag(page
				.getSearchPageMap(request));
		// nephogramatrributeService.searchNephogramatrribute(page.getSearchPageMap(request));
		return page.getPageReturn();
	}

	@RequestMapping("/getNephogrammodelmag")
	@ResponseBody
	public Map<String, Object> getNephogrammodelmag(String nephogrammodelid) {
		List<Nephogrammodelmag> nephogrammodelmag = nephogrammodelmagService
				.getNephogrammodelmag(nephogrammodelid);
		Map<String, Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, nephogrammodelmag);
		return result;
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(
			@Valid Nephogrammodelmag nephogrammodelmag,
			HttpServletRequest request, BindingResult binding) {
		if (binding.hasErrors()) {
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),
					binding);
		}
		nephogrammodelmagService.insert(nephogrammodelmag);
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(
			@Valid Nephogrammodelmag nephogrammodelmag,
			HttpServletRequest request, BindingResult binding) {
		if (binding.hasErrors()) {
			return ResultJsonUtil.analyzeError(binding);
		}
		nephogrammodelmagService.update(nephogrammodelmag);
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String nephogrammodelids) {
		if (nephogrammodelids != null) {
			String[] _ids = nephogrammodelids.split(",");
			for (String nephogrammodelid : _ids) {
				if (!nephogrammodelid.equals("")) {
					nephogrammodelmagService.delete(nephogrammodelid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/searchAll")
	@ResponseBody
	public Map<String, String> searchAll() {
		List<Nephogrammodelmag> nephogrammodelmag = nephogrammodelmagService
				.searchAll();
		Map<String, String> map = new HashMap<String, String>();
		for (Nephogrammodelmag mapmodelmag2 : nephogrammodelmag) {
			map.put(mapmodelmag2.getNephogrammodelid(), mapmodelmag2
					.getNephogrammodelname());
		}
		return map;
	}

	@RequestMapping("/getNephogrammodelmagAttr")
	@ResponseBody
	public Map<String, Object> getNephogrammodelmagAttr(String nephogrammodelid) {
		Map map = new HashMap();
		map.put("nephogrammodelid", nephogrammodelid);
		List<Nephogramatrribute> nephogramatrribute = nephogramatrributeService
				.searchNephogramatrribute(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		for (Nephogramatrribute mapmodelmag2 : nephogramatrribute) {
			String attributename = mapmodelmag2.getAttributename();
			String atrributecode = mapmodelmag2.getNephogramattrid();
			map1.put(atrributecode, attributename);
		}
		return map1;
	}

}
