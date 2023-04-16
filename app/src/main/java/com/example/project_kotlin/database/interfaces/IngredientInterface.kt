package com.example.project_kotlin.database.interfaces

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.tables.IngredientCategoryTable
import com.example.project_kotlin.database.tables.IngredientTable
import com.example.project_kotlin.domain.EUnit
import com.example.project_kotlin.domain.Ingredient
import com.example.project_kotlin.domain.IngredientCategory

object IngredientInterface {

    @SuppressLint("Range")
    fun getItems(): List<Ingredient> {
        val db = DBHelper.getDB()

        val columns = arrayOf(
            IngredientTable.COLUMN_ID,
            "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_NAME}",
            IngredientTable.COLUMN_UNIT,
            "${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_NAME} AS category_name",
            "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_INGREDIENTCATEGORYID}"
        )
        val joinClause = "${IngredientTable.TABLE_NAME} INNER JOIN ${IngredientCategoryTable.TABLE_NAME} " +
                "ON ${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_INGREDIENTCATEGORYID} = " +
                "${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_ID}"
        val cursor = db.query(joinClause, columns, null, null, null, null, null)

        val ingredients = mutableListOf<Ingredient>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(IngredientTable.COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_NAME))
            val unitString = cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_UNIT))
            val categoryid = cursor.getInt(cursor.getColumnIndex(IngredientTable.COLUMN_INGREDIENTCATEGORYID))
            val categoryname = cursor.getString(cursor.getColumnIndex("category_name"))

            var unit: EUnit
            when (unitString) {
                "g" -> unit = EUnit.g
                "ml" -> unit = EUnit.ml
                else -> unit = EUnit.g
            }

            ingredients.add(Ingredient(id, name, unit, IngredientCategory(categoryid, categoryname)))
        }

        cursor.close()

        return ingredients
    }


    fun insertItem(ingredient: Ingredient){
        val db = DBHelper.getDB()

        // Create a ContentValues object to hold the values to insert
        val values = ContentValues().apply {
            put(IngredientTable.COLUMN_NAME, ingredient.name)
            put(IngredientTable.COLUMN_UNIT, ingredient.unit.toString())
            put(IngredientTable.COLUMN_INGREDIENTCATEGORYID, ingredient.category.id)
        }

        // Insert the values into the IngredientCategoryTable
        db.insert(IngredientTable.TABLE_NAME, null, values)
    }

}