package com.example.project_kotlin.domain

enum class EUnit {
    g,
    ml;

    companion object {
        fun fromString(value: String): EUnit {
            return values().find { it.name.equals(value, ignoreCase = true) } ?: g; //default gram
        }
    }
}

