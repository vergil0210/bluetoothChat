package com.hdjd.bluetooth.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorFactory {

    private static Executor executor = new ThreadPoolExecutor(5,20,0, TimeUnit.SECONDS,new LinkedBlockingQueue());
    private static ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(3);
    private ExecutorFactory(){}
    public static Executor getExecutor(){
        return executor;
    }
    public static ScheduledThreadPoolExecutor getScheduleExecutor(){
        return scheduleExecutor;
    }
}
