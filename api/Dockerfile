FROM maven:3.8.5-openjdk-11
WORKDIR /app
COPY . /app
RUN mvn clean
RUN mvn install -DskipTests
ENTRYPOINT mvn spring-boot:run -DskipTests -D"spring-boot.run.profiles"=docker