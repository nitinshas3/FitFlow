package com.nitin.activityservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
}
// this is same as jpa config which we used in eventsphere , used for created at updated at etc @enablejpaauditing shoudl be used in jpa and here enablemongoauditing

//usualy configuration class expcetgs beans no , here also annotation @enalbleaudition is boilerplate for some code which returns beans
