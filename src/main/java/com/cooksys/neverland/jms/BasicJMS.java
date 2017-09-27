package com.cooksys.neverland.jms;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.cooksys.neverland.websocket.SocketEndpoint;
import com.google.gson.JsonSyntaxException;


public class BasicJMS
{
	static MessageProducer producer;
	static Destination destination;
	static Session session;
	private static final String FLIGHT_STATUS_PROPERTY = "FlightStatus";

	// private static final String TOPIC_NAME = "FlightUpdate";
	// private MessageConsumer debugListener;
	// private TopicConnectionFactory connFactory;
	// private Connection connection;
	// private Topic topic;
	// private Boolean connected = false;
	// private Gson gson = new Gson();
	// private Session session;

	public static void main(String[] args)
	{
		try
		{
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			destination = session.createTopic("FlightUpdate");

			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener()
			{
				@Override
				public void onMessage(Message message)
				{
					try
					{
						if (message instanceof TextMessage)
						{
							TextMessage textMessage = (TextMessage) message;
							String status = textMessage.getStringProperty(FLIGHT_STATUS_PROPERTY);
							String text = textMessage.getText();
							System.out.println("[FlightStatus] " + status
									+ " [Text]" + text);

							producer = session.createProducer(destination);
							producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

							// if (server != "17")
							// {
							try
							{
								SocketEndpoint
										.sendMsg(new com.cooksys.neverland.websocket.Message(
												status, text));
							} catch (JsonSyntaxException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// }
							// }
							// else
							// {
							// System.out.println("Received: " + message);
							// }
						} else
						{
							System.out.println("Received: " + message);
						}
					} catch (JMSException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
		} catch (Exception e)
		{
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}
}