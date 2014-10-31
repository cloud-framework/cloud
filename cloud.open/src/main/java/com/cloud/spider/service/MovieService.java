package com.cloud.spider.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.egame.common.efs.IFileSystem;
import cn.egame.common.efs.SFileSystemClient;
import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.common.util.Utils;

import com.cloud.open.dao.FileDao;
import com.cloud.open.entity.constants.FileUsedType;
import com.cloud.open.entity.po.FileInfo;
import com.cloud.spider.dao.MovieDao;
import com.cloud.spider.dao.ParameterDao;
import com.cloud.spider.entity.bo.MovieBO;
import com.cloud.spider.entity.constants.ConstVar;
import com.cloud.spider.entity.po.MovieImageInfo;
import com.cloud.spider.entity.po.MovieInfo;
import com.cloud.spider.entity.po.ParameterTagLinkInfo;
import com.cloud.spider.handler.SpiderDouBan250;
import com.cloud.spider.util.HttpUtils;

public class MovieService {
	private static MovieService instance = null;
	private static byte[] lock = new byte[0];
	private static Logger logger = Logger.getLogger(MovieService.class);

	public static MovieService getInstance() throws ExceptionCommonBase {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new MovieService();
				}
			}
		}
		return instance;
	}

	private MovieDao dao = null;
	private FileDao fileDao = null;
	private ParameterDao parameterDao = null;
	
	public MovieService() throws ExceptionCommonBase {
		dao = new MovieDao();
		fileDao = new FileDao();
		parameterDao = new ParameterDao();
	}
	
	/**
	 * @Description: 以事物方式处理一条拉取的douban电影的数据
	 * 
	 *               1.以电影名和豆瓣url为主键查找一条记录,若t_movie表中存在则update
	 *               ,若不存在则insert到t_movie 
	 *               2.拉取图片,更新信息到t_file, t_movie_image表
	 *               
	 *               注意事项:拉取记录日志
	 * @Author thinkpad
	 * @Create Date 2014年8月16日
	 * @Modified by none
	 * @Modified Date
	 */
	public void spiderDoubanMovieInfo(MovieBO movieBO, Map<Long, Integer> movieTagMap) throws Exception  {
		Connection conn = dao.getConnection();
		conn.setAutoCommit(false);
		try {
			MovieInfo movieInfo = movieBO.getMovieInfo();
			MovieInfo storeMovieInfo = dao.selectMovieInfoByMoiveNameAndDoubanId(movieInfo);
			long mId = 0;
			//1.
			if(storeMovieInfo != null){
				movieInfo.setId(storeMovieInfo.getId());
				dao.updateMovieInfo(conn, movieInfo);
			}else{
				mId = dao.insertMovieInfo(conn, movieInfo);
			}
			//2.拉取电影icon
			long fileSize = 0;
			try {
				long picLength = HttpUtils.getInputStreamLengthFromUrl(movieBO.getIconUrl());
				String writePath = ConstVar.DOUBAN_250_FILE_ROOT+Utils.getFileName(movieBO.getIconUrl());
				File file = new File(writePath);
				if(!(file.exists() && file.length() == picLength)){
					//本地文件不存在则拉取文件
					InputStream inputStream = HttpUtils.getInputStreamFromUrl(movieBO.getIconUrl());
					Thread.sleep(50);
					IFileSystem fileSystem = SFileSystemClient.getInstance("cloud");
					logger.info("writePath:" + writePath);
					fileSystem.mkdirs(writePath);
					fileSize = fileSystem.uploadFile(writePath, inputStream);
				}else{
					fileSize = picLength;
				}
			} catch (Exception e) {
				logger.error("拉取第"+movieBO.getNo()+"条电影的图片报错,iconUrl="+movieBO.getIconUrl(),e);
				throw e;
			}
			
			//存入图片信息到t_file表
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(Utils.getFileName(movieBO.getIconUrl()));
			fileInfo.setFileUsedType(FileUsedType.file);
			fileInfo.setFileSize(fileSize);
			fileInfo.setSaveName(Utils.getFileName(movieBO.getIconUrl()));
			long fileId = fileDao.insertFileInfo(fileInfo);
			//存入电影图片关联信息到t_movie_image表
			MovieImageInfo movieImageInfo = new MovieImageInfo();
			movieImageInfo.setEfsId(fileId);
			movieImageInfo.setmId(mId);
			movieImageInfo.setFileType(FileUsedType.lookup(fileInfo.getFileUsedType().value()));
			dao.insertMovieImageInfo(conn, movieImageInfo);
			
			//返回电影在豆瓣250中排位信息, 插入t_parameter_app
			movieTagMap.put(movieInfo.getId(), 999999-movieBO.getNo());
			conn.commit();
			logger.info("拉取第"+movieBO.getNo()+"条电影成功");
			
			
		} catch (Exception e) {
			conn.rollback();
			logger.error("拉取第"+movieBO.getNo()+"条电影报错", e);
			throw e;
		} finally{
			dao.releaseConnection(conn);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
//		String iconUrl = "http://img3.douban.com/view/movie_poster_cover/spst/public/p1910908765.jpg";
//		InputStream inputStream = HttpUtils.getInputStreamFromUrl(iconUrl);
//		IFileSystem fileSystem = SFileSystemClient.getInstance("cloud");
//		String writePath = ConstVar.DOUBAN_250_FILE_ROOT+Utils.getFileName(iconUrl);
//        logger.info("writePath:" + writePath);
//        fileSystem.mkdirs(writePath);
//        int fileSize = fileSystem.uploadFile(writePath, inputStream);
//        System.out.println(fileSize);
		
//		Utils.initLog4j();
		String douban250Url = "http://movie.douban.com/top250";
		int startNo = 0;
		int j = 0;
		try {
			Map<Long, Integer> movieTagMap = new HashMap<Long, Integer>();
			//遍历每页数据
			for(int i=0; i<=0; i++){
				startNo = 25*i;
				Document doc = Jsoup.connect(douban250Url+"?start="+235).get();
				Element content = doc.getElementById("content");
				Elements liElements = content.select(".grid_view > li");
				//每页10条电影的详情页
				Element liElement = liElements.get(0);
//				for(Element liElement : liElements){
					try {
						System.out.println(j++);
						MovieBO movieBO = SpiderDouBan250.parseMovieInfoFromWeb(liElement);
						MovieService.getInstance().spiderDoubanMovieInfo(movieBO, movieTagMap);
					} catch (Exception e) {
						logger.error(e);
						continue;
					}
//				}
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * @Description: 清空t_parameter_tag_link表中某个tagId下的所有电影的信息,再新增拉取的关联信息,该操作为一个事物 
	 * @Author thinkpad
	 * @Create Date  2014年8月18日
	 * @Modified by none
	 * @Modified Date
	 */
	public void updateMovieTagLink(Map<Long, Integer> movieTagMap, int tagId) throws Exception {
		Connection conn = parameterDao.getConnection();
		conn.setAutoCommit(false);
		try {
			parameterDao.deleteParameterTagLinkInfoByTagId(conn, tagId);
			for(Entry<Long, Integer> entry : movieTagMap.entrySet()){
				ParameterTagLinkInfo parameterTagLinkInfo = 
						new ParameterTagLinkInfo();
				parameterTagLinkInfo.setBusinessId(entry.getKey());
				parameterTagLinkInfo.setBusinessSortNo(new Long(entry.getValue()));
				parameterTagLinkInfo.setEnable(1);
				parameterTagLinkInfo.setTagId(new Long(tagId));
				parameterDao.insertParameterTagLinkInfo(conn, parameterTagLinkInfo);
			}
			conn.commit();
		}catch (Exception e) {
			conn.rollback();
			logger.error("更新t_parameter_tag_link表数据失败", e);
			throw e;
		} finally{
			parameterDao.releaseConnection(conn);
		}
	}
}
