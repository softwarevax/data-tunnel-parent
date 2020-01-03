package org.platform.tunnel.core.source;

import org.platform.tunnel.core.Source;

/**
 * @author twcao
 * @description PollableSource
 * @project data-tunnel-parent
 * @classname PollableSource
 * @date 2020/1/3 16:00
 */
public interface PollableSource extends Source {

    void process();
}
