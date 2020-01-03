package org.platform.tunnel.common.beans;

import java.util.Map;

/**
 * @author twcao
 * @description transform interface
 * @project: data-tunnel-parent
 * @classname: Event
 * @date 2020/1/3 11:28
 */
public interface Event {

    /**
     * Returns a map of name-value pairs describing the data stored in the body.
     */
    Map<String, String> getHeaders();

    /**
     * Set the event headers
     * @param headers Map of headers to replace the current headers.
     */
    void setHeaders(Map<String, String> headers);

    /**
     * Returns the raw byte array of the data contained in this event.
     */
    byte[] getBody();

    /**
     * Sets the raw byte array of the data contained in this event.
     * @param body The data.
     */
    void setBody(byte[] body);
}
