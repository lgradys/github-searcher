package io.atipera.githubsearcher.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class GitHubConsumerController {

    private final GitHubConsumerService gitHubConsumerService;

    GitHubConsumerController(final GitHubConsumerService gitHubConsumerService) {
        this.gitHubConsumerService = gitHubConsumerService;
    }

    private static final class Route {
        static final String ROOT = "/repos/{username}";
    }

    @GetMapping(Route.ROOT)
    List<RepositoryDto> getRepos(@PathVariable final String username) {
        return gitHubConsumerService.getRepos(username);
    }
}
