package com.example.project_kotlin.database.tables

object ShoppingListTable {
    const val TABLE_NAME = "shoppinglist"

    const val COLUMN_ID = "shoppinglistid"
    const val COLUMN_NAME = "shoppinglistname"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"


    val COLUMNS_FOR_SELECT = arrayOf(
        "$TABLE_NAME.$COLUMN_ID",
        COLUMN_NAME,

        IngredientAmountTable.COLUMN_ID,
        IngredientAmountTable.COLUMN_AMOUNT,
        "${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_SHOPPINGLIST_ID}",
        "${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_INGREDIENT_ID}",

        "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_ID}",
        IngredientTable.COLUMN_NAME,
        IngredientTable.COLUMN_UNIT,
        "${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_INGREDIENTCATEGORYID}",

        "${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_ID}",
        IngredientCategoryTable.COLUMN_NAME
    )

    val JOIN_CLAUSE = """
            ${ShoppingListTable.TABLE_NAME}.${ShoppingListTable.COLUMN_ID} 
            = ${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_SHOPPINGLIST_ID}
           AND
            ${IngredientAmountTable.TABLE_NAME}.${IngredientAmountTable.COLUMN_INGREDIENT_ID} 
            = ${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_ID}
           AND
            ${IngredientTable.TABLE_NAME}.${IngredientTable.COLUMN_INGREDIENTCATEGORYID} 
            = ${IngredientCategoryTable.TABLE_NAME}.${IngredientCategoryTable.COLUMN_ID}
        """
}