package com.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.scheduler.MyScheduler;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class ActualizadorPeriodo implements javax.servlet.ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {
        MyScheduler schedule = new MyScheduler();
        try{
            System.out.println("Entra");
            schedule.execute();
        }
        catch (Exception e){
            
        }
        
    }

    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*implements Job {

   private Logger log = Logger.getLogger(ActualizadorPeriodo.class);

   public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
      log.debug("TestJob run successfully...");
   }

}*/