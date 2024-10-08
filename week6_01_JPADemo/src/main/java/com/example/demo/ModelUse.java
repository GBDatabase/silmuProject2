package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class ModelUse {
	public static void main(String[] args) {
		Map<String, String> model = new HashMap<>();
		String sReturn = root(model);
		printData(sReturn,model); 
	}

	private static void printData(String sReturn, Map<String, String> model) {
		String str1 = model.get("name2");
		System.out.println(str1);
		System.out.println("WEB-INF/views/"+sReturn+".jsp");		
		
	}

	private static String root(Map<String, String> model) {
		// TODO Auto-generated method stub
		model.put("name1" ,"홍길동");
		model.put("name2" ,"전우치");

		return "Hello";
	}
}
