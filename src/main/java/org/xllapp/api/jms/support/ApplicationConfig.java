package org.xllapp.api.jms.support;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ffcs.icity.api.support.BaseApplicationConfig;

/**
 * 此类用于存放应用配置,这些配置可以在运行时通过访问/config进行配置.
 * 
 * 注:支持运行时配置属性的类型：基础类型,String类型.
 * 
 * 例：
 * 
 * public class ApplicationConfig extends BaseApplicationConfig {
 * 
 * @FieldDescription("是否发送事件") private String isFireEvent;
 * 
 *                             public boolean isFireEvent() {{ return
 *                             this.isFireEvent; } }
 *
 * @Copyright: Copyright (c) 2014 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Aug 21, 2014
 * @version 1.00.00
 * @history:
 * 
 */
@Component
@Lazy(false)
public class ApplicationConfig extends BaseApplicationConfig {

}
