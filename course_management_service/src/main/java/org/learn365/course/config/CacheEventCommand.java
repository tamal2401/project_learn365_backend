package org.learn365.course.config;

import org.learn365.course.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CacheEventCommand
{

    @Autowired
    CacheService cacheService;
    @EventListener
    public void hanleContextRefrestedEvent(
            ContextRefreshedEvent contextrefreshed)
    {
       List<String> grades= cacheService.gradeList();
        grades.stream().forEach(grade->cacheService.prepareNodeTree(grade));

        System.out.println("Context has been refreshed");

    }
}
