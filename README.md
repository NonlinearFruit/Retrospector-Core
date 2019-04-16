# Retrospector-Core
[![build](https://img.shields.io/travis/NonlinearFruit/Retrospector-Core.svg)](https://travis-ci.org/NonlinearFruit/Retrospector-Core)
[![coverage](https://img.shields.io/codecov/c/gh/NonlinearFruit/Retrospector-Core.svg)](https://codecov.io/gh/NonlinearFruit/Retrospector-Core)

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
