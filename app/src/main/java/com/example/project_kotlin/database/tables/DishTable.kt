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

    val COLUMNS_FOR_SELECT = arrayOf(
        "$TABLE_NAME.$COLUMN_ID",
        COLUMN_NAME,
        COLUMN_DESCRIPTION,
        COLUMN_IMAGE,
        COLUMN_PREPARATION_TIME,
        COLUMN_SERVINGS,
        COLUMN_INSTRUCTIONS,

        //ingredientamount
        "${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_ID}",
        "${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_AMOUNT}",
        "${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_DISH_ID}", //=id of this table
        "${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_INGREDIENT_ID}", //=id of table ingredient

        //ingredient
        "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_ID}",
        "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_NAME}",
        "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_UNIT}",
        "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_INGREDIENTCATEGORYID}", //=id of table ingredientcategory

        //ingredientCategory
        "${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_ID}",
        "${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_NAME}"
    )

    val JOIN_CLAUSE = """
        ${TABLE_NAME}.${COLUMN_ID} 
            = ${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_DISH_ID}
        AND
        ${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_INGREDIENT_ID} 
            = ${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_ID}
        AND
        ${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_INGREDIENTCATEGORYID} 
            = ${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_ID}
    """
}

