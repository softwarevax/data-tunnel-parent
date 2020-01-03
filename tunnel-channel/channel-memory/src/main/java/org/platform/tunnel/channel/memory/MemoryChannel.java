package org.platform.tunnel.channel.memory;

import org.platform.tunnel.core.Context;
import org.platform.tunnel.core.DefaultEvent;
import org.platform.tunnel.core.Event;
import org.platform.tunnel.core.channel.AbstractChannel;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author twcao
 * @description memory channel
 * @project data-tunnel-parent
 * @classname MemoryChennel
 * @date 2020/1/3 13:56
 */
public class MemoryChannel extends AbstractChannel {

    private BlockingDeque<Event> queue;

    public MemoryChannel() {
        this.queue = new LinkedBlockingDeque();
    }

    @Override
    public void configure(Context context) {

    }

    @Override
    public boolean put(Event event) {
        if(queue.size() == Integer.MAX_VALUE - 1) {
            return false;
        }
        this.queue.add(event);
        return true;
    }

    @Override
    public Event token() {
        try {
            return this.queue.take();
        } catch (InterruptedException e) {
            return new DefaultEvent();
        }
    }
}
