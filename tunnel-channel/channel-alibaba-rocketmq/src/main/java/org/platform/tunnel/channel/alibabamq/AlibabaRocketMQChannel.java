package org.platform.tunnel.channel.alibabamq;


import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.platform.tunnel.core.*;
import org.platform.tunnel.core.channel.AbstractChannel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author ctw
 * @Project： data-tunnel-parent Only for one topic
 * @Package: org.platform.tunnel.channel.alibabamq
 * @Description:
 * @date 2020/1/4 10:08
 */
public class AlibabaRocketMQChannel extends AbstractChannel {

    private DefaultMQProducer producer;

    private DefaultMQPushConsumer consumer;

    private String topic;

    @Override
    public void configure(Context context) {
        if(getLifecycleState() == LifecycleState.IDLE) {
            this.producer = createProducer(context);
            this.consumer = createConsumer(context);
        }
    }

    @Override
    public boolean put(Event event) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream ps = new ObjectOutputStream(bos);
            ps.writeObject(event);
            ps.flush();
            byte[] buf = bos.toByteArray();
            producer.send(new Message(topic, buf));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void pull(EventListener listener) {
        try {
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                try {
                    MessageExt msg = msgs.get(0);
                    ByteArrayInputStream bis = new ByteArrayInputStream(msg.getBody());
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Event e = (Event) ois.readObject();
                    listener.token(e);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DefaultMQProducer createProducer(Context ctx) {
        DefaultMQProducer producer = null;
        try {
            String groupName = ctx.getString(Constants.MQ_PRODUCER_GROUP_NAME);
            producer = new DefaultMQProducer(groupName);
            String serverSrv = ctx.getString(Constants.MQ_SERVER_SRV_ADDRESS);
            producer.setNamesrvAddr(serverSrv);
            String instanceName = ctx.getString(Constants.MQ_PRODUCER_INSTANCE_NAME);
            producer.setInstanceName(instanceName);
            Integer timeout = ctx.getInteger(Constants.MQ_MSG_TIMEOUT);
            producer.setSendMsgTimeout(timeout);
            Integer tryTime = ctx.getInteger(Constants.MQ_MSG_SEND_FAILED_TRY_TIME);
            producer.setRetryTimesWhenSendFailed(tryTime);
            this.topic = ctx.getString(Constants.MQ_TOPIC);
            producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producer;
    }

    private DefaultMQPushConsumer createConsumer(Context ctx) {
        DefaultMQPushConsumer consumer = null;
        try {
            String groupName = ctx.getString(Constants.MQ_CONSUMER_GROUP_NAME);
            consumer = new DefaultMQPushConsumer(groupName);
            String serverSrv = ctx.getString(Constants.MQ_SERVER_SRV_ADDRESS);
            consumer.setNamesrvAddr(serverSrv);
            String instanceName = ctx.getString(Constants.MQ_CONSUMER_INSTANCE_NAME);
            consumer.setInstanceName(instanceName);
            String topic = ctx.getString(Constants.MQ_TOPIC);
            consumer.subscribe(topic, "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumer;
    }
}
