package com.cloud.spider.entity.constants;

import java.util.Properties;

import cn.egame.common.util.Utils;

public class ConstVar {
	public static String DOUBAN_250_FILE_ROOT = "";
	public static String JIANDANMEIZI_CURRENT_PAGE_FILE_PATH = "";
	public static String JIANDAN_BORING_PIC_CURRENT_PAGE_FILE_PATH = "";
	
	static{
		Properties properties = getProperties();
		DOUBAN_250_FILE_ROOT = getPropertiesValueByKey(properties, "douban_250_file_root", DOUBAN_250_FILE_ROOT);
		System.out.println("douban_250_file_root========"+DOUBAN_250_FILE_ROOT);
		
		JIANDANMEIZI_CURRENT_PAGE_FILE_PATH = getPropertiesValueByKey(properties, "jiandanmeizi_current_page_file_path", JIANDANMEIZI_CURRENT_PAGE_FILE_PATH);
		System.out.println("jiandanmeizi_current_page_file_path========"+JIANDANMEIZI_CURRENT_PAGE_FILE_PATH);
		
		JIANDAN_BORING_PIC_CURRENT_PAGE_FILE_PATH = getPropertiesValueByKey(properties, "jiandan_boring_pic_current_page_file_path", JIANDAN_BORING_PIC_CURRENT_PAGE_FILE_PATH);
		System.out.println("jiandan_boring_pic_current_page_file_path========"+JIANDAN_BORING_PIC_CURRENT_PAGE_FILE_PATH);
		
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
