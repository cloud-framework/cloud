package com.cloud.open.entity.po;

import java.io.Serializable;

import com.cloud.open.entity.constants.ConstVar;
import com.cloud.open.entity.constants.FileUsedType;

public class ImageScaleInfo implements Serializable {
    /**
     * @return the fileUserdType
     */
    public FileUsedType getFileUserdType() {
        return fileUserdType;
    }

    /**
     * @param fileUserdType
     *            the fileUserdType to set
     */
    public void setFileUserdType(FileUsedType fileUserdType) {
        this.fileUserdType = fileUserdType;
    }

    private static final long serialVersionUID = -7360702567466419207L;
    private int smallWidth;

    /**
     * @return the smallWidth
     */
    public int getSmallWidth() {
        return smallWidth;
    }

    /**
     * @param smallWidth
     *            the smallWidth to set
     */
    public void setSmallWidth(int smallWidth) {
        this.smallWidth = smallWidth;
    }

    /**
     * @return the smallHeight
     */
    public int getSmallHeight() {
        return smallHeight;
    }

    /**
     * @param smallHeight
     *            the smallHeight to set
     */
    public void setSmallHeight(int smallHeight) {
        this.smallHeight = smallHeight;
    }

    /**
     * @return the midWidth
     */
    public int getMidWidth() {
        return midWidth;
    }

    /**
     * @param midWidth
     *            the midWidth to set
     */
    public void setMidWidth(int midWidth) {
        this.midWidth = midWidth;
    }

    /**
     * @return the midHeight
     */
    public int getMidHeight() {
        return midHeight;
    }

    /**
     * @param midHeight
     *            the midHeight to set
     */
    public void setMidHeight(int midHeight) {
        this.midHeight = midHeight;
    }

    /**
     * @return the bigWidth
     */
    public int getBigWidth() {
        return bigWidth;
    }

    /**
     * @param bigWidth
     *            the bigWidth to set
     */
    public void setBigWidth(int bigWidth) {
        this.bigWidth = bigWidth;
    }

    /**
     * @return the bigHeight
     */
    public int getBigHeight() {
        return bigHeight;
    }

    /**
     * @param bigHeight
     *            the bigHeight to set
     */
    public void setBigHeight(int bigHeight) {
        this.bigHeight = bigHeight;
    }

    private int smallHeight;
    private int midWidth;
    private int midHeight;
    private int bigWidth;
    private int bigHeight;
    private FileUsedType fileUserdType;

    public ImageScaleInfo(FileUsedType fileUsedType) {
        this.fileUserdType = fileUsedType;
        if (fileUserdType != null) {
            if (fileUserdType.value() == FileUsedType.GAME_PHOTO) {
                setSmallHeight(ConstVar.GAME_PHOTO_LOG_SMALL_HEIGHT);
                setSmallWidth(ConstVar.GAME_PHOTO_LOG_SMALL_WIDTH);
                setBigHeight(ConstVar.GAME_PHOTO_LOG_BIG_HEIGHT);
                setBigWidth(ConstVar.GAME_PHOTO_LOG_BIG_WIDTH);
            } else if (fileUserdType.value() == FileUsedType.GAME_VIEW) {
                setSmallHeight(ConstVar.GAME_PHOTO_SML_VIEW_HEIGHT);
                setSmallWidth(ConstVar.GAME_PHOTO_SML_VIEW_WIDTH);
                setBigHeight(ConstVar.GAME_PHOTO_BIG_VIEW_HEIGHT);
                setBigWidth(ConstVar.GAME_PHOTO_BIG_VIEW_WIDTH);
            } else if (fileUserdType.value() == FileUsedType.HEAD_PHOTO) {
                setSmallHeight(ConstVar.HEAD_PHOTO_SMALL_HEIGHT);
                setSmallWidth(ConstVar.HEAD_PHOTO_SMALL_WIDTH);
                setMidHeight(ConstVar.HEAD_PHOTO_MIDDEL_HEIGHT);
                setMidWidth(ConstVar.HEAD_PHOTO_MIDDEL_WIDTH);
                setBigHeight(ConstVar.HEAD_PHOTO_BIG_HEIGHT);
                setBigWidth(ConstVar.HEAD_PHOTO_BIG_WIDTH);
            } else if (fileUserdType.value() == FileUsedType.ALBUM_PHOTO) {
                setSmallHeight(ConstVar.ALBUM_PHOTO_SMALL_HEIGHT);
                setSmallWidth(ConstVar.ALBUM_PHOTO_SMALL_WIDTH);
                setMidHeight(ConstVar.ALBUM_PHOTO_MIDDEL_HEIGHT);
                setMidWidth(ConstVar.ALBUM_PHOTO_MIDDEL_WIDTH);
                setBigHeight(ConstVar.ALBUM_PHOTO_BIG_HEIGHT);
                setBigWidth(ConstVar.ALBUM_PHOTO_BIG_WIDTH);
            } else if (fileUserdType.value() == FileUsedType.GAME_BACKGROUD) {
                setBigHeight(ConstVar.GAME_PHOTO_BACKGROUD_HEIGHT);
                setBigWidth(ConstVar.GAME_PHOTO_BACKGROUD_WIDTH);
            } else if (fileUserdType.value() == FileUsedType.HEAD_BACKGROUD) {
                setBigHeight(ConstVar.HEAD_PHOTO_BACKGROUD_HEIGHT);
                setBigWidth(ConstVar.HEAD_PHOTO_BACKGROUD_WIDTH);
            }
        }
    }

}
