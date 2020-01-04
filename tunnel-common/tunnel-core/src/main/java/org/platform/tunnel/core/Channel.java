package org.platform.tunnel.core;

/**
 * @author twcao
 * @description transform channel
 * @project data-tunnel-parent
 * @classname Channel
 * @date 2020/1/3 11:48
 */
public interface Channel extends LifecycleAware, NamedComponent {

    /**
     * Puts the given event into the channel.
     */
    boolean put(Event event);

    /**
     * Returns the next event from the channel if available.
     */
    Event take();

    /**
     * Asynchronous pull
     */
    default void pull(EventListener listener) {}
}
