package org.platform.tunnel.core.channel;

import org.platform.tunnel.core.Channel;
import org.platform.tunnel.core.ChannelSelector;

/**
 * @author twcao
 * @description AbstractChannelSelector
 * @project data-tunnel-parent
 * @classname AbstractChannelSelector
 * @date 2020/1/3 14:37
 */
public abstract class AbstractChannelSelector implements ChannelSelector {

    private Channel channel;

    private String name;

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String getName() {
        return name;
    }
}
