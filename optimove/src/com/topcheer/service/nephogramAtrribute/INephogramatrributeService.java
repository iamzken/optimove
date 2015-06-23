package com.topcheer.service.nephogramAtrribute;

import java.util.List;
import java.util.Map;

import com.topcheer.model.nephogramAtrribute.Nephogramatrribute;

public interface INephogramatrributeService {
	
	public List<Nephogramatrribute> searchNephogramatrribute(Map searchMap);
	
	public List<Nephogramatrribute> getNephogramatrribute(String nephogramattrid);
	
	public List<Nephogramatrribute> searchAll();
	
	public void insert(Nephogramatrribute nephogramatrribute);
	
	public void update(Nephogramatrribute nephogramatrribute);
	
	public void delete(String id);

	public void deletebynephogrammodelid(String nephogrammodelid);
}
