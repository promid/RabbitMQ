package exchanges.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by DBQ on 2016/12/15.
 */
public class TestRecvDirectLogs {



    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(TestDirectLogs.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(TestDirectLogs.EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        if (args.length < 1){
            System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
            System.exit(1);
        }

        for(String severity : args){
            System.out.println("args: " + Arrays.asList(args));
            channel.queueBind(queueName, TestDirectLogs.EXCHANGE_NAME, severity);
        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}

