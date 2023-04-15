package com.example.project_kotlin.screens

import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_kotlin.database.interfaces.IngredientCategoryInterface
import com.example.project_kotlin.database.testdata.IngredientCategoryMock
import com.example.project_kotlin.database.testdata.IngredientMock
import com.example.project_kotlin.databinding.FragmentHomeBinding

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
            fillDb()
            binding.txtHome.text = "done, see logs \"nice\""
            logItems()
        }
    }


    private fun fillDb() {
        IngredientCategoryMock.insertMocks()
        IngredientMock.insertMocks()
        //fill other tables later
    }

    private fun logItems() {
        IngredientCategoryMock.logMocks()
        IngredientMock.logMocks()
        //log other tables later
    }

}