package org.xllapp.api.jms;

import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.ffcs.icity.api.support.RequestContextHolder;

/**
 * 此类扩展了DefaultResponseCallback类.支持在配置的字符串中使用变量.
 * 
 * 当前支持的变量有： {TIME} 当前的时间,格式：yyyy-MM-dd HH:mm:ss
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Aug 29, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class TemplateResponseCallback extends DefaultResponseCallback {

	private final static String PATTERN_TIME = Pattern.quote("{TIME}");
	
	private final static String PATTERN_REQUEST_ID = Pattern.quote("{REQUEST_ID}");

	private String getTime() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	private String getRequestId(){
		return RequestContextHolder.getRequestId();
	}

	private String parseTemplate(String template) {
		if (StringUtils.isBlank(template)) {
			return template;
		}
		return template
				.replaceAll(PATTERN_TIME, getTime())
				.replaceAll(PATTERN_REQUEST_ID, getRequestId());
	}

	@Override
	protected void out(HttpServletResponse response, String content) {
		super.out(response, parseTemplate(content));
	}

}
