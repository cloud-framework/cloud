package com.cloud.spider.util;

import cn.egame.common.util.Utils;

public class SpiderUtils {
	private static char[] charAry = new char []{
		'a', 'b', 'c', 'd', 'e'
		, 'f', 'g', 'h', 'i', 'j'
		, 'k', 'l', 'm', 'n', 'o'
		, 'p', 'q', 'r', 's', 't'
		, 'u', 'v', 'w', 'x', 'y', 'z'
		, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static String getRandomStr(int len){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<len; i++){
			sb.append(charAry[Utils.getRandom(charAry.length)]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomStr(2));
	}
}
