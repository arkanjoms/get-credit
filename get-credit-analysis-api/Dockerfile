FROM openjdk:8-jre-slim

ENV JAVA_OPTS="-Xms96m -Xmx96m -XX:MetaspaceSize=96m -XX:MaxMetaspaceSize=96m"

ADD target/get-credit-analysis-api.jar /app/
RUN groupadd -r credit \
    && useradd --no-log-init -g credit credit \
    && chown -R credit:credit  /app
USER credit
WORKDIR /app

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -server -XX:+UseContainerSupport \
    ${JAVA_OPTS} -jar get-credit-analysis-api.jar