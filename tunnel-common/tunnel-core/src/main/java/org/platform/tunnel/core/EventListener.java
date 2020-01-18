package org.platform.tunnel.core;

/**
 * @author ctw
 * @Project： data-tunnel-parent
 * @Package: org.platform.tunnel.core
 * @Description:
 * @date 2020/1/4 11:45
 */
public interface EventListener {

    /**
     * Returns the next event from the channel if available.
     */
    void token(Event event);

}
