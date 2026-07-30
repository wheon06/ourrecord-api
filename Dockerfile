# Build Stage
FROM bellsoft/liberica-openjdk-alpine:21 AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradle.properties ./

COPY support/logging/build.gradle.kts support/logging/build.gradle.kts
COPY clients/client-naver/build.gradle.kts clients/client-naver/build.gradle.kts
COPY clients/client-kakao/build.gradle.kts clients/client-kakao/build.gradle.kts
COPY storage/db-s3/build.gradle.kts storage/db-s3/build.gradle.kts
COPY storage/db-core/build.gradle.kts storage/db-core/build.gradle.kts
COPY core/core-api/build.gradle.kts core/core-api/build.gradle.kts

RUN mkdir -p core/core-enum

RUN ./gradlew dependencies --no-daemon

COPY .editorconfig .editorconfig
COPY support support
COPY clients clients
COPY core/core-enum core/core-enum
COPY storage storage
COPY core/core-api core/core-api

RUN ./gradlew build -x test --no-daemon

RUN java -Djarmode=tools -jar core/core-api/build/libs/*.jar extract --layers --launcher --destination extracted

# Run stage
FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /app

COPY --from=builder /app/extracted/dependencies/ ./
COPY --from=builder /app/extracted/spring-boot-loader/ ./
COPY --from=builder /app/extracted/snapshot-dependencies/ ./
COPY --from=builder /app/extracted/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
