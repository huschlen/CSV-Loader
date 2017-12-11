## Student Information System

## Design

This is a Spring Boot application and includes a main class, configuration class, entity classes, service classes, data access object classes and test classes.

## Build

- Install MySQL and set password for root as "root"
- Install Node and env-cmd
- gradle flywayClean -Dflyway.password=PASSWORD
- gradle flywayMigrate -Dflyway.password=PASSWORD
- gradle -Dfile.encoding=utf-8 build
- gradle bootRun -Pargs="csvUrl cleanImport(Y or N)" (e.g. gradle bootRun –Pargs=“https://raw.githubusercontent.com/huschlen/fbl-homework/master/1.csv Y”)
