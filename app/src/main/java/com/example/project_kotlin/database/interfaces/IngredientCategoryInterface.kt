package com.example.project_kotlin.database.interfaces

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.tables.IngredientCategoryTable
import com.example.project_kotlin.domain.IngredientCategory

object IngredientCategoryInterface {

    @SuppressLint("Range")
    fun getItems(): List<IngredientCategory> {
        val db = DBHelper.getDB()

        val columns =
            arrayOf(IngredientCategoryTable.COLUMN_ID, IngredientCategoryTable.COLUMN_NAME)
        val cursor =
            db.query(IngredientCategoryTable.TABLE_NAME, columns, null, null, null, null, null)

        val categories = mutableListOf<IngredientCategory>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(IngredientCategoryTable.COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndex(IngredientCategoryTable.COLUMN_NAME))
            categories.add(IngredientCategory(id, name))
        }

        cursor.close()

        return categories
    }

    @SuppressLint("Range")
    fun insertItem(ingredientCategory: IngredientCategory) {
        val db = DBHelper.getDB()

        // Create a ContentValues object to hold the values to insert
        val values = ContentValues().apply {
            put(IngredientCategoryTable.COLUMN_NAME, ingredientCategory.name)
        }

        // Insert the values into the IngredientCategoryTable
        db.insert(IngredientCategoryTable.TABLE_NAME, null, values)
    }

}


