tasks.register("run") {
    group = "application"
    description = "Runs the JavaFX application in the app module."
    dependsOn(":app:run")
}
