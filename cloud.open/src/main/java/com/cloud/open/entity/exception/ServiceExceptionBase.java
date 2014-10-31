package com.cloud.open.entity.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import cn.egame.common.exception.IExceptionCommon;
import cn.egame.common.util.Utils;

public class ServiceExceptionBase extends Exception implements IExceptionCommon {

	public static String errorCodeMessage(int errorCode) {
		return errorCodeMessage(errorCode, null);
	}

	public static String errorCodeMessage(int errorCode, String message) {
		return (new StringBuilder()).append("ErrorCode:").append(errorCode)
				.append(" / Message:").append(message).toString();
	}

	public static boolean isDatabaseIntegrityConstraintViolation(Throwable ex) {
		boolean is = ex.toString().indexOf(
				"MySQLIntegrityConstraintViolationException") > 0;
		return is;
	}

	public static boolean isDuplicateKeyException(Throwable ex) {
		boolean is = ex.toString().indexOf(
				"MySQLIntegrityConstraintViolationException: Duplicate entry") > 0;
		return is;
	}

	public static boolean isOutOfMemory(Throwable ex) {
		boolean is = ex.toString().indexOf(
				"java.lang.OutOfMemoryError: Java heap space") > 0;
		return is;
	}

	public static void logStack() {
		logStack("stack");
	}

	public static void logStack(String message) {
		Utils.printStack(message);
	}

	public static int parseErrorCode(String reason, int defaultValue) {
		Matcher matcher = pattern.matcher(reason);
		if (matcher.find()) {
			String errorCode = matcher.group(1);
			return Integer.parseInt(errorCode);
		} else {
			return defaultValue;
		}
	}

	public static int parseErrorCode(Throwable ex, int defaultValue) {
		if (ex != null) {
			if (ex instanceof RemoteException)
				return parseErrorCode(ex.getMessage(), defaultValue);
			if (ex instanceof ServiceExceptionBase)
				return ((ServiceExceptionBase) ex).getErrorCode();
			if (ex instanceof IExceptionCommon)
				return ((IExceptionCommon) ex).getErrorCode();
			Throwable cause = ex.getCause();
			if (cause instanceof ServiceExceptionBase)
				return ((ServiceExceptionBase) cause).getErrorCode();
			if (cause instanceof IExceptionCommon)
				return ((IExceptionCommon) cause).getErrorCode();
			else
				return parseErrorCode(ex.toString(), defaultValue);
		} else {
			return defaultValue;
		}
	}

	public static void releaseMemory() {
		try {
			System.gc();
		} catch (Throwable e) {
			logger.error(e);
		}
	}

	private static void resetErrorCode(ServiceExceptionBase e) {
		if (e != null) {
			int errorCode;
			if (e.cause != null) {
				errorCode = parseErrorCode(e.cause, 0);
				if (errorCode < 0) {
					e.setErrorCode(errorCode);
					return;
				}
			}
			errorCode = parseErrorCode(e.getMessage(), 0);
			if (errorCode < 0)
				e.setErrorCode(errorCode);
		}
	}

	public static void resetErrorCode(Throwable e) {
		if (e != null && (e instanceof IExceptionCommon)) {
			int errorCode = parseErrorCode(e, 0);
			if (errorCode < 0)
				((IExceptionCommon) e).setErrorCode(errorCode);
		}
	}

	public static ServiceExceptionBase throwServiceExceptionBase(Throwable ex)
			throws ServiceExceptionBase {
		logger.error("ServiceExceptionBase", ex);
		if (ex instanceof ServiceExceptionBase)
			return (ServiceExceptionBase) ex;
		if (ex instanceof IExceptionCommon) {
			IExceptionCommon e = (IExceptionCommon) ex;
			return new ServiceExceptionBase(e.getErrorCode(), ex);
		}
		if (ex instanceof SQLException) {
			if (isDuplicateKeyException(ex))
				return new ServiceExceptionBase(-101, ex.getMessage(), ex);
			if (isDatabaseIntegrityConstraintViolation(ex)) {
				return new ServiceExceptionBase(-103, ex.getMessage(), ex);
			} else {
				int code = parseErrorCode(ex, -4);
				return new ServiceExceptionBase(code, ex.getMessage(), ex);
			}
		}
		if (ex instanceof OutOfMemoryError) {
			releaseMemory();
			return new ServiceExceptionBase(-8, ex.getMessage(), ex);
		}
		if (isDuplicateKeyException(ex))
			return new ServiceExceptionBase(-101, ex.getMessage(), ex);
		if (isDatabaseIntegrityConstraintViolation(ex))
			return new ServiceExceptionBase(-103, ex.getMessage(), ex);
		if (isOutOfMemory(ex)) {
			releaseMemory();
			return new ServiceExceptionBase(-8, ex.getMessage(), ex);
		} else {
			return new ServiceExceptionBase(-1, ex.getMessage(), ex);
		}
	}

	public static SQLException throwSQLException(Throwable ex) {
		if (ex instanceof SQLException)
			return (SQLException) ex;
		else
			return new SQLException(ex);
	}

	public ServiceExceptionBase(int errorCode) {
		this(errorCode, null, null);
	}

	public ServiceExceptionBase(int errorCode, String message) {
		this(errorCode, message, null);
	}

	public ServiceExceptionBase(int errorCode, String message, Throwable cause) {
		this.errorCode = -1;
		this.cause = null;
		this.message = null;
		this.errorCode = errorCode;
		this.message = message;
		this.cause = cause;
	}

	public ServiceExceptionBase(int errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		if (cause != null && Utils.stringIsNullOrEmpty(message))
			message = cause.getMessage();
		if (-12 == getErrorCode())
			return message;
		else
			return (new StringBuilder()).append("ErrorCode:")
					.append(getErrorCode()).append(" / Message:")
					.append(message).toString();
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (cause != null)
			cause.printStackTrace();
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (cause != null)
			cause.printStackTrace(ps);
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (cause != null)
			cause.printStackTrace(pw);
	}

	public void resetErrorCode() {
		resetErrorCode(this);
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = -4594875950045103211L;
	private static final Pattern pattern = Pattern
			.compile("ErrorCode:(-[0-9]*) / Message:");
	private static Logger logger = Logger.getLogger(ServiceExceptionBase.class);
	protected int errorCode;
	private Throwable cause;
	private String message;

}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from:
 * E:\git_github\cloud-framework\cloud\cloud.open\WebRoot\WEB-INF
 * \lib\egame.common.jar Total time: 133 ms Jad reported messages/errors: Exit
 * status: 0 Caught exceptions:
 */