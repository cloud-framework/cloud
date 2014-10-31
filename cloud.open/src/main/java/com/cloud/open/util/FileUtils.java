package com.cloud.open.util;

import java.security.NoSuchAlgorithmException;

import cn.egame.common.util.Utils;

import com.cloud.open.entity.constants.FileUsedType;
import com.cloud.open.entity.exception.ErrorCode;
import com.cloud.open.entity.exception.ServiceExceptionBase;

public class FileUtils {
    public static String getFilePath(FileUsedType fileType, long fileId, String fileName) throws ServiceExceptionBase {
        String extName = Utils.getFileExtName(fileName);
        if (Utils.stringIsNullOrEmpty(extName)) {
            throw new ServiceExceptionBase(ErrorCode.ParameterError, fileType.toString());
        }
        switch (fileType.value()) {
        case FileUsedType.GAME:
            return "pkg/gm" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + "/" + fileName;
        case FileUsedType.HEAD_PHOTO:
            return "user/hd" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.HEAD_SML:
            return "user/hd/sml" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.HEAD_MID:
            return "user/hd/mid" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.HEAD_BIG:
            return "user/hd/big" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.HEAD_BACKGROUD:
            return "user/hd/back" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_PHOTO:
            return "pkg/ph" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_LOGO_SML:
            return "pkg/ph/sml" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_LOGO_BIG:
            return "pkg/ph/big" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_VIEW:
            return "pkg/ph/view" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_VIEW_SML:
            return "pkg/ph/view/sml" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_VIEW_BIG:
            return "pkg/ph/view/big" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.VIDEO:
            return "pkg/vo/fl" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.VIDEO_IMAGE:
            return "pkg/vo/ph" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_BACKGROUD:
            return "sns/ph/back" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.GAME_PHOTO_OLD:
            return "pkg/ph/old" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + "/";
        case FileUsedType.FILE:
            return "pkg/fl" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ADVER_PHOTO:
            return "pkg/ad" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ALBUM_PHOTO:
            return "sns/ab" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ALBUM_PHOTO_SML:
            return "sns/ab/sml" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ALBUM_PHOTO_MID:
            return "sns/ab/mid" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ALBUM_PHOTO_BIG:
            return "sns/ab/big" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.HEAD_DEFAULT:
            return "user/hd/def" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.CMS_PHOTO:
            return "cms/ph" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.CMS_FILE:
            return "cms/fl" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ACT_PHOTO:
            return "act/ph" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.ACT_FILE:
            return "act/fl" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId) + extName;
        case FileUsedType.CHANNEL_FILE:
            return "o/cpkg/wm" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId)+ "/" + fileName;
        default:
            throw new ServiceExceptionBase(ErrorCode.NotImplementCode, fileType.toString());
        }
    }

    public static String getFileDir(FileUsedType fileType, long fileId) throws NoSuchAlgorithmException, ServiceExceptionBase {
        switch (fileType.value()) {
        case FileUsedType.CHANNEL_FILE:
            return "o/cpkg/wm" + Utils.toPath("/", fileId) + "/" + Utils.toFileName(fileId)+"/";
        default:
            throw new ServiceExceptionBase(ErrorCode.NotImplementCode, fileType.toString());
        }
    }
}
