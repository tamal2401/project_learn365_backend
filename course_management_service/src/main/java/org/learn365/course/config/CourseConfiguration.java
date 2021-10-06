package org.learn365.course.config;

import org.learn365.course.cache.CacheService;
import org.learn365.course.cache.CourseDatastructure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfiguration
{
    @Bean
    public CacheService cacheService()
    {
        CourseDatastructure courseds = new CourseDatastructure();
        return CacheService.getCacheInstance(courseds);
    }
}
