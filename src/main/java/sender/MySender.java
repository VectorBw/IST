package sender;

import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.QueueConnectionFactory;

import org.apache.activemq.command.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			//topic
			TopicConnectionFactory factoryTopic = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			//topic
			Topic topic = (Topic) applicationContext.getBean("topic");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			QueueConnection connection = factory.createQueueConnection() ;
			
			TopicConnection conn = factoryTopic.createTopicConnection();
			// Open a session	
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE) ;
			
			TopicSession sess = conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			// Start the connection
			connection.start();
			
			conn.start();
			// Create a sender	
			QueueSender sender =session.createSender( queue ) ;
			
			TopicPublisher publisher = sess.createPublisher(topic);
			
			// Create a message
			TextMessage m =session.createTextMessage("HelloWorld");
			// Send the message
			sender.send(m);
			//publish the message
			publisher.publish(m);
			System.out.println("Message published: " + m.getText());
			
			session.commit();
			// Close the session
			session.close();
			// Close the connection
			connection.close();
		
			//topic 
			sess.commit();
			sess.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
