val ktor_version: String by project
val kotlin_version: String by project
val exposed_version: String by project
val logback_version: String by project
val koin_version: String by project
val koin_ksp_version: String by project

val majorVersion = 1
val minorVersion = 0
val patchVersion = "0-SNAPSHOT"
version = "${majorVersion}.${minorVersion}.${patchVersion}"

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.5"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    id("com.google.devtools.ksp") version "1.6.10-1.0.2" apply true
    application
}

group = "ru.tinkoff.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")

    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-annotations:$koin_ksp_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-apache-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    ksp("io.insert-koin:koin-ksp-compiler:$koin_ksp_version")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")


    implementation("ch.qos.logback.contrib:logback-json-classic:$logback_version")
    implementation("ch.qos.logback.contrib:logback-jackson:$logback_version")

    implementation("io.github.microutils:kotlin-logging:2.1.21")

    implementation("net.bis5.mattermost4j:mattermost4j-core:0.24.0")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation(group = "org.postgresql", name = "postgresql", version = "42.5.1")
    implementation("com.zaxxer:HikariCP:5.0.1")
    testImplementation(kotlin("test"))

    implementation("io.rest-assured:rest-assured:3.3.0")
    implementation("org.hibernate:hibernate-core:6.2.7.Final")

}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("ApplicationKt")
}