# filepath: /Users/samvi/Documents/GitHub/CAB-System/Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . /app

RUN apt-get update && apt-get install -y netcat

RUN javac -cp ".:lib/*" -d bin $(find src -name "*.java")

CMD ["java", "-cp", ".:lib/*:bin", "services.MainApplication"]