package org.xllapp.api.jms;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class JMSExterController implements HttpRequestHandler,ApplicationContextAware{

	private final static Logger logger = LoggerFactory.getLogger(JMSExterController.class);
	
	//获取spring的上下文内容
	private ApplicationContext applicationContext;


	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = new Date().getTime();
		logger.debug("in handleRequest,and startTime is " + startTime);
		
		String serviceType = request.getParameter("serviceType");
		JMSController controller=(JMSController)this.applicationContext.getBean("/service/icity/log/st"+serviceType);
		controller.handleRequest(request, response);
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
          this.applicationContext = applicationContext;
	}


}
