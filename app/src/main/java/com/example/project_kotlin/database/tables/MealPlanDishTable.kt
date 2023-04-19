package com.example.project_kotlin.database.tables

object MealPlanDishTable {
    const val TABLE_NAME = "mealplan_dish"

    const val COLUMN_ID = "mealplandishid"
    const val COLUMN_MEALPLAN_ID = "mealplanid"
    const val COLUMN_DISH_ID = "dishid"
    const val COLUMN_DAY_OF_WEEK = "dayofweek"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_MEALPLAN_ID INTEGER,
            $COLUMN_DISH_ID INTEGER,
            $COLUMN_DAY_OF_WEEK INTEGER,
            FOREIGN KEY ($COLUMN_MEALPLAN_ID) REFERENCES ${MealPlanTable.TABLE_NAME}(${MealPlanTable.COLUMN_ID}),
            FOREIGN KEY ($COLUMN_DISH_ID) REFERENCES ${DishTable.TABLE_NAME}(${DishTable.COLUMN_ID}),
            UNIQUE ($COLUMN_MEALPLAN_ID, $COLUMN_DAY_OF_WEEK)
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    val COLUMNS_FOR_SELECT = arrayOf(
        COLUMN_ID,
        "$TABLE_NAME.$COLUMN_MEALPLAN_ID", //fk naar mealplantable
        "$TABLE_NAME.$COLUMN_DISH_ID", //fk naar dishtable
        COLUMN_DAY_OF_WEEK,
    )
        .plus(MealPlanTable.COLUMNS_FOR_SELECT)
        .plus(DishTable.COLUMNS_FOR_SELECT)

    val JOIN_CLAUSE = """
        ${MealPlanTable.TABLE_NAME}.${MealPlanTable.COLUMN_ID}
               = ${MealPlanDishTable.TABLE_NAME}.$COLUMN_MEALPLAN_ID
        AND
        ${DishTable.TABLE_NAME}.${DishTable.COLUMN_ID}
               = ${MealPlanDishTable.TABLE_NAME}.$COLUMN_DISH_ID
        AND
        ${DishTable.JOIN_CLAUSE}
    """
}
