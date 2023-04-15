package com.example.project_kotlin.database.tables

object IngredientTable {
    const val TABLE_NAME = "ingredient"

    const val COLUMN_NAME = "name"
    const val COLUMN_UNIT = "description"

    //TODO FK category
    const val COLUMN_INGREDIENTCATEGORY = "ingredientcategory"



    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT,
            $COLUMN_UNIT TEXT,
            ${COLUMN_INGREDIENTCATEGORY} INTEGER NOT NULL,
                FOREIGN KEY (${COLUMN_INGREDIENTCATEGORY})
                REFERENCES ${IngredientCategoryTable.TABLE_NAME}(id) ON DELETE CASCADE
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
