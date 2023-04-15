package com.example.project_kotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.project_kotlin.database.tables.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DishTable.CREATE_TABLE)
        db?.execSQL(IngredientTable.CREATE_TABLE)
        db?.execSQL(IngredientAmountTable.CREATE_TABLE)
        db?.execSQL(IngredientCategoryTable.CREATE_TABLE)
        db?.execSQL(MealPlanTable.CREATE_TABLE)
        db?.execSQL(ShoppingListTable.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DishTable.DROP_TABLE)
        db?.execSQL(IngredientTable.DROP_TABLE)
        db?.execSQL(IngredientAmountTable.DROP_TABLE)
        db?.execSQL(IngredientCategoryTable.DROP_TABLE)
        db?.execSQL(MealPlanTable.DROP_TABLE)
        db?.execSQL(ShoppingListTable.DROP_TABLE)
        onCreate(db)
    }
}
