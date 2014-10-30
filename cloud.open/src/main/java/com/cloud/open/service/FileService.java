package com.cloud.open.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.egame.common.efs.IFileSystem;
import cn.egame.common.efs.SFileSystemClient;
import cn.egame.common.util.ImageUtils;
import cn.egame.common.util.Utils;

import com.cloud.open.entity.constants.ConstVar;
import com.cloud.open.entity.constants.FileUsedType;
import com.cloud.open.entity.exception.ExceptionCommon;
import com.cloud.open.entity.po.FileInfo;
import com.cloud.open.entity.po.ImageScaleInfo;
import com.cloud.open.util.FileUtils;
import com.cloud.spider.dao.FileDao;

@Service
public class FileService {
	
	private static final Logger LOGGER = Logger.getLogger(FileService.class);
	
	@Autowired
	private FileDao fileDao = null;
	/**
     * 上传文件接口
     * 
     * @param in
     *            :文件输入流
     * @param type
     *            ：文件类型,对应FileUsedType类静态变量
     * @param appId
     * @param loginUserId
     * @param fileName
     *            :文件名称
     * @return
     */
    public long writeToFile(InputStream in, int type, int appId, long loginUserId, String fileName, boolean... isScale) {
        String readRootPath = ConstVar.UPLOAD_ADDRESS;
        String writeRootPath = ConstVar.UPLOAD_WRITE_ADDRESS;
        long efsId = 0;
        String fileType = Utils.getFileExtName(fileName);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(fileName);
        fileInfo.setFileUsedType(FileUsedType.lookup(type));
        fileInfo.setCreateTime(System.currentTimeMillis());
        efsId = fileDao.insertFileInfo(fileInfo);
        OutputStream out = null;
        try {
            if (efsId > 0) {
                String filePath = FileUtils.getFilePath(FileUsedType.lookup(type), efsId, fileName);
                LOGGER.info("filePath:" + filePath);
                String writePath = writeRootPath + filePath;
                String readPath = readRootPath + filePath;
                IFileSystem fileSystem = SFileSystemClient.getInstance("egame");
                LOGGER.info("writePath:" + writePath);
                LOGGER.info("readPath:" + readPath);
                fileSystem.mkdirs(writePath);
                int fileSize = fileSystem.uploadFile(writePath, in);
                LOGGER.info("获取上传文件的总共的容量：" + fileSize);
                String imageType = ImageUtils.getFormatInFile(new File(readPath));
                fileInfo.setId(efsId);
                fileInfo.setFilePath(filePath);
                fileInfo.setSaveName(Utils.toFileName(efsId) + fileType);
                fileInfo.setFileSize(new Long(fileSize));
                if (imageType != null) {
                    fileInfo.setImageType(("image/" + imageType).toLowerCase());
                }
                if (saveTFile(type, fileInfo, appId, loginUserId, writeRootPath, readRootPath, "egame", isScale) == efsId) {
                    return efsId;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } catch (ExceptionCommon e) {
        	LOGGER.error(FileService.class, e);
        } catch (NoSuchAlgorithmException e) {
        	LOGGER.error(FileService.class, e);
        } catch (IOException e) {
        	LOGGER.error(FileService.class, e);
        } catch (Exception e) {
        	LOGGER.error(FileService.class, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                	LOGGER.error(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                	LOGGER.error(e);
                }
            }
        }
        return -1;
    }
    
    private static long saveTFile(int fileUserdType, FileInfo fileInfo, int appId, long loginUserId, String writeRootPaht,
            String readRootPath, String fileSystemType, boolean... isScale) {
        String filePath = fileInfo.getFilePath();
        String fileName = fileInfo.getFileName();
        long efsId = fileInfo.getId();
        try {
        	//TODO 图片压缩
//            if (isScale.length == 0 || isScale[0] == true) {
//                ImageScaleInfo imageScaleInfo = new ImageScaleInfo(FileUsedType.lookup(fileUserdType));
//                if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.GAME_PHOTO) {
//                    scaleGamePhoto(efsId, fileName, writeRootPaht, readRootPath, filePath, imageScaleInfo, fileSystemType);
//                } else if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.GAME_VIEW) {
//                    scaleGameView(efsId, fileName, writeRootPaht, readRootPath, filePath, imageScaleInfo, fileSystemType);
//                } else if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.HEAD_PHOTO) {
//                    scaleHeadPhoto(efsId, fileName, writeRootPaht, readRootPath, filePath, imageScaleInfo, fileSystemType);
//                } else if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.ALBUM_PHOTO) {
//                    scaleAlbumPhoto(efsId, fileName, writeRootPaht, readRootPath, filePath, imageScaleInfo, fileSystemType);
//                } else if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.GAME_BACKGROUD) {
//                    scaleGameBackgroud(efsId, fileName, writeRootPaht, readRootPath, filePath, imageScaleInfo, fileSystemType);
//                } else if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.HEAD_BACKGROUD) {
//                    scaleHeadBackgroud(efsId, fileName, writeRootPaht, readRootPath, filePath, imageScaleInfo, fileSystemType);
//                }
//            }

        } catch (Exception e) {
        	LOGGER.error(FileService.class, e);
            return -1;
        }

        String saveName = fileInfo.getSaveName();
        if (FileUsedType.lookup(fileUserdType).value() == FileUsedType.GAME) {
            saveName = fileName;
            try {
            	//md5加密
//            	String readPath = readRootPath + filePath;
//            	String md5 = Utils.encryptFileMD5(readPath);
//            	fileInfo.setMd5(md5);
			} catch (Throwable e) {
				LOGGER.error("", e);
			}
        }
        fileInfo.setSaveName(saveName);
        fileInfo.setIsSave(true);
        fileInfo.setIsComplete(true);
        // 上传成功后更新save字段
        if (setTFileInfo(fileInfo, appId, loginUserId) > 0) {
            return efsId;
        }
        return -1;
    }
    
    private long setTFileInfo(FileInfo fileInfo, int appId, long loginUserId) {
        try {
            return fileDao.updateFileInfo(fileInfo);
        } catch (ExceptionCommon e) {
            LOGGER.error(e);
            return -1;
        }

    }
    
}
