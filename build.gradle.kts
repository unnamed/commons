subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    repositories {
        mavenLocal()
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                val snapshot = version.toString().endsWith("-SNAPSHOT")
                val typeName = if (snapshot) { "snapshots" } else { "releases" }
                val repoName = "unnamed-$typeName"

                name = repoName
                url = uri("https://repo.unnamed.team/repository/$repoName")
                credentials {
                    username = System.getenv("REPO_USER")
                    password = System.getenv("REPO_PASSWORD")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}
