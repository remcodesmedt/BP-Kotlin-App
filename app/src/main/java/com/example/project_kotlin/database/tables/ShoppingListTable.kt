package com.example.project_kotlin.database.tables

object ShoppingListTable {
    const val TABLE_NAME = "shoppinglist"

    const val COLUMN_NAME = "name"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT,
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}