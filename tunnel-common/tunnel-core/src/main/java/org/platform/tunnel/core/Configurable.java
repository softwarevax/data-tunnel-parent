package org.platform.tunnel.core;

/**
 * @author twcao
 * @description Any class marked as Configurable may have a context including its sub-configuration passed to it,
 * requesting it configure itself.
 * @project data-tunnel-parent
 * @classname Configurable
 * @date 2020/1/3 13:05
 */
public interface Configurable {

    /**
     * Request the implementing class to (re)configure itself.
     * @param context
     */
    void configure(Context context);
}
