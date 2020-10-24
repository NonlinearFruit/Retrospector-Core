# Retrospector-Core
[![build](https://img.shields.io/github/workflow/status/NonlinearFruit/Retrospector-Core/.NET%20Core)](https://github.com/NonlinearFruit/Retrospector-Core/actions?query=workflow%3A".NET+Core")
[![coverage](https://img.shields.io/codecov/c/gh/NonlinearFruit/Retrospector-Core.svg)](https://codecov.io/gh/NonlinearFruit/Retrospector-Core)
[![maintainability](https://api.codeclimate.com/v1/badges/74c2c9f1864e1ec37987/maintainability)](https://codeclimate.com/github/NonlinearFruit/Retrospector-Core/maintainability)

This is the core business logic of [Retrospector](https://github.com/NonlinearFruit/Retrospector). Reading or writing data, calculating statistics and achievements, and processing search queries happens here!

## Architecture

```
     Request                                  Presenter
        +                                         ^
        |                                         |
        |                                         |
        |            Retrospector-JavaFx          |
        |                                         |
        |                                         |
 +-----------------------------------------------------------+
        |                 Boundary                |
 +-----------------------------------------------------------+
        |                                         |
        v                                         +
Request Disseminator+--------------------->Use Case
                                            +  ^
                                            |  |               Retrospector-Core
                                            |  |
                                            |  |
                                            |  |
                                            |  |
                                            |  |
                                            |  |
+------------------------------------------------------------+
                         DataGateway        |  |
+------------------------------------------------------------+
                                            |  |
                                            |  |
                     Retrospector-Hsqldb    |  |
                                            |  |
                                            |  |
                                            v  +
                                         DataGateway
```
source: [ascii flow](http://asciiflow.com)
