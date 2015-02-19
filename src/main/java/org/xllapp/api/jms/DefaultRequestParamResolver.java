package org.xllapp.api.jms;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析请求.
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Aug 21, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class DefaultRequestParamResolver implements RequestParamResolver {
	
	private final static Logger logger = LoggerFactory.getLogger(DefaultRequestParamResolver.class);
	
	private final static String HEAD_PREFIX="head.";
	
	@SuppressWarnings("serial")
	private Set<String> excludeHeads=new HashSet<String>(){{
		add("content-type");
		add("connection");
		add("content-length");
	}};
	
	@SuppressWarnings("unchecked")
	protected void resolveHead(HttpServletRequest request,Map<String,Object> map){
		Enumeration<String> headerNames=request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headerName=headerNames.nextElement();
			if(excludeHeads.contains(headerName)){
				logger.debug("exclude head[{}]",headerName);
				continue;
			}
			map.put(HEAD_PREFIX+headerName, request.getHeader(headerName));
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void resolveBody(HttpServletRequest request,Map<String,Object> map){
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			map.put(paramName, request.getParameter(paramName));
		}
	}

	@Override
	public Map<String, Object> resolve(HttpServletRequest request) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		resolveHead(request, map);
		resolveBody(request, map);
		logger.debug("resolved request param {}",map);
		return map;
	}

}
