package com.topcheer.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.topcheer.model.ChartInfo;
import com.topcheer.service.ICommService;

public class ReportUtil {
	static Font font =new Font("宋体",0,15);
	static String CHART_BIR = "Bir";
	static String CHART_PIE = "Pie";
	static String CHART_LINE = "Line";
	static String CHART_LINEXY = "LineXY";
	public static ChartInfo getSearchMap(HttpServletRequest request) throws Exception{
		String tableName = request.getParameter("tableName");
		String where = request.getParameter("where");
		String orderBy = request.getParameter("orderBy");
		//取得值  type;value1;value2  相当于  类型;Y;X
		String parameter = request.getParameter("parameter");
		String sql = request.getParameter("sql");
		
		String serviceObjId = request.getParameter("serviceObjId");
		String  methodName = request.getParameter("methodName");
		String  constantKeys = request.getParameter("constantKeys");
		String  reportType = request.getParameter("reportType");
		String  reportTitle = request.getParameter("reportTitle");
		String  reportTitleX = request.getParameter("reportTitleX");
		String  reportTitleY = request.getParameter("reportTitleY");
		String  objectToMap = request.getParameter("objectToMap");
		
		ChartInfo info = new ChartInfo(tableName,where,orderBy,parameter,sql);
		info.setReportTitleX(StringUtil.isNull(reportTitleX)?"":reportTitleX);
		info.setReportTitleY(StringUtil.isNull(reportTitleX)?"":reportTitleY);
		info.setReportTitle(StringUtil.isNull(reportTitleX)?"":reportTitle);
		info.setReportType(StringUtil.isNull(reportType)?"Bir":reportType);
		info.setMethodName(methodName);
		info.setOrderBy(orderBy);
		info.setServiceObjId(serviceObjId);
		
		
		if(!StringUtil.isNull(serviceObjId)){
			Object obj = SpringContextUtil.getBean(serviceObjId);
			Map searchMap = new HashMap();
			searchMap.putAll(getWhere(where));
			List<Object> list = (List) MethodUtils.invokeMethod(obj, methodName, searchMap);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			if(!StringUtil.isNull(objectToMap)){
				dataList = convertObjectToMap(objectToMap, list, info);
			}else{
				String[] parameters = info.getParameter().split(";");
				for(Object map : list){
					Map<String, Object> dataMap =  new HashMap<String, Object>();
					dataMap.put("type", BeanUtils.getProperty(map, parameters[0]));
					dataMap.put("value1", BeanUtils.getProperty(map, parameters[1]));
					if(parameters.length==3)
						dataMap.put("value2", BeanUtils.getProperty(map, parameters[2]));
					dataList.add(dataMap);
				}
			}
			
			info.setDataList(dataList);
		}else if(!StringUtil.isNull(tableName)){
			Map searchMap = new HashMap();
			ICommService commService = (ICommService) SpringContextUtil.getBean("commService");
			searchMap.put("tableName", tableName);
			searchMap.putAll(getWhere(where));
			if(!StringUtil.isNull(orderBy)){
				searchMap.put("orderBy", orderBy);
			}
			
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			List<Map> valueMap = commService.searchTable(searchMap);
			if(!StringUtil.isNull(objectToMap)){
				dataList = convertObjectToMap(objectToMap, valueMap,info);
			}else{
				String[] parameters = info.getParameter().split(";");
				for(Map map : valueMap){
					Map<String, Object> dataMap =  new HashMap<String, Object>();
					dataMap.put("type", map.get(parameters[0].toString().toUpperCase()));
					dataMap.put("value1", map.get(parameters[1].toString().toUpperCase()));
					if(parameters.length==3)
						dataMap.put("value2", map.get(parameters[2].toString().toUpperCase()));
					dataList.add(dataMap);
				}
			}
			info.setDataList(dataList);
		}else if(!StringUtil.isNull(sql)){
			ICommService commService = (ICommService) SpringContextUtil.getBean("commService");
			List<Map> valueMapList = commService.searchSql(sql);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			if(!StringUtil.isNull(objectToMap)){
				dataList = convertObjectToMap(objectToMap, valueMapList,info);
			}else{
				for(Map map : valueMapList){
					Map<String, Object> dataMap =  new HashMap<String, Object>();
					dataMap.put("type", map.get("TYPE"));
					dataMap.put("value1", map.get("VALUE1"));
					dataMap.put("value2", map.get("VALUE2"));
					dataList.add(dataMap);
				}
			}
			info.setDataList(dataList);
		}
		
		return info;
		
	}
	
	public static void createReport(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ChartInfo info =  getSearchMap(request);
		if(!StringUtil.isNull(info.getReportType())){
			MethodUtils.invokeExactStaticMethod(ReportUtil.class, "create"+info.getReportType(), new Object[]{request,response,info},new Class[]{HttpServletRequest.class,HttpServletResponse.class,ChartInfo.class});
		}
		
		
	}
	
	public static void createBir(HttpServletRequest request, HttpServletResponse response,ChartInfo info) throws IOException, DocumentException {
//		Font font =new Font("宋体",0,15);
		Integer width = request.getParameter("width")==null?900:Integer.valueOf(request.getParameter("width"));
		Integer height = request.getParameter("height")==null?420:Integer.valueOf(request.getParameter("height"));
		
		JFreeChart chart =null;
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		List<String> typeList = new ArrayList<String>();
		List<Map<String, Object>> dataList = info.getDataList();
		for(Map<String, Object> data : dataList){
			String type = data.get("type").toString();
			Number value1 = new BigDecimal( data.get("value1").toString().replace(",", ""));
			String value2 = data.get("value2").toString();
			dataset.addValue(value1, type, value2);
			if(!typeList.contains(type)){
				typeList.add(type);
			}
		}
		
		String orientation = request.getParameter("orientation");
		if(!StringUtil.isNull(orientation)&&"1".equals(orientation)){
			chart = ChartFactory.createBarChart3D(info.getReportTitle(), info.getReportTitleX(),info.getReportTitleY(), dataset, PlotOrientation.HORIZONTAL, true, true, true);
		}else
			chart = ChartFactory.createBarChart3D(info.getReportTitle(), info.getReportTitleX(),info.getReportTitleY(), dataset, PlotOrientation.VERTICAL, true, true, true);
//		//背景样式
//		chart.setBackgroundPaint(Color.WHITE);
//		//字体模糊边界 
//		chart.setAntiAlias(false);
		setChartStyle(chart);
		
		
		//设置标题字体，解决中文乱码问题
		chart.getTitle().setFont(font);
		
		chart.getLegend().setItemFont(font);
		chart.setTitle(info.getReportTitle());
//		chart.getXYPlot().get
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.getDomainAxis().setLabelFont(font);
//		plot.getDomainAxis().setLabel(info.getReportTitleY());
		
		plot.getRangeAxis().setLabelFont(font);
//		plot.getRangeAxis().setLabel(info.getReportTitleX());
		plot.getRangeAxis().setAutoRangeMinimumSize(10d);

		BarRenderer3D   renderer   =   new   BarRenderer3D(); 
//		renderer.setBaseOutlinePaint(Color.BLACK); 
//		renderer.setWallPaint(Color.gray);//设置   Wall   的颜色 
//		//设置每种水果代表的柱的颜色 
//		renderer.setSeriesPaint(0,   new   Color(0,   0,   255)); 
//		renderer.setSeriesPaint(1,   new   Color(0,   100,   255)); 
//		renderer.setSeriesPaint(2,   Color.GREEN); 
//		renderer.setItemMargin(0.1);//设置每个地区所包含的平行柱的之间距离 
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//设置每个柱的数值 
		renderer.setItemLabelsVisible(true);//显示每个柱的数值 
		plot.setRenderer(renderer);//让上面对柱子的设置生效 
		plot.getRangeAxis().setAutoRange(false);
		plot.getRangeAxis().setAutoRangeMinimumSize(10d);
		
		
		
//		plot.setBaseItemLabelGenerator
//		ChartUtilities.saveChartAsJPEG(new File("d://jfreechar2.jpg"), chart, 600, 400);
		
		
		response.setContentType("image/png");
//		ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, width, 400);
//		if(!StringUtil.isNull(orientation)&&"1".equals(orientation)){
//			writeChartAsPNG(request,response, chart,420,900,info);
//		}else
		writeChartAsPNG(request,response, chart,width,height,info);

		
		
	}

	public static void createLine(HttpServletRequest request, HttpServletResponse response,ChartInfo info) throws Exception{
//		Font font =new Font("宋体",0,20);
		Integer width = request.getParameter("width")==null?900:Integer.valueOf(request.getParameter("width"));
		Integer height = request.getParameter("height")==null?420:Integer.valueOf(request.getParameter("height"));
		JFreeChart chart =null;
		DefaultCategoryDataset  dataset=new DefaultCategoryDataset();
		List<Map<String, Object>> dataList = info.getDataList();
		for(Map<String, Object> data : dataList){
			String type = data.get("type").toString();
			Number value1 = new BigDecimal( data.get("value1").toString().replace(",", ""));
			String value2 = data.get("value2").toString();
			dataset.addValue(value1, type, value2);
		}
		chart = ChartFactory.createLineChart(info.getReportTitle(), info.getReportTitleX(),info.getReportTitleY(), dataset, PlotOrientation.VERTICAL, true, true, true);
		//设置标题字体，解决中文乱码问题
		chart.getTitle().setFont(font);
		chart.getLegend().setItemFont(font);
		setChartStyle(chart);
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.getDomainAxis().setLabelFont(font);
		plot.getRangeAxis().setLabelFont(font);

		LineAndShapeRenderer  renderer =(LineAndShapeRenderer) plot.getRenderer();
		
		//显示各个节点的值
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemLabelsVisible(true);
		
		writeChartAsPNG(request,response, chart,width,height,info);
	}
	
	
	public static void createLineXY(HttpServletRequest request, HttpServletResponse response,ChartInfo info)throws Exception{
//		Font font =new Font("宋体",0,20);
		Integer width = request.getParameter("width")==null?900:Integer.valueOf(request.getParameter("width"));
		Integer height = request.getParameter("height")==null?420:Integer.valueOf(request.getParameter("height"));
		JFreeChart chart =null;
		Map<String,XYSeries> xySeriesMap = new HashMap<String, XYSeries>();
		List<Map<String, Object>> dataList = info.getDataList();
		for(Map<String, Object> data : dataList){
			String type = data.get("type").toString();
			XYSeries xySeries = xySeriesMap.get(type);
			if(xySeries==null){
				xySeries = new XYSeries(type);
				xySeriesMap.put(type, xySeries);
			}
			xySeries.add(new BigDecimal(data.get("value1").toString().replace(",", "")), new BigDecimal(data.get("value2").toString().replace(",", "")));
			
		}
		XYSeriesCollection xyCollection =new XYSeriesCollection();	
		Iterator<XYSeries> iterator = xySeriesMap.values().iterator();
		while(iterator.hasNext()){
			xyCollection.addSeries(iterator.next());
		}
		
		chart = ChartFactory.createXYLineChart(info.getReportTitle(), info.getReportTitleX(),info.getReportTitleY(), xyCollection, PlotOrientation.VERTICAL, true, true, true);
		setChartStyle(chart);
		
		//设置标题字体，解决中文乱码问题
		chart.getTitle().setFont(font);
		
		chart.getLegend().setItemFont(font);
		chart.setAntiAlias(false);
//		chart.
		XYPlot plot =  (XYPlot) chart.getPlot();
		plot.getDomainAxis().setLabelFont(font);
		plot.getRangeAxis().setLabelFont(font);

		plot.setForegroundAlpha(1f);
		XYItemRenderer  renderer = plot.getRenderer();
		
		//显示各个节点的值
		//Renderer 中的带有Generator的方法就是设置节点的数值
		renderer.setItemLabelGenerator(new StandardXYItemLabelGenerator());
		renderer.setItemLabelsVisible(true);
//		//设置文字注释
//		XYTextAnnotation annotation =new XYTextAnnotation("ssss",111.2D, 25.0d);
//		annotation.setRotationAngle(22d);
//		plot.addAnnotation(annotation);
//		annotation.setFont(new Font("宋体",0,15));
		writeChartAsPNG(request,response, chart,width,height,info);
	}
	
	
	//饼图
	public static void createPie(HttpServletRequest request, HttpServletResponse response,ChartInfo info) throws Exception{
//		Font font =new Font("宋体",0,20);
		Integer width = request.getParameter("width")==null?900:Integer.valueOf(request.getParameter("width"));
		Integer height = request.getParameter("height")==null?420:Integer.valueOf(request.getParameter("height"));
		JFreeChart chart =null;
		DefaultPieDataset dataset = new DefaultPieDataset();
		List<Map<String, Object>> dataList = info.getDataList();
		for(Map<String, Object> data : dataList){
			String type = data.get("type").toString();
			Number value1 = new BigDecimal( data.get("value1").toString().replace(",", ""));
			//String value2 = data.get("value2").toString();
			 dataset.setValue(type,value1);
		}
		
		
		
		chart = ChartFactory.createPieChart3D(info.getReportTitle(), dataset, true, false, false);
		setChartStyle(chart);
		//设置标题字体，解决中文乱码问题
		chart.getTitle().setFont(font);
		chart.getLegend().setItemFont(font);
		//背景样式
		chart.setBackgroundPaint(Color.WHITE);
		//字体模糊边界 
		chart.setAntiAlias(false);
		
//		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		PiePlot pieplot = (PiePlot)chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, 12));
        pieplot.setNoDataMessage("无数据");
        pieplot.setCircular(true);
        pieplot.setLabelGap(0.02D);
        //设置显示的值 {0}{1}{2}  0：类别 ; 1:value  2：百分比
        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator( "{0} {2}",NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
		writeChartAsPNG(request,response, chart,width,height,info);
	}
	
	public static void setChartStyle(JFreeChart chart){
		if(chart==null)
			return;
		//背景样式
		chart.setBackgroundPaint(Color.WHITE);
		//字体模糊边界 
		chart.setAntiAlias(false);
	}
	
	public static void writeChartAsPNG(HttpServletRequest request,HttpServletResponse response,JFreeChart chart,int width,int height,ChartInfo info) throws IOException{
		response.setContentType("image/png");
		String donwload = request.getParameter("donwload");
		if(!StringUtil.isNull(donwload)){
			String contentType = "application/octet-stream";  
			response.setContentType("text/html;charset=UTF-8");  
		    response.setContentType(contentType);  
	        response.setHeader("Content-disposition", "attachment; filename="  
	                + new String((info.getReportTitle().replace("/", "")+".xls").getBytes("gbk"), "ISO8859-1"));
		}else
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, width, height);
	}
	
	public static void saveChartAsPDF(OutputStream out, JFreeChart chart,
			int width, int height, FontMapper mapper) throws IOException {
		Rectangle pagesize = new Rectangle(width, height);
		Document document = new Document(pagesize, 50, 50, 50, 50);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, out);
//			document.addAuthor("JFreeChart");
//			document.addSubject("Demonstration");
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfTemplate tp = cb.createTemplate(width, height);
			Graphics2D g2 = tp.createGraphics(width, height, mapper);
			Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
			chart.draw(g2, r2D);
			g2.dispose();
			cb.addTemplate(tp, 0, 0);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		}finally{
			document.close();
		}
	}
	
	
	//对应页面的字段是  objectToMap
	//name|类别|字段;字段                            类别也可以使对个要跟后面的对应
	//name|月份|月份对应字段不错
	//如果是  Pie饼图   那么就是 那么  类型|value1;value2....
	public static List<Map<String, Object>> convertObjectToMap(String str,List list,ChartInfo info) throws Exception{
		List<Map<String, Object>> dataMapList = new ArrayList<Map<String, Object>>();
		if(str==null){
			return null;
		}
		String[] strs = str.split("\\|");
		if(strs.length<2)
			return null;
		String name = strs[0];
		String[] types = strs[1].split(";");
		
		//饼图求和
		if(info.getReportType().equals(CHART_PIE)){
			for(Object obj : list){
				for(int i=0;i<types.length;i++){
					String value = BeanUtils.getProperty(obj, types[i]);
					String nameDisplay = BeanUtils.getProperty(obj, name);
					if(nameDisplay==null){
						nameDisplay = "";
					}
					Map map = null;
					for(Map temp : dataMapList){
						if(nameDisplay.equals(temp.get("type"))){
							map = temp;
							break;
						}
					}
					if(map==null){
						map = new HashMap();
						dataMapList.add(map);
					}
					
					map.put("type", nameDisplay);
					if(!StringUtil.isNull(value)){
						Object bef = map.get("value1");
						if(bef==null){
							map.put("value1", new BigDecimal(value.replace(",", "")));
						}else{
							map.put("value1", new BigDecimal(value.replace(",", "")).add(new BigDecimal(bef.toString())));
						}
						
					}
					dataMapList.add(map);
				}
			}
		}else{
			String[] fields = strs[2].split(";");
			for(Object obj : list){
				for(int i=0;i<fields.length;i++){
					Map map = new HashMap();
					String field = fields[i];
					String value = BeanUtils.getProperty(obj, field);
					String nameDisplay = BeanUtils.getProperty(obj, name);
					map.put("type", nameDisplay);
					map.put("value1", value);
					map.put("value2", types[i>=types.length?types.length-1:i]);
					dataMapList.add(map);
				}
				
			}
		}
		
		
		
		return dataMapList;
		
	}
	
	public static Map getWhere(String where){
		Map searchMap = new HashMap();
		if(!StringUtil.isNull(where)){
			String[] searchStrs = where.split(";");
			for(String searchStr : searchStrs){
				String[] strs = searchStr.split("=");
				if(strs.length==2)
					searchMap.put(strs[0], strs[1]);
			}
		}
		return searchMap;
	}
}


