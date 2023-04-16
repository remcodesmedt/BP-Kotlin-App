package com.example.project_kotlin.database.tables

object IngredientTable {
    const val TABLE_NAME = "ingredient"

    const val COLUMN_ID = "ingredientid"
    const val COLUMN_NAME = "ingredientname"
    const val COLUMN_UNIT = "unit"

    const val COLUMN_INGREDIENTCATEGORYID = "ingredientcategory"



    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT,
            $COLUMN_UNIT TEXT,
            ${COLUMN_INGREDIENTCATEGORYID} INTEGER NOT NULL,
                FOREIGN KEY (${COLUMN_INGREDIENTCATEGORYID})
                REFERENCES ${IngredientCategoryTable.TABLE_NAME}(${IngredientCategoryTable.COLUMN_ID}) ON DELETE CASCADE
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
