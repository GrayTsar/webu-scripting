package com.graytsar

import com.graytsar.definition.LibraryImpl
import com.graytsar.definition.Request
import com.graytsar.definition.RequestImpl
import com.graytsar.definition.WebuDefinition
import com.graytsar.definition.di.OkHttpModule
import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptDiagnostic
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

val request: Request = RequestImpl(type = "chapter", query = "https://allnovelfull.com/my-three-wives-are-beautiful-vampires/chapter-2-awakening.html")

fun main(vararg args: String) {
    val script = File("E:\\code\\IdeaProjects\\webu-scripting\\scripts\\allnovelheaven.webu.kts")

    //(result.valueOrNull().returnValue as ResultValue.Value).value as String
    val result = evalScript(script, request)
    result.reports.forEach {
        if(it.severity == ScriptDiagnostic.Severity.ERROR)
            println("${it.severity} ${it.message} ${it.exception}")
    }
}

private fun evalScript(scriptFile: File, request: Request): ResultWithDiagnostics<EvaluationResult> {
    return BasicJvmScriptingHost().evalWithTemplate<WebuDefinition>(scriptFile.toScriptSource()) {
        constructorArgs(LibraryImpl(OkHttpModule.okHttpClient), request)
    }
}