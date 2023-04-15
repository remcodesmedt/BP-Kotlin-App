package com.example.project_kotlin.domain

import java.util.Date

class MealPlan (
    var id: Int,
    var startDate: Date,
    var endDate: Date,
    var dishes: Array<Dish?> = Array(7){null}
)