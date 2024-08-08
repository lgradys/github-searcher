package io.atipera.githubsearcher.client;

import java.util.List;

public interface GitHubClient {

    List<Repository> fetchRepositories(final String username);

    List<Branch> fetchBranches(final String username, final String repo);
}
