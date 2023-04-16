package com.example.project_kotlin.database.interfaces

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.tables.IngredientAmountTable
import com.example.project_kotlin.database.tables.IngredientCategoryTable
import com.example.project_kotlin.database.tables.IngredientTable
import com.example.project_kotlin.database.tables.ShoppingListTable
import com.example.project_kotlin.domain.*

object ShoppingListInterface {

    @SuppressLint("Range")
    fun getItems() : List<ShoppingList> {
        Log.i("nice", "getting items...")
        val db = DBHelper.getDB()
        val columns = ShoppingListTable.COLUMNS_FOR_SELECT
        val joinClause = ShoppingListTable.JOIN_CLAUSE

        val cursor = db.query(
            "${ShoppingListTable.TABLE_NAME}, ${IngredientAmountTable.TABLE_NAME}, " +
                    "${IngredientTable.TABLE_NAME}, ${IngredientCategoryTable.TABLE_NAME}",
            columns,
            joinClause,
            null,
            null,
            null,
            null
        )

        val shoppingLists = mutableListOf<ShoppingList>()
        val ingredientAmounts = mutableListOf<IngredientAmount>()
        var currentShoppingList: ShoppingList? = null

        while (cursor.moveToNext()) {
            val shoppingListId = cursor.getInt(cursor.getColumnIndex(ShoppingListTable.COLUMN_ID))
            val shoppingListName = cursor.getString(cursor.getColumnIndex(ShoppingListTable.COLUMN_NAME))

            val ingredientAmountId = cursor.getInt(cursor.getColumnIndex(IngredientAmountTable.COLUMN_ID))
            val ingredientAmount = cursor.getDouble(cursor.getColumnIndex(IngredientAmountTable.COLUMN_AMOUNT))

            val ingredientId = cursor.getInt(cursor.getColumnIndex(IngredientTable.COLUMN_ID))
            val ingredientName = cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_NAME))
            val ingredientUnit = cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_UNIT))
            val unit = EUnit.fromString(ingredientUnit)

            val ingredientCategoryId = cursor.getInt(cursor.getColumnIndex(IngredientCategoryTable.COLUMN_ID))
            val ingredientCategoryName = cursor.getString(cursor.getColumnIndex(IngredientCategoryTable.COLUMN_NAME))


            // Create IngredientAmount object
            val ingredientAmountObj = IngredientAmount(
                id = ingredientAmountId,
                ingredient = Ingredient(
                    id = ingredientId,
                    name = ingredientName,
                    unit = unit,
                    category = IngredientCategory(
                        id = ingredientCategoryId,
                        name = ingredientCategoryName
                    )
                ),
                amount = ingredientAmount,
            )

            // Check if we're still processing the same shopping list
            if (currentShoppingList?.id != shoppingListId) {
                // If not, add the previous shopping list to the result (if there was one)
                currentShoppingList?.let {
                    shoppingLists.add(it)
                }

                // Create new shopping list with this ingredient amount
                currentShoppingList = ShoppingList(
                    id = shoppingListId,
                    name = shoppingListName,
                    ingredients = listOf(ingredientAmountObj)
                )
            } else {
                // Add this ingredient amount to the current shopping list
                currentShoppingList.ingredients = currentShoppingList.ingredients + ingredientAmountObj
            }
        }

        // Add the final shopping list to the result (if there was one)
        currentShoppingList?.let {
            shoppingLists.add(it)
        }


        cursor.close()

        return shoppingLists
    }


    @SuppressLint("Range")
    fun insertItem(shoppingList: ShoppingList) {
        val db = DBHelper.getDB()

        // Create a ContentValues object to hold the values to insert
        val values = ContentValues().apply {
            put(ShoppingListTable.COLUMN_NAME, shoppingList.name)
        }

        // Insert the values into the IngredientCategoryTable
        val shoppingListId = db.insert(ShoppingListTable.TABLE_NAME, null, values)


        //also insert all the ingredientamounts
        shoppingList.ingredients.forEach{
            val ingrAmount = ContentValues().apply {
                put(IngredientAmountTable.COLUMN_AMOUNT, it.amount)
                put(IngredientAmountTable.COLUMN_SHOPPINGLIST_ID, shoppingListId)
                put(IngredientAmountTable.COLUMN_INGREDIENT_ID, it.ingredient.id)
            }
            db.insert(IngredientAmountTable.TABLE_NAME, null, ingrAmount)
        }

    }

}