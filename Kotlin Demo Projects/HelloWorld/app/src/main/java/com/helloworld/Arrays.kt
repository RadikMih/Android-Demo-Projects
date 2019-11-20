package com.helloworld

fun main() {
    val texts = arrayOf("text", "text")

    val bytes: Array<Byte> = arrayOf(5, 4, 1, 2, 3)

    for ((index, value) in bytes.withIndex())
        println("$index $value")

    for (i in 1..3) {
        println("i = $i")
    }

    for (i in 1 until 3) {
        println("i = $i")
    }

    val elements = Array<Byte>(3){0}
}