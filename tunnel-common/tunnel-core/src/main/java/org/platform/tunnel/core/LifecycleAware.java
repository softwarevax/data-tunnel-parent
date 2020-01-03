package org.platform.tunnel.core;

/**
 * @author twcao
 * @description n interface implemented by any class that has a defined, stateful, lifecycle.
 * @project data-tunnel-parent
 * @classname LifecycleAware
 * @date 2020/1/3 13:07
 */
public interface LifecycleAware {

    /**
     * Return the current state of the service or component.
     * @return
     */
    LifecycleState getLifecycleState();

    /**
     * Starts a service or component.
     */
    void start();

    /**
     * Stops a service or component.
     */
    void stop();
}
