FROM maven:3.6.3-jdk-8-slim AS build
WORKDIR /home/application
COPY . /home/application/
RUN mvn -f /home/application/pom.xml clean install

FROM azul/zulu-openjdk:8
EXPOSE 5000
COPY --from=build /home/application/target/kubernetes.jar kubernetes.jar
ENTRYPOINT ["sh", "-c", "java -jar -Denvironment=dev /kubernetes.jar"]
