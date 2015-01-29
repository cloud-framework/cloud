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

import com.cloud.spider.dao.FileDao;
import com.cloud.spider.dao.MovieDao;
import com.cloud.spider.dao.ParameterDao;
import com.cloud.spider.dao.ProductDao;
import com.cloud.spider.entity.bo.MovieBO;
import com.cloud.spider.entity.constants.ConstVar;
import com.cloud.spider.entity.constants.EnumType.ProductType;
import com.cloud.spider.entity.constants.FileUsedType;
import com.cloud.spider.entity.po.FileInfo;
import com.cloud.spider.entity.po.MovieImageInfo;
import com.cloud.spider.entity.po.MovieInfo;
import com.cloud.spider.entity.po.ParameterTagLinkInfo;
import com.cloud.spider.handler.SpiderDouBan250;
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
	
	public ProductService() throws ExceptionCommonBase {
		productDao = new ProductDao();
	}
	
	public Long getProductMaxIdByType(ProductType productType) throws ExceptionCommonBase{
		return productDao.getMaxIdByType(productType);
	}
}
