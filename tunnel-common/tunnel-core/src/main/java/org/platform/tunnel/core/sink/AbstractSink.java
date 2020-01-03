package org.platform.tunnel.core.sink;

import org.platform.tunnel.core.Channel;
import org.platform.tunnel.core.LifecycleAware;
import org.platform.tunnel.core.LifecycleState;
import org.platform.tunnel.core.Sink;

/**
 * @author twcao
 * @description AbstractSink
 * @project data-tunnel-parent
 * @classname AbstractSink
 * @date 2020/1/3 14:28
 */
public abstract class AbstractSink implements Sink, LifecycleAware {

    protected Channel channel;

    protected String name = this.getClass().getName();

    protected LifecycleState lifecycleState;

    protected Thread thread;

    public AbstractSink() {
        lifecycleState = LifecycleState.IDLE;
        SinkRunner runner = new SinkRunner();
        runner.sink = this;
        thread = new Thread(runner);
    }

    @Override
    public void start() {
        lifecycleState = LifecycleState.START;
        thread.start();
    }

    @Override
    public void stop() {
        lifecycleState = LifecycleState.STOP;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public LifecycleState getLifecycleState() {
        return lifecycleState;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static class SinkRunner implements Runnable {
        Sink sink;

        @Override
        public void run() {
            sink.process();
        }
    }
}
