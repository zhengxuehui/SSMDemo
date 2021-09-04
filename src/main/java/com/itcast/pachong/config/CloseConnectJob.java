package com.itcast.pachong.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时关闭 HttpClient 的无效连接
 * 当定时任务没有执行完的情况下， 不会再次启动新的任务
 */
@DisallowConcurrentExecution
public class CloseConnectJob  extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //获取 Spring 的容器
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("context");

        //从spring 容器中 获取 httpClient 的连接管理器 ， 关闭无效连接
        applicationContext.getBean(PoolingHttpClientConnectionManager.class).closeExpiredConnections();

    }
}
