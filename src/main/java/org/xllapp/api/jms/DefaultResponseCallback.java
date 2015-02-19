package org.xllapp.api.jms;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 返回配置的字符串作为响应.
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Aug 22, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class DefaultResponseCallback implements ResponseCallback {

	private final static Logger logger = LoggerFactory.getLogger(DefaultResponseCallback.class);

	private String successContent;

	private String failContent;

	@Override
	public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
		out(response, this.successContent);
	}

	@Override
	public void onFail(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		out(response, this.failContent);
	}

	protected void out(HttpServletResponse response, String content) {
		logger.debug("response:{}",content);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(content);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public String getSuccessContent() {
		return this.successContent;
	}

	public void setSuccessContent(String successContent) {
		this.successContent = successContent;
	}

	public String getFailContent() {
		return this.failContent;
	}

	public void setFailContent(String failContent) {
		this.failContent = failContent;
	}

}
