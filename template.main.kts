#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.21")

import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.copyTo
import kotlin.io.path.div
import kotlin.io.path.name
import kotlin.io.path.readText
import kotlin.io.path.writeText

print("Day ➔ ")
val number = readln().toInt().toString().padStart(length = 2, padChar = '0')

val root: Path = Paths.get(".")
listOf(
    root / "src" / "main" / "kotlin" / "Day__.kt",
    root / "src" / "test" / "kotlin" / "Day__Test.kt",
    root / "src" / "test" / "resources" / "Day__.txt",
    root / "src" / "test" / "resources" / "Day__-sample.txt",
).forEach { it.template(number) }

fun Path.template(number: String) = resolveSibling(name.template(number))
    .let(::copyTo).also(::println)
    .writeText(readText().template(number))

fun String.template(number: String) = replace("__", number)
