# bodi-drillbit

Creates an Apache Drill Drillbit. A BODI project chatbot can connect to it and query data.

Data files such as csv files can be stored in the [resources](src/main/resources) folder to be available for the 
drillbit in the classpath file system storage.

The default url for the drillbit is localhost. It can be modified in [configuration.properties](src/main/resources/configuration.properties)

## Requirements

- Java 8
- Maven

## Install & Run

```bash
mvn clean compile
```

```bash
mvn exec:java -Dexec.mainClass="DrillbitConnection"
```

## Connect to the drillbit with JDBC

From the chatbot (or any application that wants to connect to the drillbit to query data), connect to 
`jdbc:drill:drillbit=localhost`. Replace 'localhost' by the url you used
(see [SqlEngine.java](https://github.com/opendata-for-all/bodi-generator/blob/main/src/main/java/com/xatkit/bot/sql/SqlEngine.java)
as an example connection in the BODI chatbots)

