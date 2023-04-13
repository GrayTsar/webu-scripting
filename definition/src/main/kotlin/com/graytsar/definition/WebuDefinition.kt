package com.graytsar.definition

import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

@KotlinScript(
    fileExtension = "webu.kts",
    compilationConfiguration = WebuConfiguration::class
)
abstract class WebuDefinition(
    private val library: Library,
    private val request: Request
): Library by library, Request by request

object WebuConfiguration: ScriptCompilationConfiguration({
    defaultImports(Library::class, Request::class)

    jvm {
        dependenciesFromCurrentContext(wholeClasspath = true)
    }

    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }
})