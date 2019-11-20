package com.helloworld

fun main() {
    var width = 2
    var length = 3
   var area = getArea(width, length)
}

fun getArea(width: Int, length: Int): Int {
    return width * length
}