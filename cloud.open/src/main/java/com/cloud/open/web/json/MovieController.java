package com.cloud.open.web.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.common.model.PageData;
import cn.egame.common.util.Utils;

import com.cloud.open.dao.MovieDao;
import com.cloud.open.web.json.base.JsonView;
import com.cloud.valueobject.vo.MovieInfo;
import com.cloud.valueobject.vo.PageDataVO;
import com.cloud.valueobject.vo.SearchResultVO;

@Controller
@RequestMapping(value = "movie")
public class MovieController {
	
	private static Logger logger = Logger.getLogger(MovieController.class);
	
	@Autowired
	private MovieDao movieDao;
	
	@ResponseBody
	@RequestMapping(value = "login", method =RequestMethod.GET)
	public Object loginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		MovieInfo movieInfo = new MovieInfo();
		movieInfo.setDirectors("wendellup");
		movieInfo.setDoubanId("doubanId");
		return movieInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "test", method =RequestMethod.GET)
	public JsonView test(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MovieInfo movieInfo = new MovieInfo();
		movieInfo.setDirectors("wendellup");
		movieInfo.setDoubanId("doubanId");
		if(1==1)
		throw ExceptionCommonBase.throwExceptionCommonBase(new RuntimeException("xxx"));
		return new JsonView(movieInfo);
//		return movieInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list/current_page/{current_page}/page_size/{page_size}", method =RequestMethod.GET)
	public Object listMovie(@PathVariable("current_page") Integer page
			, @PathVariable("page_size") Integer rowsOfPage, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<MovieInfo> movieList = movieDao.listMovies();
		int total = movieList.size();
		PageData retPd = new PageData(page, total, rowsOfPage);
		if (page < retPd.getPageCount()) {
			retPd.setContent(Utils.page(movieList, page, rowsOfPage));
		}
		
		return new PageDataVO(retPd);
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail/id/{id}", method = RequestMethod.GET)
	public Object movieDetail(@PathVariable("id") Integer id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MovieInfo movieInfo = movieDao.getMovieInfoById(id);
		return movieInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/key/{key}/current_page/{current_page}/type/{type}", method = RequestMethod.GET)
	public Object queryResource(@PathVariable("key") String key
			, @PathVariable("current_page") String current_page, @PathVariable("type") Integer type 
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String queryStr = null;
		
		if(type==1){
			//google自定义搜索
			queryStr = "https://www.google.com/cse?cx=006369457434482665413:mnzddbuz2ke&ie=UTF-8&q=";
			queryStr += "&ref=#gsc.q="+key;
			queryStr += "&gsc.page="+current_page;
		}else if(type==2){
			queryStr = "http://torrentproject.com/?t=";
			queryStr += key;
		}
		
		Document doc = null;
		int loop = 0;
		while (true) {
			try {
				doc = Jsoup.connect(queryStr)
						.timeout(10000)
//						.header("User-Agent", UserAgentAry[Utils.getRandom(UserAgentAry.length)])
//						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//						.header("Accept-Encoding", "gzip,deflate,sdch")
//						.header("Accept-Language", "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
//						.header("Connection", "keep-alive")
//						.header("Cache-Control", "max-age=0")
//						.header("Cookie", "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ")
						.get();
				if (doc != null) {
					break;
				}
				Thread.sleep(500);
			} catch (Exception e) {
				logger.info("第" + (loop + 1) + "次搜索数据失败", e);
				loop++;
				if (loop >= 3) {
					throw e;
				}
				continue;
			}
		}
		
		List<SearchResultVO> searchResultVOs = listSearchResultVOfromTorrentNet(doc);
		return searchResultVOs;
	}
	
	/**
	 * 解析搜索种子网站产生的dom树, 将搜索结果返回(以torrentproject网站为例)
	 * @param doc
	 * @return
	 */
	private List<SearchResultVO> listSearchResultVOfromTorrentNet(Document doc){
		List<SearchResultVO> searchResultVOs = new ArrayList<SearchResultVO>();
		if(doc != null){
			Element content = doc.getElementById("tt");
			if(content!=null){
				SearchResultVO vo = null;
				Elements divs = content.select("div");
				for(Element div : divs){
					if(div.hasAttr("gac_bb")){
						continue;
					}
					Elements spans = div.select("span");
					if(spans!=null && spans.size()==5){
						vo = new SearchResultVO();
						vo.setTorrentName(spans.get(0).text());
						vo.setTorrentUrl(spans.get(0).attr("href"));
						vo.setSize(spans.get(4).text());
						searchResultVOs.add(vo);
					}
				}
			}
		}
		
		return searchResultVOs;
	}
}
