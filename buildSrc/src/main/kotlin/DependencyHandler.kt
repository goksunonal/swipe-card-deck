import org.gradle.api.artifacts.dsl.DependencyHandler

private const val IMPLEMENTATION = "implementation"
private const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
private const val TEST_IMPLEMENTATION = "testImplementation"

fun DependencyHandler.typeImplementation(
    implementationType: String = IMPLEMENTATION,
    dependencyName: String,
    version: String
) {
    val fullDependency = "${dependencyName}:${version}"
    add(implementationType, fullDependency)
}

fun DependencyHandler.implementation(dependencyName: String, version: String) {
    val fullDependency = "${dependencyName}:${version}"
    add(IMPLEMENTATION, fullDependency)
}

fun DependencyHandler.androidTestImplementation(dependencyName: String, version: String) {
    val fullDependency = "${dependencyName}:${version}"
    add(ANDROID_TEST_IMPLEMENTATION, fullDependency)
}

fun DependencyHandler.testImplementation(dependencyName: String, version: String) {
    val fullDependency = "${dependencyName}:${version}"
    add(TEST_IMPLEMENTATION, fullDependency)
}