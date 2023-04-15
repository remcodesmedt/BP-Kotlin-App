package com.example.project_kotlin.database.testdata

import android.util.Log
import com.example.project_kotlin.database.interfaces.IngredientCategoryInterface
import com.example.project_kotlin.domain.IngredientCategory

object IngredientCategoryMock {
    fun insertMocks(){
        //create all mock ingredientcategory objects, just use id=0, it doesn't matter because it's autoincrement
        val categories = listOf(
            IngredientCategory(0, "Vegetables"),
            IngredientCategory(0, "Fruits"),
            IngredientCategory(0, "Meat"),
            IngredientCategory(0, "Fish"),
            IngredientCategory(0, "Dairy"),
            IngredientCategory(0, "Grains"),
            IngredientCategory(0, "Spices"),
            IngredientCategory(0, "Condiments"),
            IngredientCategory(0, "Beverages"),
            IngredientCategory(0, "Sweets"),
        )

        //insert them into the db
        IngredientCategoryInterface.insertItems(categories)
    }

    fun logMocks(){
        //get ingredientcategories from the db
        val categories = IngredientCategoryInterface.getItems()
        //just log them for now
        for (c in categories) {
            Log.i("nice", "${c.id}: ${c.name}")
        }
    }
}