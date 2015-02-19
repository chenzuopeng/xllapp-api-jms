package org.xllapp.api.jms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;

/**
 * 
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Aug 21, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class JMSController implements HttpRequestHandler {

	private final static Logger logger = LoggerFactory.getLogger(JMSController.class);

	private RequestParamResolver requestParamResolver = new DefaultRequestParamResolver();

	private ResponseCallback responseCallback;

	private JMSProducer jmsProducer;

	private String dest;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Object requestParam = this.requestParamResolver.resolve(request);
			this.jmsProducer.send(this.dest, requestParam);
			this.responseCallback.onSuccess(request, response);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			this.responseCallback.onFail(request, response, e);
		}
	}

	public String getDest() {
		return this.dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public ResponseCallback getResponseCallback() {
		return this.responseCallback;
	}

	public void setResponseCallback(ResponseCallback responseCallback) {
		this.responseCallback = responseCallback;
	}

	public JMSProducer getJmsProducer() {
		return this.jmsProducer;
	}

	public void setJmsProducer(JMSProducer jmsProducer) {
		this.jmsProducer = jmsProducer;
	}

	public RequestParamResolver getRequestParamResolver() {
		return this.requestParamResolver;
	}

	public void setRequestParamResolver(RequestParamResolver requestParamResolver) {
		this.requestParamResolver = requestParamResolver;
	}

}
