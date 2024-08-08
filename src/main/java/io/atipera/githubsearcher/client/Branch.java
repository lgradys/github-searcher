package io.atipera.githubsearcher.client;

public record Branch(String name,
                     LastCommit commit) {

    public record LastCommit(String sha) {
    }
}
