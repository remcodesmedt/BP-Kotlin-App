package com.example.project_kotlin.database.testdata

import android.util.Log
import com.example.project_kotlin.database.interfaces.IngredientCategoryInterface
import com.example.project_kotlin.database.interfaces.IngredientInterface
import com.example.project_kotlin.domain.EUnit
import com.example.project_kotlin.domain.Ingredient
import com.example.project_kotlin.domain.IngredientCategory

object IngredientMock : MockingInterface{
    override fun insertMocks(){
        //create all mock ingredient objects, just use id=0, it doesn't matter because it's autoincrement
        val ingredients = listOf(
            Ingredient(0, "kweetni", EUnit.g, IngredientCategory(2, "")), //only id matters really
            Ingredient(0, "kweetni part 2", EUnit.ml, IngredientCategory(5, "")),
            Ingredient(0, "kweetni part 500", EUnit.g, IngredientCategory(6, ""))
        )

        //insert them into the db
        IngredientInterface.insertItems(ingredients)
    }

    override fun logMocks(){
        //get ingredients from the db
        val ingredients = IngredientInterface.getItems()
        //just log them for now
        for (c in ingredients) {
            Log.i("nice", "${c.id}: ${c.name}, unit: ${c.unit}, category: ${c.category.id} - ${c.category.name}")
        }
    }
}