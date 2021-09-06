package com.boot.learn.bean.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

public class ThreadPoolHandler {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(21);
        executor.setCorePoolSize(2000);
        executor.setQueueCapacity(4);
        executor.setThreadNamePrefix("taskExecutor-");
        //setWaitForTasksToCompleteOnShutdown(true): 该方法用来设置线程池关闭的时候等待所有任务都完成后，再继续销毁其他的Bean，这样这些异步任务的销毁就会先于数据库连接池对象的销毁。
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //setAwaitTerminationSeconds(60): 该方法用来设置线程池中任务的等待时间，如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
