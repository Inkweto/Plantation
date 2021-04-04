# Plantation
## Development
Create `.env` file from example:
```bash
cp .env.example .env
```
Adjust vars in created `.env` to your needs.

## VSCode + Docker
### Prerequisites
`ms-vscode-remote.remote-containers` extension for VSCode
### Quickstart
1. Start Docker
2. Open this repo's folder with VSCode
3. `Ctrl + Shift + P` -> Reopen in Container
4. Wait or Kotlin extension to initialize classpath (after opening one of .kt files)
5. Open VSCode's terminal and run `gradle build` or use `Ctrl + Shift + B`
6. Edit files and use terminal like you'd normally do on host machine
### Run app
`gradle bootRun` or `F5` for debugging.

## Docker only
### Start containers
```bash
docker-compose up -d
```
### Run app
```
docker-compose exec kotlin gradle build && gradle bootRun
```
