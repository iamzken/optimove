package com.zken.hello;

import com.zken.hello.HelloService;

public class HelloClient {
	public static void main(String[] args) {
		HelloService helloService = new HelloService();
		System.out.println(helloService.sayHello("ÄãºÃ"));
	}

}