package com.example.project_kotlin.domain

class IngredientAmount(
    var id: Int,
    var ingredient: Ingredient,
    var amount: Double
) {
    constructor(id: Int) : this(
        id = id,
        ingredient = Ingredient(0),
        amount = 0.0
    )
}