package receiver;

import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytopic2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			//topic
			TopicConnectionFactory factoryTopic = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			//topic
			Topic topic = (Topic) applicationContext.getBean("topic");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			TopicConnection conn = factoryTopic.createTopicConnection();
		
			// Open a session	
			TopicSession sess = conn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			
			// start the connection
			conn.start();
			// Create a receive	
			TopicSubscriber subscriber = sess.createSubscriber(topic);

			// Receive the message
			//Message m = receiver.receive();
		
			subscriber.setMessageListener(new Listener());
		
			
			sess.commit();
	
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
