package org.platform.tunnel.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author twcao
 * @description default Event
 * @project data-tunnel-parent
 * @classname DefaultEvent
 * @date 2020/1/3 11:40
 */
public class DefaultEvent implements Event {

    public static final String charset = "UTF-8";

    private Map<String, String> headers;

    private byte[] body;

    public DefaultEvent() {
        this.headers = new HashMap<>();
        this.headers.put(Constants.CHARSET, charset);
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public byte[] getBody() {
        return this.body;
    }

    @Override
    public void setBody(byte[] body) {
        this.body = body;
    }
}
