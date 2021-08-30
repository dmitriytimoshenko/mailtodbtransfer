java
-jar C:\JavaProjects\source\diploma\mailtodbtransfer\target\MailToDBTransfer.jar
-Dlog4j.configuration=file:MailToDBTransfer\ config\log4j.properties
-Dcom.sun.management.jmxremote=true
-Dcom.sun.management.jmxremote.port=1099
-Dcom.sun.management.jmxremote.local.only=false
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath="java_memory_dump.hprof"
pause
