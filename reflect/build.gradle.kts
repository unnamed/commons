dependencies {
    api(project(":commons-validation"))

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks {
    test {
	    useJUnitPlatform()
    }
}