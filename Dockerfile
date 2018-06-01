FROM openjdk:8-alpine

WORKDIR /service
ENV JAVA_OPTS ""
ENV SERVICE_PARAMS ""
ADD target/api-gateway-service.jar /service/
CMD java $JAVA_OPTS -jar api-gateway-service.jar $SERVICE_PARAMS
