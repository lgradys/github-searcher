package io.atipera.githubsearcher.client;

public record Branch(String name,
                     LastCommit lastCommit) {

    public record LastCommit(String sha) {
    }
}
