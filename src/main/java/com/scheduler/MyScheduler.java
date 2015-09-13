package com.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.scheduler.TheJob;
import java.util.Date;
import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.TriggerUtils;

public class MyScheduler {
    
    
    public void execute() {
        try {
            
            // specify the job' s details..
            JobDetail job = JobBuilder.newJob(TheJob.class)
                    .withIdentity("Job")
                    .build();
            
            
            // specify the running period of the job
            Trigger trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(dailyAtHourAndMinute(15, 33))
                    .forJob(job)
                    .build();
            
            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            
            Scheduler sch = schFactory.getScheduler();
            sch.start();
            sch.scheduleJob(job, trigger);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}