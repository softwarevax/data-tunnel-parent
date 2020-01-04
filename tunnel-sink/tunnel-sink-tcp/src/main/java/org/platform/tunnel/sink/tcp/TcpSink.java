package org.platform.tunnel.sink.tcp;

import org.platform.tunnel.core.*;
import org.platform.tunnel.core.sink.AbstractSink;

import java.nio.charset.Charset;

/**
 * @author twcao
 * @description TcpSink
 * @project data-tunnel-parent
 * @classname TcpSink
 * @date 2020/1/3 17:15
 */
public class TcpSink extends AbstractSink {

    public static final String SOURCE_NAME = "Tcp Sink";

    public TcpSink() {
        this.name = SOURCE_NAME;
    }

    @Override
    public void configure(Context context) {
        System.out.println("sink configure");
    }

    @Override
    public void process() {
        System.out.println("sink start");
        Channel channel = getChannel();
        new Thread(() -> {
            while (true) {
                Event e = channel.take();
                if(e.getBody() == null) {
                    continue;
                }
                System.out.println("memory:" + new String(e.getBody()));
            }
        }).start();
        new Thread(() -> {
            channel.pull(new EventListener() {
                @Override
                public void token(Event e) {
                    System.out.println("mq:" + new String(e.getBody(), Charset.forName(e.getHeaders().get(Constants.CHARSET))));
                }
            });
        }).start();

    }
}
