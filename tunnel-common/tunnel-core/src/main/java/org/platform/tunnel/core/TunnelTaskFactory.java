package org.platform.tunnel.core;

/**
 * @author twcao
 * @description TunnelTaskFactory
 * @project data-tunnel-parent
 * @classname TunnelFactory
 * @date 2020/1/3 17:04
 */
public class TunnelTaskFactory {

    public static AbstractTunnelTask create(Context ctx, Class<? extends Source> source, Class<? extends Channel> channel, Class<? extends Sink> sink) {
        return create(ctx, source.getName(), channel.getName(), sink.getName());
    }

    public static AbstractTunnelTask create(Context ctx, String source, String channel, String sink) {
        try {
            Class<? extends Source> s = (Class<? extends Source>) Class.forName(source);
            Class<? extends Sink> si = (Class<? extends Sink>) Class.forName(sink);
            Class<? extends Channel> c = (Class<? extends Channel>) Class.forName(channel);
            return new DefaultTunnelTask(ctx, s.newInstance(), c.newInstance(), si.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
