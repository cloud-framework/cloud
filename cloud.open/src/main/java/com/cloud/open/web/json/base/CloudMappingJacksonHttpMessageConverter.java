package com.cloud.open.web.json.base;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import cn.egame.common.exception.ErrorCodeBase;
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.valueobject.vo.ReturnResult;

public class CloudMappingJacksonHttpMessageConverter extends MappingJacksonHttpMessageConverter{
	private static Logger logger = Logger.getLogger(CloudMappingJacksonHttpMessageConverter.class);
	
	private ReturnResult returnResult;

	public ReturnResult getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(ReturnResult returnResult) {
		this.returnResult = returnResult;
	}
	
	
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		try {
			returnResult = new ReturnResult();
			returnResult.setExt(object);
			super.writeInternal(returnResult, outputMessage);
		} catch (Exception e) {
			logger.error("", e);
			returnResult.setCode(ExceptionCommonBase.parseErrorCode(e,
					ErrorCodeBase.UnDefinedDatabaseError));
			returnResult.setText(e.getMessage());
			super.writeInternal(returnResult, outputMessage);
		}
	}
	
}
