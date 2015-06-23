package com.topcheer.utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * @description 解决Date类型返回json格式为自定义格式
 * @author tanxf
 */
public class CustomObjectMapper extends ObjectMapper {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	public CustomObjectMapper(){
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){
			@Override
			public void serialize(Date value, 
					JsonGenerator jsonGenerator, 
					SerializerProvider provider)
					throws IOException, JsonProcessingException {
				jsonGenerator.writeString(sdf.format(value));
			}
		});
		factory.addGenericMapping(BigDecimal.class, new JsonSerializer<BigDecimal>(){
			@Override
			public void serialize(BigDecimal value, 
					JsonGenerator jsonGenerator, 
					SerializerProvider provider)
					throws IOException, JsonProcessingException {
				DecimalFormat format = new DecimalFormat("##0.00");  
				jsonGenerator.writeString(format.format(value));
			}
		});
		this.setSerializerFactory(factory);
	}
}