package org.xllapp.api.jms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现此接口进行响应.
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Aug 21, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public interface ResponseCallback {

	public void onSuccess(HttpServletRequest request, HttpServletResponse response);
	
	public void onFail(HttpServletRequest request, HttpServletResponse response,Exception exception);
	
}
