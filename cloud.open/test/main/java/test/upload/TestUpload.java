package test.upload;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.open.dao.FileDao;
import com.cloud.open.entity.constants.FileUsedType;
import com.cloud.open.service.FileService;

public class TestUpload {
	
	/*
	public static void main3(String[] args){
    	try {
    		
    		String readRootPath = ConstVar.UPLOAD_WRITE_ADDRESS;
    		String localFileRoot = "F:/f/";
    		long efsId = 646001;
    		FileInfo fileInfo = EGameClientBiz.getInstance().getFileInfo(0, 0, efsId);
    		String ftpFilePath = FileUtils.getFilePath(
    				fileInfo.getFileUsedType(), efsId, fileInfo.getFileName());
    		System.out.println("ftpFilePath = "+readRootPath+ftpFilePath);
    		System.out.println("localFilePath = "+localFileRoot+fileInfo.getFileName());
    		File file = new File(localFileRoot+fileInfo.getFileName());
    		FileOutputStream fos = null;
    		fos = new FileOutputStream(file);
    		new EGameClientBiz().getOutputStream(fos, ftpFilePath);
//    		 new EGameClientBiz().getOutputStream(0, 0, outputStream, efsId)
		} catch (Exception e) {
			e.printStackTrace();
		}
//		File file = new File("F:/f/ad/90be655f2hc35667.jpg");
//		System.out.println(file.exists());
	}
	*/
	
	public static void main(String[] args) throws Exception {
		/** 文件上传 */
//		Utils.initLog4j();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		FileService fileService = (FileService) ac.getBean("fileService");
		FileDao fileDao = (FileDao) ac.getBean("fileDao");
		System.out.println(fileService);
//		InputStream inputStream = new FileInputStream(new File("f:\\avi.avi"));
//		System.out.println(inputStream.available());
//		long efsId = EGameClientBiz.getInstance().writeToFile(inputStream, FileUsedType.VIDEO, 0, 0, "avi.avi");
//		System.out.println(efsId);
		
		InputStream inputStream2 = new FileInputStream(new File("F:\\test1\\MyEclipse.10.5.Crack.rar"));
		System.out.println(inputStream2.available());
		long efsId2 = fileService.writeToFile(inputStream2, FileUsedType.GAME, 0, 0, "MyEclipse.10.5.Crack.rar", true);
		System.out.println(efsId2);
		
		
		/** 根据路径获取efsId */
//		String filePath = "gm/000/000/646/7728fd8b7h9db712";
//		long efsId =Utils.getFileId(filePath, 0);
//		System.out.println(efsId);//646001
//		FileInfo fileInfo = EGameClientBiz.getInstance().getFileInfo(0, 0, efsId);
		
		/** 根据efsId, 获取文件流 */
//		OutputStream fos = new FileOutputStream("f://"+fileInfo.getSaveName());
//		String ftpFilePath = "";
//		EGameClientBiz.getInstance().getOutputStream(fos, ftpFilePath);
		
	}

}


/*

def uploadVideo() {
        def resultMap = [:]
        try{
            MultipartRequest multipartRequest = (MultipartRequest) request;
            Map requestMap = multipartRequest.getFileMap();
            Set entrySet = requestMap.entrySet();
            Iterator iterator = entrySet.iterator();
            MultipartFile multipartFile = null;
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                multipartFile = (MultipartFile) entry.getValue();
                break;
            }

            Long fileSize = multipartFile.getSize();
            def maxSize = params.get("maxSize");
            if (maxSize) {
                if (fileSize/1024 > Integer.valueOf(maxSize)) {
                    resultMap << [result: false];
                    resultMap << [msg: '上传的文件不得大于' + maxSize + 'K'];
                    render resultMap;
                    return;
                }
            }
            def minSize = params.get("minSize");
            if (minSize) {
                if (fileSize/1024 < Integer.valueOf(minSize)) {
                    resultMap << [result: false];
                    resultMap << [msg: '上传的文件不得小于' + minSize + 'K'];
                    render resultMap;
                    return;
                }
            }
            def pos = multipartFile.originalFilename.lastIndexOf(".");
            String imageFileName = System.currentTimeMillis() + "." + multipartFile.originalFilename.substring(pos + 1);
            long efsId = EGameClientBiz.getInstance().writeToFile(multipartFile.getInputStream(), FileUsedType.VIDEO, 0, 0, imageFileName, false);
            if (efsId == -1L) {
                resultMap << [result: false];
                resultMap << [msg: '上传失败'];
                render resultMap;
                return;
            }
            String url = ConstVar.DOWNLOAD_URL + FileUtils.getFilePath(FileUsedType.video, efsId, imageFileName);
            resultMap << [result: true];
            resultMap << [msg: '上传成功'];
            resultMap << [efsId: efsId];
            resultMap << [url: url];
        }
        catch (Exception e) {
            resultMap << [result: false];
            resultMap << [msg: '上传失败'];
            log.error("上传文件发生异常", e);
        }
        render resultMap;
    }

*/