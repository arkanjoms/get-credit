FROM openjdk:8-jre-slim

ENV JAVA_OPTS="-Xms96m -Xmx96m -XX:MetaspaceSize=96m -XX:MaxMetaspaceSize=96m"

ADD target/get-credit-api.jar /app/
RUN groupadd -r getcredit \
    && useradd --no-log-init -g getcredit getcredit \
    && chown -R getcredit:getcredit  /app
USER getcredit
WORKDIR /app

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -server -XX:+UseContainerSupport \
    ${JAVA_OPTS} -jar get-credit-api.jar