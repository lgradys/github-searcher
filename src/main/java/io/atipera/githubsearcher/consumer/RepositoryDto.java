package io.atipera.githubsearcher.consumer;

import java.util.List;

record RepositoryDto(String repositoryName,
                     String ownerLogin,
                     List<BranchDto> branches) {

    record BranchDto(String branchName,
                     String lastCommitSha) {
    }
}
