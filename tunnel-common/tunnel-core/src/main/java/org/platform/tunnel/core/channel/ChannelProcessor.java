package org.platform.tunnel.core.channel;

import org.platform.tunnel.core.*;

import java.util.List;

/**
 * @author twcao
 * @description ChannelProcessor
 * @project data-tunnel-parent
 * @classname ChannelProcessor
 * @date 2020/1/3 14:23
 */
public class ChannelProcessor implements Configurable {

    private ChannelSelector selector;

    public ChannelProcessor(ChannelSelector selector) {
        this.selector = selector;
    }

    @Override
    public void configure(Context context) {
    }

    public ChannelSelector getSelector() {
        return selector;
    }

    public void processEventBatch(List<Event> events) {
        if(events == null || events.size() == 0) {
            return;
        }
        Channel channel = selector.getChannel();
        for (Event event : events) {
            channel.put(event);
        }
    }

    public void processEvent(Event event) {
        if (event == null) {
            return;
        }
        Channel channel = selector.getChannel();
        channel.put(event);
    }
}
