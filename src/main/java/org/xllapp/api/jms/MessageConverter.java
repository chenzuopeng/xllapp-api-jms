package org.xllapp.api.jms;

import javax.jms.Message;
import javax.jms.Session;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 Aug 22, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public interface MessageConverter {

	public Message convert(Session session,Object input) throws Exception;
	
}
