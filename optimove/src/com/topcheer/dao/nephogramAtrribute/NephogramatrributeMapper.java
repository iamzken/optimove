package com.topcheer.dao.nephogramAtrribute;

import java.util.List;
import java.util.Map;


import com.topcheer.model.nephogramAtrribute.Nephogramatrribute;


public interface NephogramatrributeMapper {
	
	public List<Nephogramatrribute> searchNephogramatrribute(Map searchMap);
	
	public List<Nephogramatrribute> searchAll();
	
	public List<Nephogramatrribute> getNephogramatrribute(String nephogramattrid);
	
	public void insert(Nephogramatrribute nephogramatrribute);
	
	public void update(Nephogramatrribute nephogramatrribute);
	
	public void delete(String nephogramattrid);
	
	public void deletebynephogrammodelid(String nephogrammodelid);
}
