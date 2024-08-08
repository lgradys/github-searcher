package io.atipera.githubsearcher.consumer;

import io.atipera.githubsearcher.client.GitHubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GitHubConsumerBeanConfiguration {

    @Bean
    GitHubConsumerService gitHubConsumerService(final GitHubClient gitHubClient) {
        return new GitHubConsumerService(gitHubClient);
    }
}
