pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ExchangeRatesApp"
include(":app")
include(":base")
include(":uikit")
include(":logger")
include(":i_currencies")
include(":domain")
include(":network")
include(":data")
