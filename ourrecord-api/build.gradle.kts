dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    implementation(project(":core-enum"))
    implementation(project(":storage:db-core"))
    implementation(project(":storage:db-s3"))
    runtimeOnly(project(":auth"))
    runtimeOnly(project(":clients:client-kakao"))
}
