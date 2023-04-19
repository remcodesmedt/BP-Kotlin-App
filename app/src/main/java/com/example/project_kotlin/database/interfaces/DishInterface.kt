package com.example.project_kotlin.database.interfaces

import android.util.Log
import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.tables.*
import com.example.project_kotlin.domain.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*


object DishInterface {

    @SuppressLint("Range")
    fun getItems(): List<Dish> {
        val db = DBHelper.getDB()

        val columns = DishTable.COLUMNS_FOR_SELECT
        val joinClause = DishTable.JOIN_CLAUSE

        val cursor =
            db.query(
                "${DishTable.TABLE_NAME}, ${IngredientAmountTable.TABLE_NAME}, " +
                        "${IngredientTable.TABLE_NAME}, ${IngredientCategoryTable.TABLE_NAME}",
                columns,
                joinClause,
                null,
                null,
                null,
                "${DishTable.TABLE_NAME}.${DishTable.COLUMN_ID}"
            )

        val dishes = mutableListOf<Dish>()
        var currentDish: Dish? = null

        while (cursor.moveToNext()) {
            val dishId = cursor.getInt(cursor.getColumnIndex(DishTable.COLUMN_ID))
            val dishName = cursor.getString(cursor.getColumnIndex(DishTable.COLUMN_NAME))
            val dishDescription = cursor.getString(cursor.getColumnIndex(DishTable.COLUMN_DESCRIPTION))
            val dishImage = cursor.getBlob(cursor.getColumnIndex(DishTable.COLUMN_IMAGE))
            val dishPrepTime = cursor.getInt(cursor.getColumnIndex(DishTable.COLUMN_PREPARATION_TIME))
            val dishServings = cursor.getInt(cursor.getColumnIndex(DishTable.COLUMN_SERVINGS))
            val dishInstructionsString = cursor.getString(cursor.getColumnIndex(DishTable.COLUMN_INSTRUCTIONS))
            val dishInstructionsList = Json.decodeFromString<List<String>>(dishInstructionsString)

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

            // Check if we're still processing the same dish
            if (currentDish?.id != dishId) {
                // If not, add the previous dish to the result (if there was one)
                currentDish?.let {
                    dishes.add(it)
                }

                // Create new dish with this ingredient amount
                currentDish = Dish(
                    id = dishId,
                    name = dishName,
                    description = dishDescription,
                    image = dishImage,
                    preparationTime = dishPrepTime,
                    servings = dishServings,
                    instructions = dishInstructionsList,
                    ingredients = listOf(ingredientAmountObj)
                )
            } else {
                // Add this ingredient amount to the current shopping list
                currentDish.ingredients = currentDish.ingredients + ingredientAmountObj
            }
        }

        // Add the final dish to the result (if there was one)
        currentDish?.let {
            dishes.add(it)
        }

        cursor.close()

        return dishes
    }

    @SuppressLint("Range")
    fun insertItem(dish: Dish) {
        val db = DBHelper.getDB()

        // Create a ContentValues object to hold the values to insert
        val values = ContentValues().apply {
            put(DishTable.COLUMN_NAME, dish.name)
            put(DishTable.COLUMN_DESCRIPTION, dish.description)
            put(DishTable.COLUMN_IMAGE, dish.image)
            put(DishTable.COLUMN_PREPARATION_TIME, dish.preparationTime)
            put(DishTable.COLUMN_SERVINGS, dish.servings)
            put(DishTable.COLUMN_INSTRUCTIONS, Json.encodeToString(dish.instructions))
        }

        // Insert the values into the dishtable
        val dishId = db.insert(DishTable.TABLE_NAME, null, values)

        //also insert all the ingredientamounts
        dish.ingredients.forEach{
            val ingrAmount = ContentValues().apply {
                put(IngredientAmountTable.COLUMN_AMOUNT, it.amount)
                put(IngredientAmountTable.COLUMN_DISH_ID, dishId)
                put(IngredientAmountTable.COLUMN_INGREDIENT_ID, it.ingredient.id)
            }
            db.insert(IngredientAmountTable.TABLE_NAME, null, ingrAmount)
        }
    }

}