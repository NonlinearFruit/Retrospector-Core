# Retrospector-Core
[![release](https://img.shields.io/github/v/tag/NonlinearFruit/Retrospector-Core)](https://img.shields.io/github/v/tag/NonlinearFruit/Retrospector-Core?label=release)
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

## Publishing a Release

Setup github as a nuget source: `dotnet nuget add source https://nuget.pkg.github.com/NonlinearFruit/index.json -n github -u NonlinearFruit -p GH_TOKEN [--store-password-in-clear-text]`

Set the api key (necessary?): `nuget.exe setApiKey GH_TOKEN -Source https://nuget.pkg.github.com/NonlinearFruit/index.json`

Create nuget release:
```
git add -A
git commit -m "MESSAGE"
git tag -a 0.1.0 -m "TAG_MESSAGE"
git push --tags
dotnet pack --configuration Release
dotnet nuget push "C:\\Projects\\Retrospector-Core\\Retrospector.Core\\bin\\Release\\Retrospector.Core.0.1.0.nupkg" --source "github"
```

source: https://rehansaeed.com/the-easiest-way-to-version-nuget-packages/
