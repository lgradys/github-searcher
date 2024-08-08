package io.atipera.githubsearcher.common;

import org.springframework.http.HttpStatus;

record ErrorDto(HttpStatus status, String message) {
}
