plugins {
    java
    application
    kotlin("jvm") version "1.9.0"
    id("org.openjfx.javafxplugin").version("0.0.13")

}

group = "edu.austral.dissis.chess"
version = "2.0.1"

repositories {
//    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-ui")
        credentials {
            username = project.properties["github.user"] as String
            password = project.properties["github.token"] as String
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("edu.austral.dissis.chess:chess-ui:2.0.1")
    implementation("edu.austral.dissis.chess:simple-client-server:1.2.0")
    implementation("io.netty:netty-all:4.1.100.Final")
    api ("com.fasterxml.jackson.core:jackson-core:2.15.3")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
    implementation ("ch.qos.logback:logback-classic:1.4.11")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

javafx {
    version = "18"
    modules = listOf("javafx.graphics")
}

application {
    // Define the main class for the application.
    mainClass.set("edu.austral.dissis.chess.AppKt")
}
