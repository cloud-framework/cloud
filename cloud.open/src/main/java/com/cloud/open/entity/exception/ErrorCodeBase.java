package com.cloud.open.entity.exception;

import java.lang.reflect.Field;
import java.util.TreeMap;

public class ErrorCodeBase {

	public ErrorCodeBase() {
	}

	public static String getFieldName(int errorCode) {
		return (String) _map.get(Integer.valueOf(errorCode));
	}

	public static String getLangString(int code) {
		return (new StringBuilder()).append("cn.egame.common.ErrorCode")
				.append(String.valueOf(code)).toString();
	}

	public static final int UnDefinedServerError = -1;
	public static final int UnDefinedClientError = -2;
	public static final int UnDefinedOtherError = -3;
	public static final int UnDefinedDatabaseError = -4;
	public static final int ParameterError = -5;
	public static final int NotImplementCode = -6;
	public static final int UnDefinedMongoError = -7;
	public static final int SysOutOfMemoryError = -8;
	public static final int SysConfigError = -9;
	public static final int ExceptionMobile = -10;
	public static final int ExceptionWeb = -11;
	public static final int ExceptionText = -12;
	public static final int ExceptionIgnoreError = -13;
	public static final int ResultNotExsits = -14;
	public static final int DatabaseDuplicateKey = -101;
	public static final int DatabaseLengthLimit = -102;
	public static final int DatabaseIntegrityConstraintViolation = -103;
	public static final int EGAMECacheError = -150;
	public static final int EFSMkdirError = -502;
	public static final int EFSUploadError = -503;

	protected static TreeMap<Integer, String> _map;
	static {
		ErrorCodeBase ec = new ErrorCodeBase();
		_map = new TreeMap<Integer, String>();
		for (Field field : ErrorCodeBase.class.getFields()) {
			try {
				_map.put(field.getInt(ec), field.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from:
 * E:\git_github\cloud-framework\cloud\cloud.open\WebRoot\WEB-INF
 * \lib\egame.common.jar Total time: 101 ms Jad reported messages/errors: Exit
 * status: 0 Caught exceptions:
 */