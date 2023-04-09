package com.example.project_kotlin.domain

import java.util.Date

class MealPlan (
    val startDate: Date,
    val endDate: Date,
    val dishes: Array<Dish?> = Array(7){null}
)