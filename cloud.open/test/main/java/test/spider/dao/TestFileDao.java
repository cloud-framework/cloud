package test.spider.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.egame.common.data.SqlUtils;

import com.cloud.spider.dao.FileDao;
import com.cloud.spider.entity.constants.FileUsedType;
import com.cloud.spider.entity.po.FileInfo;

public class TestFileDao {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		IUserServ iuserServ = (IUserServ) ac.getBean("userServImpl");
//		iuserServ.saveUser(new User());
//		iuserServ.showUser();
//		iuserServ.updateUser(new User());
		
		
		FileDao fileDao = (FileDao) ac.getBean("fileDao");
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName("xxx");
		fileInfo.setAppId(1L);
		fileInfo.setuId(1L);
		fileInfo.setFileType(FileUsedType.adver_photo);
		fileInfo.setImageType("");
		fileInfo.setFileSize(10L);
		fileInfo.setSaveName("");
		fileInfo.setIsSave(true);
		fileInfo.setIsComplete(true);
		fileInfo.setIsDelete(false);
		int id = fileDao.insertFileInfo(fileInfo);
		System.out.println(id);
	}
}
