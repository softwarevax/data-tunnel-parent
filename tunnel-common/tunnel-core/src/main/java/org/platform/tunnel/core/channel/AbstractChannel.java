package org.platform.tunnel.core.channel;

import org.platform.tunnel.core.*;

/**
 * @author twcao
 * @description abstract channel
 * @project data-tunnel-parent
 * @classname AbstractChannel
 * @date 2020/1/3 12:58
 */
public abstract class AbstractChannel implements Configurable, Channel,  LifecycleAware, NamedComponent {

    private String name;

    private LifecycleState lifecycleState;

    public AbstractChannel() {
        lifecycleState = LifecycleState.IDLE;
    }

    @Override
    public Event take() {
        return new DefaultEvent();
    }

    @Override
    public synchronized void setName(String name) {
        this.name = name;
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
    public synchronized LifecycleState getLifecycleState() {
        return lifecycleState;
    }

    @Override
    public synchronized String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{name: " + name + "}";
    }
}
