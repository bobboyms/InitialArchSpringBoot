#!/bin/bash
echo VALUE: $VALUE
echo OPT: $JAVA_TOOL_OPTIONS
java -Dspring.profiles.active=dev -jar application.jar