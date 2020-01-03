package org.platform.tunnel.core;

/**
 * @author twcao
 * @description LifecycleState
 * @project data-tunnel-parent
 * @classname LifecycleState
 * @date 2020/1/3 13:09
 */
public enum LifecycleState  {

    ERROR("error"), IDLE("idle"), START("start"), STOP("stop");

    private String value;

    LifecycleState(String value) {
        this.value = value;
    }
}
