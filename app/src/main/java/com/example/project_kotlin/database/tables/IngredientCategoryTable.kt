package com.example.project_kotlin.database.tables

object IngredientCategoryTable {
    const val TABLE_NAME = "ingredientcategory"

    const val COLUMN_ID = "ingredientcategoryid"
    const val COLUMN_NAME = "name"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}