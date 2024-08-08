package io.atipera.githubsearcher.consumer;

import io.atipera.githubsearcher.client.Branch;
import io.atipera.githubsearcher.client.Branch.LastCommit;
import io.atipera.githubsearcher.client.GitHubClient;
import io.atipera.githubsearcher.client.Repository;
import io.atipera.githubsearcher.client.Repository.Owner;
import io.atipera.githubsearcher.consumer.RepositoryDto.BranchDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GitHubConsumerServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private GitHubConsumerService gitHubConsumerService;

    @Test
    void shouldFilterForkRepositories() {
        //given
        final String username = "gitHubUsername";
        when(gitHubClient.fetchRepositories(username)).thenReturn(
                List.of(
                        new Repository("one", false, new Owner(username)),
                        new Repository("two", true, new Owner(username))));

        when(gitHubClient.fetchBranches(username, "one")).thenReturn(
                List.of(new Branch("main", new LastCommit("111")), new Branch("secondary", new LastCommit("112"))));

        //when
        final List<RepositoryDto> repos = gitHubConsumerService.getRepos(username);

        //then
        assertThat(repos).hasSize(1);
        assertThat(repos).containsExactlyInAnyOrder(
                new RepositoryDto(
                        "one",
                        username,
                        List.of(
                                new BranchDto("main", "111"),
                                new BranchDto("secondary", "112"))));
    }
}
