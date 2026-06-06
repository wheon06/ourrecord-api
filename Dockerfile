# Build Stage
FROM bellsoft/liberica-openjdk-alpine:21 AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradle.properties ./

COPY clients/client-kakao/build.gradle.kts clients/client-kakao/build.gradle.kts
COPY storage/db-core/build.gradle.kts storage/db-core/build.gradle.kts
COPY ourrecord-api/build.gradle.kts ourrecord-api/build.gradle.kts

RUN mkdir -p core-enum

RUN ./gradlew dependencies --no-daemon

COPY .editorconfig .editorconfig
COPY clients clients
COPY core-enum core-enum
COPY storage storage
COPY ourrecord-api ourrecord-api

RUN ./gradlew build -x test --no-daemon

RUN java -Djarmode=tools -jar ourrecord-api/build/libs/*.jar extract --layers --launcher --destination extracted

# Run stage
FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /app

COPY --from=builder /app/extracted/dependencies/ ./
COPY --from=builder /app/extracted/spring-boot-loader/ ./
COPY --from=builder /app/extracted/snapshot-dependencies/ ./
COPY --from=builder /app/extracted/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
