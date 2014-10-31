package com.cloud.open.entity.exception;

public class ErrorCode extends ErrorCodeBase {

    // public static final int UnDefinedServerError = -1;
    //
    // public static final int UnDefinedClientError = -2;
    // public static final int UnDefinedOtherError = -3;
    // public static final int UnDefinedDatabaseError = -4;
    //
    //
    // public static final int ParameterError = -5;
    // public static final int NotImplementCode = -6;
    // public static final int UnDefinedMongoError = -7;
    // public static final int SysOutOfMemoryError = -8;
    // public static final int SysConfigError = -9;
    // public static final int ExceptionMobile = -10;
    // public static final int ExceptionWeb = -11;
    // public static final int ExceptionText = -12;
    // public static final int ExceptionIgnoreError = -13;
     public static final int DatabaseDuplicateKey = -101;
     public static final int DATABASE_EMPTY_ENTITY = -102;
    //
    // public static final int DatabaseLengthLimit = -102;
    // public static final int DatabaseIntegrityConstraintViolation = -103;

    // charge e exception from -151 -111

    // user exception from -299 to -201
    public static final int UserIsNotLogin = -201;
    public static final int UserAccountError = -211;
    public static final int UserPasswordError = -219;

    /** 用户体系相关错误码定义 start */

    /** 用户egame账号注册时，egame账号已经注册 */
    public static final int USER_ACCOUNT_EGAME_REGISTERED = -201;

    // game exception from -499 to -301
    public static final int NotFindTerminalGroup = -499;

    /** 用户手机号注册时，手机号已经注册为注册用户 */
    public static final int USER_ACCOUNT_MOBILEPHONE_REGISTERED = -202;

    /** 用户邮箱注册时，邮箱已经注册为注册用户 */
    public static final int USER_ACCOUNT_EMAIL_REGISTERED = -203;
    
    /** 用户邮箱注册时，不主持的邮箱格式 */
	public static final int USER_ACCOUNT_EMAIL_REG_FORMAT_NOT_SUPPORT = -205;
	
	/** 用户注册时，不主持的密码格式 */
	public static final int USER_ACCOUNT_PWD_REG_FROMAT_NOT_SUPPORT = -206;
    

    /** 用户账号不存在 */
    public static final int USER_ACCOUNT_NOT_EXIST = -211;

    /** 用户账号被冻结 */
    public static final int USER_ACCOUNT_STATUS_FROZEN = -212;

    /** 用户账号未激活 */
    public static final int USER_ACCOUNT_NOT_ACTIVATED = -213;

    /** 用户手机号未绑定 */
    public static final int USER_ACCOUNT_PHONE_NOT_BOUND = -214;

    /** 用户邮箱未绑定 */
    public static final int USER_ACCOUNT_EMAIL_NOT_BOUND = -215;

    /** 用户手机号绑定时，手机已经被绑定或已经被其他用户使用 */
    public static final int USER_ACCOUNT_PHONE_BOUND_OR_REGISTERED = -216;

    /** 用户邮箱绑定时，邮箱已经被绑定或已经被其他用户注册 */
    public static final int USER_ACCOUNT_EMAIL_BOUND_OR_REGISTERED = -217;

    /** 用户绑定操做时，无用户绑定信息或信息已经过期 */
    public static final int USER_ACCOUNT_BOUNDTEMP_NOT_EXIST = -218;

    /** 用户账号密码错误 */
    public static final int USER_ACCOUNT_PASSWORD_MISMATCH = -219;

    /** 用户账号原始密码不匹配 */
    public static final int USER_ACCOUNT_OLD_PASSWORD_MISMATCH = -220;

    /** 验证码错误 */
    public static final int USER_VALID_VALIDCODE_INVALID = -230;

    /** oauth 访问令牌accessToken过期 */
    public static final int USER_OAUTH_ACCESSTOKEN_OVERDUE = -260;

    /** oauth 访问令牌accessToken无效 */
    public static final int USER_OAUTH_ACCESSTOKEN_INVALID = -261;

    /** oauth 访问令牌accessToken失效 */
    public static final int USER_OAUTH_ACCESSTOKEN_EFFECTIVENESS = -262;
    /** 用户体系相关错误码定义 end */

    // game exception from -499 to -301
    /** 7版大厅相关错误信息start */

    // 游戏信息不存在
    public static final int GAME_INFO_NOT_EXISTS = -301;
    // 用户未登陆
    public static final int USER_NOT_LOGIN = -302;
    // 父评论信息不存在
    public static final int PARENT_COMMENT_INFO_NOT_EXISTS = -303;
    // 被评论人id不存在
    public static final int BE_REPLIED_ID_NOT_EXISTS = -304;

    /**
     * 7.4.6网游相关错误
     */
    // token失效领号
    public static final int GIFT_OBTAIN_TOKEN_INVALID_CODE = -11;
    public static final String GIFT_OBTAIN_TOKEN_INVALID_INFO = "领号失败";
    // token失效淘号
    public static final int GIFT_PICK_TOKEN_INVALID_CODE = -17;
    public static final String GIFT_PICK_TOKEN_INVALID_INFO = "淘号失败";
    // 请求频繁
    public static final int GIFT_FREQUENT_QUERY_CODE = -12;
    public static final String GIFT_FREQUENT_QUERY_INFO = "1分钟后才能再继续淘号哦";
    // 超过领号截止日期
    public static final int GIFT_OBTAIN_OVER_DEADLINE_CODE = -13;
    public static final String GIFT_OBTAIN_OVER_DEADLINE_INFO = "领号失败，下次早点来吧";
    // 号领完
    public static final int GIFT_EMPTY_CODE = -14;
    public static final String GIFT_EMPTY_INFO = "号领完了，试试淘号吧";
    // 已领号
    public static final int GIFT_HAS_GET_CODE = -15;
    public static final String GIFT_HAS_GET_INFO = "已经领过号了";
    // 超过淘号截止日期
    public static final int GIFT_PICK_OVER_DEADLINE_CODE = -16;
    public static final String GIFT_PICK_OVER_DEADLINE_INFO = "淘号失败，下次早点来吧";

    /** 7版大厅相关错误信息end */

}
