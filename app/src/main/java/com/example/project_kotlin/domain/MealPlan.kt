package com.example.project_kotlin.domain

import java.time.LocalDate

class MealPlan(
    var id: Int,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var dishes: List<Dish>
)