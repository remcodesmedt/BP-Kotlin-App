package com.example.project_kotlin.database.testdata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.project_kotlin.R
import com.example.project_kotlin.database.interfaces.DishInterface
import com.example.project_kotlin.domain.*
import java.io.ByteArrayOutputStream
import java.io.InputStream

class DishMock(private val context: Context) {
    private fun imageToByteArray(id: Int): ByteArray {
        val inputStream: InputStream = context.resources.openRawResource(id)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

    fun insertMocks() {
        val ingrAmount1 = IngredientAmount(0, Ingredient(2), 125.0)
        val ingrAmount2 = IngredientAmount(0, Ingredient(1), 200.0)

        val image1 = imageToByteArray(R.raw.dishimg1)
        val image2 = imageToByteArray(R.raw.dishimg2)

        val dish1 = Dish(
            0,
            "mijn dish",
            "makkelijk te maken",
            image1,
            10,
            2,
            listOf("verwarm de oven voor", "zet de ingredienten in de oven", "bon appetit"),
            listOf(ingrAmount1, ingrAmount2)
        )

        val dish2 = Dish(
            0,
            "mijn tweede dish",
            "lekker!",
            image2,
            30,
            1,
            listOf("koude oven", "zet de ingredienten in de oven", "10min op 200°C", "smakelijk"),
            listOf(ingrAmount2, ingrAmount1)
        )

        //insert list into db
        DishInterface.insertItem(dish1)
        DishInterface.insertItem(dish2)
        dish2.name = "mijnen derde zeker"
        DishInterface.insertItem(dish2)
    }

    fun getLogsMocks(): String {
        var output = ""

        val dishes = DishInterface.getItems()

        for (dish in dishes) {
            output += "${dish.id}: ${dish.name}, ${dish.description}, " +
                    "Tijd: ${dish.preparationTime}, Personen: ${dish.servings}\n"

            output += "instructies:\n";
            var i = 1
            for (instr in dish.instructions) {
                output += "- Step ${i++}: $instr\n"
            }

            output += "ingredients:\n";
            for (ingr in dish.ingredients) {
                output +=
                    "- ${ingr.ingredient.name}: ${ingr.amount}${ingr.ingredient.unit}\n"
            }
            output += "\n"
        }

        output += "\n"
        return output
    }

}

