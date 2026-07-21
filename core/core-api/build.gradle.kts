import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.named<BootJar>("bootJar") {
    enabled = true
    layered {
        enabled.set(true)
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    implementation("com.google.firebase:firebase-admin:9.9.0")

    implementation(project(":core:core-enum"))
    implementation(project(":storage:db-core"))
    implementation(project(":storage:db-s3"))
    implementation(project(":clients:client-kakao"))
    implementation(project(":clients:client-naver"))
    runtimeOnly(project(":support:logging"))
}
