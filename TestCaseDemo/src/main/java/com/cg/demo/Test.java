package com.cg.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			sb.append(i);
		}
		

		ConcurrentHashMap<Integer, Integer> map1 = new ConcurrentHashMap<>();

		map1.put(1, 1);
		
		map1.entrySet();
		
		
		Map<Integer, Integer> map = new HashMap<>();
		
		map.put(1, 12);
		
		
		System.out.println(sb);

		LinkedList<String> l = new LinkedList<>();
		l.get(0);

		l.add("Anil");

		ArrayList<String> l1 = new ArrayList<>();

		l1.get(0);
	}

}
