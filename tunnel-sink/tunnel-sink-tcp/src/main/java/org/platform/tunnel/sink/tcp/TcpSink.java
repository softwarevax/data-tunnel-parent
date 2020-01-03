package org.platform.tunnel.sink.tcp;

import org.platform.tunnel.core.Channel;
import org.platform.tunnel.core.Constants;
import org.platform.tunnel.core.Context;
import org.platform.tunnel.core.Event;
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
        while (true) {
            Event e = channel.token();
            System.out.println(new String(e.getBody(), Charset.forName(e.getHeaders().get(Constants.CHARSET))));
        }
    }
}
