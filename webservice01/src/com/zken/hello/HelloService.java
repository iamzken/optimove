package com.zken.hello;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;

@WebService
public class HelloService {
	
	@WebMethod
	public String sayHello(String message){
		return "Hello ," + message;
	}
	
	public static void main(String[] args) {
		//create and publish an endPoint
		HelloService hello = new HelloService();
		Endpoint endPoint = Endpoint.publish("http://localhost:2222/helloService", hello);
	}
}
