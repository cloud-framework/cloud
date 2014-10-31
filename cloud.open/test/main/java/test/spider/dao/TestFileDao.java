package test.spider.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.open.dao.FileDao;
import com.cloud.open.entity.constants.FileUsedType;
import com.cloud.open.entity.exception.ServiceExceptionBase;
import com.cloud.open.entity.po.FileInfo;

public class TestFileDao {
	public static void main(String[] args) throws ServiceExceptionBase {
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
		fileInfo.setFileUsedType(FileUsedType.adver_photo);
		fileInfo.setImageType("");
		fileInfo.setFileSize(10L);
		fileInfo.setSaveName("");
		fileInfo.setSave(true);
		fileInfo.setComplete(true);
		fileInfo.setDelete(false);
		int id = fileDao.insertFileInfo(fileInfo);
		System.out.println(id);
	}
}
