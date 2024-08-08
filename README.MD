# GitHubSearcher

## Installation

```bash
./gradlew clean bootRun
```

## API reference

### GET **/repos/{username}**

```
curl -X 'GET' 'http://{host}/repos/{username}' -H 'accept: application/json'
```

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