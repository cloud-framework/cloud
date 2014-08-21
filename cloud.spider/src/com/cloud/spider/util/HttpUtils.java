
package com.cloud.spider.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import cn.egame.common.web.ExceptionWeb;

public class HttpUtils {
	public static long getInputStreamLengthFromUrl(String url) throws IOException{
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(getEncodeUrl(url));
        HttpContext localContext = new BasicHttpContext();
		HttpResponse response = httpClient.execute(httpGet, localContext);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new ExceptionWeb(url + "对应的文件不存在");
		}
		return response.getEntity().getContentLength();
    }
	
	public static InputStream getInputStreamFromUrl(String url) throws IOException{
        HttpClient httpClient = new DefaultHttpClient();
        
        HttpGet httpGet = new HttpGet(getEncodeUrl(url));
        HttpContext localContext = new BasicHttpContext();
		HttpResponse response = httpClient.execute(httpGet, localContext);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new ExceptionWeb(url + "对应的文件不存在");
		}
		return response.getEntity().getContent();
    }
	
	public static String getEncodeUrl(String url)
			throws UnsupportedEncodingException {
		if(url==null){
			return url;
		}
		int idx = url.lastIndexOf("/");
		if(idx==-1 || idx+1>=url.length()){
			return url;
		}
		String subUrl = url.substring(0,idx+1);
		String name = url.substring(idx+1);
		name = URLEncoder.encode(name, "utf-8");
		if (name != null) {
			name = name.replace("+", "%20");
		}
		return subUrl+name;
	}
}
