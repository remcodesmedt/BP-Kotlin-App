package com.example.project_kotlin.domain

class Dish(
    var id: Int,
    var name: String,
    var description: String?,
    var image: ByteArray,
    var preparationTime: Int?,
    var servings: Int,
    var instructions: List<String>?, //wordt JSON in db
    var ingredients: List<IngredientAmount>,
){
    constructor(id: Int) : this(
        id = id,
        name = "",
        description = null,
        image = byteArrayOf(),
        preparationTime = null,
        servings = 0,
        instructions = null,
        ingredients = emptyList()
    )
}