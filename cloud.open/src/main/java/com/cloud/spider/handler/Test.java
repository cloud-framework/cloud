package com.cloud.spider.handler;

import org.apache.log4j.Logger;

import cn.egame.common.util.Utils;

public class Test {
	
	private static Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		Utils.initLog4j();
		logger.info("info");
		logger.error("error");
	}
}
