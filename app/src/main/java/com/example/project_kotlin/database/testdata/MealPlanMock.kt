package com.example.project_kotlin.database.testdata

import android.util.Log
import com.example.project_kotlin.database.interfaces.MealPlanInterface
import com.example.project_kotlin.domain.Dish
import com.example.project_kotlin.domain.MealPlan
import java.time.LocalDate

object MealPlanMock {
    fun insertMocks() {
        val startDate = LocalDate.of(2023, 4, 17)
        val endDate = LocalDate.of(2023, 4, 23)

        //actually only id matters here,
        val dish1 = Dish(1)
        val dish2 = Dish(2)

        //id doesnt matter
        val mealplan = MealPlan(
            0,
            startDate,
            endDate,
            arrayOf(dish1, dish2, dish1, dish2, dish1, dish2, dish1)
        )

        val mealplan2 = MealPlan(
            0,
            startDate.plusWeeks(1),
            endDate.plusWeeks(1),
            arrayOf(dish1, dish2, dish1, dish2, dish1, dish2, dish1)
        )

        //insert list into db
        MealPlanInterface.insertItem(mealplan)
        MealPlanInterface.insertItem(mealplan2)
    }

    fun logMocks() {
        Log.i("nice", "MealPlan----------------------------------------")

        //get mealplans from the db
        val mealPlans = MealPlanInterface.getItems(null)
        mealPlans.forEach { mealPlan ->
            Log.i("nice", "mealplan ${mealPlan.id}: week van ${mealPlan.startDate} - ${mealPlan.endDate}")
            var i = 1;
            mealPlan.dishes.forEach {
                Log.i("nice", "-Dish ${i++}: ")
                it?.ingredients?.forEach {
                    Log.i("nice", "--${it.ingredient.name}: ${it.amount}${it.ingredient.unit}")
                }
            }
        }
    }
}