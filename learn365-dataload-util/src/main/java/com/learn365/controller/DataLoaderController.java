package com.learn365.controller;

import com.learn365.course.Dataloader;
import com.learn365.subscription.DataloaderSubscription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/loadData/")
public class DataLoaderController {

    private Dataloader dataloader;
    private DataloaderSubscription dataloadersubscription;

    public DataLoaderController(Dataloader dataloader, DataloaderSubscription dataloadersubscription) {
        this.dataloader = dataloader;
        this.dataloadersubscription = dataloadersubscription;
    }

    @GetMapping(value = "courseDetails")
    public void loadCourse() {
        dataloader.startMapping();
    }

    @GetMapping(value = "subscriptiondata")
    public void loadsubscription() {
        dataloadersubscription.startMapping();
    }
}
