package com.cloud.spider.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.egame.common.util.Utils;

import com.cloud.spider.entity.bo.MovieBO;
import com.cloud.spider.entity.po.MovieInfo;
import com.cloud.spider.service.MovieService;
import com.cloud.spider.util.SpiderUtils;

/**
 * 
 * Description 爬取豆瓣250数据到电影表
 * 
 * @ClassName SpiderDouBan250
 * 
 * @Project cloud.spider
 * 
 * @Author yuchao
 * 
 * @Create Date 2014年8月14日
 * 
 * @Modified by none
 * 
 * @Modified Date
 */
public class SpiderDouBan250 {

	private static Logger logger = Logger.getLogger(SpiderDouBan250.class);
	private static Logger douban250ErrorIdLog = Logger.getLogger("douban250ErrorId");
	
	public static void run(String[] args) {
		String douban250Url = "http://movie.douban.com/top250";

		int startNo = 25 * 3;
		try {
			//key为movie表中id, value为sort的id
			Map<Long, Integer> movieTagMap = new HashMap<Long, Integer>();
			
			// 遍历每页数据
			for (int i = 0; i < 10; i++) {
				startNo = 25 * i;
				//如果报错,重复拉取3次
				Document doc = null;
				int loop = 0;
				while (true) {
					try {
						doc = Jsoup
								.connect(
										douban250Url + "?start=" + startNo + "&timestamp="
												+ System.currentTimeMillis())
//								.header("User-Agent", UserAgentAry[Utils.getRandom(UserAgentAry.length)])
//								.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//								.header("Accept-Encoding", "gzip,deflate,sdch")
//								.header("Accept-Language", "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
//								.header("Connection", "keep-alive")
//								.header("Cache-Control", "max-age=0")
								.header("Cookie", "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ").get();
						if (doc != null) {
							break;
						}
						Thread.sleep(500);
					} catch (Exception e) {
						logger.info("第" + (loop + 1) + "次拉取第" + (i + 1) + "页数据失败", e);
						loop++;
						if (loop >= 3) {
							throw e;
						}
						continue;
					}
				}
				Element content = doc.getElementById("content");
				Elements liElements = content.select(".grid_view > li");
				// 每页10条电影的详情页
				int liIdx = 0;
				for (Element liElement : liElements) {
					liIdx++;
					MovieBO movieBO = null;
					try {
						movieBO = SpiderDouBan250.parseMovieInfoFromWeb(liElement);
						MovieService.getInstance().spiderDoubanMovieInfo(movieBO, movieTagMap);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						logger.error("", e);
						if(movieBO==null){
							douban250ErrorIdLog.error("拉取第"+(startNo+liIdx)+"条电影报错..");
						}else{
							douban250ErrorIdLog.error("拉取第"+movieBO.getNo()+"条电影报错.");
						}
						continue;
					}
				}
			}
			
			//更新电影排名表
			if(movieTagMap.size()>=200){
				MovieService.getInstance().updateMovieTagLink(movieTagMap, 1);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	 
	/**
	 * 解析一条电影详细信息
	 */
	public static MovieBO parseMovieInfoFromWeb(Element liElement)
			throws Exception {
		MovieBO movieBO = new MovieBO();
		MovieInfo movieInfo = new MovieInfo();
		movieBO.setMovieInfo(movieInfo);
		// 电影排名
		Elements noEles = liElement
				.select(".pic > em");
		if (noEles.size() > 0) {
			movieBO.setNo(Utils.toInt(noEles.get(0).text(), 0));
		}

		// 电影名称
		Elements titleEles = liElement
				.select(".item > .info > .hd > a > .title");
		for (int i = 0; i < titleEles.size(); i++) {
			if (i == 0) {
				movieInfo.setMovieName(titleEles.get(i).text().trim());
			}
			if (i == 1) {
				movieInfo.setMovieOriginalName(Utils.toString(titleEles.get(i)
						.text().trim().split("/")));
			}
		}

		// 电影对应的豆瓣连接
		Elements doubanIdEles = liElement.select(".item > .info > .hd > a");
		for (int i = 0; i < doubanIdEles.size(); i++) {
			if (i == 0) {
				movieInfo.setDoubanId(doubanIdEles.get(0).attr("href").trim());
				break;
			}
		}

		// 电影详情页
		Document detailDoc = null;
		int loop = 0;
		while(true){
			try {
				detailDoc = Jsoup.connect(movieInfo.getDoubanId())
//						.header("User-Agent", UserAgentAry[Utils.getRandom(UserAgentAry.length)])
//						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//						.header("Accept-Encoding", "gzip,deflate,sdch")
//						.header("Accept-Language", "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
//						.header("Connection", "keep-alive")
//						.header("Cache-Control", "max-age=0")
						.header("Cookie", "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ")
						.get();
				if(detailDoc!=null){
					break;
				}
			} catch (Exception e) {
				logger.info("第"+(loop+1)+"次拉取第"+(movieBO.getNo())+"条数据失败");
				loop++;
				if(loop>=3){
					throw e;
				}
				continue;
			}
		}
		
		Element detailContent = detailDoc.getElementById("content");
		// icon
		Elements iconElements = detailContent
				.select("img[rel=v:image]");
		if (iconElements.size() > 0) {
			movieBO.setIconUrl(iconElements.get(0).attr("src"));
		}

		// 导演
		Element infoDiv = detailContent.select(".article #info").get(0);
		Elements directorsElements = infoDiv.select("span:contains(导演)").get(0)
				.select("a");
		List<String> directorList = new ArrayList<String>();
		for (Element direcE : directorsElements) {
			String director = direcE.text().trim();
			directorList.add(director);
		}
		movieInfo.setDirectors(Utils.toString(directorList));

		// 编剧
		Elements writerElementSpans = infoDiv.select("span:contains(编剧)");
		if(writerElementSpans.size()>0){
			Elements writerElements = infoDiv.select("span:contains(编剧)").get(0)
					.select("a");
			List<String> writerList = new ArrayList<String>();
			for (Element writerE : writerElements) {
				String writer = writerE.text().trim();
				writerList.add(writer);
			}
			movieInfo.setScreenWriter(Utils.toString(writerList));
		}
		// 主演
		Elements leadingRoleElements = infoDiv.select("span:contains(主演)")
				.get(0).select("a");
		List<String> leadingRoleList = new ArrayList<String>();
		for (Element leadingRoleE : leadingRoleElements) {
			String leadingRole = leadingRoleE.text().trim();
			leadingRoleList.add(leadingRole);
		}
		movieInfo.setLeadingRole(Utils.toString(leadingRoleList));
		// 类型
		Elements typeElements = infoDiv.select("span[property=v:genre]");
		List<String> typeList = new ArrayList<String>();
		for (Element typeE : typeElements) {
			String type = typeE.text().trim();
			typeList.add(type);
		}
		movieInfo.setType(Utils.toString(typeList));
		// 制片国家
		int regionBegin = infoDiv.text().indexOf("制片国家/地区:");
		int regionEnd = infoDiv.text().indexOf("语言:");
		if (regionBegin > 0 && regionEnd > 0
				&& (regionBegin + "制片国家/地区:".length() < regionEnd)) {
			movieInfo.setRegion(infoDiv.text().substring(
					regionBegin + "制片国家/地区:".length(), regionEnd));
		}
		// 语言
		int langBegin = infoDiv.text().indexOf("语言:");
		int langEnd = infoDiv.text().indexOf("上映日期:");
		if (langBegin > 0 && langEnd > 0
				&& (langBegin + "语言:".length() < langEnd)) {
			movieInfo.setLanguage(infoDiv.text().substring(
					langBegin + "语言:".length(), langEnd));
		}
		// 上映日期
		Elements releaseElements = infoDiv
				.select("span[property=v:initialReleaseDate]");
		if (releaseElements.size() > 0) {
			movieInfo.setMovieLength(releaseElements.get(0).text());
		}
		// 片长
		Elements lengthElements = infoDiv.select("span[property=v:runtime]");
		if (lengthElements.size() > 0) {
			movieInfo.setMovieLength(lengthElements.get(0).text());
		}
		// 又名
		int aliasBegin = infoDiv.text().indexOf("又名:");
		int aliasEnd = infoDiv.text().indexOf("IMDb链接:");
		if (aliasBegin > 0 && aliasBegin > 0
				&& (aliasBegin + "IMDb链接:".length() < aliasEnd)) {
			String aliasStr = infoDiv.text().substring(
					aliasBegin + "又名:".length(), aliasEnd);
			movieInfo.setMovieAliasName(Utils.toString(aliasStr.split("/")));
		}
		// imdb连接
		Elements imdbElements = infoDiv.select("span:containsOwn(IMDb链接) + a");
		if (imdbElements.size() > 0) {
			movieInfo.setImdbId(imdbElements.get(0).attr("href").trim());
		}
		// 描述
		Elements introElements = detailContent
				.select("span[property=v:summary]");
		if (introElements.size() > 0) {
			movieInfo.setIntroduction(introElements.get(0).text().trim());
		}

		return movieBO;
	}

	public static void main(String[] args) {
		Utils.initLog4j();
		run(args);
//		Utils.initLog4j();
//		douban250ErrorIdLog.error("testError");
//		logger.error("xx");
	}

}
