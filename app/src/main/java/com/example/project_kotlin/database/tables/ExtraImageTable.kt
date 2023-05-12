package com.example.project_kotlin.database.tables

object ExtraImageTable {
    const val TABLE_NAME = "extraimagetable"

    const val COLUMN_ID = "extraimageid"
    const val COLUMN_IMAGE = "extraimage"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_IMAGE BLOB
        )
    """

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"


    val COLUMNS_FOR_SELECT = arrayOf(COLUMN_ID, COLUMN_IMAGE)
}