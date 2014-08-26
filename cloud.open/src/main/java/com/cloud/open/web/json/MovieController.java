package com.cloud.open.web.json;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.egame.common.exception.ExceptionCommonBase;

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
	@RequestMapping(value = "/movie/list", method =RequestMethod.GET)
	public Object listMovie(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			return movieDao.listMovies().get(0);
//		MovieInfo movieInfo = new MovieInfo();
//		movieInfo.setDirectors("wendellup");
//		movieInfo.setDoubanId("doubanId");
//		return movieInfo;
	}
}
