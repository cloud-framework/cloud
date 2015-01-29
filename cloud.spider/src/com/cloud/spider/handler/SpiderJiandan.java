package com.cloud.spider.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.interfaces.ExceptionCommon;

import com.cloud.spider.entity.constants.ConstVar;
import com.cloud.spider.entity.constants.EnumType.ProductType;
import com.cloud.spider.service.ProductService;
import com.cloud.spider.util.SpiderUtils;

public class SpiderJiandan {
	
	private static Logger LOGGER = Logger.getLogger(SpiderJiandan.class);
//	private static Logger douban250ErrorIdLog = Logger.getLogger("douban250ErrorId");

	private long boring_pic_store_page_id = 0;
	private long boring_pic_store_id = 0;
	
	private long boring_pic_page_id = 0;
	private long boring_pic_id = 0;
	
	public void fetch() throws ExceptionCommonBase{
		initAndValidFetchId();
	}
	
	/**
	 * 拉取无聊图逻辑
	 * 
	 * 1.读取拉取文件中开始的页数store_page_id
	 * 2.从数据库中读取已拉取的最大图片store_id
	 * 3.解析煎蛋无聊图最大的页面page_id和最大的页面图片id
	 * 4.如果拉取的页数store_page_id<=页面page_id且已拉取的store_id<=页面图片id则拉取一页图片
	 *   ,并将store_page_id写回文件,并重复1,2,3步骤.否则停止该程序
	 */
	
	public void initAndValidFetchId() throws ExceptionCommonBase{
		//1
		boring_pic_store_page_id = 
				readIdFromFile(ConstVar.JIANDAN_BORING_PIC_CURRENT_PAGE_FILE_PATH);
		if(boring_pic_store_page_id<=0){
			ExceptionCommon.throwExceptionCommon(new ExceptionCommon(-1, "开始页数错误"));
		}
		//2
		boring_pic_store_id = ProductService.getInstance()
				.getProductMaxIdByType(ProductType.jiandanBoringPic);
		
		//3
//		fetch_
//		boring_pic_page_id = 
	}
	
	
	
	private long readIdFromFile(String filePath) throws ExceptionCommonBase{
		File file = new File(filePath);
		if(!file.exists()){
			throw new ExceptionCommonBase(-1, "存储开始扫描efs_id的文件不存在");
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),"utf-8"));
			
			String idStr = br.readLine();
			if(idStr==null || "".equals(idStr)){
				return 0;
			}else{
				return new Long(idStr);
			}
			
		} catch (Exception e) {
			LOGGER.error(e);
			throw new ExceptionCommonBase(-1, "读取文件报错");
		} finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.error(e);
					throw ExceptionCommonBase.throwExceptionCommonBase(e);
				}
			}
		}
	}
	
	public static void main(String[] args) throws ExceptionCommonBase {
		new SpiderJiandan().initAndValidFetchId();
	}
	
}
