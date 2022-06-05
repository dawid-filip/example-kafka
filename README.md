# example-kafka

#### Windows

Tested with `kafka-3.2.0` -> https://www.apache.org/dyn/closer.cgi?path=/kafka/3.2.0/kafka_2.13-3.2.0.tgz

<br>

Modificate kafka properties files:
- `..\kafka\config\zookeeper.properties` set `dataDir=..\kafka\zookeeper-data`
- `..\kafka\config\server.properties` set `log.dirs=..\kafka\kafka-data`

<br>

Run each script in separate windows terminal (cmd):
- `.\..\..\kafka\bin\windows\zookeeper-server-start.bat .\..\..\kafka\config\zookeeper.properties`
- `.\..\..\kafka\bin\windows\kafka-server-start.bat .\..\..\kafka\config\server.properties`

<br>

Run `example-kafka` application and execute endpoints each in separate tab:
- http://localhost:8080/api/v1/kafka/generate-message
- http://localhost:8080/api/v1/kafka/generate-message-with-callback