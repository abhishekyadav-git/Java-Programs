package com.abhishek.string_numbers_and_math;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "geeksforgeeks";
		
		printFirstNonRepeatingCharacterOne(str); //function only supports for 26 English letters
		printFirstNonRepeatingCharacterTwo(str); //function only supports for 256 Ascii values(Solution works till Character.MAX_VALUE, that is, 65,535. )
		printFirstNonRepeatingCharacterThree(str); // Function make use of LinkedHashMap which saves the insertion order ,and group by's using compute function
		printFirstNonRepeatingCharacterFour(str); //using stream

	}
	public static void printFirstNonRepeatingCharacterFour(String str) {
		Map<Integer,Long> lhm = str.codePoints().
									mapToObj(i -> i).
									collect(Collectors.groupingBy(i -> i, LinkedHashMap::new,Collectors.counting()));
		
		int cp = lhm.entrySet().
					stream().
					filter(i -> i.getValue()==1L).
					findFirst().
					map(Map.Entry::getKey).
					orElse(Integer.valueOf(Character.MIN_VALUE));
		
		System.out.println(Character.toChars(cp));
	}
	
	public static void printFirstNonRepeatingCharacterThree(String str) {
		Map<Character,Integer> lhm = new LinkedHashMap<>();
		
		char ch[] = str.toCharArray();
		
		for(int i=0;i<str.length();i++) {
			lhm.compute(ch[i], (k,v) -> (v==null) ? 1 : v+1 );
		}
		
		for(Map.Entry<Character, Integer> m : lhm.entrySet()) {
			if(m.getValue()==1) {
				System.out.println(m.getKey());
				break;
			}
		}
	}
	
	public static void printFirstNonRepeatingCharacterTwo(String str) {
		char ch[] = str.toCharArray();
		
		int freq[] = new int[256];
		
		for(int i=0;i<256;i++) {
			freq[i] = -1;
		}
		
		for(int i=0;i<str.length();i++) {
			if(freq[ch[i]]==-1) { //if character's first time then set value as its position in array
				freq[ch[i]] = i;
			}
			else {
				freq[ch[i]] = -2; // if character occur's more then once set value as negative
			}
		}
		int minPosition = Integer.MAX_VALUE;
		for(int i=0;i<str.length();i++) {
			if(freq[ch[i]] > 0) { //if character occured only once
				minPosition = Math.min(minPosition, freq[ch[i]]); //take the min position of all
			}
		}
		
		System.out.println(ch[minPosition]);
	}
	
	public static void printFirstNonRepeatingCharacterOne(String str) {
		char ch[] = str.toCharArray();
		
		int norep[] = new int[26]; //will help finding non repeating chars
		
		for(int i=0;i<str.length();i++) {
			norep[ch[i] - 'a']++;
		}
		
		for(int i=0;i<str.length();i++) {
			if(norep[ch[i] - 'a']==1) {
				System.out.println(ch[i]); 
				break;
			}
			
		}
	}

}
