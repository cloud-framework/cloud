package com.cloud.spider.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.common.util.Utils;
import cn.egame.interfaces.ExceptionCommon;

import com.cloud.spider.entity.bo.ProductBO;
import com.cloud.spider.entity.constants.ConstVar;
import com.cloud.spider.entity.constants.EnumType.ProductType;
import com.cloud.spider.service.ProductService;
import com.cloud.spider.util.SpiderUtils;

public class SpiderJiandan {
	
	private static Logger LOGGER = Logger.getLogger(SpiderJiandan.class);
//	private static Logger douban250ErrorIdLog = Logger.getLogger("douban250ErrorId");
	
	String JIANDAN_BORING_PIC_URL = "http://jandan.net/pic";

	private long stored_boring_pic_page_id = 0;
	private long stored_boring_pic_id = 0;
	
	private long boring_pic_page_id = 0;
	private long boring_pic_id = 0;
	
	public void fetch() throws Exception{
		initAndValidFetchId();
		parseAndFetchAPageBoringPic();
	}
	
	/**
	 * 拉取无聊图逻辑
	 * 
	 * 1.读取拉取文件中开始的页数store_page_id
	 * 2.从数据库中读取已拉取的最大图片store_id
	 * 3.解析煎蛋无聊图最大的页面page_id和最大的页面图片id
	 * 4.如果拉取的页数store_page_id<=页面page_id且已拉取的store_id<=页面图片id则拉取一页图片
	 *   ,并将拉取的store_page_id写回文件(如果page_id>store_page_id则store_page_id++写回,否则store_page_id写回),并重复1,2,3步骤.否则停止该程序
	 */
	
	public void initAndValidFetchId() throws Exception{
		//1
		stored_boring_pic_page_id = 
				readIdFromFile(ConstVar.JIANDAN_BORING_PIC_CURRENT_PAGE_FILE_PATH);
		if(stored_boring_pic_page_id<=0){
			ExceptionCommon.throwExceptionCommon(new ExceptionCommon(-1, "开始页数错误"));
		}
		//2
		stored_boring_pic_id = ProductService.getInstance()
				.getProductMaxIdByType(ProductType.jiandanBoringPic);
		//3
		parseBoringPicPageId();
		
		
		
	}

	private void parseAndFetchAPageBoringPic() throws Exception {
		if(stored_boring_pic_page_id<=boring_pic_page_id
				&& stored_boring_pic_id<boring_pic_id){
			String currentPageUrl = JIANDAN_BORING_PIC_URL + "/page-"+stored_boring_pic_page_id;
			Document doc = null;
			int loop = 0;
			while (true) {
				try {
					doc = Jsoup
							.connect(currentPageUrl)
							.header("Cookie", "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ").get();
					if (doc != null) {
						break;
					}
					Thread.sleep(500);
				} catch (Exception e) {
					LOGGER.info("第" + (loop + 1) + "次拉取"+stored_boring_pic_page_id+"页数据失败", e);
					loop++;
					if (loop >= 3) {
						throw e;
					}
					continue;
				}
			}
			LOGGER.info("当前拉取的url为:"+currentPageUrl);
			try {
				Element content = doc.getElementById("comments");
				Elements idLiElements = content.select(".commentlist > li");
				if (idLiElements != null && idLiElements.size()>0) {
					//从小的id图片开始拉取,所以需要倒序
					Collections.reverse(idLiElements);
					for(Element element : idLiElements){
						if(element.hasAttr("id")){
							//图片对应的id
							Elements idElements = element.select(".righttext > a");
							String boringPicIdStr = idElements.get(0).text();
							if (boringPicIdStr.matches("\\d+")) {
								long currentFetchPicId = Utils.toLong(boringPicIdStr, 0);
								if(currentFetchPicId >= 0){
									//拉取图片
									Elements picElements = element.select(".row > .text > p > img");
									List<String> picUrls = new ArrayList<String>();
									for(Element picEle : picElements){
										String picUrl = picEle.attr("org_src");
										if(null==picUrl || "".equals(picUrl)){
											picUrl = picEle.attr("src");
										}
										//如果包含org_src属性则拉取该属性对应的原图片
										picUrls.add(picUrl);
									}
//									System.out.println("-----------------"+currentFetchPicId+"-------------------");
									//拉取完图片,存储图片对应的一条记录到数据库
									ProductBO productBO = new ProductBO();
									productBO.setPage_id(stored_boring_pic_page_id);
									productBO.setPic_id(currentFetchPicId);
									productBO.setPicUrl(picUrls);
									productBO.setProductType(new Long(ProductType.jiandanBoringPic.value()));
									ProductService.getInstance().storeProductAndImageInfo(productBO);
									
								}
								
							}
						}
						
					}
					
//					String boringPicPageIdStr = elements.get(0).text();
//					if(boringPicPageIdStr.matches("\\[\\d+\\]")){
//						boringPicPageIdStr = boringPicPageIdStr.substring(boringPicPageIdStr.indexOf("[")+1
//								, boringPicPageIdStr.indexOf("]"));
//						boring_pic_page_id = Utils.toInt(boringPicPageIdStr, 0);
//					}
				}
				
			} catch (Exception e) {
				LOGGER.info("解析最大页数据及最大图片id失败", e);
			}
			
		}
		
	}
	
	private void parseBoringPicPageId() throws Exception {
		Document doc = null;
		int loop = 0;
		while (true) {
			try {
				doc = Jsoup
						.connect(
								JIANDAN_BORING_PIC_URL + "?timestamp="
										+ System.currentTimeMillis())
//						.header("User-Agent", UserAgentAry[Utils.getRandom(UserAgentAry.length)])
//						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//						.header("Accept-Encoding", "gzip,deflate,sdch")
//						.header("Accept-Language", "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
//						.header("Connection", "keep-alive")
//						.header("Cache-Control", "max-age=0")
						.header("Cookie", "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ").get();
				if (doc != null) {
					break;
				}
				Thread.sleep(500);
			} catch (Exception e) {
				LOGGER.info("第" + (loop + 1) + "次获取最大页数据及最大图片id失败", e);
				loop++;
				if (loop >= 3) {
					throw e;
				}
				continue;
			}
		}
		try {
			Element content = doc.getElementById("comments");
			Elements elements = content.select(".current-comment-page");
			if (elements != null) {
//				if(boring_pic_page_idS)
				String boringPicPageIdStr = elements.get(0).text();
				if(boringPicPageIdStr.matches("\\[\\d+\\]")){
					boringPicPageIdStr = boringPicPageIdStr.substring(boringPicPageIdStr.indexOf("[")+1
							, boringPicPageIdStr.indexOf("]"));
					boring_pic_page_id = Utils.toInt(boringPicPageIdStr, 0);
				}
			}
			
			Elements idOLElements = content.select(".commentlist > li");
			if (idOLElements != null && idOLElements.size()>0) {
				Elements idElements = idOLElements.get(0).select(".righttext > a");
				String boringPicIdStr = idElements.get(0).text();
				if(boringPicIdStr.matches("\\d+")){
					boring_pic_id = Utils.toInt(boringPicIdStr, 0);
				}
			}
			
		} catch (Exception e) {
			LOGGER.info("解析最大页数据及最大图片id失败", e);
		}
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
	
	public static void main(String[] args) throws Exception {
		Utils.initLog4j();
		new SpiderJiandan().fetch();
	}
	
}
