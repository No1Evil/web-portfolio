# Web portfolio CMS

[![Backend CI](https://github.com/No1Evil/web-portfolio/actions/workflows/backend-ci.yml/badge.svg?branch=dev)](https://github.com/No1Evil/web-portfolio/actions/workflows/backend-ci.yml)
[![Frontend CI](https://github.com/No1Evil/web-portfolio/actions/workflows/frontend-ci.yml/badge.svg?branch=dev)](https://github.com/No1Evil/web-portfolio/actions/workflows/frontend-ci.yml)
[![Release](https://github.com/No1Evil/web-portfolio/actions/workflows/release.yml/badge.svg)](https://github.com/No1Evil/web-portfolio/actions/workflows/release.yml)

[![Coverage](https://raw.githubusercontent.com/No1Evil/web-portfolio/badges/.github/badges/jacoco.svg)](https://github.com/No1Evil/web-portfolio/actions/workflows/coverage-badge.yml)
[![Branches](https://raw.githubusercontent.com/No1Evil/web-portfolio/badges/.github/badges/branches.svg)](https://github.com/No1Evil/web-portfolio/actions/workflows/coverage-badge.yml)

![License](https://img.shields.io/github/license/No1Evil/web-portfolio?style=plastic&color=orange)
![Repo Size](https://img.shields.io/github/repo-size/No1Evil/web-portfolio?style=plastic)
![Last Commit](https://img.shields.io/github/last-commit/No1Evil/web-portfolio?style=plastic)
![Open PRs](https://img.shields.io/github/issues-pr/No1Evil/web-portfolio?style=plastic)

## Navigation

- [Description](README.md#description)
- [Tech Stack](README.md#tech-stack)
- [Project Structure](README.md#description)
- [Preview](README.md#preview)
- [Author](README.md#author)
- [License](README.md#license)

## Description

Web Portfolio CMS featuring Clean Architecture and a fully CI/CD pipeline.

## Tech stack

Project build with Clean Architecture in mind.

- Libraries
  - backend
    - Spring
    - Spring security
    - MapStruct
    - robsonkades.UUIDv7
    - LiquiBase
    - Hibernate
    - Swagger
  - backend-testing
    - JUnit
    - AssertJ
    - Mockito
    - RestAssured
  - frontend
    - Angular
    - Swagger CLI generator
  - infrastructure
    - Ansible
    - Docker

- External services
  - PostgreSQL
  - Redis

- frontend
  - Angular
  - Swagger CLI generator

## Project structure

[backend]()

[frontend]()

[infrastructure]()

[docker-compose.yml]()

## Preview

![Home Page Preview](docs/frontend/home_preview.svg)

## Author

Fjodor Tšumakov *(NotEvil)*

## License

The project is under [MIT License](LICENSE)