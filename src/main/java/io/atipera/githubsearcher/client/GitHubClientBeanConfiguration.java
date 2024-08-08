package io.atipera.githubsearcher.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class GitHubClientBeanConfiguration {

    @Bean
    GitHubClient gitHubClient(final RestClient.Builder restClientBuilder,
                              @Value("${github.api.url}") final String gitHubApiUrl) {
        final RestClient restClient = restClientBuilder
                .baseUrl(gitHubApiUrl).build();
        return new GitHubClientImpl(restClient);
    }
}
