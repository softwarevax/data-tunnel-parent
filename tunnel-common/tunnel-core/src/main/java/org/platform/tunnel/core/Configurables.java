package org.platform.tunnel.core;

/**
 * @author twcao
 * @description Configurables
 * @project data-tunnel-parent
 * @classname Configurables
 * @date 2020/1/3 15:42
 */
public class Configurables {

    public static boolean configure(Object target, Context context) {
        if (target instanceof Configurable) {
            ((Configurable) target).configure(context);
            return true;
        }
        return false;
    }
}
