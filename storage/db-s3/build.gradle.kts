dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(platform("software.amazon.awssdk:bom:2.25.66"))
    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk:url-connection-client")
}
