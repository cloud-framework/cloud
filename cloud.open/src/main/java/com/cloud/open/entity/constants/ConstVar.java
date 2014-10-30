package com.cloud.open.entity.constants;

import java.util.Properties;

import cn.egame.common.util.Utils;

public class ConstVar {
    public final static long LONG_AVANCED_USER_ID = -1L;
    public final static long LONG_DEFAULT_USER_ID = 0L;
    public final static int INT_AVANCED_APP_ID = -1;

    public final static int INT_COUNT_MAX_RECORDS = 1000;
    public final static int INT_COUNT_PAGE_RECORDS = 12;
    public final static int INT_COUNT_MIN_RECORDS = 0;
    public final static int INT_COUNT_DEFAULT_RECORDS = 20;
    // public final static int INT_COUNT_DEFAULT_TOPIC_RECORDS = 15;

    public final static int CHANNEL_ID = 8;
    public final static int TYPICAL = 1;
    public final static String DEFAULT_BIRTHDAY = "1900-01-01";
    public final static int EXPERIENCE_VALUE = 10;
    // public static String DNS_HOST="http://open.play.cn";
    public static String HTTP_HOST = "http://open.play.cn/";
    public static String HTTPS_HOST = "https://open.play.cn/";
    public static String UPLOAD_ADDRESS = "/opt/web/open.play.cn/8082/webapps/egame.sns.open/f/";
    public static String DOWNLOAD_URL = "http://cdn.play.cn/f/";
    public static String DOWNLOAD_URL_DEFAULT = "http://cdn.play.cn/f/";
    public static String DOWNLOAD_URL_OPEN = "http://open.play.cn/f/";
//    public static String DOWNLOAD_URL_LIST = "http://cdn.play.cn/f/m/";
    public static String INNER_DOWNLOAD_URL = "http://192.168.10.63:8082/f/";

    public static String UPLOAD_WRITE_ADDRESS = "/opt/web/caidan.play.cn/8181/webapps/egame.server.open/f/";

    // 4G客户端免流量
    public static String HTTP_HOST_4G = "http://open.4g.play.cn/";
    public static String DOWNLOAD_URL_4G = "http://cdn.4g.play.cn/f/";
    public static String DOWNLOAD_URL_4G_DEFAULT = "http://cdn.4g.play.cn/f/";
    public static String IMSI_4G_REG = "^\\d+$";
    public static String HTTPS_HOST_4G = "http://open.4g.play.cn/";

    public static String MASTER_READ_PATH = "/data/cdn1/f/";
    public static String MASTER_WRITE_PATH = "/data/cdn1/f/";
    public static String BACKUP_READ_PATH = "/data/cdn2/f/";
    public static String BACKUP_WRITE_PATH = "/data/cdn2/f/";
    public static String COMMENT_TOTAL_SWITCH = "1";// 1：评论全部显示；0：不显示
    public static String COMMENT_AUDIT_SWITCH = "1";// 1:先审核后显示；0:先显示后审核
    public static String COMMENT_REPLY_AUDIT_SWITCH = "1";// 1:二级评论先审核后显示；0:二级评论先显示后审核

    public static String BBS_URL = "http://bbs.play.cn";// v7.4.6网游论坛url
    public static String UNSTALL_ANSWER_URL = "http://www.sojump.com/m/3728875.aspx";// v7.4.9卸载问卷调查地址

    // 金币积分体系是否抛事件给核心

    public static int COIN_EVENT_FLAG = 1; // 0 :不抛
    // 根据IMSI判断来源正则表达式

    public static String REG_DIANXIN = "^(46003|46011|46005)\\d{10}$";
    public static String REG_LIANTONG = "^46001\\d{10}$";
    public static String REG_YIDONG = "^(46000|46002|46007)\\d{10}$";
    //猜你喜欢添加开关
    public static int  GAME_LIKE_FLAG=1; //1：走相似度矩阵

    // 现网
    // public static final String ORACLE_HOST = "http://202.102.39.26:8090";
    // 测试
    public static String ORACLE_HOST = "http://202.102.39.26:8090";
    public static String OTA_DOWNLOAD_URL = "http://202.102.39.7/sharefiles";
    public static String ORACLE_INTERFACE_LOGIN_USERNAME = "/EOI/interface/user/loginUserInfo";
    public static String ORACLE_INTERFACE_LOGIN_IMSI = "/EOI/interface/user/loginIMSIUserInfo";
    public static String ORACLE_INTERFACE_CHECK_ACCOUNT = "/EOI/interface/user/checkUserAccount";

    // 社区服务器地址
    // 现网
    public static String SNS_COMMERCIAL_SERVER_URL = "http://open.play.cn";
    public static String USER_COMMERCIAL_HTTP_SERVER_URL = "http://open.play.cn";
	public static String USER_COMMERCIAL_HTTPS_SERVER_URL = "https://open.play.cn";
    // 测试
    public static String SNS_TEST_SERVER_URL = "http://202.102.111.27:8082";
    public final static String[] CONTENTYPES = { "jar", "apk", "image/jpeg", "image/gif", "image/png" };
    public final static String PICTUREEXT = "pic2.jpg";
    // 压缩的图片大小
    public final static int GAME_PHOTO_LOG_SMALL_WIDTH = 100;
    public final static int GAME_PHOTO_LOG_SMALL_HEIGHT = 100;

    public final static int GAME_PHOTO_LOG_BIG_WIDTH = 300;
    public final static int GAME_PHOTO_LOG_BIG_HEIGHT = 300;

    public final static int GAME_PHOTO_BIG_VIEW_WIDTH = 480;
    public final static int GAME_PHOTO_BIG_VIEW_HEIGHT = 800;

    public final static int GAME_PHOTO_SML_VIEW_WIDTH = 270;
    public final static int GAME_PHOTO_SML_VIEW_HEIGHT = 450;
    // 游戏背景图片
    public final static int GAME_PHOTO_BACKGROUD_WIDTH = 480;
    public final static int GAME_PHOTO_BACKGROUD_HEIGHT = 800;

    // 用户背景图片
    public final static int HEAD_PHOTO_BACKGROUD_WIDTH = 480;
    public final static int HEAD_PHOTO_BACKGROUD_HEIGHT = 800;

    public final static int HEAD_PHOTO_SMALL_WIDTH = 80;
    public final static int HEAD_PHOTO_SMALL_HEIGHT = 80;

    public final static int HEAD_PHOTO_MIDDEL_WIDTH = 200;
    public final static int HEAD_PHOTO_MIDDEL_HEIGHT = 200;

    public final static int HEAD_PHOTO_BIG_WIDTH = 400;
    public final static int HEAD_PHOTO_BIG_HEIGHT = 400;

    public final static int ALBUM_PHOTO_SMALL_WIDTH = 120;
    public final static int ALBUM_PHOTO_SMALL_HEIGHT = 120;

    public final static int ALBUM_PHOTO_MIDDEL_WIDTH = 240;
    public final static int ALBUM_PHOTO_MIDDEL_HEIGHT = 240;

    public final static int ALBUM_PHOTO_BIG_WIDTH = 480;
    public final static int ALBUM_PHOTO_BIG_HEIGHT = 480;

    public final static String HOT_LABEL = "热门";
    public final static String NEW_LABEL = "最新";
    /************************* 用户行为日志actionCode start **************************/

    // 游戏一级入口列表
    public final static int GAME_FIRST_COLUMN_LIST = 4401;
    // 分类一级入口列表
    public final static int CLASSIFICATION_FIRST_COLUMN_LIST = 4402;
    // 游戏下推荐列表
    public final static int GAME_RECOMMEND_COLUMN_LIST = 44001;
    // 游戏下新品列表
    public final static int GAME_NEW_COLUMN_LIST = 44002;
    // 游戏下棋牌列表
    public final static int GAME_CHESSGAME_COLUMN_LIST = 44003;
    // 游戏下体感列表
    public final static int GAME_SENSEGAME_COLUMN_LIST = 44004;
    // 游戏下免费列表
    public final static int GAME_FREEGAME_COLUMN_LIST = 44005;
    // 游戏下网游列表
    public final static int GAME_ONLINEGAME_COLUMN_LIST = 44006;
    // 游戏下大家还喜欢列表
    public final static int GAME_ALLLIKEGAME_COLUMN_LIST = 44007;
    // 游戏下搜索推荐列表
    public final static int GAME_SEARCHRECOMMEND_COLUMN_LIST = 44008;
    // 搜索跳转游戏详情页
    public final static int GAME_SEARCH_GAMEDETAIL = 440088;
    // 搜索标签
    public final static int GAME_TAGS = 440001;
    // 搜索列表44009
    public final static int GAME_SEARCH_COLUMN_LIST = 44009;
    // 游戏周排行
    public final static int GAME_WEEK_RANK = 44011;
    // 游戏月排行
    public final static int GAME_MONTH_RANK = 44012;
    // 游戏总排行
    public final static int GAME_TOTAL_RANK = 44013;

    // 游戏周排行游戏详情
    public final static int GAME_WEEK_RANK_GAMEDETAIL = 440111;
    // 游戏月排行游戏详情
    public final static int GAME_MONTH_RANK_GAMEDETAIL = 440121;
    // 游戏总排行游戏详情
    public final static int GAME_TOTAL_RANK_GAMEDETAIL = 440131;
    // 分类列表
    public final static int CLASSIFICATION_COLUMN_LIST = 44021;
    // 分类列表下游戏详情
    public final static int CLASSIFICATION_COLUMN_LIST_GAMEDETAIL = 440211;
    // 版本升级
    public final static int HALL_UPGRADE = 44031;
    // 获取道具信息
    public final static int GAME_CONSUME = 44041;
    // 客户端退出退出
    public final static int HALL_LOGOUT = 44051;

    // 新春活动跳转游戏详情页
    public final static int ACTIVITY_GAMEDETAIL = 440016;
    // 广告位游戏详情
    public final static int ADVER_GAMEDETAIL = 440010;

    // 推荐位广告
    public final static int GAME_RECOMMEND_ADVER = 4400101;
    // 新品位广告
    public final static int GAME_NEW_ADVER = 4400102;
    // WIDGET广告
    public final static int GAME_WIDGET = 4400105;
    // loading页
    public final static int GAME_LOADING = 4400107;
    // 弹出广告
    public final static int GAME_POPUP = 4400109;
    // 退出大厅
    public final static int GAME_HALL_LOGOUT = 440311;
    /************************ 用户行为日志 actionCode end *******************************************/

    /************************ 下载日志downloadform start *******************************************/
    // 游戏下推荐下载
    public final static int GAME_RECOMMEND_DWONLOADFROM = 102;
    // 游戏下新品下载
    public final static int GAME_NEW_DWONLOADFROM = 104;
    // 游戏下棋牌下载
    public final static int GAME_CHESSGAME_DWONLOADFROM = 200;
    // 游戏下体感下载
    public final static int GAME_SENSEGAME_DWONLOADFROM = 201;
    // 游戏下免费下载
    public final static int GAME_FREEGAME_DWONLOADFROM = 202;
    // 游戏下网游下载
    public final static int GAME_ONLINEGAME_DWONLOADFROM = 203;
    // 大家还喜欢下载
    public final static int GAME_ALLLIKEGAME_DWONLOADFROMT = 204;
    // 搜索推荐推荐下载
    public final static int GAME_RECOMMENDSEARCH_DWONLOADFROMT = 205;
    // 搜索下载
    public final static int GAME_SEARCH_DWONLOADFROMT = 112;
    // 分类下游戏下载
    public final static int GAME_CLASSIFICATION_DWONLOADFROMT = 114;

    // 游戏周排行下载
    public final static int GAME_WEEK_DWONLOADFROMT = 106;
    // 游戏月排行下载
    public final static int GAME_MONTH_DWONLOADFROMT = 107;
    // 游戏总排行下载
    public final static int GAME_TOTAL_DWONLOADFROMT = 108;
    // 推荐位广告下载
    public final static int ADVER_RECOMMEND_DWONLOADFROMT = 101;
    // 新品位广告下载
    public final static int ADVER_NEW_DWONLOADFROMT = 103;
    // WIDGET广告下载
    public final static int ADVER_WIDGET_DWONLOADFROMT = 105;
    // 弹出广告下载
    public final static int ADVER_POPUP_DWONLOADFROMT = 109;

    /************************ 下载日志downloadform end *********************************************/

    public static final String ALL_GAME = "all";
    public static final String USER_KEY_PREFIX = "u";
    public static final String GAME_KEY_PREFIX = "g";
    public static final String MSG_KEY_PREFIX = "m";
    public static final String ACCOUNT_TYPE = "0";
    public static final String FROMER = "90250708";
    public static final String PUBLIC_KEY = "6ba4146c0d1s6678jkdf892d793ed6hf";
    // 社区游戏谁在玩，横屏客户端 玩家不足补齐马甲逻辑 玩家数
    public static final int GAME_WHO_IS_PLAY_PADDING_HORIZONTALLIMIT = 5;
    // 社区游戏谁在玩，竖屏客户端 玩家不足补齐马甲逻辑 玩家数
    public static final int GAME_WHO_IS_PLAY_PADDING_VERTICALLIMIT = 8;

    public static final String[] NICK_PRE = { "喜悦", "欢畅", "惊喜", "欢欣", "快慰", "狂喜", "欢笑", "欢呼", "逗趣", "欢乐", "乐观", "开怀", "喜气", "乐意", "爽快",
            "怡然", "羞愧", "愤慨", "怒吼", "自责", "恼怒", "悲叹", "郁闷", "恐惧", "震惊", "惊讶", "惶恐", "颤抖", "发抖", "吃惊", "恐怖", "猥琐", "清新", "沉着", "矜持", "发愣",
            "尴尬", "冷酷", "傲娇", "太正经", "直率", "开朗", "大方", "主动", "热情", "谦逊", "火大", "调皮", "坚强", "开开心心", "干干净净", "大大方方", "客客气气", "实实在在", "踏踏实实",
            "扎扎实实", "堂堂正正", "快快乐乐", "白白胖胖", "老老实实", "暖洋洋", "醉醺醺", "香喷喷", "干巴巴", "羞答答", "黑黝黝", "慢腾腾", "笑嘻嘻", "香喷喷", "湿漉漉", "喜盈盈", "亮晶晶",
            "毛绒绒", "胖乎乎", "红彤彤", "光闪闪", "油乎乎", "光溜溜", "黑油油", "粘糊糊", "亮晶晶", "胖墩墩", "肉墩墩", "瘦巴巴", "喜洋洋", "喜滋滋", "喜冲冲", "兴冲冲", "乐悠悠", "乐陶陶",
            "乐滋滋", "仪表堂堂", "言笑晏晏", "野心勃勃", "羞人答答", "铁骨铮铮", "大名鼎鼎", "可怜巴巴", "气宇昂昂", "含情脉脉", "谦谦君子", "翩翩风度", "翩翩起舞", "脉脉含情", "炯炯有神", "井井有条",
            "高高在上", "彬彬文质", "亭亭玉立", "扬扬自得", "奕奕神采", "倾国倾城", "国色天香", "沉鱼落雁", "闭月羞花", "秀色可餐", "丰容盛鬋（shi）", "体态轻盈", "丰韵娉婷", "丰姿冶丽", "珠围翠绕",
            "珠光宝气", "嫣然一笑", "秋波微转", "齿若编贝", "齿若瓠犀", "莺声燕语", "窈窕淑女", "才貌双全", "惠心纨质", "惠质兰心仪态万方", "千娇百媚", "凤表龙姿", "风流人物", "飞鸾翔凤", "风流潇洒",
            "龙驹凤雏", "龙潜凤采", "千里之足", "神采英拔", "飒爽英姿", "一表人材", "一表人物", "英姿勃勃", "英姿焕发", "英姿飒爽", "风流潇洒", "一表人材", "风度翩翩", "英姿飒爽", "气宇轩昂", "天庭饱满",
            "气宇轩昂", "玉树临风", "英俊潇洒", "风流倜傥", "貌似潘安", "仪表堂堂", "雅人深致", "温文尔雅", "雍容闲雅", "文质彬彬", "唇红齿白", "浓眉大眼", "明眸皓齿", "虎背熊腰", "朱颜鹤发", "大显神通",
            "大显身手", "大有作为", "大器晚成", "发愤图强", "奋发图强", "奋发蹈厉", "披荆斩棘", "闻鸡起舞", "碧血丹心", "不避斧钺", "成仁取义", "赤胆忠心", "赤心相待", "赤心报国", "大节不夺", "大法小廉",
            "蹈节死义", "断头将军", "肝脑涂地", "故旧不弃", "坚持不渝", "坚韧不拔", "坚定不移", "雷打不动", "木人石心", "破釜沉舟", "锲而不舍", "抱诚守真", "诚心诚意", "讲信修睦", "金石为开", "开心见诚",
            "悃愊无华", "披心相付", "披肝沥胆", "璞玉浑金", "拳拳服膺", "全心全意", "推襟送抱", "信及豚鱼", "信誓旦旦", "一寸丹心", "允执其中", "真心实意", "爱惜羽毛", "刚毅木讷", "斤斤自守", "谨言慎行",
            "慎终追远", "守口如瓶", "大公无私", "光明正大", "光明磊落", "浩然之气", "襟怀坦白", "明镜高悬", "高义薄云", "慷慨解囊", "乐善好施", "发愤忘食", "废寝忘食", "持之以恒", "坚持不懈", "胖乎乎",
            "粉嫩嫩", "白胖胖", "漂亮", "可爱", "聪明", "懂事", "乖巧", "淘气", "淘气", "顽劣", "调皮", "顽皮", "天真", "可爱", "无邪", "单纯", "纯洁", "无暇", "纯真", "稚气", "温润",
            "好奇", "天真无邪", "希望之星", "活泼可爱", "虎头虎脑", "伶牙俐齿", "冰雪聪明", "未来之星", "樱桃小嘴", "蹒跚学步", "咿呀学语", "聪慧过人", "天资聪颖", "纯洁无暇" };

    public static final String[] NICK_EXT = { "草民", "路人", "配角", "翠花", "小妞", "帅哥", "大叔", "大爷", "美人儿", "骚年", "童鞋", "小丸子", "一宿", "悟空", "史努比",
            "卡卡西", "小新", "麦兜", "公主", "王子", "超人", "蝙蝠侠", "花仙子", "警长", "男纸", "女纸", "孩纸", "神童", "凤凤", "屌丝", "小哟", "小哎", "忍者", "小盆友", "爷们儿",
            "大侠", "英雄", "剑客", "德鲁伊", "精灵", "矮人", "喵星人", "汪星人", "怪蜀黍", "宝贝", "勇士", "菜鸟", "萝莉", "正太", "御姐", "美眉", "加菲猫", "流氓兔", "柯南", "小新",
            "樱木花道", "卡卡西", "绯村剑心", "机器猫", "凌波丽", "小熊维尼", "寒羽良", "杀生丸", "樱桃小丸子", "沙加", "达也", "佐助", "米老鼠", "唐老鸭", "跳跳虎", "龙猫", "麦兜", "阿贵",
            "一休", "阿童木", "丁丁", "皮卡丘", "悟空", "阿拉蕾", "史努比", "皮诺曹", "兔八哥", "白雪公主", "鬼冢英吉", "桔梗", "珊璞", "小茜", "良牙", "卡卡西", "景麒", "小樱", "蜘蛛侠",
            "蝙蝠侠", "超人", "史瑞克", "SONIC", "HelloKitty", "赤木刚宪", "三井寿", "银次", "鸣人", "彩子", "安西教练", "流川枫", "樱木花道", "宫城良田", "藤真", "赵灵儿", "林月如",
            "阿奴", "李逍遥", "钢牙", "大力水手", "咸蛋超人", "小鹿斑比", "晴子", "擎天柱", "威震天", "补天士", "惊破天", "星矢", "紫龙", "冰河", "一辉", "瞬", "童虎", "穆先生", "撒加",
            "忍者神龟", "芭比", "高达", "孙悟空", "贝吉塔", "特兰克斯", "布欧", "孙悟天", "扬羽", "明日香", "雪代巴", "斑点狗", "乱马", "犬夜叉", "黑猫警长", "秦博士", "浦饭幽助", "PUCCA",
            "圣骑士", "法师", "术士", "武僧", "战士", "牧师", "德鲁伊", "盗贼", "萨满、猎人", "暗夜精灵", "狼人", "牛头人", "兽人", "暗夜精灵", "熊猫人", "小魔女", "琴子", "芽美", "妹妹公主",
            "小兔", "灰原哀", "凌波丽", "爆弹小新娘", "史迪仔", "史酷比", "史莱克", "史努比", "匹诺曹", "彼得潘", "艾丽丝", "跳跳虎", "妙妙龙", "小红帽", "兔八哥", "布鲁托", "灰姑娘", "睡美人",
            "泰迪熊", "憨八龟", "趴趴熊", "蓝皮鼠", "大脸猫", "加菲猫", "九色鹿", "蓝精灵", "雪孩儿", "葫芦娃", "小龙人", "阿笨猫", "威威猫", "小破孩", "喜羊羊", "懒羊羊", "沸羊羊", "美羊羊",
            "小美女", "大美人", "小帅哥", "小正太", "小萝莉", "黎叔", "高富帅", "白富美", "大叔", "小朋友", "小可爱", "小妹妹", "小弟弟", "大哥哥", "好爸爸", "好妈妈", "流川枫", "水冰月",
            "卡卡西", "加菲猫", "兔斯基", "仙道彰", "春野樱", "小红帽", "夜神月", "监狱兔", "流氓兔", "李小狼", "睡美人", "鱼美人", "蕉太郎", "小丸子", "鲁鲁修", "花太郎", "卡卡西", "小贝",
            "潮人", "黑丝控", "萌妹子", "大帝", "矮穷挫", "超级玛丽", "路易", "健次郎", "主任", "经理", "钢铁侠", "绿巨人", "雷神", "红蜘蛛", "鹰眼", "美国队长", "绿箭侠", "超胆侠", "猫女",
            "郭靖", "杨康", "苗人凤", "王语嫣", "乔峰", "张三丰", "张无忌", "杨过", "小龙女", "令狐冲", "任盈盈", "丐帮帮主", "周星星", "发哥", "大爷", "小弟", "保镖", "男孩", "女孩",
            "男人", "女人", "淑女", "汉子", "金链汉子", "乞丐", "小卒", "护卫", "学士", "统领", "大臣", "宰相", "平民", "公民", "佣兵", "战士", "圣骑士", "游民", "审判者", "终结者",
            "征服者" };

    public static final String NICK_MIDDLE = "的";
    // 1、验证用户是否存在接口返回
    public static final String ORACLE_INTERFACE_RESULT_CODE_NO_SUCH_USER = "100";
    public static final String ORACLE_INTERFACE_RESULT_CODE_COMMON_USER_EXIST = "101";
    public static final String ORACLE_INTERFACE_RESULT_CODE_REGISTER_USER_EXIST = "102";
    public static final String ORACLE_INTERFACE_RESULT_CODE_PARAM_ERROR = "103";

    // 默认头像路径
    public static final String DEFAULT_HEAD_IMG_PATH = "/data/resource/image/head/";

    // 系统参数
    public static final String SYS_PARAM__VEST_GAME = "vest_game";
    public static final String SYS_PARAM_VEST_USER = "vest_user";

    // 精彩动态参数
    // 当玩家成绩达到当日游戏排行榜前五：
    public static final String SNS_MSG_RANK_TODAY = "恭喜玩家“<font color='#429fff'>%1$s</font>”闯入“<font color='#fff642e'>%2$s</font>”本日排行<font color='#fff642e'>TOP%3$d</font>，你也快来挑战吧！";
    public static final int[] SNS_MSG_RANK_TODY_NUM = { 5 };
    public static final int[] MSG_RANK_TODY_NUM = { 3 };
    // 当玩家成绩达到全站排行榜前十
    public static final String SNS_MSG_RANK_TOTAL = "玩家“<font color='#429fff'>%1$s</font>”披荆斩棘终于问鼎“<font color='#fff642e'>%2$s</font>”全站第<font color='#fff642e'>%3$d</font>，谁与争锋！";
    public static final int[] SNS_MSG_RANK_TOTAL_NUM = { 10 };
    // 当玩家第x个登陆游戏（后台配置）
    public static final String SNS_MSG_RANK_LOGIN = "“<font color='#429fff'>%1$s</font>”是（今天）第<font color='#fff642e'>%2$d</font>个登陆游戏的玩家，一起祝贺他吧！";
    public static final int[] SNS_MSG_RANK_LOGIN_NUM = { 1 };
    // 当玩家成绩超过当日最好成绩时
    public static final String SNS_MSG_SCORE_MAX = "“<font color='#429fff'>%1$s</font>”在游戏“<font color='#fff642e'>%2$s</font>”中得到<font color='#fff642e'>%3$d</font>%4$s刷新了今日个人的最好成绩";
    // 所有充值信息
    public static final String SNS_MSG_RANK_CHARGE = "玩家“<font color='#429fff'>%1$s</font>”刚刚充值了<font color='#fff642e'>%2$d</font>爱豆";

    // 7版社区,后台用于判断channel_page_type对应的类型
    public static final int PAGE_SUB_CHANNEL = 1;
    public static final int PAGE_CHANNEL_APP = 2;
    public static final int PAGE_TOPIC_APP = 3;
    public static final int PAGE_NEED_PLAY_APP = 4;
    public static final int MORE_APP = 5;
    public static final int GUESS_LIKE_APP = 6;
    public static final int PAGE_SORT_CHANNEL = 7;
    public static final int PAGE_GAME_PKG_CHANNEL = 8;
    public static final int PAGE_WEB_GAME_APP = 9;
    public static final int PAGE_NETWORK_GAME_APP = 10;
    public static final int PAGE_NEW_TOPIC_APP = 11;
    public static final int PAGE_CHOICENESS_CHANNEL = 12;
    public static final int PAGE_CHOICENESS_CHANNEL_RANDOM = 13;
    public static final int PAGE_CHANNEL_TAG_APP = 14;
    public static final int PAGE_GAME_COLLECTION = 15;
    public static final int PAGE_GAME_CATEGORY=16;

    public static final int NEED_PLAY_CHANNEL_ID = 515;// 拉取的必玩应用对应彩蛋的频道id
    public static final String COMMENT_SENSITIVE_WORD = "您发表的内容中包含敏感字";
    public static final String ONE_MINUTE_LIMIT = "亲，您发表评论过于频繁，请稍后再试";
    // 游戏包id
    public static final int GAME_PACKAGE_ID1 = 320;
    public static final int GAME_PACKAGE_ID2 = 321;
    public static final int GAME_PACKAGE_ID3 = 322;
    public static final int GAME_PACKAGE_ID4 = 239444;
    public static final int GAME_PACKAGE_ID5 = 239448;

    // 4G专版游戏包
    public static final int GAME_PACKAGE_ID_4G = -300000;
    public static final int APP_KEY_4G = 8888015;
    
    public static final int APP_KEY_7 = 8888007;

    // 游戏上显示的五种标签对应的channelId
    // public static final Integer ACTIVE_MARK_TAG_ID = 1110;
    // public static final Integer NEW_STATUS_TAG_ID = 1111;
    // public static final Integer HOT_STATUS_TAG_ID = 1112;
    // public static final Integer FIRST_STATUS_TAG_ID = 1113;
    // public static final Integer CLASSIC_STATUS_TAG_ID = 1114;
    // 取五种标签对应的应用时默认取的条数
    public static final Integer TAG_PAGE_NUM = 100;
    // 分类下默认排序对应的id
    public static final Integer DEFAULT_ORDER_ID = 1105;

    // 7版大厅行为日志前缀
    public static final String LOG_PRE = "700";

    // 专题默认parentId
    public static final int TOPIC_PARENT_ID = 704;

    // 评论时间使用到的字符串
    public static final String COMMENT_LESS_THAN_10_MINS = "刚刚";
    public static final String COMMENT_LESS_THAN_1_HOUR = "分钟前";
    public static final String COMMENT_LESS_THAN_24_MINS = "小时前";
    public static final String COMMENT_LESS_THAN_30_DAYS = "天前";
    public static final String COMMENT_MORE_THAN_30_DAYS = "个月前";

    // 新专题对应的客户端的versionCode
    public static final Integer VERSION_CODE_FOR_NEW_TOPIC = 730;
    // versionName为7.3.2对应的versionCode
    public static final Integer VERSION_CODE_FOR_7_3_2 = 732;

    // 小编推荐在t_app_parameter中对应的id
    public static final Integer NEW_TOPIC_ID = 712;

    // 精选栏目根节点
    public static final Integer CHOICENESS_CHANNEL_ROOT_ID = 714;

    // 隐藏栏目根节点
    public static final Integer HIDDEN_CHANNEL_ROOT_ID = 715;

    // 精选栏目随便看看根节点
    public static final Integer CHOICENESS_RANDOM_CHANNEL_ROOT_ID = 716;

    /************************* 7版社区计费类型 start **************************/
    public static final String CHARGE = "收费";
    public static final String FREE_TRIAL = "免费试玩";
    public static final String PROPS_CHARGES = "道具收费";
    public static final String BAG_FREE = "包内免费";
    public static final String FREE = "免费";

    /************************* 7版社区计费类型 end **************************/

    /*************************** 游戏包订购和退订url 恩德 ***********************************/
    
    
    /************************* 短连接 **************************/
	public static String REGEX_SHORT_HOST = "(^[a-z]{1,}+\\.play\\.cn|play\\.cn)";

    /************* static 静态块放在类最后 ************/
    static {
        Properties properties = getPrpperties();

        /* download_url 获取 */
        DOWNLOAD_URL = getPropertiesValueByKey(properties, "download_url", DOWNLOAD_URL);
        System.out.println("download_url========" + DOWNLOAD_URL);

        /* download_url_open 获取 */
        DOWNLOAD_URL_OPEN = getPropertiesValueByKey(properties, "download_url_open", DOWNLOAD_URL_OPEN);
        System.out.println("download_url_open========" + DOWNLOAD_URL_OPEN);
        
        /* http_host 获取 */
        HTTP_HOST = getPropertiesValueByKey(properties, "http_host", HTTP_HOST);
        System.out.println("HTTP_HOST========" + HTTP_HOST);

        /* https_host 获取 */
        HTTPS_HOST = getPropertiesValueByKey(properties, "https_host", HTTPS_HOST);
        System.out.println("HTTPS_HOST========" + HTTPS_HOST);
        /* upload_address 获取 */
        UPLOAD_ADDRESS = getPropertiesValueByKey(properties, "upload_address", UPLOAD_ADDRESS);
        System.out.println("upload_address========" + UPLOAD_ADDRESS);

        /* upload_address 获取 */
        UPLOAD_WRITE_ADDRESS = getPropertiesValueByKey(properties, "upload_write_address", UPLOAD_WRITE_ADDRESS);
        System.out.println("UPLOAD_WRITE_ADDRESS========" + UPLOAD_WRITE_ADDRESS);

        /* 获取 MASTER_READ_PATH */
        MASTER_READ_PATH = getPropertiesValueByKey(properties, "master_read_path", MASTER_READ_PATH);
        System.out.println("MASTER_READ_PATH========" + MASTER_READ_PATH);

        /* 获取 MASTER_WRITE_PATH */
        MASTER_WRITE_PATH = getPropertiesValueByKey(properties, "master_write_path", MASTER_WRITE_PATH);
        System.out.println("MASTER_WRITE_PATH========" + MASTER_WRITE_PATH);

        /* 获取 BACKUP_READ_PATH */
        BACKUP_READ_PATH = getPropertiesValueByKey(properties, "backup_read_path", BACKUP_READ_PATH);
        System.out.println("BACKUP_READ_PATH========" + BACKUP_READ_PATH);

        /* 获取 BACKUP_WRITE_PATH */
        BACKUP_WRITE_PATH = getPropertiesValueByKey(properties, "backup_write_path", BACKUP_WRITE_PATH);
        System.out.println("BACKUP_WRITE_PATH========" + BACKUP_WRITE_PATH);

        /* 获取 COMMENT_TOTAL_SWITCH */
        COMMENT_TOTAL_SWITCH = getPropertiesValueByKey(properties, "comment_total_switch", COMMENT_TOTAL_SWITCH);
        System.out.println("COMMENT_TOTAL_SWITCH========" + COMMENT_TOTAL_SWITCH);

        /* 获取 COMMENT_AUDIT_SWITCH */
        COMMENT_AUDIT_SWITCH = getPropertiesValueByKey(properties, "comment_audit_switch", COMMENT_AUDIT_SWITCH);
        System.out.println("COMMENT_AUDIT_SWITCH========" + COMMENT_AUDIT_SWITCH);

        /* 获取 COMMENT_REPLY_AUDIT_SWITCH */
        COMMENT_REPLY_AUDIT_SWITCH = getPropertiesValueByKey(properties, "comment_reply_audit_switch", COMMENT_REPLY_AUDIT_SWITCH);
        System.out.println("COMMENT_REPLY_AUDIT_SWITCH========" + COMMENT_REPLY_AUDIT_SWITCH);

        /* http_host_4g 获取 */
        HTTP_HOST_4G = getPropertiesValueByKey(properties, "http_host_4g", HTTP_HOST_4G);
        System.out.println("http_host_4g========" + HTTP_HOST_4G);

        /* https_host_4g 获取 */
        HTTPS_HOST_4G = getPropertiesValueByKey(properties, "https_host_4g", HTTPS_HOST_4G);
        System.out.println("https_host_4g========" + HTTPS_HOST_4G);

        /* download_url_4g 获取 */
        DOWNLOAD_URL_4G = getPropertiesValueByKey(properties, "download_url_4g", DOWNLOAD_URL_4G);
        System.out.println("download_url_4g========" + DOWNLOAD_URL_4G);

        /* imsi_4g_reg 获取 */
        IMSI_4G_REG = getPropertiesValueByKey(properties, "imsi_4g_reg", IMSI_4G_REG);
        System.out.println("imsi_4g_reg========" + IMSI_4G_REG);

        INNER_DOWNLOAD_URL = getPropertiesValueByKey(properties, "inner_download_url", INNER_DOWNLOAD_URL);
        System.out.println("inner_download_url========" + INNER_DOWNLOAD_URL);

        ORACLE_HOST = getPropertiesValueByKey(properties, "oracle_host", ORACLE_HOST);
        System.out.println("oracle_host========" + ORACLE_HOST);

        OTA_DOWNLOAD_URL = getPropertiesValueByKey(properties, "ota_download_url", OTA_DOWNLOAD_URL);
        System.out.println("ota_download_url========" + OTA_DOWNLOAD_URL);

        ORACLE_INTERFACE_LOGIN_USERNAME = ORACLE_HOST
                + getPropertiesValueByKey(properties, "oracle_interface_login_username", ORACLE_INTERFACE_LOGIN_USERNAME);
        System.out.println("oracle_interface_login_username========" + ORACLE_INTERFACE_LOGIN_USERNAME);

        ORACLE_INTERFACE_LOGIN_IMSI = ORACLE_HOST
                + getPropertiesValueByKey(properties, "oracle_interface_login_imsi", ORACLE_INTERFACE_LOGIN_IMSI);
        System.out.println("oracle_interface_login_imsi========" + ORACLE_INTERFACE_LOGIN_IMSI);

        ORACLE_INTERFACE_CHECK_ACCOUNT = ORACLE_HOST
                + getPropertiesValueByKey(properties, "oracle_interface_check_account", ORACLE_INTERFACE_CHECK_ACCOUNT);
        System.out.println("oracle_interface_check_account========" + ORACLE_INTERFACE_CHECK_ACCOUNT);

        ORACLE_HOST = getPropertiesValueByKey(properties, "oracle_host", ORACLE_HOST);
        System.out.println("oracle_host========" + ORACLE_HOST);

        SNS_COMMERCIAL_SERVER_URL = getPropertiesValueByKey(properties, "sns_commercial_server_url", SNS_COMMERCIAL_SERVER_URL);
        System.out.println("sns_commercial_server_url========" + SNS_COMMERCIAL_SERVER_URL);

        SNS_TEST_SERVER_URL = getPropertiesValueByKey(properties, "sns_test_server_url", SNS_TEST_SERVER_URL);
        System.out.println("sns_test_server_url========" + SNS_TEST_SERVER_URL);
        
        REGEX_SHORT_HOST = getPropertiesValueByKey(properties, "regex_short_host", REGEX_SHORT_HOST);
        System.out.println("regex_host========" + REGEX_SHORT_HOST);
        
        COIN_EVENT_FLAG = Utils.toInt(properties.get("event_flag"), 1);
        REG_DIANXIN = getPropertiesValueByKey(properties, "reg_dianxin", REG_DIANXIN);
        REG_LIANTONG = getPropertiesValueByKey(properties, "reg_liantong", REG_LIANTONG);
        REG_YIDONG = getPropertiesValueByKey(properties, "reg_yidong", REG_YIDONG);
        GAME_LIKE_FLAG = Utils.toInt(properties.get("game_like_flag"), 1);
        BBS_URL = getPropertiesValueByKey(properties, "bbs_url", BBS_URL);
        System.out.println("bbs_url========" + BBS_URL);
        
        UNSTALL_ANSWER_URL = getPropertiesValueByKey(properties, "unstall_answer_url", UNSTALL_ANSWER_URL);
        
        System.out.println("bbs_url========" + BBS_URL);
        System.out.println("unstall_answer_url========" + UNSTALL_ANSWER_URL);

    }

    /************************ 用户体系相关常量 start *********************************************/
    // 用户默认性别 2 表示女
    public static final int USER_DEFAULT_SEX = 2;
    // 用户账号状态 1 表示冻结
    public static final int USER_ACCOUNT_STATUS_FROZEN = 1;
    // 用户账号状态 0表示正常
    public static final int USER_ACCOUNT_STATUS_NORMAL = 0;

    // 用户账号绑定状态 1表示userName绑定
    public static final int USER_ACCOUNT_VALID_USERNAME = 1;
    // 用户账号绑定状态 2表示手机绑定
    public static final int USER_ACCOUNT_VALID_PHONE = 2;
    // 用户账号绑定状态 4表示邮箱绑定
    public static final int USER_ACCOUNT_VALID_EMAIL = 4;

    // imsi 反查手机号次数限制
    public static final int USER_IMSI_REVERSE_SEARCH_LIMITS = 5;

    // 用户登录方式
    // 用户登录方式 手机号登录
    public static final int USER_LOGIN_TYPE_PHONE = 1;
    // 用户登录方式 邮箱登录
    public static final int USER_LOGIN_TYPE_EMAIL = 2;
    // 用户登录方式 egame账号登录
    public static final int USER_LOGIN_TYPE_EGAME = 3;
    // 用户登录方式 用户名主动登录
    public static final int USER_LOGIN_TYPE_USERNAME = 4;
    // 用户登录方式 udb账号登录
    public static final int USER_LOGIN_TYPE_UDB = 5;
    // 用户登录方式 imsi卡号自动登录
    public static final int USER_LOGIN_TYPE_IMSI = 6;
    // 用户登录方式 mac地址自动登录
    public static final int USER_LOGIN_TYPE_MAC = 7;
    // 用户登录方式 wap段手机号自动登录
    public static final int USER_LOGIN_TYPE_WAP = 8;

    // 用户注册账号类型
    // 用户注册账号类型 手机号注册
    public static final int USER_REGISTER_TYPE_PHONE = 1;
    // 用户注册账号类型 邮箱注册
    public static final int USER_REGISTER_TYPE_EMAIL = 2;
    // 用户注册账号类型 自定义额game账号注册
    public static final int USER_REGISTER_TYPE_EGAME = 3;
    // 一般用户oauth accestoken 过期时间 单位 秒
    public static final int USER_COMMON_OAUTH_ACCESSTOKEN_EFFECTIVE_TIME = 60 * 24 * 3600;
    // 注册用户oauth accestoken 过期时间 单位 秒
    public static final int USER_REGIST_OAUTH_ACCESSTOKEN_EFFECTIVE_TIME = 60 * 24 * 3600;
    // 用户oauth accestoken 过期时间 单位 秒
 	public static final int USER_OAUTH_ACCESSTOKEN_EFFECTIVE_TIME = 60 * 24 * 3600;
    // 一般用户oauth accestoken 访问权限范围
    public static final String USER_OAUTH_SCOPE_COMMON = "common";
    // 注册用户oauth accestoken 访问权限范围
    public static final String USER_OAUTH_SCOPE_ALL = "all";
    
    public static String REG_EMIAL = "reg_emial";
    public static String REG_PASSWORD = "reg_password";
    public static String REG_IMSI = "reg_imsi";
	public static String REG_IMSI_TELCOM = "reg_imsi_telcom";
	public static String REG_MAC = "reg_mac";

    /************************ 用户体系相关常量 end *********************************************/

    // 延迟升级3个月
    public static final int THREE_MONTH = -3;;

    /**
     * @param properties
     *            ,key,defaultResult
     */
    private static String getPropertiesValueByKey(Properties properties, String key, String defaultResult) {
        /* download_url 获取 */
        String value = properties.getProperty(key);
        if (value != null && !"".equals(value.trim())) {
            return value;
        }
        return defaultResult;
    }

    private static Properties getPrpperties() {
        Properties properties = new Properties();
        try {
            properties = Utils.getProperties("common.properties");
        } catch (Exception e) {
            System.out.println("common.properties not find");
        }
        return properties;
    }
}
