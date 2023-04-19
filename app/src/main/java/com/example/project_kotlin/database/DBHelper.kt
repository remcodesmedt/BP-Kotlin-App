package com.example.project_kotlin.database

import android.util.Log
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.project_kotlin.database.tables.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "DishDatabase.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("nice", "creating tables")
        db?.execSQL(IngredientCategoryTable.CREATE_TABLE)
        db?.execSQL(IngredientTable.CREATE_TABLE)
        db?.execSQL(ShoppingListTable.CREATE_TABLE)
        db?.execSQL(DishTable.CREATE_TABLE)
        db?.execSQL(IngredientAmountTable.CREATE_TABLE)
        db?.execSQL(MealPlanTable.CREATE_TABLE)
        db?.execSQL(MealPlanDishTable.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        dropAll(db)
        onCreate(db)
    }

    fun dropAll(db: SQLiteDatabase?){
        Log.i("nice", "dropping tables")
        db?.execSQL(IngredientCategoryTable.DROP_TABLE)
        db?.execSQL(IngredientTable.DROP_TABLE)
        db?.execSQL(ShoppingListTable.DROP_TABLE)
        db?.execSQL(DishTable.DROP_TABLE)
        db?.execSQL(IngredientAmountTable.DROP_TABLE)
        db?.execSQL(MealPlanTable.DROP_TABLE)
        db?.execSQL(MealPlanDishTable.DROP_TABLE)
    }
}


object DBHelper {
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DatabaseHelper

    fun init(context: Context) {
        dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase

        //TODO uncomment, just drop for development to test
        dbHelper.dropAll(db)
        dbHelper.onCreate(db)
    }

    fun getDB(): SQLiteDatabase {
        return db
    }
}
