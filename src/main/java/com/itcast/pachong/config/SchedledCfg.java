package com.itcast.pachong.config;


import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
public class SchedledCfg{

    // 定义关闭无效连接任务
    @Bean("closeConnectJobBean")
    public JobDetailFactoryBean closeConnectionJobBean(){
        //创建任务描述的工厂 Bean
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //设置spring管理的容器的 key 任务重， 可以根据这个key 获取spring 容器
        jobDetailFactoryBean.setApplicationContextJobDataKey("context");

        // 设置任务
        jobDetailFactoryBean.setJobClass(CloseConnectJob.class);
        //设置当没有触发器和任务绑定 ， 不会删除任务
        jobDetailFactoryBean.setDurability(true);

        return jobDetailFactoryBean;

    }

    //定义关闭无效连接触发器
    //Qualifier 通过名字注入 bean
    @Bean
    public CronTriggerFactoryBean closeConnectJobTrigger(
            @Qualifier(value="closeConnectJobBean") JobDetailFactoryBean itemJobBean){
          //创建一个表达式  触发器  工厂bean
         CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
         //设置任务描述 到触发器
         trigger.setJobDetail(itemJobBean.getObject());
         // 设置触发器 执行的时间
         trigger.setCronExpression("0/5 * * * * ? " );
         return trigger;
    }


    //定义  调度器
    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger[] cronTriggerImpl){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
       bean.setTriggers(cronTriggerImpl);
        return  bean;
    }

}
