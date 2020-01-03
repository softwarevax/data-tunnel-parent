package org.platform.tunnel.core.source;

import org.platform.tunnel.core.LifecycleState;
import org.platform.tunnel.core.channel.ChannelProcessor;

/**
 * @author twcao
 * @description AbstractPollableSource
 * @project data-tunnel-parent
 * @classname AbstractPollableSource
 * @date 2020/1/3 16:28
 */
public abstract class AbstractPollableSource implements PollableSource {

    protected ChannelProcessor processor;

    protected SourceRunner runner;

    public AbstractPollableSource() {
        runner = SourceRunner.forSource(this);
        runner.setSource(this);
    }

    @Override
    public void setChannelProcessor(ChannelProcessor channelProcessor) {
        this.processor = channelProcessor;
    }

    @Override
    public ChannelProcessor getChannelProcessor() {
        return this.processor;
    }

    @Override
    public LifecycleState getLifecycleState() {
        return runner.getLifecycleState();
    }

    @Override
    public void start() {
        runner.start();
    }

    @Override
    public void stop() {
        runner.stop();
    }
}
