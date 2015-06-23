package com.topcheer.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;

import com.topcheer.model.ColumnInfo;
import com.topcheer.model.Constant;
import com.topcheer.model.ReportInfo;

public class PoiUtil {
	static Map<String,String> fieldToTypeMap = new HashMap<String, String>();
	static Integer maxWidth = 100;
	static{
		fieldToTypeMap.put("jGStatus", "JGStatus");
		fieldToTypeMap.put("transType", "TransType");
		fieldToTypeMap.put("status", "Status");
		fieldToTypeMap.put("sendFlag", "SendFlag");
		fieldToTypeMap.put("transCode", "ResponseTradeType");
		fieldToTypeMap.put("frozenAccountMapper.type", "FrozenType");
		fieldToTypeMap.put("reverseAppsMapper.transType", "ReverseTransType");
	}
	
	public static void exportExcel(ReportInfo info,HttpServletResponse response,Map<String,List<Constant>> constatsMap) throws Exception{
		String contentType = "application/octet-stream";  
		response.setContentType("text/html;charset=UTF-8");  
	    response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String((info.getTitle().replace("/", "")+".xls").getBytes("gbk"), "ISO8859-1"));  //ISO8859-1
        
        //ÿһ�е���󳤶�
        Map<String,Integer> maxLenghtMap = new HashMap<String, Integer>();
        maxLenghtMap = getMaxLenghtMap(info,constatsMap);
        
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet(info.getTitle().replace("/", ""));
		int startRow = 0;
		if(!"false".equals(info.getIsShowTitle())){
			sheet.addMergedRegion(new Region(0, (short)0, 0, (short)(info.getColumnInfos().size()-1)));
			startRow=startRow+1;
			HSSFRow row1 = sheet.createRow((int) 0);
			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue(info.getTitle());
			HSSFCellStyle style1 = wb.createCellStyle();
			if(info.getTitleFontSize()!=null){
				HSSFFont font = wb.createFont();
				font.setFontHeight((short) (info.getTitleFontSize().shortValue()*20));
				style1.setFont(font);
			}
			if(info.getTitleHeight()!=null)
				row1.setHeight(info.getTitleHeight().shortValue());
			
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell1.setCellStyle(style1);
			
			if(!StringUtil.isNull(info.getSubTitle())){
				startRow++;
				sheet.addMergedRegion(new Region(1, (short)0, 1, (short)(info.getColumnInfos().size()-1)));
				HSSFRow subRow = sheet.createRow((int) 1);
				HSSFCell subCell = subRow.createCell(0);
				subCell.setCellValue(info.getSubTitle());
			}
			
		}
		
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) startRow);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		for( int i=0;i< info.getColumnInfos().size();i++){
			ColumnInfo column = info.getColumnInfos().get(i);
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(column.getTitle());
			HSSFCellStyle style = wb.createCellStyle();
			//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setAlignment(getAlign(column.getAlign()));
			cell.setCellStyle(style);
			//���ÿ��
			//sheet.setColumnWidth(i, column.getField().getBytes().length*2*256);
			Integer lenght = maxLenghtMap.get(column.getField())==null?column.getField().getBytes().length:maxLenghtMap.get(column.getField());
			if(maxWidth!=null&&maxWidth>0&&lenght>maxWidth)
				lenght = maxWidth;
				
			sheet.setColumnWidth(i, (lenght+3)*256);
		}
		

		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		List list = info.getDataList();

		for (int a = 0; a < list.size(); a++)
		{
			Object obj = list.get(a);
			HSSFRow rowDate = sheet.createRow(a + startRow+1);
			for( int i=0;i< info.getColumnInfos().size();i++){
				ColumnInfo column = info.getColumnInfos().get(i);
//				Object value = BeanUtils.getProperty(obj, column.getField());
				Class cls = obj.getClass();
				Field field = cls.getDeclaredField(column.getField());
				field.setAccessible(true); 
				Object value = field.get(obj);
				
				// ���Ĳ���������Ԫ�񣬲�����ֵ
				HSSFCell cell = rowDate.createCell((short) i);
				cell.setCellValue(getValue(info,constatsMap,column.getField(),value));
				HSSFCellStyle style = wb.createCellStyle();
//				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style.setAlignment(getAlign(column.getAlign()));
				cell.setCellStyle(style);
//				cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
//						.getBirth()));
			}
			
		}
		// �����������ļ��浽ָ��λ��
		try
		{
			wb.write(response.getOutputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static short getAlign(String align){
		short result = HSSFCellStyle.ALIGN_CENTER;
		if(align==null){
			result = HSSFCellStyle.ALIGN_CENTER;
		}else if("left".equals(align)){
			result = HSSFCellStyle.ALIGN_LEFT;
		}else if("right".equals(align)){
			result = HSSFCellStyle.ALIGN_RIGHT;
		}else if("center".equals(align)){
			result = HSSFCellStyle.ALIGN_CENTER;
		}else{
			result = HSSFCellStyle.ALIGN_CENTER;
		}
		
		return result;
	}
	
	public static Map<String,Integer> getMaxLenghtMap(ReportInfo info,Map<String,List<Constant>> constatsMap) throws Exception{
		//ÿһ�е���󳤶�
        Map<String,Integer> maxLenghtMap = new HashMap<String, Integer>();
		List<Object> dataList = info.getDataList();
		for( int i=0;i< info.getColumnInfos().size();i++){
			ColumnInfo column = info.getColumnInfos().get(i);
			
			int fieldLenght= column.getTitle().getBytes().length;
			String key = column.getField();
//			key = fieldToTypeMap.get(key)==null?key:fieldToTypeMap.get(key);
			key = fieldToTypeMap.get(info.getServiceObjId()+"."+key)==null?(fieldToTypeMap.get(key)==null?key:fieldToTypeMap.get(key)):fieldToTypeMap.get(info.getServiceObjId()+"."+key);
			if(constatsMap!=null){
				List<Constant> list = constatsMap.get(key.toUpperCase());
				if(list!=null){
					for(Constant constant : list){
						if(constant.getDisplay()!=null&&constant.getDisplay().getBytes().length>fieldLenght){
							fieldLenght = constant.getDisplay().getBytes().length;
						}
					}
				}
			}
			maxLenghtMap.put(column.getField(), fieldLenght);
			
			for(Object obj : dataList){
				Class cls = obj.getClass();
				Field field = cls.getDeclaredField(column.getField());
				field.setAccessible(true); 
				Object value = field.get(obj);
				int lenght = maxLenghtMap.get(column.getField());
				if(value!=null&&value.toString().getBytes().length>lenght){
					maxLenghtMap.put(column.getField(), value.toString().getBytes().length);
				}
			}
			
		}
		return maxLenghtMap;
	}
	
	public static  String getValue(ReportInfo info,Map<String,List<Constant>> constatsMap,String key,Object value){
		String result = value==null?"":value.toString();
//		key = fieldToTypeMap.get(key)==null?(fieldToTypeMap.get(info.getServiceObjId()+"."+key)==null?key:fieldToTypeMap.get(info.getServiceObjId()+"."+key)):fieldToTypeMap.get(key);
		key = fieldToTypeMap.get(info.getServiceObjId()+"."+key)==null?(fieldToTypeMap.get(key)==null?key:fieldToTypeMap.get(key)):fieldToTypeMap.get(info.getServiceObjId()+"."+key);
		if(constatsMap!=null&&value!=null){
			List<Constant> list = constatsMap.get(key.toUpperCase());
			if(list!=null){
				for(Constant constant : list){
					if(value.equals(constant.getValue())){
						result = constant.getDisplay();
						break;
					}
				}
			}
			
		}
		return result;
	}
}
