package com.example.project_kotlin.domain

class Dish(
    val name: String,
    val description: String?,
    val image: ByteArray,
    val preparationTime: Int?,
    val servings: Int,
    val instructions: List<String>?, //wordt JSON in db
    val ingredients: List<IngredientAmount>,
    val tags: List<Tag>?
)
