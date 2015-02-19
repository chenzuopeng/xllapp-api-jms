package org.xllapp.api.jms;

import javax.servlet.http.HttpServletRequest;

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
public interface RequestParamResolver {

	public Object resolve(HttpServletRequest request) throws Exception;

}
