package com.learn.async.LearnAsyncOper;

import com.learn.async.LearnAsyncOper.beans.User;
import com.learn.async.LearnAsyncOper.service.GitHubLookUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private GitHubLookUpService gitHubLookUpService;

    @Override
    public void run(String... args) throws Exception {

        logger.info("Started the Asynchronous Calls");

        long start = System.currentTimeMillis();

        CompletableFuture<User> user1 = gitHubLookUpService.findUser("PivotalSoftware");
        CompletableFuture<User> user2 = gitHubLookUpService.findUser("CloudFoundry");
        CompletableFuture<User> user3 = gitHubLookUpService.findUser("spring-projects");

        CompletableFuture.allOf(user1,user2,user3);

        logger.info("Total Elapsed Time = " + (System.currentTimeMillis() - start));

        logger.info("---> " + user1.get());

        logger.info("---> " + user2.get());

        logger.info("---> " + user3.get());


    }
}
