package com.cloud.spider.entity.constants;

import java.util.TreeMap;

public enum FileUsedType {

    game(FileUsedType.GAME), head_photo(FileUsedType.HEAD_PHOTO), head_sml(FileUsedType.HEAD_SML), head_mid(FileUsedType.HEAD_MID), head_big(
            FileUsedType.HEAD_BIG), game_photo(FileUsedType.GAME_PHOTO), game_logo_sml(FileUsedType.GAME_LOGO_SML), game_logo_big(
            FileUsedType.GAME_LOGO_BIG), game_view(FileUsedType.GAME_VIEW), game_view_sml(FileUsedType.GAME_VIEW_SML), game_view_big(
            FileUsedType.GAME_VIEW_BIG), file(FileUsedType.FILE), game_photo_old(FileUsedType.GAME_PHOTO_OLD), adver_photo(
            FileUsedType.ADVER_PHOTO), album_photo(FileUsedType.ALBUM_PHOTO), album_photo_sml(FileUsedType.ALBUM_PHOTO_SML), album_photo_mid(
            FileUsedType.ALBUM_PHOTO_MID), album_photo_big(FileUsedType.ALBUM_PHOTO_BIG), game_backgroud(FileUsedType.GAME_BACKGROUD), head_default(
            FileUsedType.HEAD_DEFAULT), head_backgroud(FileUsedType.HEAD_BACKGROUD), video(FileUsedType.VIDEO), video_image(
            FileUsedType.VIDEO_IMAGE), cms_photo(FileUsedType.CMS_PHOTO), cms_file(FileUsedType.CMS_FILE), act_photo(FileUsedType.ACT_PHOTO), act_file(
            FileUsedType.ACT_FILE), page_file(FileUsedType.PAGE_FILE);
    // thumbnail
    public final static int GAME = 1;
    public final static int VIDEO = 2;
    public final static int VIDEO_IMAGE = 21;
    public final static int HEAD_PHOTO = 100;
    public final static int HEAD_SML = 101;
    public final static int HEAD_MID = 102;
    public final static int HEAD_BIG = 103;
    public final static int HEAD_BACKGROUD = 104;
    public final static int GAME_PHOTO = 200;
    public final static int GAME_LOGO_SML = 201;// 100*100
    public final static int GAME_LOGO_BIG = 202;// 188*188
    public final static int GAME_VIEW = 203;// 800*480
    public final static int GAME_VIEW_SML = 204;// 400*240
    public final static int GAME_VIEW_BIG = 205;// 800*480
    public final static int GAME_BACKGROUD = 206;
    public final static int GAME_PHOTO_OLD = 300;
    public final static int ADVER_PHOTO = 400;
    public final static int ALBUM_PHOTO = 500;
    public final static int ALBUM_PHOTO_SML = 501;
    public final static int ALBUM_PHOTO_MID = 502;
    public final static int ALBUM_PHOTO_BIG = 503;
    public final static int HEAD_DEFAULT = 105;
    public final static int CMS_PHOTO = 600;// WEB CMS
    public final static int CMS_FILE = 601;// WEB CMS
    public final static int ACT_PHOTO = 700;// WEB 活动
    public final static int ACT_FILE = 701;// WEB 活动

    public final static int FILE = 1000;
    
    public final static int PAGE_FILE = 1010;
    int value = 0;

    FileUsedType(int value) {
        this.value = value;

    }

    public int value() {
        return this.value;
    }

    private static TreeMap<Integer, FileUsedType> _map;

    static {
        _map = new TreeMap<Integer, FileUsedType>();
        for (FileUsedType num : FileUsedType.values()) {
            _map.put(num.value(), num);
        }
    }

    public static FileUsedType lookup(int value) {
        FileUsedType obj = _map.get(value);
        if (obj == null) {
            return file;
        }
        return obj;
    }
}
