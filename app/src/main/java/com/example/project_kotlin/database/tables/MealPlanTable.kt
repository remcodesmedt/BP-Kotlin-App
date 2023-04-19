package com.example.project_kotlin.database.tables

object MealPlanTable {
    const val TABLE_NAME = "mealplan"

    const val COLUMN_ID = "mealplanid"
    const val COLUMN_START_DATE = "mpstartdate"
    const val COLUMN_END_DATE = "mpenddate"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_START_DATE DATE,
            $COLUMN_END_DATE DATE
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    val COLUMNS_FOR_SELECT = arrayOf(
        "${TABLE_NAME}.${COLUMN_ID}",
        "${TABLE_NAME}.${COLUMN_START_DATE}",
        "${TABLE_NAME}.${COLUMN_END_DATE}",
    )

}