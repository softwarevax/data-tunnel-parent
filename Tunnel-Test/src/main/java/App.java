import org.platform.tunnel.channel.memory.MemoryChannel;
import org.platform.tunnel.core.AbstractTunnelTask;
import org.platform.tunnel.core.Context;
import org.platform.tunnel.core.TunnelTaskFactory;
import org.platform.tunnel.sink.tcp.TcpSink;
import org.platform.tunnel.source.http.HttpSource;

/**
 * @author twcao
 * @description 测试
 * @project data-tunnel-parent
 * @classname App
 * @date 2020/1/3 16:58
 */
public class App {
    public static void main(String[] args) {
        Context ctx = new Context();
        ctx.put("name", "中国");
        AbstractTunnelTask task = TunnelTaskFactory.create(ctx, HttpSource.class, MemoryChannel.class, TcpSink.class);
        Thread thread = new Thread(task);
        thread.start();
    }
}
