package com.helloworld

fun main() {
    var list = listOf<String>("1", "2", "3")
    println(list[0])

    for (item in list)
        println(item)

    var newList = mutableListOf<String>()
    newList.add("1")

}