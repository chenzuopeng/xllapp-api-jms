package org.xllapp.api.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Aug 19, 2013
 * @version 1.00.00
 * @history:
 * 
 */
public class JMSProducer implements ExceptionListener,InitializingBean{

	private final static Logger logger = LoggerFactory.getLogger(JMSProducer.class);

	private final static Logger successMessagelogger = LoggerFactory.getLogger("jms.message.success");
	
	private final static Logger failedMessagelogger = LoggerFactory.getLogger("jms.message.failed");

	private boolean useAsyncSend = true;

	private boolean isPersistent = true;

	private ConnectionFactory connectionFactory;

	private MessageConverter messageConverter=new DefaultMessageConverter();

	public void send(String queue, Object requestParam) {
		try {
			logger.debug("sending message[{}] to queue[{}]", requestParam, queue);
			_send(queue, requestParam);
			logger.debug("sended message[{}] to queue[{}]", requestParam, queue);
			successMessagelogger.info("queue[{}]:message[{}]", queue, requestParam);
		} catch (Exception e) {
			logger.error("send message[" + requestParam + "] to queue[" + queue + "] error", e);
			failedMessagelogger.info("queue[{}]:message[{}],caused by {}", queue, requestParam,e.getLocalizedMessage());
		}
	}

	private void _send(String queue, Object requestParam) throws Exception {
		Connection connection = null;
		Session session = null;
		try {
			connection = getConnection();
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queue);
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(this.isPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
			Message message = this.messageConverter.convert(session, requestParam);
			producer.send(message);
		} finally {
			closeSession(session);
			closeConnection(connection);
		}
	}
	
	private Connection getConnection() throws JMSException {
		return this.connectionFactory.createConnection();
	}

	private void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	private void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void onException(JMSException exception) {
		logger.error("connection error", exception);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(this.connectionFactory instanceof ActiveMQConnectionFactory){
			((ActiveMQConnectionFactory) this.connectionFactory).setUseAsyncSend(this.useAsyncSend);
		}else if(this.connectionFactory instanceof PooledConnectionFactory){
			PooledConnectionFactory pooledConnectionFactory=(PooledConnectionFactory) this.connectionFactory;
			((ActiveMQConnectionFactory)pooledConnectionFactory.getConnectionFactory()).setUseAsyncSend(this.useAsyncSend);
		}
	}

	public boolean isUseAsyncSend() {
		return this.useAsyncSend;
	}

	public void setUseAsyncSend(boolean useAsyncSend) {
		this.useAsyncSend = useAsyncSend;
	}

	public MessageConverter getMessageConverter() {
		return this.messageConverter;
	}

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
}
