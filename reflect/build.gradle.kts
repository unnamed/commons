dependencies {
    api(project(":commons-validation"))

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks {
    test {
	    useJUnitPlatform()
    }
}