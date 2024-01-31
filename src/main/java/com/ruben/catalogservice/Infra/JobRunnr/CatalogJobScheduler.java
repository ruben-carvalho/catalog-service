package com.ruben.catalogservice.Infra.JobRunnr;

import jakarta.inject.Inject;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Component
public class CatalogJobScheduler  {

    @Job(name = "The sample job with variable %0", retries = 2)
    public void SchedulePrint(int seconds){
        BackgroundJob.<CatalogJobScheduler>schedule(Instant.now().plusSeconds(5), x-> x.PrintMsg());
    }


    public void PrintMsg(){
        System.out.println("Job Test");
    }
}
