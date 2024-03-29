package jetbrains.buildServer.configs.kotlin.v2017_2.triggers

import jetbrains.buildServer.configs.kotlin.v2017_2.*

/**
 * Perforce Shelve Trigger queues a build when a shelved changelist is created/updated in
 * the VCS Root of the build configuration.
 * 
 * @see perforceShelveTrigger
 */
open class PerforceShelveTrigger() : Trigger() {

    init {
        type = "perforceShelveTrigger"
    }

    constructor(init: PerforceShelveTrigger.() -> Unit): this() {
        init()
    }

    /**
     * The keyword which should be present in Perforce shelved changelist description to trigger the build.
     * Default keyword is `#teamcity`
     */
    var keyword by stringParameter("clDescriptionKeyword")

    override fun validate(consumer: ErrorConsumer) {
        super.validate(consumer)
    }
}


/**
 * Adds [Perforce Build Trigger](https://www.jetbrains.com/help/teamcity/?Perforce+Shelve+Trigger) to build configuration or template
 * @see PerforceShelveTrigger
 */
fun Triggers.perforceShelveTrigger(init: PerforceShelveTrigger.() -> Unit): PerforceShelveTrigger {
    val result = PerforceShelveTrigger(init)
    trigger(result)
    return result
}
