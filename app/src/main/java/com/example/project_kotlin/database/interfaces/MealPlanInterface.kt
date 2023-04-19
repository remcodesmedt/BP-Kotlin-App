package com.example.project_kotlin.database.interfaces

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.tables.*
import com.example.project_kotlin.domain.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.util.*
import android.util.Log

//!!uses mealplandishtable to read data!!
object MealPlanInterface {
    @SuppressLint("Range")
    fun getItemsForWeek(date: LocalDate): List<MealPlan> {
        val db = DBHelper.getDB()

        val columns = MealPlanDishTable.COLUMNS_FOR_SELECT
        val joinClause = MealPlanDishTable.JOIN_CLAUSE

        val selection =
            "${MealPlanTable.TABLE_NAME}.${MealPlanTable.COLUMN_START_DATE} <= DATE(?) AND ${MealPlanTable.TABLE_NAME}.${MealPlanTable.COLUMN_END_DATE} >= DATE(?)"
        val selectionArgs = arrayOf(date.toString(), date.toString())

        val cursor = db.query(
            "${MealPlanTable.TABLE_NAME}, ${MealPlanDishTable.TABLE_NAME}, ${DishTable.TABLE_NAME}, ${IngredientAmountTable.TABLE_NAME}, " + "${IngredientTable.TABLE_NAME}, ${IngredientCategoryTable.TABLE_NAME}",
            columns,
            "$joinClause AND $selection",
            selectionArgs,
            null,
            null,
            "${MealPlanTable.TABLE_NAME}.${MealPlanTable.COLUMN_ID}"
        )

        val mealplans = mutableListOf<MealPlan>()
        var currentMealPlan: MealPlan? = null
        var currentDishes = mutableListOf<Dish>()
        var currentDish: Dish? = null

        while (cursor.moveToNext()) {
            val mealPlanId =
                cursor.getInt(cursor.getColumnIndex(MealPlanTable.COLUMN_ID))
            Log.i("nice", "mealplanid: $mealPlanId")
            val mpStartDateString =
                cursor.getString(cursor.getColumnIndex(MealPlanTable.COLUMN_START_DATE))
            val mpStartDate = LocalDate.parse(mpStartDateString)
            val mpEndDateString =
                cursor.getString(cursor.getColumnIndex(MealPlanTable.COLUMN_END_DATE))
            val mpEndDate = LocalDate.parse(mpEndDateString)

            val dishId = cursor.getInt(cursor.getColumnIndex(DishTable.COLUMN_ID))
            Log.i("nice", "-dishid: $dishId")
            val dishName = cursor.getString(cursor.getColumnIndex(DishTable.COLUMN_NAME))
            val dishDescription =
                cursor.getString(cursor.getColumnIndex(DishTable.COLUMN_DESCRIPTION))
            val dishImage = cursor.getBlob(cursor.getColumnIndex(DishTable.COLUMN_IMAGE))
            val dishPrepTime =
                cursor.getInt(cursor.getColumnIndex(DishTable.COLUMN_PREPARATION_TIME))
            val dishServings = cursor.getInt(cursor.getColumnIndex(DishTable.COLUMN_SERVINGS))
            val dishInstructionsString =
                cursor.getString(cursor.getColumnIndex(DishTable.COLUMN_INSTRUCTIONS))
            val dishInstructionsList = Json.decodeFromString<List<String>>(dishInstructionsString)

            val ingredientAmountId =
                cursor.getInt(cursor.getColumnIndex(IngredientAmountTable.COLUMN_ID))
            Log.i("nice", "--ingramountid: $ingredientAmountId")
            val ingredientAmount =
                cursor.getDouble(cursor.getColumnIndex(IngredientAmountTable.COLUMN_AMOUNT))

            val ingredientId = cursor.getInt(cursor.getColumnIndex(IngredientTable.COLUMN_ID))
            val ingredientName =
                cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_NAME))
            val ingredientUnit =
                cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_UNIT))
            val unit = EUnit.fromString(ingredientUnit)

            val ingredientCategoryId =
                cursor.getInt(cursor.getColumnIndex(IngredientCategoryTable.COLUMN_ID))
            val ingredientCategoryName =
                cursor.getString(cursor.getColumnIndex(IngredientCategoryTable.COLUMN_NAME))

            // Create IngredientAmount object
            val ingredientAmountObj = IngredientAmount(
                id = ingredientAmountId,
                ingredient = Ingredient(
                    id = ingredientId,
                    name = ingredientName,
                    unit = unit,
                    category = IngredientCategory(
                        id = ingredientCategoryId, name = ingredientCategoryName
                    )
                ),
                amount = ingredientAmount,
            )


            // Check if we're still processing the same mealplan
            if (currentMealPlan?.id != mealPlanId) {
                // If not, add the previous mealplan to the result (if there was one)
                currentMealPlan?.let {
                    it.dishes = currentDishes.toTypedArray()
                    mealplans.add(it)
                    currentDishes.clear()
                }

                // Create new mealplan with new dish
                currentMealPlan = MealPlan(
                    mealPlanId, mpStartDate, mpEndDate, arrayOf(
                        Dish(
                            dishId,
                            dishName,
                            dishDescription,
                            dishImage,
                            dishPrepTime,
                            dishServings,
                            dishInstructionsList,
                            listOf(ingredientAmountObj)
                        )
                    )
                )
            } else { //same mealplan, so update the dishes OR update the dish its ingredients
                // Check if we're still processing the same dish
                if (currentDish?.id != dishId) {
                    // If not, add the previous dish to the result (if there was one)
                    currentDish?.let {
                        currentDishes.add(it)
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
                    //update dish
                    currentDish.ingredients = currentDish.ingredients + ingredientAmountObj
                }
            }
        }

        // Add the final mealplan to the result (if there was one)
        currentMealPlan?.let {
            it.dishes = currentDishes.toTypedArray()
            mealplans.add(it)
            currentDishes.clear()
        }

        cursor.close()

        return mealplans
    }

    fun insertItem(mealPlan: MealPlan) {
        if (mealPlan.dishes.contains(null)) {
            throw Error("no null dishes!!")
        }

        val db = DBHelper.getDB()

        // Create a ContentValues object to hold the values to insert
        val values = ContentValues().apply {
            put(MealPlanTable.COLUMN_START_DATE, mealPlan.startDate.toString())
            put(MealPlanTable.COLUMN_END_DATE, mealPlan.endDate.toString())
        }

        // Insert the values into the IngredientCategoryTable
        val mealPlanId = db.insert(MealPlanTable.TABLE_NAME, null, values)

        //dont insert dishes, but new table to link mealplan to 7 dishes
        var i = 0
        mealPlan.dishes.forEach {
            val values = ContentValues().apply {
                put(MealPlanDishTable.COLUMN_MEALPLAN_ID, mealPlanId)
                put(MealPlanDishTable.COLUMN_DISH_ID, it!!.id)
                put(MealPlanDishTable.COLUMN_DAY_OF_WEEK, i++)
            }
            db.insert(MealPlanDishTable.TABLE_NAME, null, values)
        }
    }
}