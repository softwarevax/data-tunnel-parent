package org.platform.tunnel.core.source;

import org.platform.tunnel.core.LifecycleAware;
import org.platform.tunnel.core.LifecycleState;
import org.platform.tunnel.core.Source;
import org.platform.tunnel.core.channel.ChannelProcessor;

/**
 * @author twcao
 * @description abstract source
 * @project data-tunnel-parent
 * @classname AbstractSource
 * @date 2020/1/3 14:17
 */
public abstract class AbstractSource implements Source, LifecycleAware {

    private ChannelProcessor channelProcessor;

    private String name;

    private LifecycleState lifecycleState;

    public AbstractSource() {
        lifecycleState = LifecycleState.IDLE;
    }

    @Override
    public synchronized void start() {
        lifecycleState = LifecycleState.START;
    }

    @Override
    public synchronized void stop() {
        lifecycleState = LifecycleState.STOP;
    }

    @Override
    public synchronized void setChannelProcessor(ChannelProcessor cp) {
        channelProcessor = cp;
    }

    @Override
    public synchronized ChannelProcessor getChannelProcessor() {
        return channelProcessor;
    }

    @Override
    public synchronized LifecycleState getLifecycleState() {
        return lifecycleState;
    }

    @Override
    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{name:" + name + ",state:" + lifecycleState + "}";
    }
}
