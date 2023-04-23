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
        val dish3 = Dish(3)

        //id doesnt matter
        val mealplan = MealPlan(
            0,
            startDate,
            endDate,
            listOf(dish1, dish2, dish1, dish2, dish1, dish2, dish3)
        )

        val mealplan2 = MealPlan(
            0,
            startDate.plusWeeks(1),
            endDate.plusWeeks(1),
            listOf(dish1, dish2, dish1, dish2, dish1, dish2, dish3)
        )

        //insert list into db
        MealPlanInterface.insertItem(mealplan)
        MealPlanInterface.insertItem(mealplan2)
    }

    fun getLogsMocks(): String {
        var output = ""

        val mealPlans = MealPlanInterface.getItems(null)

        mealPlans.forEach { mealPlan ->
            output +=
                "mealplan ${mealPlan.id}: week van ${
                    mealPlan.startDate.toString().substring(0, 10)
                }\n"
            " - ${mealPlan.endDate.toString().substring(0, 10)}\n"

            var i = 1;
            mealPlan.dishes.forEach {
                output += "-Dish ${i++}: \n"
                it.ingredients.forEach { ingr ->
                    output +=
                        "--${ingr.ingredient.name}: ${ingr.amount}${ingr.ingredient.unit}\n"
                }
            }
            output += "\n"
        }

        output += "\n"
        return output
    }
}