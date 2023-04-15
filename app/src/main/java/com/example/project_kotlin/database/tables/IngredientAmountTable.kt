package com.example.project_kotlin.database.tables

object IngredientAmountTable {
    const val TABLE_NAME = "ingredientamount"

    const val COLUMN_AMOUNT = "amount"
    const val COLUMN_SHOPPINGLIST_ID = "shoppinglistid"
    const val COLUMN_DISH_ID = "dishid"
    const val COLUMN_INGREDIENT_ID = "ingredientid"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_AMOUNT INTEGER,
            $COLUMN_SHOPPINGLIST_ID INTEGER NULL,
                FOREIGN KEY ($COLUMN_SHOPPINGLIST_ID) 
                REFERENCES ${ShoppingListTable.TABLE_NAME}(id) ON DELETE CASCADE,
            
            $COLUMN_DISH_ID INTEGER NULL,
                FOREIGN KEY ($COLUMN_DISH_ID)
                REFERENCES ${DishTable.TABLE_NAME}(id) ON DELETE CASCADE,
                
            $COLUMN_INGREDIENT_ID INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_INGREDIENT_ID)
                REFERENCES ${IngredientTable.TABLE_NAME}(id) ON DELETE CASCADE

        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}