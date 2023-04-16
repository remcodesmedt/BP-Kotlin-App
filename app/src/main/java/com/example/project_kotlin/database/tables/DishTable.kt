package com.example.project_kotlin.database.tables

import com.example.project_kotlin.domain.Dish

object DishTable {
    const val TABLE_NAME = "dish"

    const val COLUMN_ID = "dishid"
    const val COLUMN_NAME = "dishname"
    const val COLUMN_DESCRIPTION = "dishdescription"
    const val COLUMN_IMAGE = "dishimage"
    const val COLUMN_PREPARATION_TIME = "preparationTime"
    const val COLUMN_SERVINGS = "servings"
    const val COLUMN_INSTRUCTIONS = "instructions"


    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT,
            $COLUMN_DESCRIPTION TEXT,
            $COLUMN_IMAGE BLOB,
            $COLUMN_PREPARATION_TIME INTEGER,
            $COLUMN_SERVINGS INTEGER,
            $COLUMN_INSTRUCTIONS TEXT
        )
    """


    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}

