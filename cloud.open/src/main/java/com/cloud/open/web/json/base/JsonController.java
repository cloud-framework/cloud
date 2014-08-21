package com.cloud.open.web.json.base;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class JsonController extends MultiActionController{
	
	protected void jsonView(
			HttpServletRequest request,
			HttpServletResponse response,
			Object object) throws IOException{
		 response.setContentType("text/plain");
	     response.setCharacterEncoding("utf-8");

	     String json = null;
	     if (object instanceof Collection<?>){
	    	 JSONArray ja = JSONArray.fromObject(object);
	         json = ja.toString();
	      }else{
	            json = JSONObject.fromObject(object).toString();
	      }
	      response.getWriter().print(json);
	      response.getWriter().flush();
	}
	
	
}
