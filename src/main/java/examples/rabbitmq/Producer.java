package examples.rabbitmq;

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;


/**
 * The producer endpoint that writes to the queue.
 * @author syntx
 *
 */
public class Producer extends EndPoint{

    public Producer(String endPointName) throws Exception{
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws Exception {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
