package org.platform.tunnel.core;

/**
 * @author twcao
 * @description Sink
 * @project data-tunnel-parent
 * @classname Sink
 * @date 2020/1/3 14:27
 */
public interface Sink extends Configurable, LifecycleAware, NamedComponent {

    /**
     * <p>Sets the channel the sink will consume from</p>
     * @param channel The channel to be polled
     */
    void setChannel(Channel channel);

    /**
     * @return the channel associated with this sink
     */
    Channel getChannel();

    void process();
}
