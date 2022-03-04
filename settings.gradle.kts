rootProject.name = "commons-parent";

// include every subproject with a 'commons-' prefix
includePrefixed("bukkit")
includePrefixed("error")
includePrefixed("functional")
includePrefixed("reflect")
includePrefixed("validation")

fun includePrefixed(name: String) {
    include("commons-$name")
    project(":commons-$name").projectDir = file(name)
}