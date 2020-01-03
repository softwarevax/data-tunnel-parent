package org.platform.tunnel.core;

import org.platform.tunnel.core.channel.ChannelProcessor;

/**
 * @author twcao
 * @description source
 * @project data-tunnel-parent
 * @classname Source
 * @date 2020/1/3 14:21
 */
public interface Source extends Configurable, LifecycleAware, NamedComponent {

    /**
     * Specifies which channel processor will handle this source's events.
     *
     * @param channelProcessor
     */
    void setChannelProcessor(ChannelProcessor channelProcessor);

    /**
     * Returns the channel processor that will handle this source's events.
     */
    ChannelProcessor getChannelProcessor();
}
