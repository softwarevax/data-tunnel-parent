package org.platform.tunnel.core.source;

import org.platform.tunnel.core.LifecycleState;
import org.platform.tunnel.core.Source;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author twcao
 * @description PollableSourceRunner
 * @project data-tunnel-parent
 * @classname PollableSourceRunner
 * @date 2020/1/3 16:01
 */
public class PollableSourceRunner extends SourceRunner {

    private AtomicBoolean shouldStop;

    private PollingRunner runner;

    private Thread runnerThread;

    private LifecycleState lifecycleState;

    public PollableSourceRunner() {
        shouldStop = new AtomicBoolean();
        lifecycleState = LifecycleState.IDLE;
    }

    @Override
    public LifecycleState getLifecycleState() {
        return lifecycleState;
    }

    @Override
    public void start() {
        runner = new PollingRunner();
        PollableSource source = (PollableSource) getSource();
        runner.source = source;
        runner.shouldStop = shouldStop;
        runnerThread = new Thread(runner);
        runnerThread.setName(getClass().getSimpleName() + "-" + source.getClass().getSimpleName() + "-" + source.getName());
        runnerThread.start();

        lifecycleState = LifecycleState.START;
    }

    @Override
    public void stop() {
        runner.shouldStop.set(true);
        try {
            runnerThread.interrupt();
            runnerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Source source = getSource();
        source.stop();
        lifecycleState = LifecycleState.STOP;
    }

    public static class PollingRunner implements Runnable {

        private PollableSource source;

        private AtomicBoolean shouldStop;

        @Override
        public void run() {
            source.process();
        }
    }
}
