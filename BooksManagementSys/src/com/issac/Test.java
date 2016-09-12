package com.issac;

import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {
		TreeMap<String, String> treeMap = new TreeMap<>();
//		treeMap.put("11111", "aaaaa");
//		treeMap.put("11112", "bbbbb");
//		treeMap.put("11113", "ccccc");
//		treeMap.put("11114", "ddddd");
//		treeMap.put("11115", "eeeee");
		treeMap.lastKey();
		System.out.println(new Test().newId(null));
	}
	
	public String newId (String key){
		if (key==null) {
			return "11111";
		}
		return key;
	}
}
