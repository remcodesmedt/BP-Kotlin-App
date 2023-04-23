package com.example.project_kotlin.screens

import android.graphics.BitmapFactory
import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_kotlin.database.DBHelper
import com.example.project_kotlin.database.testdata.*
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
        binding.btnCreateDB.setOnClickListener {
            DBHelper.init(requireContext())
            binding.txtOutput.text = "Database gecreëerd!"
        }

        binding.btnInsertIntoDB.setOnClickListener {
            binding.txtOutput.text = "Data toevoegen..."
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    fillDb()
                    withContext(Dispatchers.Main) {
                        binding.txtOutput.text = "Data ingevoerd!"
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        binding.txtOutput.text = "Databank nog niet aangemaakt!"
                    }
                }

            }
        }

    }

    private fun fillDb() {
        IngredientCategoryMock.insertMocks()
        IngredientMock.insertMocks()
        ShoppingListMock.insertMocks()
        DishMock(requireContext()).insertMocks()
        MealPlanMock.insertMocks()
    }

}