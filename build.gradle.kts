plugins {
    `java-library`
    `maven-publish`
}

group = "com.bmuschko"
version = "0.3.1-rc1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.asciidoctor:asciidoctorj:2.5.6")
    testImplementation("org.asciidoctor:asciidoctorj:2.5.6")
    testImplementation("org.jsoup:jsoup:1.15.3")
    val junitJupiterVersion = "5.9.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("org.junit-pioneer:junit-pioneer:0.3.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

publishing {
    val sourcesJar by tasks.registering(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}
