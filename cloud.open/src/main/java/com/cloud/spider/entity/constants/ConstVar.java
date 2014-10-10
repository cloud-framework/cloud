package com.cloud.spider.entity.constants;

import java.util.Properties;

import cn.egame.common.util.Utils;

public class ConstVar {
	public static String DOUBAN_250_FILE_ROOT = "";
	
	static{
		Properties properties = getProperties();
		DOUBAN_250_FILE_ROOT = getPropertiesValueByKey(properties, "douban_250_file_root", DOUBAN_250_FILE_ROOT);
		System.out.println("douban_250_file_root========"+DOUBAN_250_FILE_ROOT);
		
	}
	
	private static String getPropertiesValueByKey(Properties properties,
			String key, String defaultResult) {
		/* download_url 获取 */
		String value = properties.getProperty(key);
		if (value != null && !"".equals(value.trim())) {
			return value;
		}
		return defaultResult;
	}

	private static int getPropertiesIntValueByKey(Properties properties,
			String key, int defaultResult) {
		/* download_url 获取 */
		String value = properties.getProperty(key);
		if (value != null && !"".equals(value.trim()) && value.matches("[-]?\\d+")) {
			return Integer.parseInt(value);
		}
		return defaultResult;
	}
	
	private static Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties = Utils.getProperties("common.properties");
		} catch (Exception e) {
			System.out.println("common.properties not find");
		}
		return properties;
	}
	
	public static void main(String[] args) {
		System.out.println("".matches("[-]?\\d+"));
		
	}
}
