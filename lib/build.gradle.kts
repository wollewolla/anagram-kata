plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val junitVersion: String by project
val kotestVersion: String by project
val kotlinVersion: String by project
val mockkVersion: String by project

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation(platform("io.kotest:kotest-bom:$kotestVersion"))
    testImplementation("io.kotest:kotest-assertions-core")
    testImplementation("io.kotest:kotest-assertions-json")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.8.10")
        }
    }
}
