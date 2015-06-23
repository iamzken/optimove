package com.topcheer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.topcheer.model.User;
/**
 * 
 * @author tanxf
 *
 */
public interface CommMapper {
	
	@Update("update ${table} set CheckAccFlag=#{checkAccFlag} where SerialNo = #{serialNo}")
	public void updateStatus(@Param("table") String table,@Param("CheckAccFlag") String checkAccFlag,@Param("serialNo") String serialNo);
	
	@Select("select CheckAccFlag from ${table} where SerialNo = #{serialNo}")
	@One
	@Result(property="CheckAccFlag")
	public String getCheckAccFlagBySerialNo(@Param("table") String table,@Param("serialNo") String serialNo);
	
	@Select("select * from tbluserinfo  where WorkId = #{workId}")
	public List<User> getUserInfo(@Param("workId") String workId);
	
//	public List<Map> searchTableByTableName(@Param("tableName") String tableName);
	public List<Map> searchTableByTableName(@Param("searchMap") Map searchMap);
	
	public List<Map> searchSql(@Param("paramSql") String paramSql);
	
}
