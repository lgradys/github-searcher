package io.atipera.githubsearcher.client;

import io.atipera.githubsearcher.common.NotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

class GitHubClientImpl implements GitHubClient {

    private static final class Path {
        static final String REPOS = "/users/%s/repos";
        static final String BRANCHES = "/repos/%s/%s/branches";
    }

    private static final class Message {
        static final String USER_NOT_FOUND = "Failed to fetch, user with username: %s does not exist";
        static final String REPO_NOT_FOUND = "Failed to fetch, user: %s repository with name: %s does not exist";
    }

    private final RestClient restClient;

    GitHubClientImpl(final RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Repository> fetchRepositories(final String username) {
        return restClient.get()
                .uri(String.format(Path.REPOS, username))
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        (request, response) -> handleNotFound(Message.USER_NOT_FOUND, username))
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public List<Branch> fetchBranches(final String username, final String repositoryName) {
        return restClient.get()
                .uri(String.format(Path.BRANCHES, username, repositoryName))
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        (request, response) -> handleNotFound(Message.REPO_NOT_FOUND, username, repositoryName))
                .body(new ParameterizedTypeReference<>() {
                });
    }

    private void handleNotFound(final String message, final Object... args) {
        throw new NotFoundException(message, args);
    }
}
