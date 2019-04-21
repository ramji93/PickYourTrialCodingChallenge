package com.pickyourtrail.challenges;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalancedStrings {

	static String x = "^([^(a|c)]*(a|c)[^(a|c)]*(a|c)[^(a|c)]*)+$|^(?:(?!(a|c)).)*$";
	static String y = "^([^(b|d)]*(b|d)[^(b|d)]*(b|d)[^(b|d)]*)+$|^(?:(?!(b|d)).)*$";
	
	public static void main(String[] args) {
		
		Pattern pattern = Pattern.compile("(?=" + x + ")" + y);
		Scanner in = new Scanner(System.in);
		Matcher matcher = pattern.matcher(in.nextLine());
		System.out.println(matcher.find());
		in.close();
	}
	
}
