package com.github.mrebrahimidev.sample

import java.util.*

fun main() {
    applyAction("hello", "hi") { s: String -> println(s.uuid()) }

    val names = listOf("Alice", "Bob", "Charlie")

    // without Trailing Lambda
    names.forEach({ name -> println(name) })

    // با Trailing Lambda with
    names.forEach { name ->
        println(name)
    }
}

fun String.uuid() =
    UUID.nameUUIDFromBytes(this.toByteArray()).toString()

private fun applyAction(vararg items: String, action: (String) -> Unit) {
    items.forEach { action(it) }
}