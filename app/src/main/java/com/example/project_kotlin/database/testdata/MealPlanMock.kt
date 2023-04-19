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
        val dish1 = Dish(0)
        val dish2 = Dish(1)

        //id doesnt matter
        val mealplan = MealPlan(0, startDate, endDate, arrayOf(dish1, dish2, dish1, dish2, dish1, dish2, dish1))

        //insert list into db
        MealPlanInterface.insertItem(mealplan)
    }

    fun logMocks() {
        Log.i("nice", "MealPlan----------------------------------------")

        //get mealplans from the db
        val mealPlan = MealPlanInterface.getItemsForWeek(LocalDate.of(2023,4,19)).first()
        Log.i("nice", "${mealPlan.id}: ${mealPlan.startDate} - ${mealPlan.endDate}")
        Log.i("nice", "${mealPlan.dishes.count()}")

    }
}