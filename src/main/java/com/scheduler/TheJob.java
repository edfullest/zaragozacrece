package com.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TheJob implements Job {
    
    private Logger log = Logger.getLogger(TheJob.class);
    
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        Date fecha = new Date();
        //Obtengo la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date d = new Date();
        sdf.applyPattern("EEE, d MMM yyyy HH:mm:ss Z");
        String newFecha = sdf.format(d);
        
        try {
            fecha = sdf.parse(newFecha);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(TheJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Yes");
        System.out.println(fecha);
        log.debug("Job run successfully on "+fecha);
        
        
    }
    
}