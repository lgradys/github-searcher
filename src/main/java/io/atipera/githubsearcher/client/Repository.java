package io.atipera.githubsearcher.client;

public record Repository(String name,
                         boolean fork,
                         Owner owner) {

    public record Owner(String login) {
    }
}
