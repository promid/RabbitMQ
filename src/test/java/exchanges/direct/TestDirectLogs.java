package exchanges.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.MyUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by DBQ on 2016/12/15.
 */
public class TestDirectLogs {
    protected static final String HOST = MyUtils.getHost();
    protected static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String message = MyUtils.getMessage(args);
        String severity = MyUtils.getSeverity(args);
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
