package org.xllapp.api.jms;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

/**
 * 解析使用JSON格式传递参数的请求,将请求体中的JSON信息使用"request.body"这个key保存到map中.
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Aug 29, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class JSONRequestParamResolver extends DefaultRequestParamResolver implements RequestParamResolver {

	@Override
	protected void resolveBody(HttpServletRequest request, Map<String, Object> map){
		try {
			String content=IOUtils.toString(request.getInputStream(), "UTF-8");
			map.put("request.body", content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
