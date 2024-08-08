# GitHubSearcher

## Installation

```bash
./gradlew clean bootRun
```

## API reference

### GET **/repos/{username}**

- status 200 (OK)

```json
[
  {
    "repositoryName": "string",
    "ownerLogin": "string",
    "branches": [
      {
        "branchName": "string",
        "lastCommitSha": "string"
      }
    ]
  }
]
```

- status 404 (NOT_FOUND)

```json
{
  "status": "100 CONTINUE",
  "message": "string"
}
```