package com.topcheer.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseXml extends DefaultHandler {
	private Logger logger = Logger.getLogger(SaxParseXml.class);
	
	//��ű�������
    private List<Object> resultList;
    //�������ÿ�α������Ԫ������(�ڵ�����)
    private String tagName;
    //�����ռ����ԡ�characters�� SAX�¼�������
    private StringBuffer contents = new StringBuffer();
    
    @Override
	public void startDocument() throws SAXException {
    	resultList = new ArrayList<Object>();
	}
    
    @Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
    	contents.setLength(0);
    	if(qName.equals("root")){
    		
        }
        this.tagName=qName;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(this.tagName!=null){
			resultList.add(contents.toString());
		}
        this.tagName=null;
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		contents.append(ch, start, length);
	}
	
	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}

	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}
