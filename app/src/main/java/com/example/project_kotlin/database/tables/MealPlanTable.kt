package com.example.project_kotlin.database.tables

object MealPlanTable {
    const val TABLE_NAME = "mealplan"

    const val COLUMN_ID = "mealplanid"
    const val COLUMN_START_DATE = "mpstartdate"
    const val COLUMN_END_DATE = "mpenddate"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_START_DATE TEXT,
            $COLUMN_END_DATE TEXT
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}