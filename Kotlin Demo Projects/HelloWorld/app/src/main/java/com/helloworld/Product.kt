package com.helloworld

class Product(
    var category: String,
    var name: String,
    var price: Double,
    var discountPercent: Byte)
{


    fun calculateFinalPrice(): Double {
        val discount = price * discountPercent / 100
        return price - discount
    }
}

fun Product.getFinalPrice(): Double {
    return this.calculateFinalPrice()
}