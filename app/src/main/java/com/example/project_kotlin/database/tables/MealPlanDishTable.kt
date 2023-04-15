package com.example.project_kotlin.database.tables

object MealPlanDishTable {
    const val TABLE_NAME = "mealplan_dish"

    const val COLUMN_MEALPLAN_ID = "mealplan_id"
    const val COLUMN_DISH_ID = "dish_id"
    const val COLUMN_DAY_OF_WEEK = "day_of_week"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_MEALPLAN_ID INTEGER,
            $COLUMN_DISH_ID INTEGER,
            $COLUMN_DAY_OF_WEEK INTEGER,
            FOREIGN KEY ($COLUMN_MEALPLAN_ID) REFERENCES ${MealPlanTable.TABLE_NAME}(id),
            FOREIGN KEY ($COLUMN_DISH_ID) REFERENCES ${DishTable.TABLE_NAME}(id),
            UNIQUE ($COLUMN_MEALPLAN_ID, $COLUMN_DAY_OF_WEEK)
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
