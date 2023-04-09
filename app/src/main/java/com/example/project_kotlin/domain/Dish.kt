package com.example.project_kotlin.domain

class Dish(
    var name: String,
    var description: String?,
    var image: ByteArray,
    var preparationTime: Int?,
    var servings: Int,
    var instructions: List<String>?, //wordt JSON in db
    var ingredients: List<IngredientAmount>,
    var tags: List<Tag>?
)
