package org.platform.tunnel.core.source;

import org.platform.tunnel.core.LifecycleAware;
import org.platform.tunnel.core.Source;

/**
 * @author twcao
 * @description SourceRunner
 * @project data-tunnel-parent
 * @classname SourceRunner
 * @date 2020/1/3 16:05
 */
public abstract class SourceRunner implements LifecycleAware {

    private Source source;

    public static SourceRunner forSource(Source source) {
        SourceRunner runner = null;
        if (source instanceof PollableSource) {
            runner = new PollableSourceRunner();
            runner.setSource(source);
        } else {
            throw new IllegalArgumentException("No known runner type for source " + source);
        }
        return runner;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
