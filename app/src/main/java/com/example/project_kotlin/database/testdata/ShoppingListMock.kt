package com.example.project_kotlin.database.testdata

import android.util.Log
import com.example.project_kotlin.database.interfaces.IngredientInterface
import com.example.project_kotlin.database.interfaces.ShoppingListInterface
import com.example.project_kotlin.domain.*

object ShoppingListMock {
    fun insertMocks() {
        //for ingredient, only id matters, category could be anything rn
        val ingrAmount1 = IngredientAmount(0, Ingredient(2), 25.0)
        val ingrAmount2 = IngredientAmount(0, Ingredient(1), 25.0)

        val shoppingList1 = ShoppingList(0, "mijne lijst", listOf(ingrAmount1, ingrAmount2))
        val shoppingList2 = ShoppingList(0, "mijne lijst part 2", listOf(ingrAmount2, ingrAmount1))

        //insert list into db
        ShoppingListInterface.insertItem(shoppingList1)
        ShoppingListInterface.insertItem(shoppingList2)
    }

    fun logMocks() {
        Log.i("nice", "Shoppinglist----------------------------------------")

        //get shoppinglists from the db
        val shoppingLists = ShoppingListInterface.getItems()

        //just log them for now
        for (l in shoppingLists) {
            Log.i("nice", "${l.id}: ${l.name}:\n")
            for (i in l.ingredients){
                Log.i("nice", "- ${i.ingredient.name}: ${i.amount}${i.ingredient.unit}")
            }
        }
    }
}