package com.example.project_kotlin.domain

class Ingredient(
    var id: Int,
    var name: String,
    var unit: EUnit,
    var category: IngredientCategory
) {
    constructor(id: Int) : this(
        id = id,
        name = "",
        unit = EUnit.ml,
        category = IngredientCategory(0)
    )
}