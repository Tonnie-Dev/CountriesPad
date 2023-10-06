package com.uxstate.countriespad.di

class Product private constructor(
    val name: String,
    val price: Double,
    val description: String
) {
    // Define a Builder class as a nested class
    class Builder {
        private var name = ""
        private var price = 0.0
        private var description = ""

        // Add methods to set the optional parameters
        fun setName(name: String): Builder {
            this.name = name
            return this
        }

        fun setPrice(price: Double): Builder {
            this.price = price
            return this
        }

        fun setDescription(description: String): Builder {
            this.description = description
            return this
        }

        // Create a build() method to construct the object
        fun build(): Product {
            return Product(name, price, description)
        }
    }
}
