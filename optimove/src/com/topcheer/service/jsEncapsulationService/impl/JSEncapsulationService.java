package com.topcheer.service.jsEncapsulationService.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public interface JSEncapsulationService {

	/**
	 * @param map
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public abstract StringBuffer getMapScripts(Map<String, Object> map)
			throws MalformedURLException, IOException;

}