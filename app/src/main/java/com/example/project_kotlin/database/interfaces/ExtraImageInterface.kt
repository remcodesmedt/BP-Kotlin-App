package com.example.project_kotlin.database.interfaces

import android.util.Log

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.tables.ExtraImageTable
import com.example.project_kotlin.domain.ExtraImage

object ExtraImageInterface {

    @SuppressLint("Range")
    fun getItems(): List<ExtraImage> {
        val db = DBHelper.getDB()
        val columns = ExtraImageTable.COLUMNS_FOR_SELECT

        val cursor =
            db.query(
                ExtraImageTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
            )

        val images = mutableListOf<ExtraImage>()

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(ExtraImageTable.COLUMN_ID))
            val img = cursor.getBlob(cursor.getColumnIndex(ExtraImageTable.COLUMN_IMAGE))
            images.add(ExtraImage(image = img))
        }

        cursor.close()

        return images
    }

    @SuppressLint("Range")
    fun insertItem(image: ExtraImage) {
        val db = DBHelper.getDB()

        // Create a ContentValues object to hold the values to insert
        val values = ContentValues().apply {
            put(ExtraImageTable.COLUMN_IMAGE, image.image)
        }

        // Insert the values into the IngredientCategoryTable
        db.insert(ExtraImageTable.TABLE_NAME, null, values)
    }

}