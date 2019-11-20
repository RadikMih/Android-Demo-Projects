package com.helloworld

fun main() {
    val a = 10 // statement
    val b = 20f

    var max = if (a > b) {
        a
    } else {
        b
    }

    val result =
        when {
            a > b -> "a"
            b > a -> "b"
            else -> "a == b"
        }

    val x = 3
    var text =
        when (x) {
            1, 5, 6, 8 -> "a"
            2, 0, 9 -> "b"
            3 -> "x"
            else -> "Invalid number!"
        }

    val groupA = 12..18
    val groupB = 19..35

    val ageGroup =
        when (x) {
            in groupA -> "Group A"
            in groupB -> "Group B"
            else -> "unknown"
        }
}