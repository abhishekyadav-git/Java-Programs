package com.abhishek.string_numbers_and_math;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "Be strong, be fearless, be beautiful";
		String str1 = "😍 I love 💕 you Ӝ so much 💕 😍 🎼🎼🎼!";
		//Below functions give count of duplicates
		countDuplicateOne(str); 
		countDuplicateTwo(str);
		countDuplicateThree(str);
		
		//Below functions print duplicates
		printDuplicatesOne(str); // allows Ascii and Unicode values
		printDuplicatesTwo(str1); //// allows Ascii and Unicode values use Java 8 stream
		

	}
	
	public static void printDuplicatesTwo(String str) {
		Map<String,Long> hm = str.codePoints(). //instream
								  mapToObj(i -> String.valueOf(Character.toChars(i))). //int -> char ->String
								  collect(Collectors.groupingBy(i -> i,Collectors.counting())); //do group by for each char
		
		System.out.println(Arrays.toString(hm.entrySet().toArray()));
		//or
		//hm.forEach((k,v) -> System.out.print(k + "=" + v + " , "));
	}

	public static void printDuplicatesOne(String str) {
		Map<String,Integer> hm = new HashMap<>();
		
		for(int i=0;i<str.length();i++) {
			int cp = str.codePointAt(i);
			String ch = String.valueOf(Character.toChars(cp));
			
			//handle surrogate pair : means their can be a char represented by two unicode ,so while reading need to jump +1 char
			if(i<str.length()-1 && str.codePointCount(i, i+2)==1) { //for surrogate pair as even if two unicode it returns 1
				i++;
			}
			
			hm.compute(ch, (k,v) -> (v==null) ? 1 : v + 1); 
			// if character comes first time while traversing , means value is null, then pass count as 1 else increase character occurrence + 1
		}
		
		//print the map
		System.out.println(Arrays.toString(hm.entrySet().toArray()));
		//or
		//hm.forEach((k,v) -> System.out.print(k +  "=" + v + " , "));
	}
	public static void countDuplicateOne(String str) {
		char[] ch = str.toCharArray();
		//List<Character> charList = str.chars().mapToObj(i->(char)i).collect(Collectors.toList());
		
		Map<Character,Integer> hm = new HashMap<>();
		for(Character c : ch) {
			if(hm.containsKey(c)) {
				hm.put(c, hm.get(c) + 1);
			}
			else {
				hm.put(c, 1);
			}
		}
		int countDuplicate = 0;
		for(Map.Entry<Character, Integer> m : hm.entrySet()) {
			
			if(m.getValue() > 1) {
				countDuplicate++;
			}
		}
		System.out.println(countDuplicate);
	}
	
	public static void countDuplicateTwo(String str) {
		
		Map<Character,Integer> hm = new HashMap<>();
		for(Character c : str.toCharArray()) {
			hm.compute(c, (k,v) -> (v==null) ? 1 : v+1);//method is used to automatically update a value for given key in HashMap
		}
		int countDuplicate = 0;
		for(Map.Entry<Character, Integer> m : hm.entrySet()) {
			
			if(m.getValue() > 1) {
				countDuplicate++;
			}
		}
		System.out.println(countDuplicate);
	}
	
	public static void countDuplicateThree(String str) {
		Map<Object,Long> hm = str.chars(). //string to IntStream
								  mapToObj((ch -> (char)ch)) // use mapToObj to convert int to char
								  .collect(Collectors.groupingBy(ch->ch,Collectors.counting()));
								  // do group by of chars to find count of each char like sql ,it returns a map<obj,long>
		
		int countDuplicate = 0;
		
		for(Map.Entry<Object, Long> m : hm.entrySet()) {
			if(m.getValue()>1) {
				countDuplicate++;
			}
		}
		
		System.out.println(countDuplicate);
		
	}

}
