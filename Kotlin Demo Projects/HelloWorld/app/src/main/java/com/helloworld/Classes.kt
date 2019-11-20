package com.helloworld

fun main() {
    var product = Product("1", "Phone", 10.0, 1)
    var products = arrayOf(product)

    for (item in products) {
        println(product.name)
    }


    var carDirection: Directions = Directions.SOUTH
    if (carDirection == Directions.SOUTH) {
        println("true")
    }

}