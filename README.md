# Single Page Application development with React and Vert.x

[![OpenAPI Validation](http://validator.swagger.io/validator?url=https://raw.githubusercontent.com/NiccoMlt/single-page-react-vertx-howto/master/src/main/resources/api.yaml)](https://editor.swagger.io/?url=https://raw.githubusercontent.com/NiccoMlt/single-page-react-vertx-howto/master/src/main/resources/api.yaml)
[![Build Status](https://travis-ci.com/NiccoMlt/single-page-react-vertx-howto.svg?branch=master)](https://travis-ci.com/NiccoMlt/single-page-react-vertx-howto)

|              | Framework | Language | Style | 
| ------------ | --------- | -------- | ----- |
| **Backend**  | [![Vert.x Version](https://img.shields.io/badge/vert.x-3.8.3-purple.svg)](https://vertx.io) | [![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.20-blue.svg?logo=kotlin)](https://kotlinlang.org/docs/reference/whatsnew13.html) | [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/) |
| **Frontend** | [![React.JS Version](https://img.shields.io/badge/React.JS-16.10.2-blue.svg?logo=react)](https://reactjs.org/) | [![Typescript Version](https://img.shields.io/badge/typescript-3.6.4-blue.svg?logo=typescript)](https://www.typescriptlang.org/) | [![Code Style](https://badgen.net/badge/code%20style/airbnb/ff5a5f?icon=airbnb)](https://github.com/airbnb/javascript) |

This repository contains a demo of a React Single Page Application (SPA) realized in Typescript and deployed with a Vert.x verticle realized in Kotlin.

The repository is Gradle project importable in Intellij IDEA;
the [frontend sourceSet](./src/main/frontend) is automatically built and linked by Gradle, but it can be also opened separately as a VS Code project.
