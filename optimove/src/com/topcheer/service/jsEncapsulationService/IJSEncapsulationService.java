package com.topcheer.service.jsEncapsulationService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public interface IJSEncapsulationService {

	/**
	 * @param map
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public abstract String getMapScripts(Map<String, Object> map) throws MalformedURLException, IOException;

	public abstract String getMainScripts(Map<String, Object> map);

}