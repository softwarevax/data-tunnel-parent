package org.platform.tunnel.test;

import org.platform.tunnel.channel.alibabamq.AlibabaRocketMQChannel;
import org.platform.tunnel.channel.memory.MemoryChannel;
import org.platform.tunnel.core.AbstractTunnelTask;
import org.platform.tunnel.core.Constants;
import org.platform.tunnel.core.Context;
import org.platform.tunnel.core.TunnelTaskFactory;
import org.platform.tunnel.sink.tcp.TcpSink;
import org.platform.tunnel.source.http.HttpSource;

/**
 * @author twcao
 * @description 测试
 * @project data-tunnel-parent
 * @classname App
 * @date 2020/1/3 16:58
 */
public class App {
    public static void main(String[] args) {
        testMemory();
    }

    public static void testRocketMQ() {
        String topic = "test";
        Context ctx = new Context();
        ctx.put(Constants.MQ_PRODUCER_GROUP_NAME, "ProducerGroupName");
        ctx.put(Constants.MQ_SERVER_SRV_ADDRESS, "localhost:9876");
        ctx.put(Constants.MQ_PRODUCER_INSTANCE_NAME, "Producer");
        ctx.put(Constants.MQ_MSG_TIMEOUT, "3000");
        ctx.put(Constants.MQ_MSG_SEND_FAILED_TRY_TIME, "2");
        ctx.put(Constants.MQ_TOPIC, topic);

        ctx.put(Constants.MQ_CONSUMER_GROUP_NAME, "ConsumerGroupName");
        ctx.put(Constants.MQ_CONSUMER_INSTANCE_NAME, "Consumer");

        AbstractTunnelTask task = TunnelTaskFactory.create(ctx, HttpSource.class, AlibabaRocketMQChannel.class, TcpSink.class);
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void testMemory() {
        Context ctx = new Context();
        AbstractTunnelTask task2 = TunnelTaskFactory.create(ctx, HttpSource.class, MemoryChannel.class, TcpSink.class);
        Thread thread2 = new Thread(task2);
        thread2.start();
    }
}
