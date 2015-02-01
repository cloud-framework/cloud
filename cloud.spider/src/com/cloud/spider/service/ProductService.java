package com.cloud.spider.service;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import cn.egame.common.efs.IFileSystem;
import cn.egame.common.efs.SFileSystemClient;
import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.common.util.Utils;

import com.cloud.spider.dao.FileDao;
import com.cloud.spider.dao.ProductDao;
import com.cloud.spider.entity.bo.ProductBO;
import com.cloud.spider.entity.constants.ConstVar;
import com.cloud.spider.entity.constants.EnumType.ProductType;
import com.cloud.spider.entity.constants.FileUsedType;
import com.cloud.spider.entity.po.FileInfo;
import com.cloud.spider.entity.po.MovieImageInfo;
import com.cloud.spider.entity.po.ProductImageInfo;
import com.cloud.spider.entity.po.ProductInfo;
import com.cloud.spider.util.HttpUtils;

public class ProductService {
	private static ProductService instance = null;
	private static byte[] lock = new byte[0];
	private static Logger logger = Logger.getLogger(ProductService.class);

	public static ProductService getInstance() throws ExceptionCommonBase {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new ProductService();
				}
			}
		}
		return instance;
	}

	private ProductDao productDao = null;
	private FileDao fileDao = null;
	
	
	public ProductService() throws ExceptionCommonBase {
		productDao = new ProductDao();
		fileDao = new FileDao();
	}
	
	public Long getProductMaxIdByType(ProductType productType) throws ExceptionCommonBase{
		return productDao.getMaxIdByType(productType);
	}

	public void storeProductAndImageInfo(ProductBO productBO) throws Exception {
		Connection conn = productDao.getConnection();
		conn.setAutoCommit(false);
		try {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductSrcId(productBO.getPic_id());
			productInfo.setProductSrcPageId(productBO.getPage_id());
			productInfo.setProductType(productBO.getProductType());
			productInfo.setProductStatus(1);
			long identity = productDao.insertProductInfo(conn, productInfo);
			//1.拉取每条信息对应的图片,并存入图片库
			long fileSize = 0;
			List<String> picUrlList = productBO.getPicUrl();
			int picCount = 0;
			for(String url : picUrlList){
				picCount++;
				String writePath = ConstVar.UPLOAD_ADDRESS
						+File.separator+productInfo.getProductType()
						+File.separator+productInfo.getProductSrcPageId()
						+ Utils.getFileName(url);
				File file = new File(writePath);
				if (!(file.exists())) {
					// 本地文件不存在则拉取文件
					InputStream inputStream = HttpUtils
							.getInputStreamFromUrl(url);
					Thread.sleep(50);
					IFileSystem fileSystem = SFileSystemClient
							.getInstance("cloud");
					logger.info("writePath:" + writePath);
					fileSystem.mkdirs(writePath);
					fileSize = fileSystem.uploadFile(writePath, inputStream);
				}else{
					logger.info("第"+productBO.getPage_id()+"页的id为"+productBO.getPic_id()
							+"的第"+picCount+"张图片"+productBO.getPicUrl()+"已经存在...");
				}
				
				//存入图片信息到t_file表
				FileInfo fileInfo = new FileInfo();
				fileInfo.setFileName(Utils.getFileName(url));
				fileInfo.setFileType(FileUsedType.page_file);
				fileInfo.setFileSize(fileSize);
				fileInfo.setSaveName(Utils.getFileName(url));
				long fileId = fileDao.insertFileInfo(conn, fileInfo);
				//存入电影图片关联信息到t_movie_image表
				ProductImageInfo productImageInfo = new ProductImageInfo();
				productImageInfo.setEfsId(fileId);
				productImageInfo.setProductId(identity);
				productImageInfo.setFileType(FileUsedType.lookup(fileInfo.getFileType().value()));
				productDao.insertProductImageInfo(conn, productImageInfo);
			}
			conn.commit();
			logger.info("拉取第"+productBO.getPage_id()+"页的id为"+productBO.getPic_id()+"的图片成功");
		} catch (Exception e) {
			conn.rollback();
			logger.error("拉取第"+productBO.getPage_id()+"页的id为"+productBO.getPic_id()+"图片报错",e);
			throw e;
		} finally{
			productDao.releaseConnection(conn);
		}
	}
}
