package io.atipera.githubsearcher.consumer;

import io.atipera.githubsearcher.client.GitHubClient;
import io.atipera.githubsearcher.consumer.RepositoryDto.BranchDto;

import java.util.List;

class GitHubConsumerService {

    private final GitHubClient gitHubClient;

    GitHubConsumerService(final GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    List<RepositoryDto> getRepos(final String username) {
        return gitHubClient.fetchRepositories(username).stream()
                .filter(repo -> !repo.fork())
                .map(repo -> new RepositoryDto(
                        repo.name(),
                        repo.owner().login(),
                        gitHubClient.fetchBranches(username, repo.name()).stream()
                                .map(branch -> new BranchDto(branch.name(), branch.lastCommit().sha()))
                                .toList()))
                .toList();
    }
}
