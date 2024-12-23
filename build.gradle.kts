plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2" // For creating a fat JAR
}

group = "me.stay1444"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.ow2.asm:asm:9.5")

    implementation("com.google.code.gson:gson:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "me.stay1444.Main" // Replace with your main class
        )
    }
}

tasks.shadowJar {
    archiveClassifier.set("all")
    configurations = listOf(project.configurations.runtimeClasspath.get())
    mergeServiceFiles() // Merges service provider configuration files

    manifest {
        attributes(
            "Main-Class" to "me.stay1444.Main", // Replace with your main class
            "Permissions" to "all-permissions",
            "Can-Redefine-Classes" to "True",
            "Can-Retransform-Classes" to "True"
        )
    }
}
