# For Java 8, try this
FROM openjdk:8

# Refer to Maven build -> finalName
ARG JAR_FILE=target/ecom-cart-svc-1.0.0.jar

# cd /opt/app
WORKDIR /opt/app

# cp JAR_FILE /opt/app/ecom-cart-svc.jar
COPY ${JAR_FILE} ecom-cart-svc.jar

# java -jar /opt/app/gateway-server.jar
ENTRYPOINT ["java","-jar","ecom-cart-svc.jar"]
