package com.example.project_kotlin.screens

import android.graphics.BitmapFactory
import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_kotlin.database.testdata.DishMock
import com.example.project_kotlin.database.testdata.IngredientCategoryMock
import com.example.project_kotlin.database.testdata.IngredientMock
import com.example.project_kotlin.database.testdata.ShoppingListMock
import com.example.project_kotlin.databinding.FragmentHomeBinding
import kotlinx.coroutines.*

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.FABAddDataDb.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                fillDb()
                withContext(Dispatchers.Main) {
                    binding.txtHome.text = "done, see logs \"nice\""
                }
                logItems()
            }
        }

    }


    private fun fillDb() {
        IngredientCategoryMock.insertMocks()
        IngredientMock.insertMocks()
        ShoppingListMock.insertMocks()
        DishMock(requireContext()).insertMocks()
        //fill other tables later
    }

    private fun logItems() {
        IngredientCategoryMock.logMocks()
        IngredientMock.logMocks()
        ShoppingListMock.logMocks()
        var dish = DishMock(requireContext()).logMocks()
        var bitmap = BitmapFactory.decodeByteArray(dish.image, 0, dish.image.size)
        activity?.runOnUiThread{
            binding.testImage.setImageBitmap(bitmap)
        }
        //log other tables later
    }

}