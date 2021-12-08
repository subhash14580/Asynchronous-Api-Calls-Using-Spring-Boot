package com.learn.async.LearnAsyncOper.service;

import com.learn.async.LearnAsyncOper.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookUpService {

    @Value("${github.url}")
    private String gitHubUrl;

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookUpService.class);

    private final RestTemplate restTemplate;

    public GitHubLookUpService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<User> findUser(String userName) throws InterruptedException {
        String url = String.format(gitHubUrl, userName);
        logger.info("URL=["+ url+"]");
        User userResponse = restTemplate.getForObject(url,User.class);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(userResponse);
    }
}
