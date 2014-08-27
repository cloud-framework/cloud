package com.cloud.open.web.json;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller
@RequestMapping(value = "movie")
public class MovieController {
	
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
	@RequestMapping(value = "/movie/list/current_page/{current_page}/page_size/{page_size}", method =RequestMethod.GET)
	public Object listMovie(@PathVariable("current_page") Integer page
			, @PathVariable("page_size") Integer rowsOfPage, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<MovieInfo> movieList = movieDao.listMovies();
		int total = movieList.size();
		PageData retPd = new PageData(page, total, rowsOfPage);
		if (page < retPd.getPageCount()) {
			retPd.setContent(Utils.page(movieList, page, rowsOfPage));
		}
		return retPd;
//			return movieDao.listMovies().get(0);
//		MovieInfo movieInfo = new MovieInfo();
//		movieInfo.setDirectors("wendellup");
//		movieInfo.setDoubanId("doubanId");
//		return movieInfo;
	}
}
