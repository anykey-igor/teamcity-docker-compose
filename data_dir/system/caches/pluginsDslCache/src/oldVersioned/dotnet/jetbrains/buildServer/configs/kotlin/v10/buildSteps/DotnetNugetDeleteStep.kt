package jetbrains.buildServer.configs.kotlin.v10.buildSteps

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * A [dotnet nuget delete step](https://github.com/JetBrains/teamcity-dotnet-plugin) to run .NET CLI command
 * 
 * @see dotnetNugetDelete
 */
open class DotnetNugetDeleteStep : BuildStep {
    constructor(init: DotnetNugetDeleteStep.() -> Unit = {}, base: DotnetNugetDeleteStep? = null): super(base = base as BuildStep?) {
        type = "dotnet"
        param("command", "nuget-delete")
        init()
    }

    /**
     * Specify the server URL.
     */
    var serverUrl by stringParameter("nuget.packageSource")

    /**
     * Specify the package name and version separated by a space.
     */
    var packageId by stringParameter("nuget.packageId")

    /**
     * Specify the API key to access the NuGet packages feed.
     */
    var apiKey by stringParameter("secure:nuget.apiKey")

    /**
     * Enter additional command line parameters for dotnet nuget delete.
     */
    var args by stringParameter()

    /**
     * Specify logging verbosity
     * @see Verbosity
     */
    var logging by enumParameter<Verbosity>("verbosity")

    /**
     * .NET SDK versions separated by semicolon to be required on agents.
     */
    var sdk by stringParameter("required.sdk")

    /**
     * Specifies which Docker image platform will be used to run this build step.
     */
    var dockerImagePlatform by enumParameter<ImagePlatform>("plugin.docker.imagePlatform", mapping = ImagePlatform.mapping)

    /**
     * If enabled, "docker pull [image][dockerImage]" will be run before docker run.
     */
    var dockerPull by booleanParameter("plugin.docker.pull.enabled", trueValue = "true", falseValue = "")

    /**
     * Specifies which Docker image to use for running this build step. I.e. the build step will be run inside specified docker image, using 'docker run' wrapper.
     */
    var dockerImage by stringParameter("plugin.docker.imageId")

    /**
     * Additional docker run command arguments
     */
    var dockerRunParameters by stringParameter("plugin.docker.run.parameters")

    /**
     * Logging verbosity
     */
    enum class Verbosity {
        Quiet,
        Minimal,
        Normal,
        Detailed,
        Diagnostic;

    }
    /**
     * Docker image platforms
     */
    enum class ImagePlatform {
        Any,
        Linux,
        Windows;

        companion object {
            val mapping = mapOf<ImagePlatform, String>(Any to "", Linux to "linux", Windows to "windows")
        }

    }
}


/**
 * Adds a [dotnet nuget delete step](https://github.com/JetBrains/teamcity-dotnet-plugin) to run .NET CLI command
 * @see DotnetNugetDeleteStep
 */
fun BuildSteps.dotnetNugetDelete(base: DotnetNugetDeleteStep? = null, init: DotnetNugetDeleteStep.() -> Unit = {}) {
    step(DotnetNugetDeleteStep(init, base))
}
