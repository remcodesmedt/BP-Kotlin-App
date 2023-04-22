package com.example.project_kotlin.domain

class IngredientCategory(
    var id: Int,
    var name: String
) {
    constructor(id: Int) : this(
        id = id,
        name = ""
    )
}

