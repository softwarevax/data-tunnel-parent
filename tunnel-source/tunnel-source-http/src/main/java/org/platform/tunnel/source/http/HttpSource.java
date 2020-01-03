package org.platform.tunnel.source.http;

import org.platform.tunnel.core.Constants;
import org.platform.tunnel.core.Context;
import org.platform.tunnel.core.DefaultEvent;
import org.platform.tunnel.core.Event;
import org.platform.tunnel.core.source.AbstractPollableSource;

/**
 * @author twcao
 * @description HttpSource
 * @project data-tunnel-parent
 * @classname HttpSource
 * @date 2020/1/3 15:51
 */
public class HttpSource extends AbstractPollableSource {

    public static final String SOURCE_NAME = "HTTP Poll Source";

    private String name;

    public HttpSource() {
        this.name = SOURCE_NAME;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void configure(Context context) {
        System.out.println("source configure");
        context.put("sourceName", SOURCE_NAME);
    }

    @Override
    public void process() {
        System.out.println("source start");
        try {
            while (true) {
                Event e = new DefaultEvent();
                e.setBody("中国".getBytes());
                processor.processEvent(e);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
