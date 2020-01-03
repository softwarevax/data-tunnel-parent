package org.platform.tunnel.core;

import org.platform.tunnel.core.channel.ChannelProcessor;
import org.platform.tunnel.core.channel.ChannelSelectorFactory;

/**
 * @author twcao
 * @description TunnelTask
 * @project data-tunnel-parent
 * @classname TunnelTask
 * @date 2020/1/3 15:44
 */
public abstract class AbstractTunnelTask implements Runnable {

    protected Channel channel;

    protected Source source;

    protected Sink sink;

    protected Context context;

    public AbstractTunnelTask(Context context, Source source, Channel channel, Sink sink) {
        this.source = source;
        this.sink = sink;
        this.channel = channel;
        this.context = context;
        Configurables.configure(source, context);
        ChannelProcessor processor = new ChannelProcessor(ChannelSelectorFactory.create(channel, context));
        source.setChannelProcessor(processor);
        sink.setChannel(channel);
        Configurables.configure(sink, context);
    }

    @Override
    public void run() {
        source.start();
        sink.start();
    }
}
