package org.platform.tunnel.core;

/**
 * @author twcao
 * @description ChannelSelector
 * @project data-tunnel-parent
 * @classname ChannelSelector
 * @date 2020/1/3 14:36
 */
public interface ChannelSelector extends NamedComponent, Configurable {
    /**
     * @param channel all channels the selector could select from.
     */
    void setChannel(Channel channel);

    /**
     * @return the list of all channels that this selector is configured to work
     * with.
     */
    Channel getChannel();
}
