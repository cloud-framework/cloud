package com.cloud.open.web.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.sina.sae.fetchurl.SaeFetchurl;

@Controller
@RequestMapping(value = "help")
public class HelpController {
	
	private static Logger logger = Logger.getLogger(HelpController.class);
	
	
	
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
	@RequestMapping(value = "/revert_time/time/{time}/format/{format}", method =RequestMethod.GET)
	public Object revert_time(@PathVariable("time") Long time
			, @PathVariable("format") String format, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dateStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = new Date(time);
			dateStr = sdf.format(date);
		} catch (Exception e) {
			logger.error("", e);
			dateStr = "格式为:"+format+",无法转换.";
		}
		
		return dateStr;
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
