package org.platform.tunnel.core.channel;

import org.platform.tunnel.core.Channel;
import org.platform.tunnel.core.ChannelSelector;
import org.platform.tunnel.core.Configurables;
import org.platform.tunnel.core.Context;

/**
 * @author twcao
 * @description ChannelSelectorFactory
 * @project data-tunnel-parent
 * @classname ChannelSelectorFactory
 * @date 2020/1/3 15:29
 */
public class ChannelSelectorFactory {

    public static final String DEFAULT_SELECTOR = "org.platform.tunnel.core.channel.DefaultChannelSelector";

    public static ChannelSelector create(Channel channel, Context ctx) {
        ChannelSelector selector = null;
        try {
            Class<? extends ChannelSelector> clazz = (Class<? extends ChannelSelector>) Class.forName(DEFAULT_SELECTOR);
            selector =  clazz.newInstance();
            selector.setChannel(channel);
            Configurables.configure(selector, ctx);
            Configurables.configure(channel, ctx);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return selector;
    }
}
