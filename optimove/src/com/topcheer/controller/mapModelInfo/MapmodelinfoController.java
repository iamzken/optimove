package com.topcheer.controller.mapModelInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.topcheer.model.mapModelInfo.Mapmodelinfo;
import com.topcheer.model.modelAttribute.Modelattribute;
import com.topcheer.service.mapModelInfo.IMapmodelinfoService;
import com.topcheer.service.modelAttribute.IModelattributeService;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/mapmodelinfos")
public class MapmodelinfoController {
	@Autowired
	private IMapmodelinfoService mapmodelinfoService;
	@Autowired
	private IModelattributeService modelattributeService;
	
	@RequestMapping("/searchMapmodelinfo")
	@ResponseBody
	public Map<String,Object> searchMapmodelinfo(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		mapmodelinfoService.searchMapmodelinfo(Page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/getMapmodelinfo")
	@ResponseBody
	public Map<String,Object> getMapmodelinfo(String modelid) {
		List<Mapmodelinfo> mapmodelinfo = mapmodelinfoService.getMapmodelinfo(modelid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, mapmodelinfo);
		return result;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Mapmodelinfo mapmodelinfo,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		//设置主键
		mapmodelinfo.setModelid(UUID.randomUUID().toString());
		//设置属性表表名
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		mapmodelinfo.setModeldatatable("tb_" + format.format(new Date()));
		//设置创建时间
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapmodelinfo.setModelcreatetime(format.format(new Date()));
		mapmodelinfoService.insert(mapmodelinfo);
		//创建数据表
		createTable(mapmodelinfo, request, binding);
		//创建主键约束
		String sql = "alter table " + mapmodelinfo.getModeldatatable() + " add constraint pk_" + mapmodelinfo.getModeldatatable() + " primary key(id)";
		alterTable(sql);
		//根据数据表填写空间元数据
		sql = "insert into user_sdo_geom_metadata values('" + mapmodelinfo.getModeldatatable() + "', 'location', "
				+ "mdsys.sdo_dim_array( mdsys.sdo_dim_element('longitude',-180,180,10), "
				+ "mdsys.sdo_dim_element('latitude',-90,90,10) ), 8307)";
		alterTable(sql);
		//建立空间索引
		sql = "create index " + mapmodelinfo.getModeldatatable() + "_idx on "
				+ mapmodelinfo.getModeldatatable() + "(location) indextype is mdsys.spatial_index";
 		alterTable(sql);
		
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Mapmodelinfo mapmodelinfo,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		//设置修改时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapmodelinfo.setModelupdatetime((format.format(new Date())));
		mapmodelinfoService.update(mapmodelinfo);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String modelids) {
		if(modelids!=null){
			String[] _ids = modelids.split(",");
			for(String modelid : _ids){
				if(!modelid.equals("")){
					List<Mapmodelinfo> mapmodelinfo = mapmodelinfoService.getMapmodelinfo(modelid);
					for (Mapmodelinfo mi : mapmodelinfo) {
						modelattributeService.deleteByTablename(mi.getModeldatatable());
						dropTable(mi.getModeldatatable());
						alterTable("delete from user_sdo_geom_metadata where table_name='"+mi.getModeldatatable()+"'");
					}
					mapmodelinfoService.delete(modelid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/createTable")
	@ResponseBody
	public Map<String,Object> createTable(@Valid Mapmodelinfo mapmodelinfo,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		mapmodelinfoService.createTable(mapmodelinfo);
		return null;
	}
	
	@RequestMapping("/alterTable")
	@ResponseBody
	public Map<String,Object> alterTable(String sql) {
		Map<String,Object> attrMap = ResultJsonUtil.getResultMap();
		attrMap.put("alterSql", sql);
		mapmodelinfoService.alterTable(attrMap);
		return null;
	}
	
	@RequestMapping("/dropTable")
	@ResponseBody
	public Map<String,Object> dropTable(String tablename) {
		String dropSql = "drop table " + tablename;
		Map<String,Object> map = ResultJsonUtil.getResultMap();
		map.put("dropSql", dropSql);
		mapmodelinfoService.dropTable(map);
		return null;
	}
	
	@RequestMapping("/getModelData")
	@ResponseBody
	public Map<String,Object> getModelData(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		mapmodelinfoService.getModelData(Page.getSearchPageMap(request));
		return page.getPageReturn();
	}
	
	@RequestMapping("/importModelData")
	@ResponseBody
	public Map<String,Object> importModelData(HttpServletRequest request, HttpServletResponse response) {
		File uploadFile = null;
		try {
			uploadFile = uploadFile(request, response);
	        
			String tablename = request.getParameter("uploadTablename");
			InputStream iStream = new FileInputStream(uploadFile);
			Workbook workbook = new XSSFWorkbook(iStream);//excel2007以上
			for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workbook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				int colcount = 0;//总列数
				int longNum = 0;//经度所在列数
				int latNum = 1;//维度所在列数
				String cols = "(id";
				Row row1 = sheet.getRow(2);
				for (int colNum = 0; colNum < row1.getLastCellNum(); colNum++) {
					Cell cell = row1.getCell(colNum);
					if (cell == null) {
						continue;
					}
					if ("longitude".equals(getValue(cell))) {
						longNum = colNum;
					} else if ("latitude".equals(getValue(cell))) {
						latNum = colNum;
					} else {
						cols += "," + getValue(cell);
					}
					++colcount;
				}
				cols += ", location)";
				for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					String longlat = "mdsys.sdo_geometry( 2001, 8307, mdsys.sdo_point_type(longStr, latStr, 0), null, null )";
					String values = "'" + UUID.randomUUID().toString() + "'";
					for (int colNum = 0; colNum < colcount; colNum++) {
						Cell cell = row.getCell(colNum);
						if (cell == null) {
							values += ",''";
						} else {
							if (longNum == colNum) {
								longlat = longlat.replace("longStr", getValue(cell));
							} else if (latNum == colNum) {
								longlat = longlat.replace("latStr", getValue(cell));
							} else {
								values += ",'" + getValue(cell) + "'";
							}
						}
					}
					values = values + "," + longlat;
					String insertSql = "insert into " + tablename + cols + " values (" + values + ")";
					Map<String,Object> attrMap = ResultJsonUtil.getResultMap();
					attrMap.put("insertSql", insertSql);
					mapmodelinfoService.insertData(attrMap);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (uploadFile != null) {
				uploadFile.delete();
			}
		}
		return ResultJsonUtil.getResultMap();
	}
	
	private File uploadFile(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("fileId");
        // 获得文件名
        String realFileName = file.getOriginalFilename();
        // 获取路径
        String ctxPath = request.getSession().getServletContext().getRealPath( "/") + "mobileimages/";
        // 创建文件
        File dirPath = new File(ctxPath);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
        File uploadFile = new File(ctxPath + UUID.randomUUID() + "_"+realFileName);
        try {
			FileCopyUtils.copy(file.getBytes(), uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return uploadFile;
        
	}
	

	@RequestMapping("/uploadimgData")
	@ResponseBody
	public Map<String,Object> uploadimgData(HttpServletRequest request, HttpServletResponse response) {
		File file = uploadFile(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileName", file.getName());
		map.put("result", "ok");
		return map;
	}
	
	
	
	private String getValue(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (DateUtil.isCellDateFormatted(cell)) {
					short format = cell.getCellStyle().getDataFormat();
					SimpleDateFormat sdf = null;
					Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
					if (format == 22) {
						sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						return String.valueOf(sdf.format(date));
					} else if (format == 14 || format == 31) {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						return String.valueOf(sdf.format(date));
					} else if (format == 20 || format == 32) {
						sdf = new SimpleDateFormat("HH:mm:ss");
						return String.valueOf(sdf.format(date));
					} else if (format == 57) {
						sdf = new SimpleDateFormat("yyyy-MM");
						return String.valueOf(sdf.format(date));
					} else if (format == 58) {
						sdf = new SimpleDateFormat("MM-dd");
						return String.valueOf(sdf.format(date));
					} else {
						String dateFmt = cell.getCellStyle().getDataFormatString();
						return new CellDateFormatter(dateFmt).format(date);
					}
				} else {
					return String.valueOf(cell.getNumericCellValue());
				}
			} else {
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}

	@RequestMapping("/gettablename")
	@ResponseBody
	public Map<String,Object> gettablename(String modelid) {
		List<Mapmodelinfo> mapmodelinfo = mapmodelinfoService.getMapmodelinfo(modelid);
		Map<String,Object> result = new HashMap<String,Object>();
		String tablename = mapmodelinfo.get(0).getModeldatatable();
		result.put("tablename", tablename);
		return result;
	}
	
	@RequestMapping("/downloadFile")
	@ResponseBody
	public Map<String,Object> downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String tablename = request.getParameter("tablename");
		Map<String,Object> map = ResultJsonUtil.getResultMap();
		map.put("tablename", tablename);
		List<Modelattribute> modelattributes = modelattributeService.searchModelattribute(map);
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("sheet1");
		
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setColor(IndexedColors.RED.index);
		
		Row row = sheet.createRow(0);//第一行
		row.setHeight((short)400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, modelattributes.size()-1));
		Cell regCell = row.createCell(0);
		regCell.setCellType(Cell.CELL_TYPE_STRING);
		regCell.setCellValue("格式请勿改动！对应列没有值可以空着，日期格式数据请转成字符串格式数据");
		cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setWrapText(true);
		regCell.setCellStyle(cellStyle);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setFont(font);
		Row row1 = sheet.createRow(1);//第二行
		Row row2 = sheet.createRow(2);//第三行
		for (int i = 0; i < modelattributes.size(); i++) {
			sheet.setColumnWidth(i, 4000);
			Modelattribute modelattribute = modelattributes.get(i);
			Cell cell = row1.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(modelattribute.getModelattributename());
			cell.setCellStyle(cellStyle);
			
			Cell cell1 = row2.createCell(i);
			cell1.setCellType(Cell.CELL_TYPE_STRING);
			cell1.setCellValue(modelattribute.getModelattribute());
			cell1.setCellStyle(cellStyle);
		}
		
		String filename = tablename+".xls";
		try {
			if (workbook instanceof XSSFWorkbook) {
				filename += "x";
			}
			OutputStream out = response.getOutputStream();
			response.setContentType("application/msexcel;charset=UTF-8");
		    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("/insertModelData")
	@ResponseBody
	public Map<String,Object> insertModelData(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = UUID.randomUUID().toString();
		String tablename = request.getParameter("tablename");
		//根据tablename获取属性
		List<Modelattribute> modelattributes = new ArrayList<Modelattribute>();
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("tablename", tablename);
		modelattributes = modelattributeService.searchModelattribute(searchMap);
		StringBuffer sqlhead =new StringBuffer("insert into "+tablename+" (id,");
		StringBuffer value =new StringBuffer(" ('"+id+"',");
		String longitude = "";
		String latitude = "";
		for (int i = 0; i < modelattributes.size(); i++) {
			String attribute = modelattributes.get(i).getModelattribute();
			String attributevalue = request.getParameter(attribute);
			if(i == modelattributes.size()-1){
				if("longitude".equals(attribute)){
					longitude = attributevalue;
				}else if("latitude".equals(attribute)){
					latitude = attributevalue;
				}else{
					sqlhead.append(attribute);
					value.append("'"+attributevalue+"'");
				}
			}else{
				if("longitude".equals(attribute)){
					longitude = attributevalue;
				}else if("latitude".equals(attribute)){
					latitude = attributevalue;
				}else{
					sqlhead.append(attribute+",");
					value.append("'"+attributevalue+"',");
				}
			}
		}
		sqlhead.append(",location) ");
		value.append(",mdsys.sdo_geometry( 2001, 8307, mdsys.sdo_point_type(longStr, latStr, 0), null, null ))");
		String sql = sqlhead+"values"+value;
		String sql1 = sql.replace("longStr", longitude);
		String isql = sql1.replace("latStr", latitude);
		System.out.println("******************"+isql);
		Map<String,Object> attrMap = ResultJsonUtil.getResultMap();
		attrMap.put("insertSql", isql);
		mapmodelinfoService.insertData(attrMap);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/getSuperModelData")
	@ResponseBody
	public Map<String,Object> getSuperModelData(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		String tablename = request.getParameter("tablename");
		Map<String,Object> searchMap = new HashMap<String,Object>();
		List<Modelattribute> modelattributes = new ArrayList<Modelattribute>();
		searchMap.put("tablename", tablename);
		modelattributes = modelattributeService.searchModelattribute(searchMap);
		StringBuffer searchSql = new StringBuffer("select ");
		for (int i = 0; i < modelattributes.size(); i++) {
			String attribute = modelattributes.get(i).getModelattribute();
			if(i==modelattributes.size()-1){
				if("longitude".equals(attribute)){
					searchSql.append("nvl(t.location.sdo_point.x,0) as longitude");
				}else if("latitude".equals(attribute)){
					searchSql.append("nvl(t.location.sdo_point.y,0) as latitude");
				}else{
					searchSql.append("nvl(t."+attribute+",' ') as "+attribute);
				}
			}else{
				if("longitude".equals(attribute)){
					searchSql.append("nvl(t.location.sdo_point.x,0) as longitude,");
				}else if("latitude".equals(attribute)){
					searchSql.append("nvl(t.location.sdo_point.y,0) as latitude,");
				}else{
					searchSql.append("nvl(t."+attribute+",' ') as "+attribute+",");
				}
			}
		}
		searchSql.append(" from "+tablename+" t");
		//根据属性拼sql
//		String sql = "select nvl(t.address,' ') as address,t.picture,t.createtime,t.updatetime,nvl(t.name,' ') as name,t.location.sdo_point.x as longitude,t.location.sdo_point.y as latitude from tb_201501302058090897 t";
//		System.out.println("---------------"+searchSql);
		Map map2 = page.getSearchPageMap(request);
		map2.put("sql", searchSql.toString());
		mapmodelinfoService.getSuperModelData(map2);
		return page.getPageReturn();
	}
	
	@RequestMapping("/searchRegionModelData")
	@ResponseBody
	public Map<String,Object> searchRegionModelData(HttpServletRequest request) {
		Map<String, Object> returnMap  = new HashMap<String, Object>();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Page page = Page.newBuilder(request);
		String tablename = request.getParameter("tablename");
		String resultpoint = request.getParameter("resultpoint");
		if(resultpoint != null && !"".equals(resultpoint)){
			String[] splitresult = resultpoint.split(",");
			String topleftx = splitresult[0];
			String toplefty = splitresult[1];
			
			List<Modelattribute> modelattributes = new ArrayList<Modelattribute>();
			searchMap.put("tablename", tablename);
			modelattributes = modelattributeService.searchModelattribute(searchMap);
			StringBuffer searchSql = new StringBuffer("select ");
			for (int i = 0; i < modelattributes.size(); i++) {
				String attribute = modelattributes.get(i).getModelattribute();
				if(i==modelattributes.size()-1){
					if("longitude".equals(attribute)){
						searchSql.append("nvl(A.location.sdo_point.x,0) as longitude");
					}else if("latitude".equals(attribute)){
						searchSql.append("nvl(A.location.sdo_point.y,0) as latitude");
					}else{
						searchSql.append("nvl(A."+attribute+",' ') as "+attribute);
					}
				}else{
					if("longitude".equals(attribute)){
						searchSql.append("nvl(A.location.sdo_point.x,0) as longitude,");
					}else if("latitude".equals(attribute)){
						searchSql.append("nvl(A.location.sdo_point.y,0) as latitude,");
					}else{
						searchSql.append("nvl(A."+attribute+",' ') as "+attribute+",");
					}
				}
			}
			/* SELECT
			 *
		  FROM
		    optiMoveDemo A 
		  WHERE
		 SDO_FILTER(A.location, mdsys.sdo_geometry(2003,8307,NULL,  mdsys.sdo_elem_info_array(1,1003,1),mdsys.sdo_ordinate_array(116.074677,40.121141,116.065063,39.702961, 116.754456,39.718807, 116.721497,40.135841, 116.074677,40.121141)),  
		'querytype=WINDOW') = 'TRUE'  
		AND SDO_RELATE(A.location, mdsys.sdo_geometry(2003,8307,NULL,  mdsys.sdo_elem_info_array(1,1003,1), mdsys.sdo_ordinate_array(116.074677,40.121141,116.065063,39.702961, 116.754456,39.718807, 116.721497,40.135841, 116.074677,40.121141)),  
		'mask=INSIDE querytype=WINDOW') = 'TRUE' 
		  ORDER BY A.name*/
			StringBuffer selectsql = new StringBuffer(searchSql+" from ");
			selectsql.append(tablename+" A");
			StringBuffer conditionsql0  = new StringBuffer(" where sdo_filter(A.location,mdsys.sdo_geometry(2003,8307,null,mdsys.sdo_elem_info_array(1,1003,1),mdsys.sdo_ordinate_array("+resultpoint+","+topleftx+","+toplefty+")),'querytype=WINDOW')='TRUE'");
			StringBuffer conditionsql1 = new StringBuffer(" and sdo_relate(A.location,mdsys.sdo_geometry(2003,8307,null,mdsys.sdo_elem_info_array(1,1003,1),mdsys.sdo_ordinate_array("+resultpoint+","+topleftx+","+toplefty+")),'mask=INSIDE querytype=WINDOW') = 'TRUE' order by A.name");
			selectsql.append(conditionsql0);
			selectsql.append(conditionsql1);
			System.out.println("*****框选查询sql*******"+selectsql.toString());
			Map map2 = page.getSearchPageMap(request);
			map2.put("searchsql", selectsql.toString());
			mapmodelinfoService.searchRegionModelData(map2);
		}
		
		return  page.getPageReturn();
	}
	
	
}
