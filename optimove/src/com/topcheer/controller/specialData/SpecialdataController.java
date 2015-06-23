package com.topcheer.controller.specialData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.topcheer.model.specialData.Specialdata;
import com.topcheer.service.specialData.ISpecialdataService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;
import com.topcheer.utils.SaxParseXml;

@Controller
@RequestMapping("/specialdatas")
public class SpecialdataController {
	@Autowired
	private ISpecialdataService specialdataService;
	
	@RequestMapping("/searchSpecialdata")
	@ResponseBody
	public Map<String,Object> searchSpecialdata(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		specialdataService.searchSpecialdata(page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getSpecialdata")
	@ResponseBody
	public Map<String,Object> getSpecialdata(String specialdataid) {
		List<Specialdata> specialdata = specialdataService.getSpecialdata(specialdataid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, specialdata);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Specialdata specialdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		specialdataService.insert(specialdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Specialdata specialdata,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		specialdataService.update(specialdata);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String specialdataids) {
		if(specialdataids!=null){
			String[] _ids = specialdataids.split(",");
			for(String specialdataid : _ids){
				if(!specialdataid.equals("")){
					specialdataService.delete(specialdataid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/saxParseXml")
	@ResponseBody
	public Map<String,String> saxParseXml(String filePath) {
		String result = "";
		if (!"".equals(filePath)) {
			SAXParser parser = null;
	        try {
	        	//����SAX��������
		        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		        //����SAXParser
		        parser = parserFactory.newSAXParser();
		        //ʵ����DefaultHandler����
		        SaxParseXml handler = new SaxParseXml();
		        //������Դ�ļ� ת��Ϊһ��������
		        InputStream stream = new FileInputStream(new File(filePath));
		        //����parse()����
		        parser.parse(stream, handler);
	            //�������
	            List<Object> list = handler.getResultList();
	            result += list.size();
	            for(Object obj : list){
	                System.out.println(obj.toString());
	            }
	            stream.close();
	        } catch (IOException e) {
	            result="5";
	        } catch (ParserConfigurationException e) {
	        	result="5";
			} catch (SAXException e) {
				result="5";
			} finally {
	        	 
	        }
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", result);
		return map;
	}

}
