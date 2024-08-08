package io.atipera.githubsearcher.consumer;

import io.atipera.githubsearcher.common.NotFoundException;
import io.atipera.githubsearcher.consumer.RepositoryDto.BranchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@WebMvcTest(GitHubConsumerController.class)
class GitHubConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubConsumerService gitHubConsumerService;

    @Test
    void shouldReturn404WhenUsernameNotFound() throws Exception {
        //given
        when(gitHubConsumerService.getRepos("githubUsername"))
                .thenThrow(new NotFoundException("User githubUsername not found"));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/repos/githubUsername"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User githubUsername not found"));
    }

    @Test
    void shouldReturn() throws Exception {
        //given
        when(gitHubConsumerService.getRepos("githubUsername"))
                .thenReturn(
                        List.of(
                                new RepositoryDto("one", "githubUsername", singletonList(new BranchDto("main", "111"))),
                                new RepositoryDto(
                                        "two",
                                        "githubUsername",
                                        List.of(
                                                new BranchDto("main", "112"),
                                                new BranchDto("second", "113")))));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/repos/githubUsername"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].branches", hasSize(2)));
    }
}
