package com.topcheer.service.jsEncapsulationService.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.topcheer.service.jsEncapsulationService.IJSEncapsulationService;

@Service("jsEncapsulationService")
public class JSEncapsulationServiceImpl implements IJSEncapsulationService {
	public String getMapScripts(Map<String, Object> map) throws MalformedURLException, IOException {
		URL url = new URL(String.valueOf(map.get("api")) + String.valueOf(map.get("key")));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer nbcbScripts = new StringBuffer("");
		String line = null;
		while ((line = reader.readLine()) != null) {
			nbcbScripts.append(line+"\n");
		}

		reader.close();
		connection.disconnect();
		return nbcbScripts.toString();
	}

	public String getMainScripts(Map<String, Object> map) {
		StringBuffer mainScripts = new StringBuffer("");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(map.get("mainScriptsPath"))));
			String line = null;
			while ((line = reader.readLine()) != null) {
				mainScripts.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mainScripts.toString();
	}

}
