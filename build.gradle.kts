subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    repositories {
        mavenLocal()
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    val repositoryName: String by project
    val snapshotRepository: String by project
    val releaseRepository: String by project

    configure<PublishingExtension> {
        repositories {
            maven {
                val snapshot = version.toString().endsWith("-SNAPSHOT")

                name = repositoryName
                url = if (snapshot) { uri(snapshotRepository) } else { uri(releaseRepository) }
                credentials(PasswordCredentials::class)
            }
        }
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}
